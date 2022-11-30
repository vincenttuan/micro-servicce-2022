package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CloudUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 假設資料庫的 name / pwd 如下
		String name = "john";
		String pwd  = new BCryptPasswordEncoder().encode("1234");
		
		if(!username.equals(name)) {
			System.out.println("Usename not found");
			throw new UsernameNotFoundException("Usename nnot found");
		}
		
		// 建立 UserDetials
		List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER");
		UserDetails userDetails = new User(username, pwd, auths);
		
		return userDetails;
	}

}
