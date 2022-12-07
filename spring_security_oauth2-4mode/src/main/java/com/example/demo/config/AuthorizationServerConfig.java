package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer // 啟動授權伺服器
public class AuthorizationServerConfig extends  AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*
	 * 配置授權服務器客戶端詳情
	 * 配置說明
	​ * scopes ​​:授權範圍標識，比如指定微服務名稱，則只可以訪問指定的微服務(Ex: all, profile)
	 * autoApprove :false跳轉到授權頁面手動點擊授權，true不需要手動授權，直接響應授權碼
	 * redirectUris :當獲取授權碼後，認證服務器會重定向到指定的這個​​URL​​​，並且帶著一個授權碼​​code​​響應。
	 * withClient :允許訪問此認證服務器的客戶端ID (client-id)
	 * secret :客戶端密碼，加密存儲 (client-secret)
	 * authorizedGrantTypes :授權類型，支持同時多種授權類型
		 * authorization_code :標準授權模式
		 * implicit :隱式授權模式(簡化模式)
		 * password :密碼模式
		 * client_credentials :客戶端模式
	 * refresh_token :刷新令牌模式(只有在授權模式或者密碼模式才會生效)
	 * accessTokenValiditySeconds :token有效時間（單位毫秒）
	 * refreshTokenValiditySeconds :刷新token有效期(單位毫秒)
	*/
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// TODO Auto-generated method stub
		super.configure(clients);
	}
	
	
	
}
