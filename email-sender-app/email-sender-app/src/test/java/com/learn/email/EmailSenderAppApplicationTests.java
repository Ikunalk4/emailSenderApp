package com.learn.email;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.email.services.EmailService;

@SpringBootTest
class EmailSenderAppApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private EmailService emailService;
	
	@Test
	void emailSendTest() {
		System.out.println("sending email...");
		emailService.sendEmail("abc@gmail.com", "Email from Spring Boot", "This email has send using spring boot "
				+ "while create email service.");
	}
	
	@Test
	void sendEmailWithFile() {
		System.out.println("file sent trying..");
		emailService.sendEmailWithFile("abc@gmail.com",
		"Email from Spring with file", "This email contains file",
		new File("C:\\Users\\Downloads\\abc.png"));
		System.out.println("file sent..");
	}

}
