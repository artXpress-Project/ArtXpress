package com.group5.ArtExpress.config;

import com.group5.ArtExpress.service.ArtistService;
import com.group5.ArtExpress.service.ArtistServiceImpl;
import com.group5.ArtExpress.service.CollectorService;
import com.group5.ArtExpress.service.CollectorServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
