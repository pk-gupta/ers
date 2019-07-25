package com.marlabs.rmbs.request.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.entities.ClaimApproveDetails;
import com.marlabs.rmbs.entities.ClaimSubmitDetails;
import com.marlabs.rmbs.entities.ClaimSubmitHeader;
import com.marlabs.rmbs.entities.ProjectMaster;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.constant.DepartmentConstant;
import com.marlabs.rmbs.masters.constant.StatusConstant;
import com.marlabs.rmbs.masters.repository.ClaimTypeRepository;
import com.marlabs.rmbs.masters.repository.CurrencyMasterRepository;
import com.marlabs.rmbs.masters.repository.EmployeeProjectDetailsRepository;
import com.marlabs.rmbs.masters.repository.PerDiemMasterRepository;
import com.marlabs.rmbs.masters.repository.ProjectMasterRepository;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.controller.ClaimController;
import com.marlabs.rmbs.request.repository.ClaimApproveDetailsRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestDetailRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestHeaderRepository;
import com.marlabs.rmbs.request.vo.ApprovelDetailsVo;
import com.marlabs.rmbs.request.vo.ClaimRequestDetailsVo;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;
import com.marlabs.rmbs.request.vo.ClaimRequestLoad;
import com.marlabs.rmbs.request.vo.ClaimRequestVo;
import com.marlabs.rmbs.request.vo.PerDiem;
import com.marlabs.rmbs.user.vo.UserDetails;

@Service
public class ClaimRequestServiceImpl implements ClaimRequestService {

	@Autowired
	private ClaimTypeRepository claimTypeRepository;

	@Autowired
	private CurrencyMasterRepository currencyMasterRepository;

	@Autowired
	private ClaimRequestHeaderRepository claimRequestHeaderRepository;

	@Autowired
	private UserMasterRepository userMasterRepository;

	@Autowired
	private ClaimRequestDetailRepository claimRequestDetailRepository;

	@Autowired
	private ClaimApproveDetailsRepository claimApproveDetailsRepository;

	@Autowired
	private ProjectMasterRepository projectMasterRepository;

	@Autowired
	private EmployeeProjectDetailsRepository employeeProjectDetailsRepository;
	
	@Autowired
	private PerDiemMasterRepository perDiemNewRepository;
	SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
	
	private static final Logger LOG = LoggerFactory.getLogger(ClaimRequestServiceImpl.class);

	@Override
	public ClaimRequestLoad loadClaimRequest(Integer userId) throws Exception {
		ClaimRequestLoad claimRequestLoad = new ClaimRequestLoad();
		claimRequestLoad.setCurrencyList(currencyMasterRepository.findAll());
		claimRequestLoad.setClaimTypeList(claimTypeRepository.findAll());
		claimRequestLoad.setClaimDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		claimRequestLoad.setProjectList(
				employeeProjectDetailsRepository.getProjectDetails(userMasterRepository.getEmployeeId(userId), "NA"));
		claimRequestLoad.setLocationList(perDiemNewRepository.distinctByLocation());
		UserMaster userobj = userMasterRepository.getDesignationLevel(userId);
		Integer designationLevel = userobj.getDesignationLevel();
		List<UserMaster> managerList = userMasterRepository.isEmployeeManager(userId);
		List<ProjectMaster> projectManagerList = projectMasterRepository.isEmployeeProjectManager(userId);
		Boolean managerCheck = managerList.isEmpty() ? false : true;
		Boolean pmCheck = projectManagerList.isEmpty() ? false : true;
		if (managerCheck || pmCheck) {
			claimRequestLoad.setReportTo("RM");
		} else {
			claimRequestLoad.setReportTo("PM");
		}

		return claimRequestLoad;

	}

