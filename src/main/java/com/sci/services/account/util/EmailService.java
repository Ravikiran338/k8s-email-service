/**
 * 
 */
package com.sci.services.account.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author mn259
 *
 */
//@Service
public class EmailService {

	public void sendMail(String toEmail) {
		String username = "mhari.techie@gmail.com";
		String password = "09g81a0505";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message consumeMessage = new MimeMessage(session);
			consumeMessage.setFrom(new InternetAddress(username));
			consumeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			consumeMessage.setSubject("Testing Radiant Digital mail");
			consumeMessage.setText("Dear Radiant Digital," + "\n\n Please do not spam my email!");
			Transport.send(consumeMessage);

			System.out.println("email sent successfully---!!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	}