/**
 * 
 */
package com.marlabs.rmbs.masters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.entities.ClaimTypeMaster;
import com.marlabs.rmbs.masters.repository.ClaimTypeRepository;

/**
 * ClaimTypeServiceImpl.java
 * @author Pappu.kumar
 * Dec 31, 2015
 */

@Service
public class ClaimTypeServiceImpl implements ClaimTypeService{
	@Autowired
	private ClaimTypeRepository claimTypeRepository;
	public List<ClaimTypeMaster> loadClaimType(){
		return (List<ClaimTypeMaster>) claimTypeRepository.findAll();
	  }
		
	}

