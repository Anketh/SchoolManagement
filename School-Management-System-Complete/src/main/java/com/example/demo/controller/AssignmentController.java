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

import com.example.demo.entity.Assignments;

import com.example.demo.service.AssignmentService;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;

	@GetMapping("/getassignments")
	public ResponseEntity<List<Assignments>> getAllAssignments() {
		List<Assignments> assignments = assignmentService.getAssignments();
		return ResponseEntity.ok(assignments);
	}

	@GetMapping("getassignmentsbyid/{id}")
	public ResponseEntity<Assignments> getAssignmentById(@PathVariable Long id) {

		Assignments assignment = assignmentService.getAssignmentsById(id);
		return ResponseEntity.ok(assignment);

	}

	@PostMapping("/saveassignments")
	public ResponseEntity<Assignments> createAssignments(@RequestBody Assignments assignments) {
		Assignments assignment = assignmentService.createAssignments(assignments);
		return ResponseEntity.ok(assignment);

	}

	@DeleteMapping("/deleteassignments/{id}")
	public ResponseEntity<String> deleteAssignment(@PathVariable Long id) {
		String response = assignmentService.deleteAssignment(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/editassignments/{id}")
	public ResponseEntity<Assignments> updateAssignment(@PathVariable Long id,
			@RequestBody Assignments assignmentDetails) {

		Assignments updatedAssignment = assignmentService.editAssignments(id, assignmentDetails);

		return ResponseEntity.ok(updatedAssignment);

	}
	
	@GetMapping("/getbystandard/{standard}")
	public ResponseEntity<List<Assignments>> getAssignmentsByStandard(@PathVariable Long standard) {
		
		List<Assignments> assignmentlist=new ArrayList<Assignments>();
		assignmentlist =assignmentService.getByStandard(standard);
		return ResponseEntity.ok(assignmentlist);
		
	}

}
