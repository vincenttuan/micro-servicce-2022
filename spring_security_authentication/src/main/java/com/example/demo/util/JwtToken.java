package com.example.demo.util;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import javax.security.auth.message.AuthException;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {
	// 有效期間
	private static final long EXPIRATION_TIME = 1 * 60 * 1000; // 60 秒
	// 自訂密鑰
	public static final String SECRET = "MYSECRET";
	
	// 簽發 JWT
	public String generateToken(Map<String, Object> claims) {
		
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET)
				.compact();
		// 簡潔(compact)：體積非常的小，可放在 URL、POST 參數或 HTTP Header 內發送請求，體積小意味著傳輸速度快。
	}
	
	// 驗證
	public void validateToken(String token) throws AuthException {
		try {
			Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token);
		} catch (Exception e) {
			throw new AuthException("無效的 JWT: " + e);
		}
	}
}
