package com.group5.ArtExpress.config;

import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.emailService.EmailServiceImpl;
import com.group5.ArtExpress.service.ArtistService;
import com.group5.ArtExpress.service.ArtistServiceImpl;
import com.group5.ArtExpress.service.CollectorService;
import com.group5.ArtExpress.service.CollectorServiceImpl;
import jakarta.mail.internet.MimeMessage;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

import java.io.InputStream;

@Configuration
public class AppConfigurations {
    @Bean
    public CollectorService collectorService(){
        return new CollectorServiceImpl();
    }
    @Bean
    public ArtistService artistService(){
        return new ArtistServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public EmailService emailService(JavaMailSender emailSender, TemplateEngine templateEngine){
//        return new EmailServiceImpl(emailSender,templateEngine);
//    }

//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        return mailSender;
//    }


}
