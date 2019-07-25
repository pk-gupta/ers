package com.marlabs.rmbs.approve.service;

import java.util.Date;

public interface ClaimList {
	public int getProjectId();
	public String getProjectCode();
	public String getProjectDesc();
	public Date getClaimDate();
	public String getRemarks();
	public String getClaimNo();
}
