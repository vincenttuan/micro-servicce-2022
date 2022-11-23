package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CloudUser;

public interface CloudUserRepository extends JpaRepository<CloudUser, Integer> {
	Optional<CloudUser> findByUsername(String username);
}
