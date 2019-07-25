package com.marlabs.rmbs.approve.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.config.EmailService;
import com.marlabs.rmbs.entities.ClaimApproveDetails;
import com.marlabs.rmbs.entities.ClaimStatus;
import com.marlabs.rmbs.entities.ClaimSubmitHeader;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.constant.DepartmentConstant;
import com.marlabs.rmbs.masters.constant.StatusConstant;
import com.marlabs.rmbs.masters.repository.ClaimStatusRepository;
import com.marlabs.rmbs.masters.repository.DepartmentRepository;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.repository.ClaimApproveDetailsRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestHeaderRepository;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVoComparatoer;
import com.marlabs.rmbs.request.vo.DashBoardVo;

@Service
public class ClaimApproveServiceImpl implements ClaimApproveService {

	@Autowired
	private ClaimRequestHeaderRepository claimRequestHeaderRepository;

	@Autowired
	private UserMasterRepository userMasterRepository;

	@Autowired
	private ClaimApproveDetailsRepository claimApproveDetailsRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ClaimStatusRepository claimStatusRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private Environment env;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	

	@Override
	public ClaimRequestHeaderVo approve(Integer claimId, ClaimRequestHeaderVo claimRequestHeaderObj) throws DepartmentManagerMissingException, ReportingManagerOfDepartmentManagerMissingException {
		
		ClaimSubmitHeader claimSubmitHeader = claimRequestHeaderRepository.findOne(claimId);
		ClaimStatus claimStatusObj = claimStatusRepository.findOne(claimRequestHeaderObj.getClaimsStatus());
		UserMaster approverObj = userMasterRepository.findByEmployeeId(claimRequestHeaderObj.getEmployeeId().toString());
		UserMaster claimantObj = claimSubmitHeader.getRequestingUserId();
		String deptHeadId = departmentRepository.findByDepatmentId(claimantObj.getEmployeeDepartmentId()).getDepartmentManagerId();
		if (null == deptHeadId || deptHeadId.contains("[null]") || deptHeadId.contains("NULL")
				|| userMasterRepository.findByEmployeeId(deptHeadId).getIsActive() == false
				|| deptHeadId.isEmpty()) {
			throw new DepartmentManagerMissingException("Please check : Department Manager id is missing");
		}
		UserMaster deptHeadObj = userMasterRepository.findByEmployeeId(deptHeadId);
		//UserMaster deptHeadManagerObj = userMasterRepository.findByEmployeeId(deptHeadObj.getEmployeeManagerId());
		log.debug("ClaimSubmit Header details", claimSubmitHeader.getApprovedPersonId(),claimSubmitHeader.getFinalStatus());
		List<ClaimApproveDetails> claimApproveList = claimApproveDetailsRepository.getApprovelClaims(claimId);
		ClaimApproveDetails claimApproveDetails = claimApproveList.get(claimApproveList.size() - 1);
		log.debug("ClaimApproveList details", claimApproveList.toString());
		
		
		if(claimRequestHeaderObj.getClaimsStatus().equals(StatusConstant.FORWARD.value)){
            //////////////////////////////////////////////////////
			claimSubmitHeader.setApprovedPersonId(userMasterRepository.findByMailIgnoreCase(claimRequestHeaderObj.getEmailToForward()));
			claimSubmitHeader.setModifiedDate(new Date());
			claimSubmitHeader.setFinalStatus(claimStatusObj);
			claimRequestHeaderRepository.save(claimSubmitHeader);
			//////////////////////////////////////////////////////
			claimApproveDetails.setClaimStatus(claimStatusObj);
			claimApproveDetails.setRemarks(claimRequestHeaderObj.getRemarks());
			claimApproveDetails.setModifiedDate(new Date());
			claimApproveDetails.setApproverId(approverObj);
			claimApproveDetailsRepository.save(claimApproveDetails);
			/////////////////////////////////////////////////////////////
			ClaimApproveDetails claimApproveDetailsEntity = new ClaimApproveDetails();
			claimApproveDetailsEntity.setClaimStatus(claimStatusRepository.findOne(StatusConstant.PENDING.value));
			claimApproveDetailsEntity.setApproverId(userMasterRepository.findByMailIgnoreCase(claimRequestHeaderObj.getEmailToForward()));
			claimApproveDetailsEntity.setClaimHeaderId(claimSubmitHeader);
			claimApproveDetailsRepository.save(claimApproveDetailsEntity);
			emailService.send(claimantObj, approverObj,claimRequestHeaderObj.getEmailToForward(),claimStatusObj.getClaimStatusCode(),claimRequestHeaderObj.getRemarks(),claimSubmitHeader.getClaimNo());
			log.debug("-- in ClaimApproveServiceImpl approve() method FORWARD condition---",claimSubmitHeader.getApprovedPersonId(),claimSubmitHeader.getFinalStatus());	
			
		}else if(claimRequestHeaderObj.getClaimsStatus().equals(StatusConstant.VALIDATE.value)) {
            //////////////////////////////////////////////////////
			claimSubmitHeader.setApprovedPersonId(userMasterRepository.findByMailIgnoreCase(env.getProperty("validatorEmail")));
			claimSubmitHeader.setModifiedDate(new Date());
			claimSubmitHeader.setFinalStatus(claimStatusObj);
			claimRequestHeaderRepository.save(claimSubmitHeader);
			//////////////////////////////////////////////////////
			claimApproveDetails.setClaimStatus(claimStatusObj);
			claimApproveDetails.setRemarks(claimRequestHeaderObj.getRemarks());
			claimApproveDetails.setModifiedDate(new Date());
			claimApproveDetails.setApproverId(approverObj);
			claimApproveDetailsRepository.save(claimApproveDetails);
			//////////////////////////////////////////////////////////
			ClaimApproveDetails claimApproveDetailsEntity = new ClaimApproveDetails();
			claimApproveDetailsEntity.setClaimStatus(claimStatusRepository.findOne(StatusConstant.PENDING.value));
			claimApproveDetailsEntity.setApproverId(userMasterRepository.findByMailIgnoreCase(env.getProperty("validatorEmail")));
			claimApproveDetailsEntity.setClaimHeaderId(claimSubmitHeader);
			claimApproveDetailsRepository.save(claimApproveDetailsEntity);
			emailService.send(claimantObj, approverObj,env.getProperty("validatorEmail"),claimStatusObj.getClaimStatusCode(),claimRequestHeaderObj.getRemarks(),claimSubmitHeader.getClaimNo());
			log.debug("-- in ClaimApproveServiceImpl approve() method VALIDATE condition---",claimSubmitHeader.getApprovedPersonId(),claimSubmitHeader.getFinalStatus());		
			
		}else if(claimRequestHeaderObj.getClaimsStatus().equals(StatusConstant.REJECT.value)) {
            //////////////////////////////////////////////////////
			claimSubmitHeader.setApprovedPersonId(approverObj);
			claimSubmitHeader.setModifiedDate(new Date());
			claimSubmitHeader.setFinalStatus(claimStatusObj);
			claimRequestHeaderRepository.save(claimSubmitHeader);
			//////////////////////////////////////////////////////
			claimApproveDetails.setClaimStatus(claimStatusObj);
			claimApproveDetails.setRemarks(claimRequestHeaderObj.getRemarks());
			claimApproveDetails.setModifiedDate(new Date());
			claimApproveDetails.setApproverId(approverObj);
			claimApproveDetailsRepository.save(claimApproveDetails);
			emailService.send(claimantObj, approverObj,claimStatusObj.getClaimStatusCode(),claimRequestHeaderObj.getRemarks(),claimSubmitHeader.getClaimNo());
			
			log.debug("-- in ClaimApproveServiceImpl approve() method REJECT condition---",claimSubmitHeader.getApprovedPersonId(),claimSubmitHeader.getFinalStatus());
			
		}else if(claimRequestHeaderObj.getClaimsStatus().equals(StatusConstant.REVIEW.value)) {
            //////////////////////////////////////////////////////
			claimSubmitHeader.setApprovedPersonId(approverObj);
			claimSubmitHeader.setModifiedDate(new Date());
			claimSubmitHeader.setFinalStatus(claimStatusObj);
			claimRequestHeaderRepository.save(claimSubmitHeader);
			//////////////////////////////////////////////////////
			claimApproveDetails.setClaimStatus(claimStatusObj);
			claimApproveDetails.setRemarks(claimRequestHeaderObj.getRemarks());
			claimApproveDetails.setModifiedDate(new Date());
			claimApproveDetails.setApproverId(approverObj);
			claimApproveDetailsRepository.save(claimApproveDetails);
			emailService.send(claimantObj, approverObj,claimStatusObj.getClaimStatusCode(),claimRequestHeaderObj.getRemarks(),claimSubmitHeader.getClaimNo());
			log.debug("-- in ClaimApproveServiceImpl approve() method REVIEW condition---",claimSubmitHeader.getApprovedPersonId(),claimSubmitHeader.getFinalStatus());
			
		}else if(claimRequestHeaderObj.getClaimsStatus().equals(StatusConstant.APPROVE.value)) {
            //////////////////////////////////////////////////////
			if(claimantObj.getEmployeeId().equals(deptHeadId)) {
				claimSubmitHeader.setApprovedPersonId(approverObj);
				claimSubmitHeader.setModifiedDate(new Date());
				claimSubmitHeader.setFinalStatus(claimStatusObj);
				claimRequestHeaderRepository.save(claimSubmitHeader);
				//////////////////////////////////////////////////////
				claimApproveDetails.setClaimStatus(claimStatusObj);
				claimApproveDetails.setRemarks(claimRequestHeaderObj.getRemarks());		
				claimApproveDetails.setModifiedDate(new Date());
				claimApproveDetails.setApproverId(approverObj);
				claimApproveDetailsRepository.save(claimApproveDetails);
				/////////////////////////////////////////////////////////////
				ClaimApproveDetails claimApproveDetailsEntity = new ClaimApproveDetails();
				claimApproveDetailsEntity.setClaimStatus(claimStatusRepository.findOne(StatusConstant.HOLD.value));
				claimApproveDetailsEntity.setClaimHeaderId(claimSubmitHeader);
				claimApproveDetailsRepository.save(claimApproveDetailsEntity);
				emailService.send(claimantObj, approverObj,env.getProperty("indiaFinance"),claimStatusObj.getClaimStatusCode(),claimRequestHeaderObj.getRemarks(),claimSubmitHeader.getClaimNo());
				
				
			}else {
	            //////////////////////////////////////////////////////
				if(approverObj.getEmployeeId().equals(deptHeadId)) {
					claimSubmitHeader.setApprovedPersonId(approverObj);
					claimSubmitHeader.setModifiedDate(new Date());
					claimSubmitHeader.setFinalStatus(claimStatusObj);
					claimRequestHeaderRepository.save(claimSubmitHeader);
					//////////////////////////////////////////////////////
					claimApproveDetails.setClaimStatus(claimStatusObj);
					claimApproveDetails.setRemarks(claimRequestHeaderObj.getRemarks());	
					claimApproveDetails.setModifiedDate(new Date());
					claimApproveDetails.setApproverId(approverObj);
					claimApproveDetailsRepository.save(claimApproveDetails);
					/////////////////////////////////////////////////////////////
					ClaimApproveDetails claimApproveDetailsEntity = new ClaimApproveDetails();
					claimApproveDetailsEntity.setClaimStatus(claimStatusRepository.findOne(StatusConstant.HOLD.value));
					claimApproveDetailsEntity.setClaimHeaderId(claimSubmitHeader);
					claimApproveDetailsRepository.save(claimApproveDetailsEntity);
					emailService.send(claimantObj, approverObj,env.getProperty("indiaFinance"),claimStatusObj.getClaimStatusCode(),claimRequestHeaderObj.getRemarks(),claimSubmitHeader.getClaimNo());
					
				}else {
		            //////////////////////////////////////////////////////
					claimSubmitHeader.setApprovedPersonId(deptHeadObj);
					claimSubmitHeader.setModifiedDate(new Date());
					claimSubmitHeader.setFinalStatus(claimStatusRepository.findOne(StatusConstant.PENDING.value));
					claimRequestHeaderRepository.save(claimSubmitHeader);
					//////////////////////////////////////////////////////
					claimApproveDetails.setClaimStatus(claimStatusObj);
					claimApproveDetails.setRemarks(claimRequestHeaderObj.getRemarks());	
					claimApproveDetails.setModifiedDate(new Date());
					claimApproveDetails.setApproverId(approverObj);
					claimApproveDetailsRepository.save(claimApproveDetails);
					/////////////////////////////////////////////////////////////
					ClaimApproveDetails claimApproveDetailsEntity = new ClaimApproveDetails();
					claimApproveDetailsEntity.setClaimStatus(claimStatusRepository.findOne(StatusConstant.PENDING.value));
					claimApproveDetailsEntity.setApproverId(deptHeadObj);
					claimApproveDetailsEntity.setClaimHeaderId(claimSubmitHeader);
					claimApproveDetailsRepository.save(claimApproveDetailsEntity);
					emailService.send(claimantObj, approverObj,deptHeadObj.getMail(),claimStatusObj.getClaimStatusCode(),claimRequestHeaderObj.getRemarks(),claimSubmitHeader.getClaimNo());
					
				}
			}
			
			
			log.debug("-- in ClaimApproveServiceImpl approve() method APPROVE condition---",claimSubmitHeader.getApprovedPersonId(),claimSubmitHeader.getFinalStatus());
			
		}else if(claimRequestHeaderObj.getClaimsStatus().equals(StatusConstant.CLEAR.value)) {
            //////////////////////////////////////////////////////
			claimSubmitHeader.setApprovedPersonId(approverObj);
			claimSubmitHeader.setModifiedDate(new Date());
			claimSubmitHeader.setFinalStatus(claimStatusObj);
			claimRequestHeaderRepository.save(claimSubmitHeader);
			/////////////////////////////////////////////////////
			claimApproveDetails.setClaimStatus(claimStatusObj);
			claimApproveDetails.setRemarks(claimRequestHeaderObj.getRemarks());
			claimApproveDetails.setModifiedDate(new Date());
			claimApproveDetails.setApproverId(approverObj);
			claimApproveDetails.setApproveType(claimRequestHeaderObj.getApproveType());
			claimApproveDetails.setApprovedAmount(claimRequestHeaderObj.getApprovedAmount());
			claimApproveDetails.setApproveCurrencyId(claimRequestHeaderObj.getApprovedCurrencyId());
			claimApproveDetailsRepository.save(claimApproveDetails);
			emailService.send(claimantObj, approverObj,env.getProperty("indiaFinance"),claimStatusObj.getClaimStatusCode(),claimRequestHeaderObj.getRemarks(),claimSubmitHeader.getClaimNo());
			
			log.debug("-- in ClaimApproveServiceImpl approve() method CLEAR condition---",claimSubmitHeader.getApprovedPersonId(),claimSubmitHeader.getFinalStatus());

			
		}
		return claimRequestHeaderObj;
	}

