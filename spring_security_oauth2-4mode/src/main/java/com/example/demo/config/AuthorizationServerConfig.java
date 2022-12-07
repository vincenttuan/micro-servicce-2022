package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer // 啟動授權伺服器
public class AuthorizationServerConfig extends  AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder; // (密碼模式也需要使用)
	
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
	 * refresh_token :刷新令牌模式(只有在標準授權模式或者密碼模式才會生效)
	 * accessTokenValiditySeconds :token有效時間（單位毫秒）
	 * refreshTokenValiditySeconds :刷新token有效期(單位毫秒)
	 * 
	 * 一、標準授權模式: 申請授權碼 URL
	 * 1. 得到 code
	 * GET http://localhost:8080/oauth/authorize?response_type=code&client_id=admin&scope=all
	 * 輸入使用者的帳密 user/1234
	 * 若 autoApprove(false)
	 * 	選擇 Approve 並按下 Authorize
	 * 得到 code -> http://localhost:8080/callback?code=tKoS43tKoS43
	 * 
	 * 2. 透過 code 取得 token
	 * POST http://localhost:8080/oauth/token
	 * Query params
	 *     grant_type=authorization_code
	 *     code=tKoS43
	 *     client_id=admin
	 *     redirect_uri=http://localhost:8080/callback
	 *     scope=all
	 * Basic Auth:
	 * 	   Username: admin
	 *     Password: 1234
	 * 
	 * 得到
	 * {
	 *     "access_token": "7462841f-eec7-40be-ab87-4a98e6a89af8",
	 *     "token_type": "bearer",
	 *     "refresh_token": "d4be2217-5c2a-4c50-b4a6-47babea5e141",
	 *     "expires_in": 5999,
	 *     "scope": "all"
	 * }
	 * 
	 * 校驗 Token (/oauth/check_token)
	 * 因為授權服務器 AuthorizationServer 預設是 "denyAll()" 拒絕校驗
	 * 所以必須在 conf/AuthorizationServerconfig.java
	 * 加入啟動校驗("isAuthenticated()")服務
	 * 
	 * GET/POST http://localhost:8080/oauth/check_token
	 * Query params:
	 *     token=7462841f-eec7-40be-ab87-4a98e6a89af8
	 * Basic Auth:
	 *     Username: admin
	 *     Password: 1234    
	 * 
	 * 更新 Token
	 * 要使用 refresh_token 的話，授權服務器 AuthorizationServer 需要额外配置 userDetailsService
	 * 
	 * POST http://localhost:8080/oauth/token
	 * Query params:
	 *     grant_type=refresh_token
	 *     refresh_token=02d1d317-b471-4b70-bc5b-efa1aa25b5be
	 *     client_id=admin
	 *     client_secret=1234
	 * Basic Auth:
	 *     Username: admin
	 *     Password: 1234  
	 * 
	 * 二、簡單授權模式:
	 * http://localhost:8080/oauth/authorize?response_type=token&client_id=admin&scope=all
	 * 得到
	 * http://localhost:8080/callback#access_token=1d00df0a-3177-45fb-aee4-b27644dee5e5&token_type=bearer&expires_in=5999   
	 *
	 * 三、客戶端模式
	 * POST http://localhost:8080/oauth/token
	 * Query 參數:
	 *     grant_type=client_credentials
	 * Basic Auth:
	 * 	   Username: admin
	 * 	   Password: 1234
	 * 
	 * 四、密碼模式
	 * 
	 * POST http://localhost:8080/oauth/token
	 * Query 參數:
	 * 	   grant_type=password
	 * 	   username=user
	 * 	   password=1234
	 * Basic Auth:
	 * 	   Username: admin
	 * 	   Password: 1234
	 * 
	*/
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("admin") // client_id
			.secret(passwordEncoder.encode("1234")) // client_secure
			.redirectUris("http://localhost:8080/callback") //  客戶端重定向
			.scopes("all")
			.authorities("all")
			//.authorizedGrantTypes("authorization_code", "refresh_token")  // 授權流程類型: 標準授權模式 + 刷新令牌模式
			//.authorizedGrantTypes("implicit")  // 授權流程類型: 簡單授權模式
			//.authorizedGrantTypes("client_credentials")  // 授權流程類型: 客戶端模式
			//.authorizedGrantTypes("password", "refresh_token")  // 授權流程類型: 密碼模式 + 刷新令牌模式
			.authorizedGrantTypes("authorization_code", "implicit", "client_credentials", "password", "refresh_token") // 授權類型全部支援
			.autoApprove(true) // 是否自動授權
			.accessTokenValiditySeconds(6000) // token 有效時間
			.refreshTokenValiditySeconds(7000); // 刷新 token 有效時間
	}
	
	// 啟動 token 校驗
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("isAuthenticated()");
	}
	
	@Autowired
	private UserDetailsService userDetailsService; // refresh_token 用
	
	@Autowired
	private AuthenticationManager authenticationManager; // 密碼模式用
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.userDetailsService(userDetailsService); // refresh_token 用
		endpoints.authenticationManager(authenticationManager); // 密碼模式用密碼模式用
	}
	
	
	
	
}
