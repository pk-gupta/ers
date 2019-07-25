package com.marlabs.rmbs.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marlabs.rmbs.entities.ClaimTypeMaster;

@Repository
public interface ClaimTypeRepository extends JpaRepository<ClaimTypeMaster,Integer>{
}
