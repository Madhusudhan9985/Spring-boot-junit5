package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	

	public UserEntity addUser(UserEntity user) {
		return repository.save(user);
	}

	public List<UserEntity> getUsers() {
		List<UserEntity> users = repository.findAll();
		System.out.println("Getting data from DB : " + users);
		return users;
	}

	public List<UserEntity> getUserbyAddress(String address) {
		return repository.findByAddress(address);
	}

	public void deleteUser(UserEntity user) {
		repository.delete(user);
	}
}