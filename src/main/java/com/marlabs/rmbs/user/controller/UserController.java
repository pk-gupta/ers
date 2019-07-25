/*package com.marlabs.rmbs.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.marlabs.consumer.ldap.repo.user.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.user.service.UserServiceImpl;
import com.marlabs.rmbs.user.vo.AdminRequest;
import com.marlabs.rmbs.user.vo.UserDetails;

import io.jsonwebtoken.Claims;

@RestController
public class UserController {

	@Autowired
	private UserMasterRepository userMasterRepository;
	@Autowired
	private UserServiceImpl userServiceImpl;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/adminform", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AdminRequest> getAdminForm(HttpServletRequest http) {

		Claims claim = (Claims) http.getAttribute("claims");
		String email = (String) claim.get("contactId");

		UserMaster user = userMasterRepository.findByMailIgnoreCase(email);
		if (user.getRoleId() == 3 || user.getRoleId() == 2) {
			return new ResponseEntity<AdminRequest>(HttpStatus.FORBIDDEN);

		}
		return new ResponseEntity<AdminRequest>(userServiceImpl.getAdminForm(), HttpStatus.OK);
	}

	@RequestMapping(value = "/usersList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDetails>> searchUser(@RequestParam("user") String user, HttpServletRequest http) {

		Claims claim = (Claims) http.getAttribute("claims");
		String email = (String) claim.get("contactId");

		UserMaster requestingUser = userMasterRepository.findByMailIgnoreCase(email);
		List<UserDetails> userDetailList = userServiceImpl.userSearch(user);
		if (requestingUser.getRoleId() == 3) {
			log.debug(requestingUser.getRoleId() + "Normal user" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			if (userDetailList != null) {
				return new ResponseEntity<>(userDetailList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		}

	}

	@RequestMapping(value = "/config/getEmployee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminRequest> getConfigDetailEmployee(@RequestParam("userId") final Integer userId,
			HttpServletRequest http) {

		Claims claim = (Claims) http.getAttribute("claims");
		String email = (String) claim.get("contactId");

		UserMaster requestingUser = userMasterRepository.findByMailIgnoreCase(email);
		if (requestingUser.getRoleId() == 3 || requestingUser.getRoleId() == 2) {
			return new ResponseEntity<AdminRequest>(HttpStatus.FORBIDDEN);

		}
		return new ResponseEntity<AdminRequest>(userServiceImpl.getEmployeeConfigDetails(userId), HttpStatus.OK);
	}

	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AdminRequest> updateUser(@RequestBody AdminRequest adminRequest, HttpServletRequest http) {
		Claims claim = (Claims) http.getAttribute("claims");
		String email = (String) claim.get("contactId");

		UserMaster user = userMasterRepository.findByMailIgnoreCase(email);
		if (user.getRoleId() == 3 || user.getRoleId()== 2) {
			return new ResponseEntity<AdminRequest>(HttpStatus.FORBIDDEN);

		}

		AdminRequest adminRequestObj = userServiceImpl.updateUser(adminRequest);
		return new ResponseEntity<AdminRequest>(adminRequestObj, HttpStatus.OK);
	}

}
*/