package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.User;
import com.example.demo.service.UserControllerService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserControllerService userService;
	
	
	
	@PostMapping("/admitUser")
	public ResponseEntity<User> senddata(@RequestBody User userdetails) {
		User user = userService.enterUserDetails(userdetails);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/access/{user_id}/{userRole}")
	public ResponseEntity<String> giveAccessToUser(@PathVariable Long user_id,@PathVariable String userRole, Principal principal) {
	
		String str=userService.giveAccessToUser(user_id, userRole, principal);
		return ResponseEntity.ok(str);
		
	}


}
