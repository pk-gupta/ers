//package com.marlabs.rmbs.login.repository;
//
//import org.springframework.stereotype.Repository;
//
//import com.marlabs.rmbs.entities.RoleMaster;
//import com.marlabs.rmbs.entities.UserMaster;
//
//@Repository
//public class LoginRepositoryMock {
//
//	public UserMaster findByMailContainingIgnoreCase(){
//
//		UserMaster user=new UserMaster();
//		user.setId(1758);
//		user.setGivenName("Jithin");
//		user.setRoleId(getRoleId());
//		user.setCn("Jithin Chandran");
//		user.setMail("jithin.chandran@marlabs.com");
//		user.setDepartment("GDIC Kochi");
//		user.setTelephoneNumber("");
//		user.setLoc("");
//
//		return user;
//
//	}
//
//	RoleMaster getRoleId(){
//
//		RoleMaster role=new RoleMaster();
//		role.setId(3);
//		role.setRoleCode("NormalUser");
//		role.setRoleDesc("Normal User");
//		return role;
//
//	}
//
//}
