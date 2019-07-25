package com.marlabs.rmbs.request.controller;


import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.rmbs.approve.service.DepartmentManagerMissingException;
import com.marlabs.rmbs.entities.ClaimSubmitDetails;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.exception.FileNotSupportedException;
import com.marlabs.rmbs.exception.ProjectMissingException;
import com.marlabs.rmbs.masters.constant.DepartmentConstant;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestDetailRepository;
import com.marlabs.rmbs.request.service.ClaimRequestService;
import com.marlabs.rmbs.request.service.ClaimRequestUpdateService;
import com.marlabs.rmbs.request.service.FileDownloadService;
import com.marlabs.rmbs.request.service.ManagerIdInActiveException;
import com.marlabs.rmbs.request.vo.ClaimRequestDetailsVo;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;
import com.marlabs.rmbs.request.vo.ClaimRequestLoad;
import com.marlabs.rmbs.request.vo.ClaimRequestVo;
import com.marlabs.rmbs.request.vo.PerDiem;

import io.jsonwebtoken.Claims;


@RestController
public class ClaimController {

	@Autowired
	private ClaimRequestService claimRequestService;

	@Autowired
	private FileDownloadService fileDownloadService;

	@Autowired
	private ClaimRequestUpdateService claimRequestSaveOrUpdateService;

	@Autowired
	private ClaimRequestDetailRepository claimRequestDetailRepository;

	@Autowired
	private UserMasterRepository userMasterRepository;

	SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");

	private static final Logger LOG = LoggerFactory.getLogger(ClaimController.class);


