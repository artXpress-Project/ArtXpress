package com.group5.ArtExpress.service;


import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.emailService.EmailVerificationService;
import com.group5.ArtExpress.confirmation.CollectorConfirmation;
import com.group5.ArtExpress.customException.TokenWasNotFoundException;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.dto.requestDto.CollectorRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.repository.CollectorConfirmationRepo;
import com.group5.ArtExpress.repository.CollectorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;


public class CollectorServiceImpl implements CollectorService{
    @Autowired
    private EmailService emailService;
    @Autowired
    private CollectorRepo collectorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private CollectorConfirmationRepo collectorConfirmationRepo;
    @Override
    public Collector registerCollector(CollectorRequest collectorRequest) {
        emailVerificationService.ifEmailAlreadyExist(collectorRequest.getEmail());
        emailVerificationService.verifyEmailFormat(collectorRequest.getEmail());
        Collector collector = modelMapper.map(collectorRequest, Collector.class);
//        String encodedPassword = passwordEncoder.encode(collectorRequest.getPassword());
//        collector.setPassword(encodedPassword);
        collector.setEnabled(false);
        collector.setDateTime(LocalDateTime.now());
        Collector collects = collectorRepo.save(collector);


        CollectorConfirmation confirmation = new CollectorConfirmation(collector);
        collectorConfirmationRepo.save(confirmation);

//        emailService.sendHtmlEmailWithEmbeddedFiles(collector.getFirstName(),collector.getEmail(),confirmation.getToken());
        return collects;
    }



    @Override
    public Boolean verifyToken(String token) {
        CollectorConfirmation confirmation = collectorConfirmationRepo.findByToken(token);
        if(confirmation == null) throw new TokenWasNotFoundException("Could not find token");
        Collector collector = collectorRepo.findByEmailIgnoreCase(confirmation.getCollector().getEmail());
        collector.setEnabled(true);
        collectorRepo.save(collector);
        return Boolean.TRUE;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        return null;
    }
}
