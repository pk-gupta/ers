package com.marlabs.rmbs.request.service;

import com.marlabs.rmbs.entities.ClaimTypeMaster;
import com.marlabs.rmbs.entities.CurrencyMaster;
import com.marlabs.rmbs.entities.ProjectMaster;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;
import com.marlabs.rmbs.request.vo.ClaimRequestVo;

public interface ClaimService {
	
	public ClaimRequestHeaderVo update(ClaimRequestVo claimRequestVo,
			Integer claimId,UserMaster userMaster,ClaimTypeMaster claimTypeMaster,
			CurrencyMaster currencyMaster,ProjectMaster projectMaster,UserMaster userApprover) throws Exception;
	
}
