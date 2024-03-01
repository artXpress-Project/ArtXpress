package com.group5.ArtExpress.emailService;

import com.group5.ArtExpress.dto.requestDto.EmailSendingRequest;
import jakarta.mail.MessagingException;

public interface EmailProvider {
    Boolean sendEmail(EmailSendingRequest request)  throws MessagingException ;
    Boolean sendEmail(String email, String htmlContent, String topic)  throws MessagingException;
}
