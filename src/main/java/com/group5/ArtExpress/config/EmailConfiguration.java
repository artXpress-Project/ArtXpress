//package com.group5.ArtExpress.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//@Configuration
//public class  EmailConfiguration {
//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Value("${spring.mail.port}")
//    private int port;
//
//    @Value("${spring.mail.username}")
//    private String username;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        mailSender.setUsername("ikennajames03@gmail.com");
//        mailSender.setPassword("awxz kcbg zmdp exgg");
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.smtp.starttls.enable", true);
//        props.put("mail.smtp.auth", true);
//        props.put("mail.smtp.timeout", "5000");
//        props.put("mail.smtp.connectiontimeout", "5000");
//        props.put("mail.smtp.writetimeout", "5000");
//        props.put("mail.smtp.ssl.enable", true);
//        return mailSender;
//    }
//}