	///////////////////// getClaimsForUsers ////////////////////////
	@Override
	public DashBoardVo getClaimsForUser(Integer userId, Integer pageNum) {

		try {
			DashBoardVo dashBoardVo = new DashBoardVo();
			dashBoardVo.setEnableClearer(false);
			Pageable page = new PageRequest(pageNum, 10);
			List<ClaimSubmitHeader> claimsList = claimRequestHeaderRepository.getPageClaims(userId, page);
			dashBoardVo.setCount(claimRequestHeaderRepository.getCountForNormalUser(userId));
			dashBoardVo.setPendingCount(claimRequestHeaderRepository.getPendingCount(userId));
			dashBoardVo.setApproverCount(claimRequestHeaderRepository.getCountForApprover(userId,
					StatusConstant.FORWARD.value, StatusConstant.PENDING.value, StatusConstant.VALIDATE.value));
			UserMaster userObj = userMasterRepository.getOne(userId);
			if (DepartmentConstant.isClearer(userObj.getEmployeeName())) {
				dashBoardVo.setEnableClearer(true);
				dashBoardVo.setClearCount(
						claimRequestHeaderRepository.getCountForClearer(userId, StatusConstant.APPROVE.value));
			}
			List<ClaimRequestHeaderVo> claims = new ArrayList<>();
			for (ClaimSubmitHeader claimheader : claimsList) {
				ClaimRequestHeaderVo claimRequestHeaderVo = new ClaimRequestHeaderVo();
				claimRequestHeaderVo.setId(claimheader.getId());
				claimRequestHeaderVo
						.setClaimDate(new SimpleDateFormat("dd-MMM-yyyy").format(claimheader.getClaimDate()));
				claimRequestHeaderVo.setClaimNo(claimheader.getClaimNo());
				claimRequestHeaderVo.setSpecialApprove(claimheader.getSpecialApprove());
				claimRequestHeaderVo.setClaimTypeName(claimheader.getClaimTypeId().getClaimCode());
				claimRequestHeaderVo.setRemarks(claimheader.getRemarks());
				claimRequestHeaderVo.setStatus(claimheader.getFinalStatus().getClaimStatusCode());
				claimRequestHeaderVo
						.setModifiedDate(new SimpleDateFormat("dd-MMM-yyyy").format(claimheader.getModifiedDate()));
				claims.add(claimRequestHeaderVo);
			}
			Collections.sort(claims, new ClaimRequestHeaderVoComparatoer());
			dashBoardVo.setClaimRequestList(claims);

			return dashBoardVo;
		} catch (Exception e) {
			log.debug("Exception in ClaimApproveServiceImpl getClaims() method", e);
			return (DashBoardVo) Collections.emptyList();
		}
	}

