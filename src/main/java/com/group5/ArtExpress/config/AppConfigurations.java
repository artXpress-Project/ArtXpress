package com.group5.ArtExpress.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.group5.ArtExpress.service.ArtistService;
import com.group5.ArtExpress.service.ArtistServiceImpl;
import com.group5.ArtExpress.service.CollectorService;
import com.group5.ArtExpress.service.CollectorServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Configuration
public class AppConfigurations {

    @Value("${mail.api.key}")
    private String mailApiKey;

    @Value("${mail.api.url}")
    private String mailServiceUrl;

    @Value("${cloud.api.name}")
    private String cloudApiName;
    @Value("${cloud.api.key}")
    private String cloudApiKey;
    @Value("${cloud.api.secret}")
    private String cloudApiSecret;

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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", getCloudApiName(),
                        "api_key", getCloudApiKey(),
                        "api_secret", getCloudApiSecret()
                )
        );
        return cloudinary;

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
