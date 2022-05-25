package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import com.example.demo.service.UserControllerService;

@SpringBootTest
public class UserControllerTest {
	
	@Mock
	private UserControllerService userservice;
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	Principal principal;
	
	
	@Test
	public void saveUserTest() {
		User user=userDetails();
		when(userservice.enterUserDetails(user)).thenReturn(user);
		ResponseEntity<User> responseEntity=userController.senddata(user);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void giveAccessToUserTest() {
		User user=userDetails();
		when(userservice.giveAccessToUser(1L, "ROLE_STUDENT",principal )).thenReturn("New Role Assigned");
		ResponseEntity<String> responseEntity=userController.giveAccessToUser(1L,"ROLE_STUDENT",principal );
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	

	private User userDetails() {
		User user=new User();
		user.setActive(true);
		user.setPassword("admin");
		user.setRole("ROLE_ADMIN");
		user.setUsername("admin");
		user.setUser_id(1);
		return user;
	}

}
