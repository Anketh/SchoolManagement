package com.example.demo.service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.common.UserConstant;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserControllerService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User enterUserDetails(User user) {
		user.setRole(UserConstant.DEFAULT_ROLE);
		String encyptedpassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encyptedpassword);
		userRepository.save(user);
		return user;
	}

	public String giveAccessToUser(long id, String role, Principal principal) {
		User user = userRepository.findById(id).get();
		List<String> activeRoles = getRoleByLoggedInUser(principal);
		if (activeRoles.contains(role)) {
			user.setRole(role);
			userRepository.save(user);
			return user.getUsername() + " new role assigned by " + principal.getName();
		} else {
			return "You dont have the Rights to change role to " + role;
		}
	}

	private List<String> getRoleByLoggedInUser(Principal principal) {
		String roles = getloggedInUser(principal).getRole();
		List<String> assignedRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if (assignedRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
		}
		if (assignedRoles.contains("ROLE_TEACHER")) {
			return Arrays.stream(UserConstant.TEACHER_ACCESS).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	private User getloggedInUser(Principal principal) {

		return userRepository.findByUsername(principal.getName()).get();

	}

}
