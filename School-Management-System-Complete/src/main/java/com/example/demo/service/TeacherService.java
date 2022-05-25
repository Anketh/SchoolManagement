package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Teachers;
import com.example.demo.repository.TeacherRepo;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepo teacherRepo;
	
	public List<Teachers> getAllTeachers(){
		return teacherRepo.findAll();
	}
	
	public Teachers saveTeachers(Teachers teacherdetails) {
		Teachers teacher= teacherRepo.save(teacherdetails);
		return teacher;
		
	}
	
	public Teachers getTeacherById(long id) {
		Teachers teacher = teacherRepo.findById(id).get();
		return teacher;
		
	}
	
	public String deleteTeacher(long id) {
		Teachers teacher =teacherRepo.findById(id).get();
		teacherRepo.delete(teacher);
		return "Teacher deleted successfully";
		
	}
	
	public Teachers editTeacher(long id,Teachers teacherDetails) {
		Teachers teacher = teacherRepo.findById(id).get();
		teacher.setSubject(teacherDetails.getSubject());
		teacher.setTaddress(teacherDetails.getTaddress());
		teacher.setTname(teacherDetails.getTname());
		teacher.setTReg_no(teacherDetails.getTReg_no());
		teacherRepo.save(teacher);
		return teacher;
	}
}
