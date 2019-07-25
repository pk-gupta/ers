package com.marlabs.rmbs.config;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailConfig {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;

	public void send(String to1, String to2, String to3, String subject, String body) throws MessagingException {
		String from = env.getProperty("mailSender");
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setFrom(new InternetAddress(from));
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true); // true indicates
		String[] to = { to1, to2, to3 }; // multipart message
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true); // true indicates html
		// continue using helper object for more functionalities like adding
		// attachments, etc.
		javaMailSender.send(message);
	}

	public void send(String to1, String to2, String subject, String body) throws MessagingException {
		String from = env.getProperty("mailSender");
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setFrom(new InternetAddress(from));
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		String[] to = { to1, to2 };
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		javaMailSender.send(message);
	}
}