	///////////////////// getClaimsForUsers ////////////////////////

	///////////////////// getClaimsForApprover ////////////////////////
	@Override
	public DashBoardVo getClaimsForApprover(Integer userId, Integer pageNum) {

		try {
			DashBoardVo dashBoardVo = new DashBoardVo();
			dashBoardVo.setEnableClearer(false);
			Pageable page = new PageRequest(pageNum, 10);
			List<ClaimSubmitHeader> claimsList = claimRequestHeaderRepository.getPageClaimsForApprover(userId,
					StatusConstant.FORWARD.value, StatusConstant.PENDING.value, StatusConstant.VALIDATE.value, page);
			dashBoardVo.setCount(claimRequestHeaderRepository.getCountForNormalUser(userId));
			dashBoardVo.setPendingCount(claimRequestHeaderRepository.getPendingCount(userId));
			dashBoardVo.setApproverCount(claimRequestHeaderRepository.getCountForApprover(userId,
					StatusConstant.FORWARD.value, StatusConstant.PENDING.value, StatusConstant.VALIDATE.value));
			UserMaster userObj = userMasterRepository.getOne(userId);
			if (DepartmentConstant.isClearer(userObj.getEmployeeName())) {
				dashBoardVo.setEnableClearer(true);
				dashBoardVo.setClearCount(
						claimRequestHeaderRepository.getCountForClearer(userId, StatusConstant.APPROVE.value));
			}
			List<ClaimRequestHeaderVo> claims = new ArrayList<>();
			for (ClaimSubmitHeader claimheader : claimsList) {
				ClaimRequestHeaderVo claimRequestHeaderVo = new ClaimRequestHeaderVo();
				claimRequestHeaderVo.setId(claimheader.getId());
				claimRequestHeaderVo
						.setClaimDate(new SimpleDateFormat("dd-MMM-yyyy").format(claimheader.getClaimDate()));
				claimRequestHeaderVo.setClaimNo(claimheader.getClaimNo());
				claimRequestHeaderVo.setEmployeeName(claimheader.getRequestingUserId().getCn());
				claimRequestHeaderVo.setEmpId(claimheader.getRequestingUserId().getEmployeeId());
				claimRequestHeaderVo.setSpecialApprove(claimheader.getSpecialApprove());
				claimRequestHeaderVo.setClaimTypeName(claimheader.getClaimTypeId().getClaimCode());
				claimRequestHeaderVo.setRemarks(claimheader.getRemarks());
				claimRequestHeaderVo.setStatus(claimheader.getFinalStatus().getClaimStatusCode());
				claimRequestHeaderVo
						.setModifiedDate(new SimpleDateFormat("dd-MMM-yyyy").format(claimheader.getModifiedDate()));
				claims.add(claimRequestHeaderVo);
			}
			Collections.sort(claims, new ClaimRequestHeaderVoComparatoer());
			dashBoardVo.setClaimRequestList(claims);

			return dashBoardVo;
		} catch (Exception e) {
			log.debug("Exception in ClaimApproveServiceImpl getClaimsForApprover() method", e);
			return (DashBoardVo) Collections.emptyList();
		}
	}

