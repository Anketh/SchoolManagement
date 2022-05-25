package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Students;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.StudentService;





@SpringBootTest
public class StudentServiceTest {
	

	@Autowired
	private StudentService studentService;
	
	@MockBean
	private StudentRepo studentRepo;
	
	@Test
	public void getAllStudentsTest() {
		when(studentRepo.findAll()).thenReturn(Stream.of(new Students(1203,"ajay","kannur","02-02-2000","100","male",
		9, "100"),new Students(1204,"sam","kannur","02-02-2000","100","female",
		9, "100")).collect(Collectors.toList()));
		
		assertEquals(2, studentService.getAllStudents().size());
	}
	@Test
	public void saveStudentTest() {
		Students student=new Students(1203,"ajay","kannur","02-02-2000","100","male",
				9, "100");
		when(studentRepo.save(student)).thenReturn(student);
		
		assertEquals(student,studentService.saveStudent(student));
	}
	
	@Test
	public void deleteStudentTest() {
		Students student=new Students(1203,"ajay","kannur","02-02-2000","100","male",
				9, "100");
		long id=1;
		student.setSid(id);
		studentRepo.save(student);
		when(studentRepo.findById(id)).thenReturn(Optional.of(student));
		String s=studentService.deleteStudent(id);
		
//		studentRepo.delete(student);
//		when(studentService.deleteStudent(id)).thenReturn("Student deleted sucessfully");
//		assertEquals("Student deleted sucessfully", studentService.deleteStudent(id));
		verify(studentRepo,times(1)).delete(student);
	}
	
	@Test
	public void findStudentsByIdTest() {
		Students student=new Students(1203,"ajay","kannur","02-02-2000","100","male",
				9, "100");
		long id=1;
		Students student1=new Students(1204,"sam","kannur","02-02-2000","100","female",
				9, "100");
		long id1=2;
		student1.setSid(id1);
		student.setSid(id);
		studentRepo.save(student1);
		studentRepo.save(student);
		
		when(studentRepo.findById(id)).thenReturn(Optional.of(student));
		assertEquals(id, studentService.getStudentsById(id).getSid());
		
		}
	
	
	@Test
	public void updateTest() {
		Students student=studentDetails();
		Students student1=new Students();
		student1.setSaddress("kannur");
		student1.setSDoB("11-02-1999");
		student1.setSname("aja");
		student1.setStandard(2);
		studentRepo.save(student);
		when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
		assertEquals("aja", studentService.updatedStudents(1L, student1).getSname());
		
	}
	
	
	private Students studentDetails() {
		Students student=new Students();
		student.setSid(1);
		student.setAttendence("100");
		student.setRegistration_no(1200);
		student.setSaddress("kannur");
		student.setSDoB("11-02-1999");
		student.setSMarks("100");
		student.setSname("ajay");
		student.setStandard(9);
		return student;
	}
	
	@Test
	public void updateByTeacherTest() {
		Students student=studentDetails();
		Students student1=new Students();
		student1.setAttendence("99");
		student1.setSMarks("88");
		studentRepo.save(student);
		when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
		assertEquals("99", studentService.updateByTeacher(1L, student1).getAttendence());
		
		
		
	}
	
	@Test
	public void getByStandard() {
		List<Students> studentlist=new ArrayList<Students>();
		studentlist.add(studentDetails());
		when(studentRepo.findbyStandard(9L)).thenReturn(studentlist);
		assertEquals(1, studentService.getByStandard(9L).size());
	}
	


    
 

}
