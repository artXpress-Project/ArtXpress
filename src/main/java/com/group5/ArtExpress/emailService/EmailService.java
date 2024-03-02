package com.group5.ArtExpress.emailService;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);
    void sendSimpleMailMessage(String name, String to, String token);

   void sendHtmlEmailWithEmbeddedFilesToCollector(String name, String to, String token);

    void sendSimpleCollectorMailMessage(String name, String to, String token);
}
