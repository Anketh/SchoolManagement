package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Students;



@Repository
public interface StudentRepo extends JpaRepository<Students, Long>{
	
	@Query(value = "select * from students where standard= :standard" ,nativeQuery = true)
	public List<Students> findbyStandard(@Param("standard") long standard);
	
	
	@Query(value = "select * from students where registraion_no= :registraion_no" ,nativeQuery = true)
	public List<Students> findbyReg_no(@Param("registraion_no") long registraion_no);
	
	
	

}
