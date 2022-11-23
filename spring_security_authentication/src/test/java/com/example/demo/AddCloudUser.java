package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dao.CloudUserRepository;
import com.example.demo.model.CloudUser;

@SpringBootTest
class AddCloudUser {
	
	@Autowired
	CloudUserRepository cloudUserRepository;
	
	@Test
	void addUsers() {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String ecode = pe.encode("1234");
		System.out.println(ecode);
		ecode = pe.encode("1234");
		System.out.println(ecode);
		/*
		 	1234 -> $2a$10$GM.p9Atp.mW0z53BeB40MuDrf9nosmhUlHqlkDQH0AcMYdMAiNgVi
			1234 -> $2a$10$oeUXMykpSGyYZLc.qBr2M.g7j1DExFLAhvNpffZQr1b1Af4ABcTRO
		*/
		System.out.println(pe.matches("1234", "$2a$10$GM.p9Atp.mW0z53BeB40MuDrf9nosmhUlHqlkDQH0AcMYdMAiNgVi"));
		System.out.println(pe.matches("1234", "$2a$10$oeUXMykpSGyYZLc.qBr2M.g7j1DExFLAhvNpffZQr1b1Af4ABcTRO"));
		cloudUserRepository.deleteAll(); // 先將資料表清空(不一定要寫)
		cloudUserRepository.save(new CloudUser("admin", pe.encode("1234"), true, "ROLE_ADMIN"));
		cloudUserRepository.save(new CloudUser("user", pe.encode("1234"), true, "ROLE_USER"));
		cloudUserRepository.save(new CloudUser("manager", pe.encode("1234"), true, "ROLE_USER,ROLE_MANAGER"));
		cloudUserRepository.save(new CloudUser("john", pe.encode("1234"), false, "ROLE_ADMIN"));
		System.out.println("Add OK!");
	}

}
