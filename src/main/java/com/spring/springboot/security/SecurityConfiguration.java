package com.spring.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.spring.springboot.utils.StringUtils;

@Configuration
@Profile("secure")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private CustomAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET).permitAll()
				.antMatchers(HttpMethod.POST).access(StringUtils.HAS_ROLE_ADMIN).antMatchers(HttpMethod.PUT)
				.access(StringUtils.HAS_ROLE_ADMIN).antMatchers(HttpMethod.DELETE).access(StringUtils.HAS_ROLE_ADMIN)
				.anyRequest().authenticated().and().httpBasic().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("test").password("{noop}test").roles("USER").and().withUser("admin")
				.password("{noop}admin").roles("ADMIN");
	}

}

