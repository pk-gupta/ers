package com.marlabs.rmbs.request.service;


import java.util.Date;
import com.marlabs.rmbs.entities.ClaimSubmitDetails;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.request.vo.ClaimRequestDetailsVo;
import com.marlabs.rmbs.request.vo.ClaimRequestLoad;
import com.marlabs.rmbs.request.vo.ClaimRequestVo;
import com.marlabs.rmbs.request.vo.PerDiem;

public interface ClaimRequestService {
	public ClaimRequestLoad loadClaimRequest(Integer userId) throws Exception;
	public void delete(Integer claimId,UserMaster user) throws Exception;
	public ClaimRequestVo getClaim(Integer claimId,Integer userId,Integer role) throws Exception;
	public ClaimRequestVo viewClaim(Integer claimId,Integer userId) throws Exception ;
	public PerDiem loadPerDiemAmnt(Integer claimType, String employeeId, String claimCategory, String location, Date _fromPerdiemDate, Date _toPerdiemDate)throws Exception;
	public ClaimSubmitDetails verifyBillDetails(ClaimRequestDetailsVo billVerificationDetails) throws Exception;
		
	
	
}
