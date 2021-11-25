package com.trent.system.service.impl.login;

import com.trent.common.utils.redis.RedisUtil;
import com.trent.common.utils.result.ResultUtil;
import com.trent.system.jwt.JwtTokenUtil;
import com.trent.system.mapper.login.AdminMapper;
import com.trent.system.pojo.admin.Admin;
import com.trent.system.service.login.IAdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Trent
 * @since 2021-11-12
 */
@Service
public class AdminServiceImpl  implements IAdminService{
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private RedisUtil redisUtil;
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	
	/**
	 * 登录返回token
	 *
	 * @param userName
	 * @param passWord
	 * @param captcha
	 * @param captchFlag
	 * @return
	 */
	@Override
	public ResultUtil login(String userName, String passWord, String captcha, String captchFlag, HttpServletRequest request) throws Exception{
		//验证验证码从redis里面取
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		if( StringUtils.isBlank(captcha)||StringUtils.isBlank(captchFlag)|| !captcha.toLowerCase().equals(redisUtil.get(captchFlag))){
			redisUtil.delete(captchFlag);
			return ResultUtil.fail("验证码有误,请重新输入验证码!");
		}
		if( null == userDetails || !passwordEncoder.matches(passWord, userDetails.getPassword()) ){
			return ResultUtil.fail("用户名或密码不正确!");
		}
		if( !userDetails.isEnabled() ){
			return ResultUtil.fail("账号被禁用，请联系管理员!");
		}
		//更新secret登录用户对象
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//生成token
		String token = jwtTokenUtil.generateToken(userDetails);
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		System.out.println("token:"+tokenHead+token);
		
		//删除验证码缓存
		redisUtil.delete(captchFlag);
		
		return ResultUtil.ok(tokenMap);
	}
	/**
	 * 根据用户名获取用户
	 *
	 * @param userName
	 * @return
	 */
	@Override
	public Admin getAdminByUserName(String userName){
		return adminMapper.selectAdminByName(userName);
	}
	
}
