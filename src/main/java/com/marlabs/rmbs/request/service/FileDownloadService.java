package com.marlabs.rmbs.request.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.entities.ClaimSubmitDetails;
import com.marlabs.rmbs.request.repository.ClaimRequestDetailRepository;

@Service
public class FileDownloadService {

	@Autowired
	private ClaimRequestDetailRepository claimRequestDetailRepository;
	
	public HashMap<String, Object> getFile(Integer fileId) {
		
		HashMap<String, Object> hmap =new HashMap<>();
		ClaimSubmitDetails claimSubmitDetails=claimRequestDetailRepository.findOne(fileId);
		hmap.put("file",claimSubmitDetails.getFile());
		hmap.put("fileName", claimSubmitDetails.getFileName());
		return hmap;
	}

	
	
	
	

}
