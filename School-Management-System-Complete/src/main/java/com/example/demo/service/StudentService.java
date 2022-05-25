package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Students;
import com.example.demo.repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;

	public List<Students> getAllStudents() {
		return studentRepo.findAll();
	}

	public Students saveStudent(Students student) {
		student.setAttendence(null);
		student.setSMarks(null);
		studentRepo.save(student);
		return student;
	}

	public Students getStudentsById(long id) {
		Students student = studentRepo.findById(id).get();
		return student;
	}

	public String deleteStudent(long id) {
		Students student = studentRepo.findById(id).get();
		studentRepo.delete(student);
		return "Student deleted sucessfully";
	}

	public Students updatedStudents(long id, Students studentDetails) {

		Students student = studentRepo.findById(id).get();
		// student.setSid(studentDetails.getSid());
		student.setSname(studentDetails.getSname());
		student.setSaddress(studentDetails.getSaddress());
		student.setSDoB(studentDetails.getSDoB());
		student.setSGender(studentDetails.getSGender());
		// student.setSMarks(studentDetails.getSMarks());
		student.setStandard(studentDetails.getStandard());
		// student.setAttendence(studentDetails.getAttendence());
		student.setRegistration_no(studentDetails.getRegistration_no());
		studentRepo.save(student);
		return student;
	}

	public Students updateByTeacher(long id, Students studentDetails) {
		Students student = studentRepo.findById(id).get();

		student.setSMarks(studentDetails.getSMarks());
		student.setAttendence(studentDetails.getAttendence());

		studentRepo.save(student);
		return student;
	}
	
	public List<Students> getByStandard(long standard){
		return studentRepo.findbyStandard(standard);
	}

}
