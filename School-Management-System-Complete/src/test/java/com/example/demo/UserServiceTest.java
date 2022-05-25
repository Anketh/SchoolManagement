package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GroupUserDetailService;
import com.example.demo.service.GroupUserDetails;
import com.example.demo.service.UserControllerService;

@SpringBootTest
public class UserServiceTest {

	
	@Autowired
	private UserControllerService userControllerService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Mock
	private Principal principal;
	
	@Test
	public void enterUserDetailsTest() {
		User user=new User("admin","admin",true,"ROLE_ADMIN");
		user.setUser_id(1L);
		when(userRepository.save(user)).thenReturn(user);
		
		assertEquals(user.getUser_id(), userControllerService.enterUserDetails(user).getUser_id());
	}
	
	@Test
	public void getLoggedInUserTest() {
		User user1=userDetails();
		User user=new User();
		user.setActive(true);
		user.setPassword("teacher");
		user.setRole("ROLE_TEACHER");
		user.setUsername("teacher");
		user.setUser_id(2L);
		when(userRepository.findById(2L)).thenReturn(Optional.of(user));
		when(userRepository.findByUsername(principal.getName())).thenReturn(Optional.of(user1));
		userControllerService.giveAccessToUser(2L, "ROLE_STUDENT", principal);
		assertEquals(true, user.isActive());
		
		
	}
	

	@Test
	public void groupUserDetailsTest() {
		GroupUserDetails details=new GroupUserDetails(userDetails());
		assertEquals(true, details.isEnabled());
	}
	
	
	private User userDetails() {
		User user=new User();
		user.setActive(true);
		user.setPassword("admin");
		user.setRole("ROLE_ADMIN");
		user.setUsername("admin");
		user.setUser_id(1L);
		return user;
	}
	
	
	
	}
