/**
 * 
 */
package com.email.services.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author mn259
 *
 */
@Service
public class EmailService {
	
   private JavaMailSender javaMailSender;
     
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(String toEmail, String subject, String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailMessage.setFrom("hari.zwinny@gmail.com");
		System.out.println("EMail Sent Successfully!!");
		javaMailSender.send(mailMessage);
		System.out.println("EMail Sent Successfully!!---");
	}
	/*

	public EmailUtil() {

	}

	public void prepareEmail(String emailID) {
		System.out.println("SimpleEmail Start");
		// String smtpHostServer = "smtp.gmail.com";
		// Properties props = System.getProperties();

		final String from = "nagarajumodeboyina@gmail.com";
		final String password = "9247125688";

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// props.put("mail.smtp.host", smtpHostServer);
		// Session session = Session.getInstance(props, null);
		sendEmail(session, emailID, "SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

	*//**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 *//*
	public void sendEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("nagarajumodeboyina@gmail.com", "NoReply-JD"));
			msg.setReplyTo(InternetAddress.parse("no_reply@radiant.digital", false));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("Message is ready");
			Transport.send(msg);
			System.out.println("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/}
