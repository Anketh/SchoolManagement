package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/user/admitUser").permitAll()
		.and().authorizeRequests().antMatchers("/user/access/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/students/getstudents").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/students/savestudents").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER","ROLE_STUDENT")
		.and().authorizeRequests().antMatchers("/students/getstudentbyid/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER","ROLE_STUDENT")
		.and().authorizeRequests().antMatchers("/students/deletestudent/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/students/editstudentdetails/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER","ROLE_STUDENT")
		.and().authorizeRequests().antMatchers("/students/teachersacesstostudent/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/teachers/getteachers").hasAnyAuthority("ROLE_ADMIN")
		.and().authorizeRequests().antMatchers("/teachers/saveteachers").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/teachers/getteacherbyid/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/teachers/deleteteachers/**").hasAnyAuthority("ROLE_ADMIN")
		.and().authorizeRequests().antMatchers("/teachers/editteacher/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/assignments/getassignments").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/assignments/getassignmentsbyid/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER","ROLE_STUDENT")
		.and().authorizeRequests().antMatchers("/assignments/saveassignments").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/assignments/deleteassignments/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.and().authorizeRequests().antMatchers("/assignments/editassignments/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
		.anyRequest().authenticated().and().httpBasic();
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
