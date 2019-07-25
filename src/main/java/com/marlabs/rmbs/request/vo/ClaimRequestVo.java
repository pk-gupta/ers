package com.marlabs.rmbs.request.vo;


import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

public class ClaimRequestVo extends ResourceSupport{
	
	private ClaimRequestHeaderVo claimRequestHeaderVo =new ClaimRequestHeaderVo();
	private List<ClaimRequestDetailsVo> claimRequestDetailList=new ArrayList<>();
	private List<ApprovelDetailsVo> approveDetailsList=new ArrayList<>();
	
	
	public ClaimRequestHeaderVo getClaimRequestHeaderVo() {
		return claimRequestHeaderVo;
	}
	public void setClaimRequestHeaderVo(ClaimRequestHeaderVo claimRequestHeaderVo) {
		this.claimRequestHeaderVo = claimRequestHeaderVo;
	}	
	
	public List<ClaimRequestDetailsVo> getClaimRequestDetailList() {
		return claimRequestDetailList;
	}

	public void setClaimRequestDetailList(List<ClaimRequestDetailsVo> claimRequestDetailList) {
		this.claimRequestDetailList=claimRequestDetailList;
	}
	public List<ApprovelDetailsVo> getApproveDetailsList() {
		return approveDetailsList;
	}
	public void setApproveDetailsList(List<ApprovelDetailsVo> approveDetailsList) {
		this.approveDetailsList = approveDetailsList;
	}
	
}
