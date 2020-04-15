package com.in28minutes.rest.basic.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable() //Will use the JWT token for this so we will disable this.
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS,"/**").permitAll() //Any req coming we are enabling it except for options
				.anyRequest().authenticated()
				.and()
			//.formLogin().and() //Commented this because am not allowing form login
			.httpBasic();
	}

}
