package com.group5.ArtExpress.service;


import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.emailService.EmailVerificationService;
import com.group5.ArtExpress.confirmation.CollectorConfirmation;
import com.group5.ArtExpress.customException.TokenWasNotFoundException;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.dto.requestDto.CollectorRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.repository.CollectorConfirmationRepo;
import com.group5.ArtExpress.repository.CollectorRepo;
import com.group5.ArtExpress.repository.LogoutRequest;
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
        emailVerificationService.ifCollectorEmailAlreadyExist(collectorRequest.getEmail());
        emailVerificationService.verifyEmailFormat(collectorRequest.getEmail());
        Collector collector = modelMapper.map(collectorRequest, Collector.class);
//        String encodedPassword = passwordEncoder.encode(collectorRequest.getPassword());
//        collector.setPassword(encodedPassword);
        collector.setEnabled(false);
        collector.setDateTime(LocalDateTime.now());
        Collector collects = collectorRepo.save(collector);


        CollectorConfirmation confirmation = new CollectorConfirmation(collects);
        collectorConfirmationRepo.save(confirmation);

        emailService.sendHtmlEmailWithEmbeddedFilesToCollector(collector.getFirstName(),collector.getEmail(),confirmation.getToken());
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
    public MessageResponse login(LoginRequest loginRequest) {
        Collector collector = collectorRepo.findByEmailIgnoreCase(loginRequest.getEmail());
        if(collector != null && collector.getPassword().equals(loginRequest.getPassword())){
            if(collector.isEnabled()) {
                collector.setLocked(false);
                collectorRepo.save(collector);
                return new MessageResponse("Login successful. \n", 200);
            }
            else return new MessageResponse("Account not verified. Please verify your email.\n", 401);
        }
        else return new MessageResponse("Login Unsuccessful, Account does not exist.\n", 401);
    }

    @Override
    public MessageResponse logout(LogoutRequest logoutRequest) {
       Collector collector = collectorRepo.findByEmailIgnoreCase(logoutRequest.getEmail());
        if(collector.isEnabled()) {
            collector.setLocked(true);
            collectorRepo.save(collector);
            return new MessageResponse("Logout successful",
                    200);
        }else return new MessageResponse( "message: " + "Artist is not logged in yet",
                401);

    }


}
