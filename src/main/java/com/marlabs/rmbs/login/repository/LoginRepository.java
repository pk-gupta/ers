package com.marlabs.rmbs.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marlabs.rmbs.entities.UserMaster;

@Repository
public interface LoginRepository extends JpaRepository<UserMaster, Long>{
   
	
	UserMaster  findByMailContainingIgnoreCase(String mail);
	
	
	
	 
	
}



