package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Assignments;





@Repository
public interface AssignmentRepo extends JpaRepository<Assignments, Long>{
	
	@Query(value = "select * from assignments where standard= :standard" ,nativeQuery = true)
	public List<Assignments> findbyStandard(@Param("standard") long standard);
	
	
	@Query(value = "select answer from assignments where assignment_id= :assignment_id" ,nativeQuery = true)
	public String findAllanswers(@Param("assignment_id") long assignment_id);
	
	
	


}
