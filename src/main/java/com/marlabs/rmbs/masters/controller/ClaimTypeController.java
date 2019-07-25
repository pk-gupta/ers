/**
 * 
 */
package com.marlabs.rmbs.masters.controller;

import java.util.Collections;
import java.util.List;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.rmbs.entities.ClaimTypeMaster;
import com.marlabs.rmbs.masters.service.ClaimTypeService;




/**
 * ClaimTypeController.java
 * @author Pappu.kumar
 * Dec 30, 2015
 * @param <InputStreamResource>
 */

@RestController
public class ClaimTypeController {
	
		@Autowired
		private ClaimTypeService claimTypeService;

		@RequestMapping(value = "/claimtypes", method = RequestMethod.GET, 
				produces = MediaType.APPLICATION_JSON_VALUE)

		@ResponseBody
		public List<ClaimTypeMaster> loadClaimType() {
			try {
				return claimTypeService.loadClaimType();
			} catch (Exception e) {
				Log.debug("---In load claim type method----"+e.getMessage());
				return Collections.emptyList();
			}
		}


	}



