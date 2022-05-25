package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Teachers;
import com.example.demo.repository.TeacherRepo;
import com.example.demo.service.TeacherService;

@SpringBootTest
public class TeacherServiceTest {

	@Autowired
	private TeacherService teacherService;
	
	@MockBean
	private TeacherRepo teacherRepo;
	
	
	
	
	@Test
	public void getAllTeachersTest() {
		when(teacherRepo.findAll()).thenReturn(Stream.of(new Teachers(1103, "Susan","Kochi","Science"),
				new Teachers(1104, "Natasha","Kochi","Maths")).collect(Collectors.toList()));
		
		assertEquals(2,teacherService.getAllTeachers().size());
		
	}
	
	@Test
	public void saveTeachersTest() {
		Teachers teacher=new Teachers(1104, "Natasha","Kochi","Maths");
		when(teacherRepo.save(teacher)).thenReturn(teacher);
		assertEquals(teacher, teacherService.saveTeachers(teacher));
		
	}
	
	@Test
	public void deleteTeacherTest() {
		Teachers teacher=teacherDetails();
		
		
		when(teacherRepo.findById(1L)).thenReturn(Optional.of(teacher));
		String s=teacherService.deleteTeacher(1L);
		verify(teacherRepo,times(1)).delete(teacher);
		
	}
	
	@Test
	public void getTeacherByIdTest() {
		Teachers teacher=teacherDetails();
		when(teacherRepo.findById(1L)).thenReturn(Optional.of(teacher));
		assertEquals(1L, teacherService.getTeacherById(1L).getTid());
	}
	
	@Test
	public void editTeacherTest() {
		Teachers teacher=teacherDetails();
		Teachers teacher1=new Teachers();
		teacher1.setSubject("Social");
		teacher1.setTaddress("Kannur");
		teacher1.setTname("sam");
		when(teacherRepo.findById(1L)).thenReturn(Optional.of(teacher));
		assertEquals("sam", teacherService.editTeacher(1L, teacher1).getTname());
		
		
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
