package com.marlabs.rmbs.request.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.marlabs.rmbs.entities.ClaimApproveDetails;
import com.marlabs.rmbs.entities.ClaimStatus;
import com.marlabs.rmbs.entities.ClaimSubmitDetails;
import com.marlabs.rmbs.entities.ClaimSubmitHeader;
import com.marlabs.rmbs.entities.ClaimTypeMaster;
import com.marlabs.rmbs.entities.CurrencyMaster;
import com.marlabs.rmbs.entities.ProjectMaster;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.exception.FileNotSupportedException;
import com.marlabs.rmbs.masters.repository.ClaimStatusRepository;
import com.marlabs.rmbs.request.repository.ClaimApproveDetailsRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestDetailRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestHeaderRepository;
import com.marlabs.rmbs.request.vo.ClaimRequestDetailsVo;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;
import com.marlabs.rmbs.request.vo.ClaimRequestVo;

public class GiftService implements ClaimService {

	@Autowired
	private ClaimRequestHeaderRepository claimRequestHeaderRepository;

	@Autowired
	private ClaimRequestDetailRepository claimRequestDetailRepository;

	@Autowired
	private ClaimApproveDetailsRepository claimApproveDetailsRepository;

	@Autowired
	private ClaimStatusRepository claimStatusRepository;

	@Transactional
	@Override
	public ClaimRequestHeaderVo update(ClaimRequestVo claimRequestVo, Integer claimId, UserMaster userObj,
			ClaimTypeMaster claimTypeobj, CurrencyMaster currencyobj, ProjectMaster projectMaster,
			UserMaster approveObj) throws FileNotSupportedException, Exception {

		ClaimRequestHeaderVo claimRequestHeaderVo = new ClaimRequestHeaderVo();
		ClaimStatus claimStatus = null;
		if ("save".equalsIgnoreCase(claimRequestVo.getClaimRequestHeaderVo().getSubmitType())) {
			claimStatus = claimStatusRepository.getOne(1);
		} else {
			claimStatus = claimStatusRepository.getOne(2);
		}

		ClaimSubmitHeader lastHeader = claimRequestHeaderRepository.findTop1ByOrderByIdDesc();
		ClaimSubmitHeader claimSubmitHeader = null;
		String uniqueNumber = "";
		if (claimId.equals(0)) {
			if (lastHeader == null) {
				uniqueNumber = "CLM001";
			} else {
				uniqueNumber = "CLM00" + lastHeader.getId();
			}
			claimSubmitHeader = new ClaimSubmitHeader();
		} else {
			claimSubmitHeader = claimRequestHeaderRepository.getOne(claimId);

			List<ClaimSubmitDetails> claimHeaderList = claimRequestDetailRepository.getClaimDetails(claimId);
			for (ClaimSubmitDetails claimSubmitDetails : claimHeaderList) {
				claimRequestDetailRepository.delete(claimSubmitDetails.getId());
			}
		}
		claimSubmitHeader.setProjectMasterId(projectMaster);
		claimSubmitHeader.setRequestingUserId(userObj);
		claimSubmitHeader.setNoOfREceipts(0);
		claimSubmitHeader.setCurrencyId(currencyobj);
		claimSubmitHeader.setClaimTypeId(claimTypeobj);
		claimSubmitHeader.setPeriodTo(claimRequestVo.getClaimRequestHeaderVo().getPeriodTo());
		claimSubmitHeader.setPeriodFrom(claimRequestVo.getClaimRequestHeaderVo().getPeriodFrom());
		claimSubmitHeader.setProject(projectMaster.getProjectCode());
		claimSubmitHeader.setRemarks(claimRequestVo.getClaimRequestHeaderVo().getRemarks());
		claimSubmitHeader.setClaimDate(new Date());
		claimSubmitHeader.setFinalStatus(claimStatus);
		claimSubmitHeader.setApprovedPersonId(approveObj);
		claimSubmitHeader.setClaimAmount(claimRequestVo.getClaimRequestHeaderVo().getClaimedAmount());
		claimSubmitHeader.setSpecialApprove(claimRequestVo.getClaimRequestHeaderVo().getSpecialApprove());
		claimSubmitHeader.setModifiedDate(new Date());
		if (userObj.getEmployeeType().equalsIgnoreCase("H") || userObj.getEmployeeType().equalsIgnoreCase("E")) {
			claimSubmitHeader.setFinanceType(1);
		} else
			claimSubmitHeader.setFinanceType(2);

		if (claimId.equals(0)) {
			claimSubmitHeader.setClaimNo(uniqueNumber);
			claimRequestHeaderRepository.save(claimSubmitHeader);
		} else {
			claimRequestHeaderRepository.saveAndFlush(claimSubmitHeader);
		}

		for (ClaimRequestDetailsVo claimRequestDetailsVo : claimRequestVo.getClaimRequestDetailList()) {
			ClaimSubmitDetails claimSubmitDetails = new ClaimSubmitDetails();
			claimSubmitDetails.setClaimHeaderId(claimSubmitHeader);
			claimSubmitDetails.setBillDate(claimRequestDetailsVo.getBillDate());
			claimSubmitDetails.setVendor(claimRequestDetailsVo.getVendor());
			claimSubmitDetails.setIssuedTo(claimRequestDetailsVo.getIssuedTo());
			claimSubmitDetails.setGiftQuantity(claimRequestDetailsVo.getGiftQuantity());
			claimSubmitDetails.setBillCurrency(claimRequestDetailsVo.getBillCurrency());
			claimSubmitDetails.setTotalExpenseAmount(claimRequestDetailsVo.getExpenseAmount());
			claimSubmitDetails.setClaimAmount(claimRequestDetailsVo.getClaimAmount());
			claimSubmitDetails.setFile(claimRequestDetailsVo.getFile());
			claimSubmitDetails.setFileName(claimRequestDetailsVo.getFileName());
			claimSubmitDetails.setRemarks(claimRequestDetailsVo.getRemarks());
			claimSubmitDetails.setClientBillable(claimRequestDetailsVo.getClientBillable());
         	claimRequestDetailRepository.save(claimSubmitDetails);
		}

		if (claimId.equals(0)) {
			ClaimApproveDetails claimApproveDetails = new ClaimApproveDetails(claimSubmitHeader, approveObj,
					claimStatus);
			claimApproveDetails.setModifiedDate(new Date());
			claimApproveDetailsRepository.save(claimApproveDetails);

		} else {
			List<ClaimApproveDetails> claimApproveList = claimApproveDetailsRepository.getApprovelClaims(claimId);
			for (ClaimApproveDetails claimApproveDetails : claimApproveList) {
				if (claimApproveDetails.getClaimStatus().getId() == 1)
					claimApproveDetailsRepository.delete(claimApproveDetails.getId());
			}

			ClaimApproveDetails claimApproveDetails = new ClaimApproveDetails(claimSubmitHeader, approveObj,
					claimStatus);
			claimApproveDetails.setModifiedDate(new Date());
			claimApproveDetailsRepository.save(claimApproveDetails);
		}

		if (claimId.equals(0)) {
			claimRequestHeaderVo.setClaimNo(uniqueNumber);
		}else {
			claimRequestHeaderVo.setClaimNo(claimSubmitHeader.getClaimNo());
		}
		claimRequestHeaderVo.setId(claimSubmitHeader.getId());

		return claimRequestHeaderVo;
	}

}