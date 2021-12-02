package com.my.pet.spring.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.my.pet.spring.exception.DBException;

import jdbc.DBManager;

@Controller
public class SendEmailsController {
	
	@GetMapping(value = "sendEmails")
	public void sendEmails(HttpServletRequest request, HttpServletResponse response) {
		Logger.getLogger(SendEmailsController.class.getName()).log(Level.INFO, "trying to send emails!");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<List<String>> list = (List<List<String>>) request.getSession().getAttribute("listSession");
		int bp = (Integer) request.getSession().getAttribute("bp");
		int tp = (Integer) request.getSession().getAttribute("tp");
	    
		List<Integer> ids = list.stream().map(s -> Integer.parseInt(s.get(0))).collect(Collectors.toList());
		String range = ids.stream().map(String::valueOf).collect(Collectors.joining(",", "(", ")"));
	    
	    Map<Integer, String> map = new HashMap<>();
	    try {
			map = DBManager.reportIdToMail(range);
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    int counter = 0;
	    StringBuilder presentation = new StringBuilder();
	    for (Integer i : ids) {
	    	presentation.append(map.get(i)).append(": ");
	    	if (counter < bp) {
	    		presentation.append("Congrat's you pass to budget!");
	    	} else if (counter < tp) {
	    		presentation.append("Congrat's you pass to common!");
	    	} else {
	    		presentation.append("Sorry you don't pass!");
	    	}
	    	presentation.append("\n");
	    	counter++;
	    }
	    
		// sending email
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", 465);
        
		Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("testwebappsender@gmail.com", "qwer_1234");
			}
		});
		
		String email = "peterr79@mail.ru";
		String content = presentation.toString();
		
		String curLocale = (String) request.getSession().getAttribute("currentLocale");
		if (curLocale == null) {
			curLocale = request.getServletContext().getInitParameter("javax.servlet.jsp.jstl.fmt.locale");
		}
		String baseName = request.getServletContext().getInitParameter("javax.servlet.jsp.jstl.fmt.localizationContext");
		ResourceBundle rb = ResourceBundle.getBundle(baseName, new Locale(curLocale));
		String result = rb.getString("report.emails.sent.yes");
		
		try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("noreply@mail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("University inspection board");
            BodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart.setContent(content, "text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            message.saveChanges();
            Transport.send(message);
        } catch (Exception ex) {
        	Logger.getLogger(SendEmailsController.class.getName()).log(Level.ERROR, "Can't send messages!", ex);
        	result = rb.getString("report.emails.sent.not");
        }
		
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
