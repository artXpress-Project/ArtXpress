package com.group5.ArtExpress.emailService;

import com.group5.ArtExpress.dto.requestDto.EmailSendingRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor

public class EmailServicesImpl implements EmailProvider{
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public Boolean sendEmail(EmailSendingRequest request) throws MessagingException {
        try{
            MimeMessage message = mailSender.createMimeMessage();


            message.setFrom(new InternetAddress("ikennajames03@gmail.com"));
            message.setRecipients(MimeMessage.RecipientType.TO, request.getReceiverMail());
            message.setSubject(request.getSubject());
            message.setContent(request.getReceiverContent(), "text/html; charset=utf-8");
            mailSender.send(message);
            return true;
        }catch (Exception e){
            System.out.println(e+"     <<<<<<<<<<<<<<<<<");
            System.out.println(e.getMessage()+"     <<<<<<<<<<<<<<<<<");
            return false;
        }
    }

    @Override
    public Boolean sendEmail(String email, String htmlContent, String topic) throws MessagingException {
        try{
            MimeMessage message = mailSender.createMimeMessage();

            message.setFrom(new InternetAddress("internshipcentraluk@gmail.com"));
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject(topic);
            message.setContent(htmlContent, "text/html; charset=utf-8");
            mailSender.send(message);
            return true;
        }catch (Exception e){
            System.out.println(e+"     <<<<<<<<<<<<<<<<<");
            System.out.println(e.getMessage()+"     <<<<<<<<<<<<<<<<<");
            return false;
        }
    }
    }
