package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Students;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class StudentControllerTest {


	@Mock
	private StudentService studentService;
	
	@InjectMocks
	StudentController studentController;
	
	
	
	@Test
	public void getAllStudentsTest() {
		List<Students> studentlist =new ArrayList<Students>();
		studentlist.add(new Students());
		studentlist.add(new Students());
		when(studentService.getAllStudents()).thenReturn(studentlist);
		ResponseEntity<List<Students>> responseEntity=studentController.getStudents();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2, responseEntity.getBody().size());
	}
	
	@Test
	public void saveStudentTest() {
		Students student=studentDetails();
		when(studentService.saveStudent(student)).thenReturn(student);
		ResponseEntity<Students> responseEntity=studentController.saveStudent(student);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	
	@Test
	public void getStudentByIdTest() {
		Students student=studentDetails();
		when(studentService.getStudentsById(1L)).thenReturn(student);
		ResponseEntity<Students> responseEntity=studentController.getStudentsById(1L);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void deleteStudentsTest() {
		Students students=studentDetails();
		when(studentService.deleteStudent(1L)).thenReturn("Student deleted sucessfully");
		ResponseEntity<String> responseEntity= studentController.deleteStudent(1L);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void updateStudentTest() {
		Students student=studentDetails();
		Students student1=new Students();
		student1.setSaddress("kannur");
		student1.setSDoB("11-02-1999");
		student1.setSname("aja");
		student1.setStandard(2);
		when(studentService.updatedStudents(1L, student1)).thenReturn(student);
		ResponseEntity<Students> responseEntity=studentController.updateStudent(1L, student1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void updateByTeacherTest() {
		Students student=studentDetails();
		Students student1=new Students();
		student1.setSMarks("66");
		student1.setAttendence("99");
		when(studentService.updateByTeacher(1L, student1)).thenReturn(student);
		ResponseEntity<Students> responseEntity=studentController.updateByTeacher(1L, student1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	
	@Test
	public void findByStandard() {
		List<Students> studentList=new ArrayList<Students>();
		studentList.add(studentDetails());
		when(studentService.getByStandard(9L)).thenReturn(studentList);
		ResponseEntity<List<Students>> responseEntity=studentController.getStudentsByStandard(9L);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(1, responseEntity.getBody().size());
	}
	
	private Students studentDetails() {
		Students student=new Students();
		student.setSid(1);
		student.setAttendence("100");
		student.setRegistration_no(1200);
		student.setSaddress("kannur");
		student.setSDoB("11-01-1999");
		student.setSGender("male");
		student.setSMarks("100");
		student.setSname("ajay");
		student.setStandard(9);
		return student;
	}
	
}



