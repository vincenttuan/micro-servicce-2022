package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// 配置密碼
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 密碼模式用
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
		
	// 配置過濾
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable() // 關閉 cors 防護
			.authorizeRequests() // 權限請求配置
			.antMatchers("/oauth/**").permitAll() // /oauth 與其子路徑不需認證直接可以訪問
			.anyRequest().authenticated() // 其他路徑都要認證
			.and()
			.formLogin().permitAll(); // 放行登入表單路徑
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
	
}
