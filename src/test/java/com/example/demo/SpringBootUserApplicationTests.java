package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootUserApplicationTests {
	
	
	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new UserEntity(376, "Madhu", 31, "Hyd"), new UserEntity(958, "Siva", 35, "Chennai")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}
	
	@Test
	public void getUserbyAddressTest() {
		String address = "Bangalore";
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new UserEntity(376, "Danile", 31, "USA")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}
	
	@Test
	public void saveUserTest() {
		UserEntity user = new UserEntity(999, "Madhu", 33, "Bangalore");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}
	
	@Test
	public void deleteUserTest() {
		UserEntity user = new UserEntity(5, "Vivek", 33, "Hyd");
		service.deleteUser(user);
		verify(repository, times(1)).delete(user);
	}

}
