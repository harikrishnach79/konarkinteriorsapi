package in.konarkinteriors.app.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.konarkinteriors.app.bean.ContactUsOnline;
import in.konarkinteriors.app.repository.ContactUsOnlineRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ContactUsOnlineController {

	@Value("${konark.contact.no}")
	private String konarkContact;

	@Value("${konark.email}")
	private String konarkMail;

	@Autowired
	private ContactUsOnlineRepository contactUsOnlineRepository;

	@RequestMapping(value = "/api/contact/online", method = RequestMethod.POST, consumes = "application/json")
	private String saveContactusOnlineRequest(@RequestBody ContactUsOnline reqContactUsOnline)
			throws AddressException, MessagingException, IOException {
		ContactUsOnline contactUsOnline = contactUsOnlineRepository.save(reqContactUsOnline);
		if (contactUsOnline != null) {
			sendmail(contactUsOnline);
			if (contactUsOnline.getId() > 0) {
				return "We will contact you soon, thank you.";
			}
		}
		return "Something Went Wrong, Please Retry";
	}

	private void sendmail(ContactUsOnline contactUsOnline) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("konarktest1@gmail.com", "79812Panda!");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("KonarkInteriors", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contactUsOnline.getEmail()));
		msg.setSubject("Thank you for your submission");
		String header = "Hi " + contactUsOnline.getFullName() + ",<br> Thank you for your submission."
				+ "<br>We thank you for your interest shown in KonarkInteriors.\r\n" + "\r\n"
				+ "<br>Our Relationship Manager, will get in touch  with you shortly.\r\n" + "\r\n"
				+ "<br>Please do take note of our numbers and email-id for future correspondence.\r\n" + "\r\n"
				+ "<br>Contact No :" + konarkContact + "\r\n" + "\r\n" + "<br>EMAIL-ID :" + konarkMail + " \r\n"
				+ "\r\n" + "<br>Thanking you and assuring you the best of our services.\r\n" + "\r\n"
				+ "<br>For more details, please visit konarkinteriors.in.<br>" + "<br>Regards,<br> KonarkInteriors";
		msg.setContent(header, "text/html");
		msg.setSentDate(new Date());
		Transport.send(msg);
	}
}
