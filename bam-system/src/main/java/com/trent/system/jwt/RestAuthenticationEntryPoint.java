package com.trent.system.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trent.common.utils.result.ResultCode;
import com.trent.common.utils.result.ResultVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当未登录或者token失效时访问接口时，自定义的返回结果
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ResultVo bean = new ResultVo(ResultCode.FAILED,"未登录,请重新登录！");
		bean.setCode(401);
		out.write(new ObjectMapper().writeValueAsString(bean));
		out.flush();
		out.close();
	}
}

