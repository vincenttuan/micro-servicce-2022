package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.util.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


//能夠確保在一次請求只通過一次filter，而不需要重複執行
//在servlet-2.3中，Filter會過濾一切請求，包括服務器內部使用forward轉發請求和<%@ include file="/index.jsp"%>的情況。
//到了servlet-2.4中Filter默認下只攔截外部提交的請求，forward和include這些內部轉發都不會被過濾，但是有時候我們需要forward的時候也用到Filter。
//因此，為了兼容各種不同的運行環境和版本，默認 filter 繼承 OncePerRequestFilter 是一個比較穩妥的選擇
public class PathAndJwtCheckFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 判斷 servletPath  是否是 /api 開頭?
		String servletPath = request.getServletPath();
		if(servletPath != null && servletPath.length() >= 4 && servletPath.substring(0, 4).equals("/api")) {
			// 檢查是否有 Authorization 資料
			String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); // "Authorization"
			String bearer = "Bearer ";
			if(authHeader != null && authHeader.startsWith(bearer)) {
				try {
					// 將 "Bearer " 後面的資料取出
					String token = authHeader.substring(bearer.length());
					Claims claims = Jwts.parser()
							.setSigningKey(JwtToken.SECRET)
							.parseClaimsJws(token)
							.getBody();
					System.out.println("JWT Payload: " + claims.toString());
					System.out.println("userName: " + claims.get("userName"));
					System.out.println("userAuthorities: " + claims.get("userAuthorities"));
					System.out.println("expiration: " + claims.getExpiration());
					filterChain.doFilter(request, response);
					
				} catch (Exception e) {
					// token 錯誤
					response.sendError(HttpStatus.FORBIDDEN.value(), "Token error");
				}
			} else {
				// 沒有 Authorization 資料
				response.sendError(HttpStatus.UNAUTHORIZED.value(), "None Authorization");
			}
			
		} else {
			filterChain.doFilter(request, response);
		}
		
	}

}
