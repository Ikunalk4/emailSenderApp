package com.learn.email.services;

import java.io.File;

public interface EmailService {

		void sendEmail(String to, String subject, String message);
		
		void sendEmail(String[] to, String subject, String message);
				
		void sendEmailWithFile(String to, String subject, String message, File file);
}
