package com.sdp.project.model;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sdp.project.util.AmazonClient;

public class Mail {
	
	public boolean sendOtpMail(String mailTo, String body) {
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("urban2cloud@gmail.com", "Cloud2Urban");
			    }
			});
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("urban2cloud@gmail.com", "Urban2Cloud"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			msg.setSubject("Reset Password");
			msg.setContent(body, "text/html");
			msg.setSentDate(new Date());
			Transport.send(msg);
			
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
}