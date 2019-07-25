package com.marlabs.rmbs.user.service;

import java.util.List;

import com.marlabs.rmbs.user.vo.AdminRequest;
import com.marlabs.rmbs.user.vo.UserDetails;

public interface UserService  {
	
	 public List<UserDetails> userSearch(String userDetails) ; 
	 
	 //public AdminRequest updateUser(AdminRequest adminRequest);
	 
	 
	 //public AdminRequest getAdminForm();
	 
	 
	 //public AdminRequest getEmployeeConfigDetails(Integer userId);
}
