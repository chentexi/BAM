package com.trent.system.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Trent
 * @Date: 2021/11/12 13:19
 * @program: BAM
 * @Description:
 */
@Component
public class JwtTokenUtil{
	private static final String CLAIM_KEY_USERNAME = "sub";
	private static final String CLAIM_KEY_CREATED = "created";
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	/**
	 * 根据用户信息生成token
	 *
	 * @param userDetails
	 * @return
	 */
	public String generateToken(UserDetails userDetails) throws Exception{
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}
	/**
	 * 根据负载生成JWT Token
	 *
	 * @param claims
	 * @return
	 */
	private String generateToken(Map<String, Object> claims) throws Exception{
		String decode = new String(Base64.getMimeDecoder().decode(secret.toString().replace("\r\n", "")),"utf-8");
		System.out.println(secret);
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	/**
	 * 验证token是否有效
	 *
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public boolean validateToken(String token, UserDetails userDetails){
		String username = getUserNameFormToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	/**
	 * 从token中获取登录用户名
	 *
	 * @param token
	 * @return
	 */
	public String getUserNameFormToken(String token){
		String username;
		try{
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		}catch( Exception e ){
			username = null;
		}
		return username;
	}
	/**
	 * 从token中获取JWT中的负载
	 *
	 * @param token
	 * @return
	 */
	private Claims getClaimsFromToken(String token){
		Claims claims = null;
		try{
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}catch( Exception e ){
			e.printStackTrace();
		}
		return claims;
	}
	/**
	 * 刷新token
	 *
	 * @param token
	 * @return
	 */
	public String refreshToken(String token) throws Exception{
		Claims claims = getClaimsFromToken(token);
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}

	
	/**
	 * 生成token过期时间
	 *
	 * @return
	 */
	private Date generateExpirationDate(){
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}
	/**
	 * 从token中获取过期时间
	 *
	 * @param token
	 * @return
	 */
	private Date getExpiredDateFromToken(String token){
		Claims claims = getClaimsFromToken(token);
		return claims.getExpiration();
	}
	/**
	 * 判断token是否失效
	 *
	 * @param token
	 * @return
	 */
	private boolean isTokenExpired(String token){
		Date expiredDate = getExpiredDateFromToken(token);
		return expiredDate.before(new Date());
	}


	/**
	 * 判断token是否可以被刷新
	 *
	 * @param token
	 * @return
	 */
	public boolean
	canRefresh(String token){
		return !isTokenExpired(token);
	}
	
	
}
