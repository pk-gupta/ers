package com.marlabs.rmbs.config;
/*package com.marlabs.rmbs.config.smtp.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.marlabs.rmbs.entities.ClaimStatus;
import com.marlabs.rmbs.entities.ClaimSubmitHeader;
import com.marlabs.rmbs.entities.ClaimTypeMaster;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.repository.ClaimStatusRepository;
import com.marlabs.rmbs.masters.repository.ClaimTypeRepository;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestHeaderRepository;




@Component
public class EmailNotification {

	
@Autowired
private ClaimRequestHeaderRepository claimRequestHeaderRepository;
@Autowired
private  UserMasterRepository userMasterRepository;

@Autowired
private SmtpMailSender smtpMailSender;

@Autowired
private Environment env;
@Autowired
private ClaimStatusRepository claimStatusRepository;



@Scheduled(cron = "0 30 10 ? * MON") //Every friday 5:30PM
public void SendNotificationMail() throws MessagingException, IOException{
	String sendTofinanceTeam = env.getProperty("indiaFinanceMailID");
	Properties prop = new Properties();
	InputStream input = getClass().getClassLoader().getResourceAsStream("htmlString.properties");
	prop.load(input);
	String claimTypeMail = prop.getProperty("html.submission.middle1");
	List<ClaimSubmitHeader> claimsList=claimRequestHeaderRepository.findAll();
	for(ClaimSubmitHeader claim:claimsList){
		
		if(claim.getFinalStatus().getId()==2 || claim.getFinalStatus().getId()==5){
			String approverId="";
			String approverPerson="";
			if(claim.getFinalStatus().getId()==2){
			 approverId = claim.getApprovedPersonId().getMail();
			 approverPerson=claim.getApprovedPersonId().getCn();
			 String body =  prop.getProperty("html.submission.header") + approverPerson
						+ claimTypeMail + claim.getRequestingUserId().getCn() + " in " + new Date()
						+ prop.getProperty("html.submission.middle2") + claim.getClaimNo()
						+ prop.getProperty("html.submission.middle3") + claim.getClaimTypeId().getClaimCode()
						+ prop.getProperty("html.submission.middle4")
						+ claim.getClaimAmount() + " " + claim.getCurrencyId().getCurrencyCode()
						+ prop.getProperty("html.submission.bottom");
				
			smtpMailSender.send(approverId,claim.getRequestingUserId().getMail(),
					" Reminder: Reimburment Notification !!!", body);
			}
			else if(claim.getFinalStatus().getId()==5){
				UserMaster user=userMasterRepository.findOne(claim.getForwardPersonId());
				approverId=user.getMail();		
				approverPerson=user.getCn();
				String body =  prop.getProperty("html.submission.header") + approverPerson
						+ claimTypeMail + claim.getRequestingUserId().getCn() + " in " + new Date()
						+ prop.getProperty("html.submission.middle2") + claim.getClaimNo()
						+ prop.getProperty("html.submission.middle3") + claim.getClaimTypeId().getClaimCode()
						+ prop.getProperty("html.submission.middle4")
						+ claim.getClaimAmount() + " " + claim.getCurrencyId().getCurrencyCode()
						+ prop.getProperty("html.submission.bottom");
				
			smtpMailSender.send(approverId,claim.getApprovedPersonId().getMail(),claim.getRequestingUserId().getMail(),
					" Reminder: Reimburment Notification !!!", body);
			}

			String body =  prop.getProperty("html.submission.header") + approverPerson
					+ claimTypeMail + claim.getRequestingUserId().getCn() + " in " + new Date()
					+ prop.getProperty("html.submission.middle2") + claim.getClaimNo()
					+ prop.getProperty("html.submission.middle3") + claim.getClaimTypeId().getClaimCode()
					+ prop.getProperty("html.submission.middle4")
					+ claim.getClaimAmount() + " " + claim.getCurrencyId().getCurrencyCode()
					+ prop.getProperty("html.submission.bottom");
			
		smtpMailSender.send(claim.getRequestingUserId().getMail(),claim.getApprovedPersonId().getMail(),sendTofinanceTeam,
				" Reminder: Reimburment Notification !!!", body);
		}
		


		
		
	}
	
	
	
	
}

	
	
	
}
*/