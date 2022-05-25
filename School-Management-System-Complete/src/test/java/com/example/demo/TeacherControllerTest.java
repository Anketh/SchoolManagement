package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.TeacherController;
import com.example.demo.entity.Teachers;
import com.example.demo.service.TeacherService;

@SpringBootTest
public class TeacherControllerTest {
	
	@Mock
	private TeacherService teacherService;
	
	@InjectMocks
	private TeacherController teacherController;
	
	@Test
	public void getAllTeacherTest() {
		List<Teachers> teacherList=new ArrayList<Teachers>();
		teacherList.add(new Teachers());
		teacherList.add(new Teachers());
		when(teacherService.getAllTeachers()).thenReturn(teacherList);
		ResponseEntity<List<Teachers>> responseEntity=teacherController.getAllTeachers();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2, responseEntity.getBody().size());
	}
	
	@Test
	public void saveTeacherTest() {
		Teachers teacher =teacherDetails();
		when(teacherService.saveTeachers(teacher)).thenReturn(teacher);
		ResponseEntity<Teachers> responseEntity=teacherController.saveTeacher(teacher);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
	}
	
	@Test
	public void getTeachetByIdTest() {
		Teachers teacher =teacherDetails();
		when(teacherService.getTeacherById(1L)).thenReturn(teacher);
		ResponseEntity<Teachers> responseEntity=teacherController.getTeacherById(1L);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void deleteTeacherTest() {
		Teachers teacher =teacherDetails();
		when(teacherService.deleteTeacher(1L)).thenReturn("Teacher deleted successfully");
		ResponseEntity<String> responseEntity=teacherController.deleteStudent(1L);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void upadteTeacherTest() {
		Teachers teacher=teacherDetails();
		Teachers teacher1=new Teachers();
		teacher1.setSubject("Social");
		teacher1.setTaddress("Kannur");
		teacher1.setTname("sam");
		when(teacherService.editTeacher(1L, teacher1)).thenReturn(teacher);
		ResponseEntity<Teachers> responseEntity=teacherController.updateStudent(1L, teacher1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	
	private Teachers teacherDetails() {
		Teachers teacher=new Teachers();
		teacher.setTid(1);
		teacher.setSubject("Maths");
		teacher.setTaddress("Kochi");
		teacher.setTname("Natasha");
		teacher.setTReg_no(1104);
		return teacher;
		
	}
}
