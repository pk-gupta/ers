package com.marlabs.rmbs.masters.constant;

import org.springframework.beans.factory.annotation.Autowired;

import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.service.UserMasterRepository;

public class DepartmentConstant {
	
	 public static final Integer DELIVERY=1;
	 public static final Integer FINANCE=2;
	 public static final Integer IT=3;
	 public static final Integer ADMINISTRATOR=4;
	 	 
@Autowired
private UserMasterRepository usermasterrepo;

public  Boolean isFinance(int empId){
	UserMaster user = usermasterrepo.findOne(empId);
	if(user.getDepartment().contains("finance"))
		return true;
	else
		return false;	
	}	

public  static Boolean isClearer(String employeeName){
	if (("Sreedhara M N").equalsIgnoreCase(employeeName)
			|| ("Pappu Kumar Gupta").equalsIgnoreCase(employeeName)
			|| ("Durga Prasad  Mohanty").equalsIgnoreCase(employeeName)
			|| ("Sudarshan  Hegde").equalsIgnoreCase(employeeName)
			|| ("Shreya Dayanand Shetty").equalsIgnoreCase(employeeName))
		return true;
	else
		return false;
		
	}
}
