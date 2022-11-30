package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.util.JwtToken;

@Controller
public class JwtController {
	
	@Autowired
	private JwtToken jwtToken;
	
	@GetMapping("/jwt")
	@ResponseBody
	public ResponseEntity index(HttpServletResponse response) {
		// 得到登入者的 username / authorities 資料
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		String userAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		// 放入到 jwt payload 的資料
		Map<String, Object> claims = new HashMap<>();
		claims.put("userName", userName);
		claims.put("userAuthorities", userAuthorities);
		String token = jwtToken.generateToken(claims);
		// 將 token 傳送出去
		return ResponseEntity.status(HttpStatus.OK).body(token);
	}
	
}
