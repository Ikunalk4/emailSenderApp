package com.learn.email.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
	
	@Value("${spring.mail.username}")
	private String emailId;
	
	private JavaMailSender mailSender;
	
	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String to, String subject, String message) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailMessage.setFrom(emailId);
		mailSender.send(mailMessage);
		log.info("Email has been sent..");
	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailMessage.setFrom(emailId);
		mailSender.send(mailMessage);
		log.info("Email has been sent to multiple person..");

	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			
			helper.setFrom(emailId);
			helper.setTo(to);
			helper.setSubject(subject);
			
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mimeMessage);
			log.info("Email Send with file Success");
		} catch (MessagingException e) {
			System.out.println("e" + e);
			e.printStackTrace();
		}
	}

}
