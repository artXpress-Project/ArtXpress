package com.group5.ArtExpress.emailService;

public interface EmailService {
    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);
}
