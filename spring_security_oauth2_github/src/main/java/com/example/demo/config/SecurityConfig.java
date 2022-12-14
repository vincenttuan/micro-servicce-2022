package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
			.formLogin()
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/logout_success")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.clearAuthentication(true);
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager udm = new InMemoryUserDetailsManager();
		UserDetails uds = User.withUsername("user")
				.password(passwordEncoder().encode("1234"))
				.authorities("read")
				.build();
		udm.createUser(uds);
		return udm;
	}
	
	// 配置密碼
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
