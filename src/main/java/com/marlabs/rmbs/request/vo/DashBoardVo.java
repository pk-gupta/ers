package com.marlabs.rmbs.request.vo;

import java.util.ArrayList;
import java.util.List;

public class DashBoardVo {
	private List<ClaimRequestHeaderVo> claimRequestList=new ArrayList<>();
	private List<ClaimRequestHeaderVo> claimApproveList=new ArrayList<>();
	private Boolean enableClearer;
	private Integer count;
	private Integer pendingCount;
	private Integer approverCount;
	private Integer clearCount;
	public List<ClaimRequestHeaderVo> getClaimRequestList() {
		return claimRequestList;
	}
	public void setClaimRequestList(List<ClaimRequestHeaderVo> claimRequestList) {
		this.claimRequestList = claimRequestList;
	}
	public List<ClaimRequestHeaderVo> getClaimApproveList() {
		return claimApproveList;
	}
	public void setClaimApproveList(List<ClaimRequestHeaderVo> claimApproveList) {
		this.claimApproveList = claimApproveList;
	}
	public Boolean getEnableClearer() {
		return enableClearer;
	}
	public void setEnableClearer(Boolean enableClearer) {
		this.enableClearer = enableClearer;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Integer pendingCount) {
		this.pendingCount = pendingCount;
	}
	public Integer getApproverCount() {
		return approverCount;
	}
	public void setApproverCount(Integer approverCount) {
		this.approverCount = approverCount;
	}
	public Integer getClearCount() {
		return clearCount;
	}
	public void setClearCount(Integer clearCount) {
		this.clearCount = clearCount;
	}
	
	
	
}
