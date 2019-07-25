package com.marlabs.rmbs.login.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.login.repository.LoginRepository;
import com.marlabs.rmbs.login.vo.LoginVo;
import com.marlabs.rmbs.masters.constant.DepartmentConstant;

import io.jsonwebtoken.Claims;

@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public LoginVo loginAuthentication(HttpServletRequest http) {

		Claims claim = (Claims) http.getAttribute("claims");
		String email = (String) claim.get("contactId");

		LoginVo logModel = new LoginVo();
		UserMaster user = loginRepository.findByMailContainingIgnoreCase(email);
		if (user != null && user.getIsActive()) {
			logModel.setId(user.getId());
			logModel.setEmployeeId(user.getEmployeeId());
			logModel.setMail(user.getMail());
			logModel.setEmployeeName(user.getEmployeeName());
			logModel.setDesignation(user.getDesignation());
			logModel.setLocation(user.getLocation());
			logModel.setEmployeeManager(user.getEmployeeManager());
			logModel.setEmployeeDepartment(user.getEmployeeDepartment());
			logModel.setEmployeeType(user.getEmployeeType());
			logModel.setRole(user.getRoleId());
			logModel.setApprover(user.getIsManager());
			logModel.setClearer(DepartmentConstant.isClearer(user.getEmployeeName()));
			}
		return logModel;
	}
}
