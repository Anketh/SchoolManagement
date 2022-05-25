package com.example.demo.controller;

import java.util.ArrayList;
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

import com.example.demo.entity.Students;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/getstudents")
	public ResponseEntity<List<Students>> getStudents() {
		List<Students> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}

	@PostMapping("/savestudents")
	public ResponseEntity<Students> saveStudent(@RequestBody Students student) {
		
		student = studentService.saveStudent(student);
		return ResponseEntity.ok(student);
	}

	@GetMapping("/getstudentbyid/{id}")
	public ResponseEntity<Students> getStudentsById(@PathVariable Long id) {
		Students student = studentService.getStudentsById(id);
		return ResponseEntity.ok(student);
	}

	@DeleteMapping("/deletestudent/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		String response = studentService.deleteStudent(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/editstudentdetails/{id}")
	public ResponseEntity<Students> updateStudent(@PathVariable Long id, @RequestBody Students studentDetails) {

		Students updatedStudent = studentService.updatedStudents(id, studentDetails);
		return ResponseEntity.ok(updatedStudent);
	}

	@PutMapping("/teachersacesstostudent/{id}")
	public ResponseEntity<Students> updateByTeacher(@PathVariable Long id, @RequestBody Students studentDetails) {
		Students updatedStudent = studentService.updateByTeacher(id, studentDetails);
		return ResponseEntity.ok(updatedStudent);
	}

	@GetMapping("/getbystandard/{standard}")
	public ResponseEntity<List<Students>> getStudentsByStandard(@PathVariable Long standard) {
		
		List<Students> studentslist=new ArrayList<Students>();
		studentslist =studentService.getByStandard(standard);
		return ResponseEntity.ok(studentslist);
		
	}
	
}