	@Override
	public PerDiem loadPerDiemAmnt(Integer claimType, String employeeId, String claimCategory, String location,
			Date fromPerdiemDate, Date toPerdiemDate) throws NullPointerException, Exception {
		UserMaster userobj = userMasterRepository.findByEmployeeId(employeeId);
		Integer designationLevel = userobj.getDesignationLevel();
		List<Date> dateList = perDiemNewRepository.distinctByDate();
		Double totalPerDiemAmnt = 0.00;
		Date afterFirstjuly = dateList.get(0);
		Date beforeFirstjuly = dateList.get(1);

		if (fromPerdiemDate.getTime() <= beforeFirstjuly.getTime()
				&& toPerdiemDate.getTime() <= beforeFirstjuly.getTime()) {
			long totalDay = (toPerdiemDate.getTime() - fromPerdiemDate.getTime()) / 86400000;
			long day = Math.abs(totalDay) + 1;

			totalPerDiemAmnt = perDiemNewRepository.getperDiemAmntBeforeJuly(claimType, claimCategory, designationLevel,
					location, beforeFirstjuly);
			if (totalPerDiemAmnt != null) {
				totalPerDiemAmnt = totalPerDiemAmnt * day;
			} else
				throw new NullPointerException();

		} else if (toPerdiemDate.getTime() >= afterFirstjuly.getTime()
				&& fromPerdiemDate.getTime() >= afterFirstjuly.getTime()) {
			long totalDay = (toPerdiemDate.getTime() - fromPerdiemDate.getTime()) / 86400000;
			long day = Math.abs(totalDay) + 1;

			totalPerDiemAmnt = perDiemNewRepository.getperDiemAmntAfterJuly(claimType, claimCategory, designationLevel,
					location, afterFirstjuly);
			if (totalPerDiemAmnt != null) {
				totalPerDiemAmnt = totalPerDiemAmnt * day;
			} else
				throw new NullPointerException();
		} else {
			long totalDaybeforeJulyFirst = (beforeFirstjuly.getTime() - fromPerdiemDate.getTime()) / 86400000;
			long dayBefore = Math.abs(totalDaybeforeJulyFirst) + 1;

			Double perDiemAmnt = perDiemNewRepository.getperDiemAmntBeforeJuly(claimType, claimCategory,
					designationLevel, location, beforeFirstjuly);
			Double totalAmountBeforeJuly = 0.00;
			if (perDiemAmnt != null) {
				totalAmountBeforeJuly = perDiemAmnt * dayBefore;
			} else
				throw new NullPointerException();
			long totalDayafterFirstjuly = (toPerdiemDate.getTime() - afterFirstjuly.getTime()) / 86400000;
			long dayAfter = Math.abs(totalDayafterFirstjuly) + 1;

			Double perDiemAmntAfter = perDiemNewRepository.getperDiemAmntAfterJuly(claimType, claimCategory,
					designationLevel, location, afterFirstjuly);
			Double totalAmountAfterJuly = 0.00;
			if (perDiemAmntAfter != null) {
				totalAmountAfterJuly = perDiemAmntAfter * dayAfter;
			} else
				throw new NullPointerException();
			totalPerDiemAmnt = totalAmountBeforeJuly + totalAmountAfterJuly;
		}

		int currencyId = currencyMasterRepository.findCurrencyId(perDiemNewRepository.getCurrency(location));
		PerDiem p = new PerDiem();
		p.setTotalPerDiemAmnt(totalPerDiemAmnt);
		p.setCurrency(currencyId);
		return p;

	}

	@Override
	public void delete(Integer claimId, UserMaster user) throws Exception {
		List<ClaimSubmitDetails> claimHeaderList = claimRequestDetailRepository.getClaimDetails(claimId);
		for (ClaimSubmitDetails claimSubmitDetails : claimHeaderList) {
			claimRequestDetailRepository.delete(claimSubmitDetails.getId());
		}
		List<ClaimApproveDetails> claimApproveList = claimApproveDetailsRepository.getApprovelClaims(claimId);
		for (ClaimApproveDetails claimApproveDetails : claimApproveList) {
			claimApproveDetailsRepository.delete(claimApproveDetails.getId());
		}
		claimRequestHeaderRepository.delete(claimId);
	}

