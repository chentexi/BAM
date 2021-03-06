package com.trent.system.jwt;

import com.trent.system.pojo.admin.Admin;
import com.trent.system.service.login.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: Trent
 * @Date: 2021/11/14 23:59
 * @program: BAM
 * @Description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/login",
				"/logout",
				"/css/**",
				"/js/**",
				"/index.html",
				"favicon.ico",
				"/doc.html",
				"/webjars/**",
				"/swagger-resources/**",
				"/v2/api-docs/**",
				"/captcha",
				"/ws/**",
				"/captcha",
				"/images/**"
		);
	}
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		//??????JWT????????????csrf
		httpSecurity.csrf()
				.disable()
				//??????token????????????session
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				//??????????????????
				.antMatchers("/login","/logout")
				.permitAll()
				//??????????????????????????????????????????
				.anyRequest()
				.authenticated()
				.and()
				//????????????
				.headers()
				.cacheControl();
		//??????jwt ?????????????????????
		httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(),
				UsernamePasswordAuthenticationFilter.class);
		//????????????????????????????????????????????????
		httpSecurity.exceptionHandling()
				.accessDeniedHandler(restfulAccessDeniedHandler)
				.authenticationEntryPoint(restAuthenticationEntryPoint);
	}
	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
		return new JwtAuthenticationTokenFilter();
	}
	
	@Override
	@Bean
	public UserDetailsService userDetailsService(){
		return userName -> {
			Admin admin = adminService.getAdminByUserName(userName);
			if( null != admin ){
				return admin;
			}
			return null;
		};
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
