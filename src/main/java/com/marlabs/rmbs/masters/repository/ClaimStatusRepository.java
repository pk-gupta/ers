package com.marlabs.rmbs.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marlabs.rmbs.entities.ClaimStatus;

@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus,Integer>{

}
