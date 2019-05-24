package in.konarkinteriors.app.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.konarkinteriors.app.bean.ServiceRequest;
import in.konarkinteriors.app.repository.ServiceRequestsRepository;
import in.konarkinteriors.app.service.SlotService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ServiceRequestController {

	@Autowired
	private ServiceRequestsRepository serviceRequestsRepository;

	@Autowired
	private SlotService slotService;

	@RequestMapping(value = "/saveservicerequest", method = RequestMethod.POST, consumes = "application/json")
	public int saveServiceRequest(@RequestBody ServiceRequest serviceRequest)
			throws AddressException, MessagingException, IOException {
		if (slotService.checkAvailability(serviceRequest.getDate(), serviceRequest.getSlot())) {
			ServiceRequest savedServiceRequest = serviceRequestsRepository.save(serviceRequest);
			if (savedServiceRequest != null) {
				sendmail(savedServiceRequest);
				if (slotService.updateSlots(serviceRequest.getDate(), serviceRequest.getSlot())) {
					return savedServiceRequest.getReqId();
				} else {
					return -1;
				}
			}
		}
		return -1;
	}

	private void sendmail(ServiceRequest serviceRequest) throws AddressException, MessagingException, IOException {
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

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(serviceRequest.getCustomerEmail()));
		msg.setSubject("Request Confirmation");
		String header = "Hi " + serviceRequest.getCustomerName()
				+ ",<br> Thank you for your request with reference id #" + serviceRequest.getReqId()
				+ ". We’ll send a email when a agent is assigned. If you would like to view the status of your request or make any changes to it, please visit konarkinteriors.in.<br>";
		msg.setContent(header, "text/html");
		msg.setSentDate(new Date());
		/*
		 * MimeBodyPart messageBodyPart = new MimeBodyPart();
		 * messageBodyPart.setContent("Tutorials point email", "text/html");
		 * 
		 * Multipart multipart = new MimeMultipart();
		 * multipart.addBodyPart(messageBodyPart); MimeBodyPart attachPart = new
		 * MimeBodyPart();
		 * 
		 * attachPart.attachFile("/var/tmp/image19.png");
		 * multipart.addBodyPart(attachPart); msg.setContent(multipart);
		 */
		Transport.send(msg);
	}
}
