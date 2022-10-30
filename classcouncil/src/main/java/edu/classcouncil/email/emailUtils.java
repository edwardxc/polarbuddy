package edu.classcouncil.email;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * 
 * @author Cheng Xing
 *
 */
public class emailUtils {
	Properties properties;
	Session session;
	MimeMessage mimeMessage;

	String username;
	String password;
	final String HOSTNAME = "smtp.office365.com";
	final String STARTTLS_PORT = "587";
	boolean STARTTLS = true;
	boolean AUTH = true;
	String fromAddress;
	String toAddress;
	static String EmailSubject;
	static String EmailBody;

	String bowdoinAddress;
	String bowdoinPassword;
	String personalAddress;
	String personalPassword;

	public static void main(String args[]) throws MessagingException {
		emailUtils emailSender = new emailUtils();
		// Option 1: using Bowdoin email
		// Option 2: using personal outlook email
		emailSender.init(2);
		setEmailSubject();
		setEmailBody();
		emailSender.sendEmail(EmailSubject, EmailBody);

	}

	public void init(int option) {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("/Users/edwardxc/git/email.txt"));
			bowdoinAddress = reader.readLine();
			bowdoinPassword = reader.readLine();
			personalAddress = reader.readLine();
			personalPassword = reader.readLine();
			reader.close();

		} catch (IOException e) {
			throw new IllegalArgumentException("Fail to read email address and password");
		}

		switch (option) {
		case 1:
			username = bowdoinAddress;
			password = bowdoinPassword;
			toAddress = personalAddress;
			break;
		case 2:
			username = personalAddress;
			password = personalPassword;
			toAddress = bowdoinAddress;
			break;
		}

		fromAddress = username;

	}

	public static void setEmailSubject() {
		EmailSubject = "This is a subject";

	}

	public static void setEmailBody() {
		EmailBody = "This is a body";

	}

	public void sendEmail(String EmailSubject, String EmailBody) {
		try {
			properties = new Properties();
			properties.put("mail.smtp.host", HOSTNAME);
			properties.put("mail.smtp.port", STARTTLS_PORT);
			properties.put("mail.smtp.auth", AUTH);
			properties.put("mail.smtp.starttls.enable", STARTTLS);
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};

			session = Session.getInstance(properties, auth);

			mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(new InternetAddress(fromAddress));
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(toAddress));
			mimeMessage.setSubject(EmailSubject);

			mimeMessage.setText(EmailBody);

			Transport.send(mimeMessage);
			System.out.println("Email Sent from " + username);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}