	///////////////////// getClaimsForApprover ////////////////////////

	///////////////////// getClaimsForClearer ////////////////////////

	@Override
	public DashBoardVo getClaimsForClearer(Integer userId, Integer pageNum) {

		try {
			DashBoardVo dashBoardVo = new DashBoardVo();
			UserMaster userObj = userMasterRepository.getOne(userId);
			if (DepartmentConstant.isClearer(userObj.getEmployeeName())) {
				dashBoardVo.setEnableClearer(true);
				Pageable page = new PageRequest(pageNum, 10);
				List<ClaimSubmitHeader> claimsList = claimRequestHeaderRepository.getPageClaimsForClearer(userId,
						StatusConstant.APPROVE.value, page);
				dashBoardVo.setCount(claimRequestHeaderRepository.getCountForNormalUser(userId));
				dashBoardVo.setPendingCount(claimRequestHeaderRepository.getPendingCount(userId));
				dashBoardVo.setApproverCount(claimRequestHeaderRepository.getCountForApprover(userId,
						StatusConstant.FORWARD.value, StatusConstant.PENDING.value, StatusConstant.VALIDATE.value));
				dashBoardVo.setClearCount(
						claimRequestHeaderRepository.getCountForClearer(userId, StatusConstant.APPROVE.value));
				List<ClaimRequestHeaderVo> claims = new ArrayList<>();
				for (ClaimSubmitHeader claimheader : claimsList) {
					ClaimRequestHeaderVo claimRequestHeaderVo = new ClaimRequestHeaderVo();
					claimRequestHeaderVo.setId(claimheader.getId());
					claimRequestHeaderVo
							.setClaimDate(new SimpleDateFormat("dd-MMM-yyyy").format(claimheader.getClaimDate()));
					claimRequestHeaderVo.setClaimNo(claimheader.getClaimNo());
					claimRequestHeaderVo.setEmployeeName(claimheader.getRequestingUserId().getCn());
					claimRequestHeaderVo.setEmpId(claimheader.getRequestingUserId().getEmployeeId());
					claimRequestHeaderVo.setEmployeeName(claimheader.getRequestingUserId().getEmployeeName());
					claimRequestHeaderVo.setEmpId(claimheader.getRequestingUserId().getEmployeeId());
					claimRequestHeaderVo.setSpecialApprove(claimheader.getSpecialApprove());
					claimRequestHeaderVo.setClaimTypeName(claimheader.getClaimTypeId().getClaimCode());
					claimRequestHeaderVo.setRemarks(claimheader.getRemarks());
					claimRequestHeaderVo.setStatus(claimheader.getFinalStatus().getClaimStatusCode());
					claimRequestHeaderVo
							.setModifiedDate(new SimpleDateFormat("dd-MMM-yyyy").format(claimheader.getModifiedDate()));
					claims.add(claimRequestHeaderVo);
				}
				Collections.sort(claims, new ClaimRequestHeaderVoComparatoer());
				dashBoardVo.setClaimRequestList(claims);
			}
			return dashBoardVo;
		} catch (Exception e) {
			log.debug("Exception in ClaimApproveServiceImpl getClaimsForClearer() method", e);
			return (DashBoardVo) Collections.emptyList();
		}
	}

	///////////////////// getClaimsForClearer ////////////////////
}
