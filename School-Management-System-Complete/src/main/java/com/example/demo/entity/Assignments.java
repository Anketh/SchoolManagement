package com.example.demo.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "assignments")
public class Assignments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long assignment_id;
	
	public long getAssignment_id() {
		return assignment_id;
	}
	public void setAssignment_id(long assignment_id) {
		this.assignment_id = assignment_id;
	}
	@Column(name = "standard")
	private long Standard;
	
	@Column(name = "question")
	private String Question;
	
	@Column(name = "answer")
	private String Answer;
	
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	public long getStandard() {
		return Standard;
	}
	public void setStandard(long standard) {
		Standard = standard;
	}
	public Assignments(String question, String answer, long standard) {
		super();
		Question = question;
		Answer = answer;
		Standard = standard;
	}
	public Assignments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
