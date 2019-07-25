package com.marlabs.rmbs.approve.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;
import com.marlabs.rmbs.request.vo.DashBoardVo;

public interface ClaimApproveService {
	
	public ClaimRequestHeaderVo approve(Integer claimId,ClaimRequestHeaderVo claimRequestHeaderVo) throws IOException,MessagingException,DepartmentManagerMissingException,ReportingManagerOfDepartmentManagerMissingException;
	public DashBoardVo getClaimsForApprover(Integer userId, Integer pageNum);
	public DashBoardVo getClaimsForClearer(Integer userId, Integer pageNum);
	public DashBoardVo getClaimsForUser(Integer userId, Integer pageNum);
}
