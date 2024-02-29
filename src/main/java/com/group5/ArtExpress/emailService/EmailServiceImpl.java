package com.group5.ArtExpress.emailService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    @Value("${verify.host}")
    private String host;

    @Value("${mail.username}")
    private String fromMail;

    @Override
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {
    }
}
