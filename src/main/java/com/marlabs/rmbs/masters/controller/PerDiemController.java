//package com.marlabs.rmbs.masters.controller;
//
//import java.util.Collections;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.marlabs.rmbs.entities.PerDiemData;
//import com.marlabs.rmbs.entities.UserMaster;
//import com.marlabs.rmbs.masters.repository.PerDiemRepository;
//import com.marlabs.rmbs.masters.service.UserMasterRepository;
//
//import io.jsonwebtoken.Claims;
//@RestController
//public class PerDiemController {
//	@Autowired
//	private PerDiemRepository perDiemRepository;
//	@Autowired
//	private UserMasterRepository userMasterRepository;
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
//	@RequestMapping(value = "/loadPerDiem", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public List<PerDiemData> loadPerDiem() {
//		try {
//			return perDiemRepository.findAll();
//		} catch (Exception e) {
//			log.debug("Exception in PerDiemController loadPerDiem() method",e);
//			return Collections.emptyList();			 
//		}
//		
//	}
//	
//	@RequestMapping(value = "/perDiemUpdate", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<String> updatePerDiem(@RequestBody PerDiemData perdiemdata,HttpServletRequest http) {
//		 try {
//			 Claims claim = (Claims) http.getAttribute("claims");
//				String email = (String) claim.get("contactId");
//
//				UserMaster user = userMasterRepository.findByMailIgnoreCase(email);
//				if (user.getRoleId() == 3 || user.getRoleId() == 2) {
//					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//
//				}
//		 Integer designationLevel=perdiemdata.getDesignationLevel();
//		 Double perdiemAmnt=perdiemdata.getPerDiemAmnt();
//		 
//		 perDiemRepository.saveData(designationLevel,perdiemAmnt);	
//		
//		 } catch (Exception e) {
//			 log.debug("Exception in PerDiemController updatePerDiem() method",e);
//		 }
//		return new ResponseEntity<>(HttpStatus.OK);
//		}
//		
//
//}
