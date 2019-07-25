package com.marlabs.rmbs.approve.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.marlabs.rmbs.approve.service.ClaimApproveServiceImpl;
import com.marlabs.rmbs.approve.service.DepartmentManagerMissingException;
import com.marlabs.rmbs.approve.service.ReportingManagerOfDepartmentManagerMissingException;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;
import com.marlabs.rmbs.request.vo.DashBoardVo;

import io.jsonwebtoken.Claims;

@RestController
public class ClaimApproveController {
	
	@Autowired
	private ClaimApproveServiceImpl claimApproveServiceImpl;
	
	@Autowired
	private UserMasterRepository userMasterRepository;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	// Dashboard tab for user claims 
	@RequestMapping(value="users/claims",
	method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<DashBoardVo> listClaims(@RequestParam("Id") int userId,@RequestParam("dataCount") int dataCount,HttpServletRequest http) {
		try {
			
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			

			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			
			if(!user.getId().equals(userId)){
			
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			DashBoardVo dashBoardVo =claimApproveServiceImpl.getClaimsForUser(userId, dataCount);
		    return new ResponseEntity<>(dashBoardVo,HttpStatus.OK);
		} catch (Exception e) {
			log.debug("Exception in  ClaimApproveController listClaims() with endpoint users/claims: ",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	// Dashboard tab for approver 
	@RequestMapping(value="approver/claims",
	method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<DashBoardVo> listApproverClaims(@RequestParam("Id") int userId,@RequestParam("dataCount") int dataCount,HttpServletRequest http) {
		try {
			
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			

			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			
			if(!user.getId().equals(userId)){		
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		   
			DashBoardVo dashBoardVo =claimApproveServiceImpl.getClaimsForApprover(userId, dataCount);
		    return new ResponseEntity<>(dashBoardVo,HttpStatus.OK);
		} catch (Exception e) {
			log.debug("Exception in  ClaimApproveController listApproverClaims() with endpoint approver/claims: ",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	// Dashboard tab for clearer 
	@RequestMapping(value="clearer/claims",
	method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<DashBoardVo> listClearerClaims(@RequestParam("Id") int userId,@RequestParam("dataCount") int dataCount,HttpServletRequest http) {
		try {
			
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			

			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			
			if(!user.getId().equals(userId)){
			
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		   
			DashBoardVo dashBoardVo =claimApproveServiceImpl.getClaimsForClearer(userId, dataCount);
		    return new ResponseEntity<>(dashBoardVo,HttpStatus.OK);
		} catch (Exception e) {
			log.debug("Exception in  ClaimApproveController listClearerClaims() with endpoint clearer/claims: ",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			
		}
	}
	
	
	
    // Action taken by approver or clearer like forward,approve,reject,review etc.
	@RequestMapping(value ="approve/claims/{id}",method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<ClaimRequestHeaderVo> approveClaimRequst(@PathVariable("id") Integer claimId,
			@RequestBody ClaimRequestHeaderVo claimRequestHeaderVo,HttpServletRequest http) {
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			
			if(!user.getEmployeeId().equals(claimRequestHeaderVo.getEmployeeId().toString())){
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			
			ClaimRequestHeaderVo claimRequestHeaderObj=claimApproveServiceImpl.approve(claimId,claimRequestHeaderVo);
		    return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (DepartmentManagerMissingException dmme) {
			log.debug("Department manager id exception : ",dmme);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (ReportingManagerOfDepartmentManagerMissingException rmdmme) {
			log.debug("Reprting Manager of Department manager id exception : ",rmdmme);
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		
		 catch (Exception e) {
			 log.debug("Exception in claim Approve controller with end point approve/claims/{id} : ",e);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
 }
	
}
