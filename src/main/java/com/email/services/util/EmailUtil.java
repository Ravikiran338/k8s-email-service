/**
 * 
 */
package com.email.services.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author mn259
 *
 */
public class EmailUtil {
	
	public EmailUtil(){
		
	}

	public void prepareEmail(String emailID) {
		System.out.println("SimpleEmail Start");
		String smtpHostServer = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHostServer);
		Session session = Session.getInstance(props, null);
		sendEmail(session, emailID, "SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
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
}
