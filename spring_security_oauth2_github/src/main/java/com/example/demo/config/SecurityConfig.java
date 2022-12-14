package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/**").authorizeRequests()
			.antMatchers("/", "/logout_success").permitAll()
			.anyRequest().authenticated()
			.and()
			.oauth2Login()
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/logout_success")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.clearAuthentication(true);
		
	}
	
	
}