	// When user is creating claim, sending necessary details for claim creation
	@RequestMapping(value = "/claims",produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<ClaimRequestLoad> loadClaimRequst(@RequestParam("userId") final Integer userId,HttpServletRequest http) {
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");


			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);

			if(!user.getId().equals(userId)){

				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			ClaimRequestLoad claimRequestLoad=claimRequestService.loadClaimRequest(userId);

			return new ResponseEntity<>(claimRequestLoad,HttpStatus.OK);
		} catch (Exception e) {
			LOG.debug("Exception in loadClaimRequest() method of ClaimController ----- "+e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Get request for per dieam amount based on claimType,userID,Grade,Location and effective date
	@RequestMapping(value = "/getPerDiemAmnt",produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<PerDiem> getPerDiemAmnt(@RequestParam("claimType") final Integer claimType,
			@RequestParam("employeeId") final String employeeId,
			@RequestParam("claimCategory") final String claimCategory,
			@RequestParam("location") final String location,
			@RequestParam("_fromPerdiemDate") final String _fromPerdiemDate,
			@RequestParam("_toPerdiemDate") final String _toPerdiemDate, HttpServletRequest http) {
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");


			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);

			if(!user.getEmployeeId().equals(employeeId)){

				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			Date fromPerdiemDate = null;
			Date toPerdiemDate = null;
			fromPerdiemDate= sdfr.parse(_fromPerdiemDate);
			toPerdiemDate= sdfr.parse(_toPerdiemDate);
			PerDiem perDiemdata=claimRequestService.loadPerDiemAmnt(claimType,employeeId,claimCategory,location
					,fromPerdiemDate,toPerdiemDate);

			return new ResponseEntity<>(perDiemdata,HttpStatus.OK);
		} catch (NullPointerException npe) {
			LOG.debug("Null vale of per diem amount coming from database"+npe);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			LOG.debug("Exception in ClaimController with /getPerDiemAmnt"+e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}




	@RequestMapping(value ="/verifyBill",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<ClaimSubmitDetails> verifyBillDetails(@RequestBody ClaimRequestDetailsVo billVerificationDetails,HttpServletRequest http) throws Exception {
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			LOG.debug(billVerificationDetails.getPayableAmount()+billVerificationDetails.getFinanceRemarks()+billVerificationDetails.getDetailsId());

			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			if(DepartmentConstant.isClearer(user.getEmployeeName())){
				billVerificationDetails.setVerified(true);
				ClaimSubmitDetails billVerificationData = claimRequestService.verifyBillDetails(billVerificationDetails);
				return new ResponseEntity<>(billVerificationData,HttpStatus.OK);

			}else{

				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}



		} catch (Exception e) {
			LOG.debug("-----------Exception in ClaimController with verifyBillDetails() method-------------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// used to get claim details page for approval purpose or normal user can see details of claims and also for expense review tab details page for particular claims
	@RequestMapping(value="claims/details",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<ClaimRequestVo> listClaim(@RequestParam("userId") final Integer userId,
			@RequestParam("claimId") final Integer claimId,HttpServletRequest http) {
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);

			if(!user.getId().equals(userId)){
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);

			}
			ClaimRequestVo claimRequestVo=claimRequestService.getClaim(claimId,userId,user.getRoleId());
			return new ResponseEntity<>(claimRequestVo,HttpStatus.OK);
		} catch (Exception e) {
			LOG.debug("-----------Exception in ClaimController with listClaim() method with end point:claims/details-------------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@RequestMapping(value="claims/view",produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<ClaimRequestVo> viewClaim(@RequestParam("userId") final Integer userId,
			@RequestParam("claimId") final Integer claimId) {
		try {
			ClaimRequestVo claimRequestVo=claimRequestService.viewClaim(claimId,userId);
			return new ResponseEntity<>(claimRequestVo,HttpStatus.OK);
		} catch (Exception e) {
			LOG.debug("-----------Exception in ClaimController with viewClaim() method with end point:claims/view-------------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Saving a claim request

	@RequestMapping(value="claims/{id}",produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClaimRequestHeaderVo> claimEdit(@RequestBody ClaimRequestVo claimRequestVo, @PathVariable("id") Integer claimId,HttpServletRequest http) {
		try {
			UserMaster userObj = userMasterRepository.findOne(claimRequestVo.getClaimRequestHeaderVo().getEmployeeId());
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			if(!user.getId().equals(claimRequestVo.getClaimRequestHeaderVo().getEmployeeId())){
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			ClaimRequestHeaderVo claimRequestHeaderVo =claimRequestSaveOrUpdateService.createRequest(claimRequestVo,claimId);
			return new ResponseEntity<>(claimRequestHeaderVo,HttpStatus.OK);
		} catch (ProjectMissingException pme) {
			LOG.debug("Project Missing Exception : ",pme);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (DepartmentManagerMissingException dmme) {
			LOG.debug("Department Manager Missing Exception : ",dmme);
			return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
		}catch (ManagerIdInActiveException miae) {
			LOG.debug("ManagerId InActive Exception : ",miae);
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}catch (Exception e) {
			LOG.debug("Edit in claim controller got exception :",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	// Saving a claim request


	// Submit a claim request

	@RequestMapping(value ="/claims",method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<ClaimRequestHeaderVo> saveClaimRequst(@RequestBody ClaimRequestVo claimRequestVo,HttpServletRequest http) {

		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");


			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);

			if(!user.getId().equals(claimRequestVo.getClaimRequestHeaderVo().getEmployeeId())){

				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}

			ClaimRequestHeaderVo claimRequestHeaderVo =claimRequestSaveOrUpdateService.createRequest(claimRequestVo,0);
			if (!claimRequestHeaderVo.getClaimNo().equals("")) {
				return new ResponseEntity<>(claimRequestHeaderVo,HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}catch (ProjectMissingException pme) {
			LOG.debug("Project Missing Exception : ",pme);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (DepartmentManagerMissingException dmme) {
			LOG.debug("Department Manager Missing Exception : ",dmme);
			return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
		}catch (ManagerIdInActiveException miae) {
			LOG.debug("ManagerId InActive Exception : ",miae);
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}catch (FileNotSupportedException fnse) {
			LOG.debug("ManagerId InActive Exception : ",fnse);
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
		}catch (Exception e) {
			LOG.debug("edit in draft in claim controller got exception :",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Submit a claim request


	@RequestMapping(value="claims/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<ClaimRequestHeaderVo> claimDelete(@PathVariable("id") Integer claimId,HttpServletRequest http) {
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");			
			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			claimRequestService.delete(claimId,user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			LOG.debug("-----------Exception in ClaimController with claimDelete() method with end point:claims/{id}-------------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="/fileDownload",method = RequestMethod.GET)
	public  ResponseEntity<byte[]>  fileDownload(@RequestParam("fileId") Integer fileId,HttpServletRequest http) {
		try {
			byte[] data = null;
			HttpHeaders headers = new HttpHeaders();
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			ClaimSubmitDetails claimSubmitDetails=claimRequestDetailRepository.findOne(fileId);
			String employeeName=userMasterRepository.findByMailIgnoreCase(email).getEmployeeName();
			if(DepartmentConstant.isClearer(employeeName) 
					|| employeeName.equalsIgnoreCase(claimSubmitDetails.getClaimHeaderId().getApprovedPersonId().getEmployeeName())
					|| employeeName.equalsIgnoreCase(claimSubmitDetails.getClaimHeaderId().getRequestingUserId().getEmployeeName())) {

				HashMap<String,Object> hmap=new HashMap<>();
				hmap=fileDownloadService.getFile(fileId);
				data =(byte[]) hmap.get("file");
				String fileName=(String) hmap.get("fileName");
				File file = new File(fileName);
				String fileType = Files.probeContentType(file.toPath());  
				headers.setContentType(MediaType.parseMediaType(fileType));
				headers.setContentDispositionFormData("filename",fileName);
				headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			}
			return new ResponseEntity<>(data, headers, HttpStatus.OK);
		} catch (Exception e) {
			LOG.debug("-----------Exception in ClaimController with fileDownload() method with end point:fileDownload-------------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}



}
