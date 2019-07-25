package com.marlabs.rmbs.review.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.rmbs.entities.FrequentAskedQuestions;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.constant.DepartmentConstant;
import com.marlabs.rmbs.masters.repository.ProjectMasterRepository;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;
import com.marlabs.rmbs.review.service.FaqDTO;
import com.marlabs.rmbs.review.service.ReviewServiceImpl;
import com.marlabs.rmbs.user.vo.CommonBean;

import io.jsonwebtoken.Claims;

@RestController
public class ReviewController {
	
	private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
	SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private ProjectMasterRepository projectMasterRepository;
	
	@Autowired
	private ReviewServiceImpl reviewServiceImpl;
	
	@Autowired
	private UserMasterRepository userMasterRepository;
	
	//In Projectwise claim details ,need project related information to get data
	@RequestMapping(value = "/review",produces = MediaType.APPLICATION_JSON_VALUE,
	method = RequestMethod.GET)
	public ResponseEntity<List<CommonBean>> loadClaimReview(HttpServletRequest http) {
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			

			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			if(DepartmentConstant.isClearer(user.getEmployeeName())){
				List<CommonBean> projectList=projectMasterRepository.findProjects();
				return new ResponseEntity<>(projectList,HttpStatus.OK);
				
			}else{
				
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			
			
			
		} catch (Exception e) {
			log.debug("-----Exception in ReviewController with loadClaimReview() method and end point /review/-----",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//In Projectwise claim details list of data with date,status,project
	@RequestMapping(value = "/review/details",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
	public ResponseEntity<List<ClaimRequestHeaderVo>> claimsReviews(@RequestParam(value="projectId", defaultValue="0")
	final Integer projectId,@RequestParam("status") Integer status,@RequestParam("fromDate") final String fromDateString,
	@RequestParam("toDate") final String toDateString,HttpServletRequest http) {
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			

			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			if(DepartmentConstant.isClearer(user.getEmployeeName())){
				Date fromDate = null;
				Date toDate = null;
				if(!("null").equals(fromDateString)){
					fromDate= sdfr.parse(fromDateString);
				}
				if(!("null").equals(toDateString)){
					toDate= sdfr.parse(toDateString);
					toDate=DateUtils.addDays(toDate, 1);
					
				}
				List<ClaimRequestHeaderVo> claimsList=reviewServiceImpl.getClaimsByProject(projectId,status,fromDate,toDate);
				return new ResponseEntity<>(claimsList,HttpStatus.OK);
				
			}else{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				
				
			}
		} catch (Exception e) {
			log.debug("-----Exception in ReviewController with claimsReviews() method and end point /review/details------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	///////////////25-09-2017-Monday/////////////////////

	@RequestMapping(value = "/review/details/{userId}",produces = MediaType.APPLICATION_JSON_VALUE,
	method = RequestMethod.GET)
	public ResponseEntity<List<ClaimRequestHeaderVo>> claimsByUserId(@PathVariable("userId") Integer userId,HttpServletRequest http) {
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
			if(DepartmentConstant.isClearer(user.getEmployeeName())){
				List<ClaimRequestHeaderVo> claimsList=reviewServiceImpl.getClaimsByUserId(userId);
				return new ResponseEntity<>(claimsList,HttpStatus.OK);
				
			}else{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				
				
			}
		} catch (Exception e) {
			log.debug("-----Exception in ReviewController with claimsByUserId() method and end point /review/details/{userId}------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	////////////////////25-09-2017-Monday//////////////////
	
	

	@RequestMapping(value = "/faq",produces = MediaType.APPLICATION_JSON_VALUE,
	method = RequestMethod.GET)
	public ResponseEntity<List<FrequentAskedQuestions>> getFaq() {
		try {
			List <FrequentAskedQuestions> faqList=reviewServiceImpl.getFreqAskedQuestion();
			return new ResponseEntity<>(faqList,HttpStatus.OK);
		} catch (Exception e) {
			log.debug("-----Exception in ReviewController with getFaq() method and end point /faq------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value ="/faqs",method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<List<FrequentAskedQuestions>> setFaq(@RequestBody FaqDTO faqDTO,HttpServletRequest http) throws Exception {
		Claims claim=(Claims) http.getAttribute("claims");
		String email=(String) claim.get("contactId");
		

		UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
		
		if(DepartmentConstant.isClearer(user.getEmployeeName())){
		try {
			List<FrequentAskedQuestions> faqList=reviewServiceImpl.saveData(faqDTO);
			return new ResponseEntity<>(faqList,HttpStatus.OK);
			
		}catch (Exception e) {
			log.debug("-----Exception in ReviewController with setFaq() method and end point /faq------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}}else{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}
	

	@RequestMapping(value = "/deleteFaq",produces = MediaType.APPLICATION_JSON_VALUE,
	method = RequestMethod.GET)
	public ResponseEntity<String> deleteFaq(@RequestParam("Id") int Id, HttpServletRequest http) {
		Claims claim=(Claims) http.getAttribute("claims");
		String email=(String) claim.get("contactId");
		

		UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
		
		if(DepartmentConstant.isClearer(user.getEmployeeName())){
			try{
				reviewServiceImpl.deleteFreqAskedQuestion(Id);
				return new ResponseEntity<>(HttpStatus.OK);
			}catch(Exception e) {
				log.debug("-----Exception in ReviewController with deleteFaq() method and end point /deleteFaq------",e);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
			
	
	}
	
	
}