	@Override
	public ClaimRequestVo getClaim(Integer claimId, Integer userId, Integer role) throws Exception {
		ClaimRequestVo claimRequestVo = new ClaimRequestVo();
		try {
			//ClaimHeader part info
			LOG.info("------Enter into ClaimHeader part info------");
			ClaimRequestHeaderVo claimRequestHeaderVo = new ClaimRequestHeaderVo();  //Used for Claim Header Info
			ClaimSubmitHeader claimSubmitHeader = claimRequestHeaderRepository.findOne(claimId);
			// Claim info
			claimRequestHeaderVo.setClaimNo(claimSubmitHeader.getClaimNo());
			claimRequestHeaderVo.setId(claimSubmitHeader.getId());
			claimRequestHeaderVo.setClaimType(claimSubmitHeader.getClaimTypeId().getId());
			claimRequestHeaderVo.setClaimTypeName(claimSubmitHeader.getClaimTypeId().getClaimCode());
			// Employee info
			claimRequestHeaderVo.setEmployeeName(claimSubmitHeader.getRequestingUserId().getEmployeeName());
			claimRequestHeaderVo.setEmpId(claimSubmitHeader.getRequestingUserId().getEmployeeId());
			claimRequestHeaderVo.setDepartment(claimSubmitHeader.getRequestingUserId().getDepartment());
			claimRequestHeaderVo.setDesignation(claimSubmitHeader.getRequestingUserId().getDesignation());
			// Project info 
			claimRequestHeaderVo.setProjectId(claimSubmitHeader.getProjectMasterId().getId());
			claimRequestHeaderVo.setProject(claimSubmitHeader.getProject());
			// Claim Amount info
			claimRequestHeaderVo.setClaimedAmount(claimSubmitHeader.getClaimAmount());
			claimRequestHeaderVo.setCurrencyCode(claimSubmitHeader.getCurrencyId().getCurrencyCode());
			claimRequestHeaderVo.setClaimCurrency(claimSubmitHeader.getCurrencyId().getId());
			// Claim Date info
			claimRequestHeaderVo.setClaimDate(new SimpleDateFormat("dd-MMM-yyyy").format(claimSubmitHeader.getClaimDate()));
			claimRequestHeaderVo.setPeriodFrom(claimSubmitHeader.getPeriodFrom());
			claimRequestHeaderVo.setPeriodTo(claimSubmitHeader.getPeriodTo());
			claimRequestHeaderVo.setRemarks(claimSubmitHeader.getRemarks());
			
			claimRequestHeaderVo.setCurrencyList(currencyMasterRepository.findAll());
			
			// control to approve or clear for approver and clearer
			if (StatusConstant.APPROVE.value.equals(claimSubmitHeader.getFinalStatus().getId())) {
				claimRequestHeaderVo.setShowApproval(false);
				claimRequestHeaderVo.setShowClear(true);
			} else if (StatusConstant.PENDING.value.equals(claimSubmitHeader.getFinalStatus().getId())
					|| StatusConstant.VALIDATE.value.equals(claimSubmitHeader.getFinalStatus().getId())
					|| StatusConstant.FORWARD.value.equals(claimSubmitHeader.getFinalStatus().getId())) {
				claimRequestHeaderVo.setShowApproval(true);
				claimRequestHeaderVo.setShowClear(false);
			}
			LOG.info("------Exit from ClaimHeader part info------");
			//ClaimHeader part info
			
			
			
			//Claim Submit bill Details info
			LOG.info("------Enter into Claim Submit bill Details info------");
			List<ClaimRequestDetailsVo> claimRequestDetailsList = new ArrayList<>();//Used for Claim bill details Info to user view		
			List<ClaimSubmitDetails> claimDetailsList = claimRequestDetailRepository.getBillDetailsWithoutFile(claimId);
			Map<Integer, Double> totalAmount = new HashMap<>(); 
			for (ClaimSubmitDetails claimSubmitDetails : claimDetailsList) {
				ClaimRequestDetailsVo claimRequestDetails = new ClaimRequestDetailsVo();
				
				claimRequestDetails.setDetailsId(claimSubmitDetails.getId());
				claimRequestDetails.setClaimCategory(claimSubmitDetails.getClaimCategory());
				claimRequestDetails.setMode(claimSubmitDetails.getMode());
				claimRequestDetails.setBillDate((claimSubmitDetails.getBillDate()));
				claimRequestDetails.setVisitedLocation(claimSubmitDetails.getVisitedLocation());
				claimRequestDetails.setSourceAddress(claimSubmitDetails.getSourceAddress());
				claimRequestDetails.setDestinationAddress(claimSubmitDetails.getDestinationAddress());
				claimRequestDetails.setVendor(claimSubmitDetails.getVendor());
				claimRequestDetails.setBillCurrency(claimSubmitDetails.getBillCurrency());
				claimRequestDetails.setRate(claimSubmitDetails.getRate());
				claimRequestDetails.setDistance(claimSubmitDetails.getDistance());
				claimRequestDetails.setGiftQuantity(claimSubmitDetails.getGiftQuantity());
				claimRequestDetails.setIssuedTo(claimSubmitDetails.getIssuedTo());
				claimRequestDetails.setFromDate(claimSubmitDetails.getFromDate());
				claimRequestDetails.setToDate(claimSubmitDetails.getToDate());
				claimRequestDetails.setExpenseAmount(claimSubmitDetails.getTotalExpenseAmount());
				claimRequestDetails.setClaimAmount(claimSubmitDetails.getClaimAmount());
				claimRequestDetails.setClientBillable(claimSubmitDetails.getClientBillable());
				//claimRequestDetails.setFile(claimSubmitDetails.getFile());
				claimRequestDetails.setFileName(claimSubmitDetails.getFileName());
				claimRequestDetails.add(linkTo(methodOn(ClaimController.class).fileDownload(claimSubmitDetails.getId(), null)).withRel("file"));
				claimRequestDetails.setRemarks(claimSubmitDetails.getRemarks());
				
				// For Finance person to give verify each bills
				claimRequestDetails.setPayableAmount(claimSubmitDetails.getPayableAmount());
				claimRequestDetails.setFinanceRemarks(claimSubmitDetails.getFinanceRemarks());
				claimRequestDetails.setVerified(claimSubmitDetails.getVerified());
				// For Finance person to give verify each bills
				if(totalAmount.containsKey(claimSubmitDetails.getBillCurrency())){
					totalAmount.put(claimSubmitDetails.getBillCurrency(), claimSubmitDetails.getClaimAmount()+totalAmount.get(claimSubmitDetails.getBillCurrency()) );
				}else{
					totalAmount.put(claimSubmitDetails.getBillCurrency(), claimSubmitDetails.getClaimAmount());
		            }
				claimRequestDetailsList.add(claimRequestDetails);
			}
			claimRequestHeaderVo.setTotalAmount(totalAmount);
			LOG.info("------Exit from Claim Submit bill Details info------");
			//Claim Submit bill Details info
			
			
			//Claim Approval Details info
			LOG.info("------Enter into Claim Approval Details info------");
			List<ApprovelDetailsVo> approvelDetailsList = new ArrayList<>();         //Used for Claim approver details Info to use view
			List<ClaimApproveDetails> claimApproveList = claimApproveDetailsRepository.getApprovelClaims(claimId);
			for (ClaimApproveDetails claimApproveDetails : claimApproveList) {
				ApprovelDetailsVo approvelDetailsVo = new ApprovelDetailsVo();
				if (claimApproveDetails.getClaimStatus().getId() == StatusConstant.HOLD.value) {
					approvelDetailsVo.setManagerName("Finance");
				}else {
					if(null == claimApproveDetails.getApproverId() && claimApproveDetails.getClaimHeaderId().getForwardPersonId()!=0) {
						approvelDetailsVo.setManagerName(userMasterRepository.getOne(claimApproveDetails.getClaimHeaderId().getForwardPersonId()).getEmployeeName());
					}else {
					approvelDetailsVo.setManagerName(claimApproveDetails.getApproverId().getEmployeeName());
					}
				}
				approvelDetailsVo.setModifiedDate((null==claimApproveDetails.getModifiedDate())?"":(new SimpleDateFormat("dd-MMM-yyyy").format(claimApproveDetails.getModifiedDate())));
				approvelDetailsVo.setClaimStatus(claimApproveDetails.getClaimStatus().getClaimStatusCode());
				approvelDetailsVo.setRemarks(claimApproveDetails.getRemarks());
				if (claimApproveDetails.getClaimStatus().getId() == StatusConstant.CLEAR.value) {
					approvelDetailsVo.setApproveType(claimApproveDetails.getApproveType());
					approvelDetailsVo.setApprovedAmount(claimApproveDetails.getApprovedAmount());
					approvelDetailsVo.setApproveCurrencyId(claimApproveDetails.getApproveCurrencyId());
				}
				approvelDetailsList.add(approvelDetailsVo);
			}
			LOG.info("------Exit from Claim Approval Details info------");
			//Claim Approval Details info
			
			// Set value in claimRequestVo object
			
			claimRequestVo.setClaimRequestHeaderVo(claimRequestHeaderVo);
			claimRequestVo.setClaimRequestDetailList(claimRequestDetailsList);
			claimRequestVo.setApproveDetailsList(approvelDetailsList);
			return claimRequestVo;
		}catch(Exception e){
			LOG.debug("---------Exception in ClaimRequestServiceImpl getClaim() method-----",e);
			return null;	
		}

	}

