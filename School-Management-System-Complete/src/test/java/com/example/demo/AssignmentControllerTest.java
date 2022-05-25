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

import com.example.demo.controller.AssignmentController;
import com.example.demo.entity.Assignments;
import com.example.demo.service.AssignmentService;

@SpringBootTest
public class AssignmentControllerTest {

	@Mock
	private AssignmentService assignmentService;
	
	@InjectMocks
	private AssignmentController assignmentController;
	
	@Test
	public void getAllAssignments() {
		List<Assignments> assignmentlist =new ArrayList<Assignments>();
		assignmentlist.add(new Assignments());
		assignmentlist.add(new Assignments());
		when(assignmentService.getAssignments()).thenReturn(assignmentlist);
		ResponseEntity<List<Assignments>> responseEntity=assignmentController.getAllAssignments();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2,responseEntity.getBody().size());
		
	}
	
	@Test
	public void getAssignmentByIdTest() {
		Assignments assignment=assignmentDetails();
		when(assignmentService.getAssignmentsById(1L)).thenReturn(assignment);
		ResponseEntity<Assignments> responseEntity=assignmentController.getAssignmentById(1L);
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
	}
	
	@Test
	public void createAssignmentTest() {
		Assignments assignment=assignmentDetails();
		when(assignmentService.createAssignments(assignment)).thenReturn(assignment);
		ResponseEntity<Assignments> responseEntity=assignmentController.createAssignments(assignment);
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
	}
	
	@Test
	public void deleteAssignmentTest() {
		Assignments assignments=assignmentDetails();
		when(assignmentService.deleteAssignment(1L)).thenReturn("Assignment deleted successfully");
		ResponseEntity<String> responseEntity=assignmentController.deleteAssignment(1L);
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
	}
	
	@Test
	public void updateAssignmentTest() {
		Assignments assignments=assignmentDetails();
		Assignments assignment1=new Assignments();
		assignment1.setQuestion("How are you");
		assignment1.setAnswer("Fine");
		assignment1.setStandard(2);
		when(assignmentService.editAssignments(1L, assignment1)).thenReturn(assignments);
		ResponseEntity<Assignments> responseEntity=assignmentController.updateAssignment(1L, assignment1);
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
	}
	
	
	@Test
	public void getAssignmentByStandardTest() {
		List<Assignments> assignments=new ArrayList<Assignments>();
		when(assignmentService.getByStandard(2L)).thenReturn(assignments);
		ResponseEntity<List<Assignments>> responseEntity=assignmentController.getAssignmentsByStandard(2L);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	
	private Assignments assignmentDetails() {
		Assignments assignment=new Assignments(); 
		assignment.setQuestion("What is colour of apple");
		assignment.setAnswer("Red");
		assignment.setStandard(2);
		assignment.setAssignment_id(1);
		return assignment;
	}
}
