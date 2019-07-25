package com.marlabs.rmbs.report.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.report.service.ReportGenerationService;
import com.marlabs.rmbs.report.vo.PerDiemReportVo;

import io.jsonwebtoken.Claims;

@RestController
public class ReportGenerationController {
    
	@Autowired
	private UserMasterRepository userMasterRepository;
	@Autowired 
	private ReportGenerationService reportGenerationService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
	// 13-10-2017 Creating report for per diem
	@RequestMapping(value="/getPerDiemReprtData", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PerDiemReportVo>> getPerDiemReportData(@RequestParam("claimType") Integer claimType, 
			@RequestParam("claimCategory") String claimCategory,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, HttpServletRequest http){
		try {
			Claims claim=(Claims) http.getAttribute("claims");
			String email=(String) claim.get("contactId");
			
			UserMaster user = userMasterRepository.findByMailIgnoreCase(email);
			if(user.getIsActive()){	
				List<PerDiemReportVo> perDiemReportDetails = null;
				Date fromPerdiemDate = null;
				Date toPerdiemDate = null;
				fromPerdiemDate= sdfr.parse(fromDate);
				toPerdiemDate= sdfr.parse(toDate);			
				
				perDiemReportDetails = reportGenerationService.getPerDiemReport(claimType,claimCategory,fromPerdiemDate,toPerdiemDate);
				return new ResponseEntity<>(perDiemReportDetails,HttpStatus.OK);
				
			}else{
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		
		}catch(Exception e){
			log.debug("Exception in Get PerDiem Report", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
	
	
}
