package com.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
public class EmailController {
    @RequestMapping(value = "/sendemail")
    public String sendEmail() throws AddressException, MessagingException, IOException {
        sendmail();
        return "Email sent successfully";
    }

    private void sendmail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("misa30122@gmail.com", "contrasena");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("javierrazzzo@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("aguilar.misael@uabc.edu.mx"));
        msg.setSubject("Tutorials point email");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("C:\\Users\\Misael\\IdeaProjects\\Mail\\src\\main\\resources\\static\\mail.jpg");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }


}