	@Override
	public ClaimRequestVo viewClaim(Integer claimId, Integer userId) throws Exception {

		ClaimRequestVo claimRequestVo = new ClaimRequestVo();

		List<ClaimApproveDetails> claimApproveList = new ArrayList<>();
		List<ApprovelDetailsVo> approvelDetailsList = new ArrayList<>();

		UserMaster approverObj = userMasterRepository.getOne(userId);
		String fwdDept = "----";

		claimApproveList = claimApproveDetailsRepository.getApprovelClaims(claimId);
		for (ClaimApproveDetails claimApproveDetails : claimApproveList) {
			ApprovelDetailsVo approvelDetailsVo = new ApprovelDetailsVo();
			approvelDetailsVo.setClaimStatus(claimApproveDetails.getClaimStatus().getClaimStatusCode());
			if (claimApproveDetails.getApproverId() != null) {
				approvelDetailsVo.setManagerName(claimApproveDetails.getApproverId().getEmployeeName());
			} else {
				approvelDetailsVo.setManagerName(fwdDept);
			}

			approvelDetailsVo.setRemarks(claimApproveDetails.getRemarks());
			approvelDetailsList.add(approvelDetailsVo);
		}

		List<ClaimSubmitDetails> claimDetailsList = new ArrayList<>();
		List<ClaimRequestDetailsVo> claimRequestDetailsList = new ArrayList<ClaimRequestDetailsVo>();

		claimDetailsList = claimRequestDetailRepository.getClaimDetails(claimId);

		for (ClaimSubmitDetails claimSubmitDetails : claimDetailsList) {

			ClaimRequestDetailsVo claimRequestDetails = new ClaimRequestDetailsVo();
			claimRequestDetails.setRemarks(claimSubmitDetails.getRemarks());
			claimRequestDetails.setClaimAmount(claimSubmitDetails.getClaimAmount());
			claimRequestDetails.setVendor(claimSubmitDetails.getVendor());
			if (claimSubmitDetails.getBillDate() != null) {
				claimRequestDetails.setBillDate((claimSubmitDetails.getBillDate()));
			}

			claimRequestDetails.setVisitedLocation(claimSubmitDetails.getVisitedLocation());
			claimRequestDetails.setMode(claimSubmitDetails.getMode());
			claimRequestDetails.setRate(claimSubmitDetails.getRate());
			claimRequestDetails.setDistance(claimSubmitDetails.getDistance());
			claimRequestDetails.setClaimAmount(claimSubmitDetails.getClaimAmount());
			claimRequestDetails.setDetailsId(claimSubmitDetails.getId());
			claimRequestDetails.setGiftQuantity(claimSubmitDetails.getGiftQuantity());
			claimRequestDetails.setClientBillable(claimSubmitDetails.getClientBillable());
			claimRequestDetails.setBillCurrency(claimSubmitDetails.getBillCurrency());
			claimRequestDetails.setIssuedTo(claimSubmitDetails.getIssuedTo());
			claimRequestDetails.setExpenseAmount(claimSubmitDetails.getTotalExpenseAmount());
			claimRequestDetails.setLessPaidByCompany(claimSubmitDetails.getLessPaidByCompany());
			claimRequestDetails.setBillNo(claimSubmitDetails.getBillNo());
			claimRequestDetails.setClaimCategory(claimSubmitDetails.getClaimCategory());
			claimRequestDetails.setFileName(claimSubmitDetails.getFileName());
			claimRequestDetails
					.add(linkTo(methodOn(ClaimController.class).fileDownload(claimSubmitDetails.getId(), null))
							.withRel("file"));

			claimRequestDetailsList.add(claimRequestDetails);
		}

		ClaimSubmitHeader claimSubmitHeader = claimRequestHeaderRepository.findOne(claimId);
		ClaimRequestHeaderVo claimRequestHeaderVo = new ClaimRequestHeaderVo();

		if (StatusConstant.APPROVE.value.equals(claimSubmitHeader.getFinalStatus().getId())) {
			claimRequestHeaderVo.setShowClear(true);
		} else {
			claimRequestHeaderVo.setShowClear(false);
		}

		claimRequestHeaderVo.setClaimNo(claimSubmitHeader.getClaimNo());
		claimRequestHeaderVo.setId(claimSubmitHeader.getId());
		claimRequestHeaderVo.setClaimTypeName(claimSubmitHeader.getClaimTypeId().getClaimCode());
		claimRequestHeaderVo.setEmployeeName(claimSubmitHeader.getRequestingUserId().getEmployeeName());
		claimRequestHeaderVo.setEmployeeId(claimSubmitHeader.getRequestingUserId().getId());
		claimRequestHeaderVo.setCurrencyCode(claimSubmitHeader.getCurrencyId().getCurrencyCode());
		claimRequestHeaderVo.setClaimCurrency(claimSubmitHeader.getCurrencyId().getId());
		claimRequestHeaderVo.setClaimType(claimSubmitHeader.getClaimTypeId().getId());
		claimRequestHeaderVo.setClaimedAmount(claimSubmitHeader.getClaimAmount());
		claimRequestHeaderVo
				.setClaimDate((new SimpleDateFormat("dd-MM-yyyy").format(claimSubmitHeader.getClaimDate())));
		claimRequestHeaderVo.setRemarks(claimSubmitHeader.getRemarks());
		claimRequestHeaderVo.setPeriodFrom(claimSubmitHeader.getPeriodFrom());
		claimRequestHeaderVo.setPeriodTo(claimSubmitHeader.getPeriodTo());
		claimRequestHeaderVo.setSpecialApprove(claimSubmitHeader.getSpecialApprove());
		claimRequestHeaderVo.setProject(claimSubmitHeader.getProject());
		if (claimSubmitHeader.getProjectMasterId() != null) {
			claimRequestHeaderVo.setProjectId(claimSubmitHeader.getProjectMasterId().getId());
		}
		claimRequestHeaderVo.setClientBillable(claimSubmitHeader.getClientBillable());

		claimRequestHeaderVo.setDepartment(claimSubmitHeader.getRequestingUserId().getDepartment());
		claimRequestHeaderVo.setShowApproval(DepartmentConstant.isClearer(approverObj.getEmployeeName())?true:false);

		List<UserDetails> approverList = new ArrayList<>();
		if (claimSubmitHeader.getRequestingUserId().getUserManagerId() != null) {
			UserDetails uerDetails = new UserDetails();
			uerDetails.setUserId(userMasterRepository
					.getEmployeeManagerId(claimSubmitHeader.getRequestingUserId().getEmployeeManagerId()));
			uerDetails.setUserName(claimSubmitHeader.getRequestingUserId().getEmployeeManager());
			approverList.add(uerDetails);
		}

		claimRequestVo.setApproveDetailsList(approvelDetailsList);
		claimRequestVo.setClaimRequestDetailList(claimRequestDetailsList);
		claimRequestVo.setClaimRequestHeaderVo(claimRequestHeaderVo);
		return claimRequestVo;

	}

	public ClaimSubmitDetails verifyBillDetails(ClaimRequestDetailsVo billVerificationDetails) throws Exception {
		ClaimSubmitDetails claimSubmitDetails = claimRequestDetailRepository
				.findOne(billVerificationDetails.getDetailsId());
		claimSubmitDetails.setPayableAmount(billVerificationDetails.getPayableAmount());
		claimSubmitDetails.setFinanceRemarks(billVerificationDetails.getFinanceRemarks());
		claimSubmitDetails.setVerified(billVerificationDetails.getVerified());
		claimRequestDetailRepository.save(claimSubmitDetails);
		return null;
	}

}
