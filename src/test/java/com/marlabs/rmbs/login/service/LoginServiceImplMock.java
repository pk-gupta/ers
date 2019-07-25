//package com.marlabs.rmbs.login.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.marlabs.rmbs.entities.UserMaster;
//import com.marlabs.rmbs.login.repository.LoginRepository;
//import com.marlabs.rmbs.login.repository.LoginRepositoryMock;
//import com.marlabs.rmbs.login.vo.LoginVo;
//
//
//@Service
//public class LoginServiceImplMock {
//
//
//
//	@Autowired
//	private LoginRepositoryMock loginRepositoryMock;
//
//
//	public LoginVo loginAuthentication(LoginVo loginModel){
//
//
//		LoginVo logModel=new LoginVo();
//			UserMaster userInfo=loginRepositoryMock.findByMailContainingIgnoreCase();
//			if(userInfo!= null){
//			    logModel.setEmployeeId(userInfo.getId());
//				logModel.setEmployeeName(userInfo.getGivenName());
//
//				if (userInfo.getUserManagerId()!=null) {
//					logModel.setManagerCode(userInfo.getUserManagerId().getCn());
//					logModel.setManagerId(userInfo.getUserManagerId().getId());
//				}else{
//					logModel.setManagerCode("Not Updated");
//				}
//
//				logModel.setRole(userInfo.getRoleId().getRoleCode());
//				logModel.setEmployeeCode(userInfo.getCn());
//				logModel.setMail(userInfo.getMail());
//				logModel.setDepartment(userInfo.getDepartment());
//				logModel.setTelephoneNumber(userInfo.getTelephoneNumber());
//				logModel.setLoc(userInfo.getLoc());
//			 }
//			 return logModel;
//	 }
//
//
//
//}
//
//
//
//
