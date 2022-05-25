package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Teachers;
import com.example.demo.service.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@GetMapping("/getteachers")
	public ResponseEntity<List<Teachers>> getAllTeachers() {
		List<Teachers> teachers=teacherService.getAllTeachers();
		return ResponseEntity.ok(teachers);
	}

	@PostMapping("/saveteachers")
	public ResponseEntity<Teachers> saveTeacher(@RequestBody Teachers teacherDetails) {
		
		Teachers teacher= teacherService.saveTeachers(teacherDetails);
		return ResponseEntity.ok(teacher);
		
	}

	@GetMapping("/getteacherbyid/{id}")
	public ResponseEntity<Teachers> getTeacherById(@PathVariable Long id) {
		Teachers teacher = teacherService.getTeacherById(id);
		return ResponseEntity.ok(teacher);
	}

	@DeleteMapping("/deleteteachers/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		String response= teacherService.deleteTeacher(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/editteacher/{id}")
	public ResponseEntity<Teachers> updateStudent(@PathVariable Long id, @RequestBody Teachers teacherDetails) {
		
		Teachers updatedTeacher = teacherService.editTeacher(id, teacherDetails);
		return ResponseEntity.ok(updatedTeacher);
	}

	

}
