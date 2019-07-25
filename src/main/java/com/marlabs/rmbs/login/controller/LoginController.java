package com.marlabs.rmbs.login.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.marlabs.rmbs.login.service.LoginService;
import com.marlabs.rmbs.login.vo.LoginVo;

@RestController
public class LoginController {
	@Autowired
	public LoginService loginService;
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/userdetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginVo> loginAuthentication(HttpServletRequest http){
		LoginVo userInformatiom = null;
		try {
			userInformatiom = loginService.loginAuthentication(http);
			if (userInformatiom.getEmployeeId() != null) {
				return new ResponseEntity<>(userInformatiom, HttpStatus.OK);
			} else {
				LOG.info("--------------- Exit from LoginController.loginAuthentication() with null value ---------------");
				return new ResponseEntity<>(userInformatiom, HttpStatus.NOT_FOUND);
			}	
		}catch(Exception e) {
			LOG.error("---------------Excetion in LoginController.loginAuthentication()------------------",e);
			return new ResponseEntity<>(userInformatiom, HttpStatus.NOT_FOUND);
		}
		
	}

}
