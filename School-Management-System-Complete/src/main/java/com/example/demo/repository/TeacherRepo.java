package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demo.entity.Teachers;



@Repository
public interface TeacherRepo extends JpaRepository<Teachers, Long>{
	
	@Query(value = "select * from teachers where treg_no= :treg_no" ,nativeQuery = true)
	public List<Teachers> findbytreg_no(@Param("treg_no") long treg_no);

}



