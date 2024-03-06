package com.group5.ArtExpress.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.group5.ArtExpress.service.*;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
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
    public ExhibitionEventService exhibitionEventService(){
        return new ExhibitionEventServiceImpl();
    }

    @Bean
    public CommentService commentService(){
        return new CommentServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }




}
