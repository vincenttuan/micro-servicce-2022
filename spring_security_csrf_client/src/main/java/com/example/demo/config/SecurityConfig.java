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
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager udm = new InMemoryUserDetailsManager();
		UserDetails uds = User.withUsername("user")
				.password(passwordEncoder().encode("1234"))
				.authorities("all")
				.build();
		udm.createUser(uds);
		return udm;
	}
	
	// 配置 HTTP 安全性
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configure(HttpSecurity http)");
			http.authorizeHttpRequests() // 授權請求
			.anyRequest().authenticated() // 所有請求都要驗證
			.and()
			//.csrf().disable() // 關閉 csrf 防護
			.formLogin(); // 利用表單來登入
	}
	
	
	
}
