package com.marlabs.rmbs.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.entities.UserMaster;

@Service
public class EmailService  {
	@Autowired
	private MailConfig mailConfig;
	@Autowired
	private Properties prop;
	@Autowired
	private Environment env;
	private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);
	private static final String MAIL_NOTIFICATION = "Expense Reimbursement Notification !!!";
	private static final String HTML_TEMPLATE = "mailTemplate.properties";
	private static final String TO_SEND = "pappu.kumar@marlabs.com";
	
	
	public void send(UserMaster claimant, UserMaster approver, String to, String claimStatus, String remarks,
			String claimNo) {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream(HTML_TEMPLATE);
			prop.load(input);

			String body = prop.getProperty("html.approve.header") + " Claim #" + claimNo + " " + claimStatus
					+ prop.getProperty("html.approve.middle") + " " + remarks + " By " + approver.getEmployeeName()
					+ " " + prop.getProperty("html.approve.bottom");
			if(Arrays.asList(env.getActiveProfiles()).contains("prod"))
				mailConfig.send(claimant.getMail(), approver.getMail(), to, MAIL_NOTIFICATION, body);
			else
				mailConfig.send("julie.kumari@marlabs.com", TO_SEND, "sandeep.patil@marlabs.com",MAIL_NOTIFICATION, body);
		} catch (IOException | MessagingException e) {
			LOG.debug("-----------Exception in MailSender while sending mail-----------------", e);
		}
	}

	public void send(UserMaster claimant, UserMaster approver, String claimStatus, String remarks, String claimNo) {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream(HTML_TEMPLATE);
			prop.load(input);

			String body = prop.getProperty("html.approve.header") + " Claim #" + claimNo + " " + claimStatus
					+ prop.getProperty("html.approve.middle") + " " + remarks + " By " + approver.getEmployeeName()
					+ " " + prop.getProperty("html.approve.bottom");
			if(Arrays.asList(env.getActiveProfiles()).contains("prod"))
				mailConfig.send(claimant.getMail(), approver.getMail(), MAIL_NOTIFICATION, body);
			else
				mailConfig.send("sandeep.patil@marlabs.com", TO_SEND, MAIL_NOTIFICATION, body);
		} catch (IOException | MessagingException e) {
			LOG.debug("---------Exception in MailSender while sending mail---------------", e);
		}
	}

	public void send(UserMaster claimant, UserMaster approver, String claimType, String claimNo,
			Boolean specialApproval) {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream(HTML_TEMPLATE);
			prop.load(input);
			String claimTypeMail;
			if (specialApproval.equals(true)) {
				claimTypeMail = prop.getProperty("html.submission.special.middle1");
			} else {
				claimTypeMail = prop.getProperty("html.submission.middle1");
			}

			String body = prop.getProperty("html.submission.header") + approver.getEmployeeName() + claimTypeMail
					+ claimant.getEmployeeName() + " [ Employee Id : " + claimant.getEmployeeId() + " ] on "
					+ new Date() + prop.getProperty("html.submission.middle2") + claimNo
					+ prop.getProperty("html.submission.middle3") + claimType
					+ prop.getProperty("html.submission.statement") + prop.getProperty("html.submission.bottom");
			if(Arrays.asList(env.getActiveProfiles()).contains("prod"))
				mailConfig.send(claimant.getMail(), approver.getMail(), MAIL_NOTIFICATION, body);
			else
				mailConfig.send("sandeep.patil@marlabs.com", TO_SEND, MAIL_NOTIFICATION, body);
		} catch (IOException | MessagingException e) {
			LOG.debug("----------Exception in MailSender while sending mail----------------", e);
		}
	}
}
