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
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Assignments;
import com.example.demo.repository.AssignmentRepo;
import com.example.demo.service.AssignmentService;

@SpringBootTest
public class AssignmentServiceTest {

	@Autowired
	private AssignmentService assignmentService;
	
	@MockBean
	private AssignmentRepo assignmentRepo;
	
	@Test
	public void getAllAssignmentsTest() {
		
		when(assignmentRepo.findAll()).thenReturn(Stream.of(new Assignments("How are you", "Fine", 9),
				new Assignments("What is colour of apple", "red", 9)).collect(Collectors.toList()));
		assertEquals(2, assignmentService.getAssignments().size());
	}
	
	@Test
	public void deleteAssignmentsTest() {
		Assignments assignment=new Assignments("What is colour of apple", "red", 9);
		when(assignmentRepo.findById(1L)).thenReturn(Optional.of(assignment));
		String s=assignmentService.deleteAssignment(1L);
		verify(assignmentRepo,times(1)).delete(assignment);
	}
	
	@Test
	public void saveAssignmentsTest() {
		Assignments assignment=new Assignments("What is colour of apple", "red", 9);
		
		when(assignmentRepo.save(assignment)).thenReturn(assignment);
		assertEquals(assignment, assignmentService.createAssignments(assignment));
	
	}
	
	@Test
	public void getAssignmentsByIdTest() {
		
		Assignments assignments=assignmentDetails();
		when(assignmentRepo.findById(1L)).thenReturn(Optional.of(assignments));
		assertEquals(1L,assignmentService.getAssignmentsById(1L).getAssignment_id());
	}
	
	@Test
	public void editAssignmentsTest() {
		Assignments assignments=assignmentDetails();
		Assignments assignment1=new Assignments();
		assignment1.setQuestion("How are you");
		assignment1.setAnswer("Fine");
		assignment1.setStandard(2);
		when(assignmentRepo.findById(1L)).thenReturn(Optional.of(assignments));
		assertEquals("Fine", assignmentService.editAssignments(1L, assignment1).getAnswer());
		
	}
	
	@Test
	public void getByStandardTest() {
		List<Assignments> assignmentList=new ArrayList<Assignments>();
		assignmentList.add(assignmentDetails());
		when(assignmentRepo.findbyStandard(2L)).thenReturn(assignmentList);
		assertEquals(1, assignmentService.getByStandard(2L).size());
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
