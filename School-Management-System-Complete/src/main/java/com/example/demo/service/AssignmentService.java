package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Assignments;
import com.example.demo.repository.AssignmentRepo;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentRepo assignmentRepo;

	public List<Assignments> getAssignments() {
		return assignmentRepo.findAll();
	}

	public Assignments getAssignmentsById(long id) {
		Assignments assignment = assignmentRepo.findById(id).get();
		return assignment;
	}

	public Assignments createAssignments(Assignments assignmentDetails) {
		Assignments assignment = assignmentRepo.save(assignmentDetails);
		return assignment;

	}

	public String deleteAssignment(long id) {
		Assignments assignment = assignmentRepo.findById(id).get();
		assignmentRepo.delete(assignment);
		return "Assignment deleted successfully";
	}

	public Assignments editAssignments(long id, Assignments assignmentDetails) {
		Assignments assignment = assignmentRepo.findById(id).get();
		assignment.setQuestion(assignmentDetails.getQuestion());
		assignment.setStandard(assignmentDetails.getStandard());
		assignment.setAnswer(assignmentDetails.getAnswer());
		assignmentRepo.save(assignment);
		return assignment;

	}

	public List<Assignments> getByStandard(Long standard) {
		
		return assignmentRepo.findbyStandard(standard);
	}
}
