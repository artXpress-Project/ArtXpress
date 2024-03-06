package com.group5.ArtExpress.service;

import com.group5.ArtExpress.confirmation.ExhibitionConfirmation;
import com.group5.ArtExpress.customException.NoListOfEnabledAttendeesFound;
import com.group5.ArtExpress.customException.TokenWasNotFoundException;
import com.group5.ArtExpress.data.models.ExhibitionEventRegistration;
import com.group5.ArtExpress.dto.requestDto.ExhibitionRegistrationRequest;
import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.emailService.EmailVerificationService;
import com.group5.ArtExpress.repository.ExhibitionConfirmationRepo;
import com.group5.ArtExpress.repository.ExhibitionEventRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionEventServiceImpl implements ExhibitionEventService{
    @Autowired
    private ExhibitionEventRepo exhibitionEventRepo;

    @Autowired
    EmailVerificationService emailVerificationService;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ExhibitionConfirmationRepo exhibitionConfirmationRepo;

    @Autowired
    EmailService emailService;

    @Override
    public ExhibitionEventRegistration register(ExhibitionRegistrationRequest exhibitionRegistration) {
        emailVerificationService.ifExhibitionUserEmailAlreadyExist(exhibitionRegistration.getEmail());
        emailVerificationService.verifyEmailFormat(exhibitionRegistration.getEmail());
        ExhibitionEventRegistration eventRegistration = modelMapper.map(exhibitionRegistration, ExhibitionEventRegistration.class);
        eventRegistration.setEnabled(false);
        eventRegistration.setDateRegistered(LocalDateTime.now());
        ExhibitionEventRegistration registration = exhibitionEventRepo.save(eventRegistration);

        ExhibitionConfirmation exhibitionConfirmation = new ExhibitionConfirmation(registration);
        exhibitionConfirmationRepo.save(exhibitionConfirmation);

        emailService.sendHtmlEmailWithEmbeddedFilesToExhibitionAttendees(registration.getFirstName(),registration.getEmail(),exhibitionConfirmation.getToken());


        return registration;
    }

    @Override
    public Boolean verifyToken(String token) {
        ExhibitionConfirmation exhibitionConfirmation = exhibitionConfirmationRepo.findByToken(token);
        if(exhibitionConfirmation == null) throw new TokenWasNotFoundException("Could not find token");
        ExhibitionEventRegistration registration = exhibitionEventRepo.findByEmailIgnoreCase(exhibitionConfirmation.getExhibitionEventRegistration().getEmail());
        registration.setEnabled(true);
        exhibitionEventRepo.save(registration);
        return Boolean.TRUE;
    }
    @Override
    public List<ExhibitionEventRegistration> listOfRegisteredAttendees() {
        return exhibitionEventRepo.findAll();
    }

    @Override
    public List<ExhibitionEventRegistration> listOfRegisteredEnabledAttendees() {
        List<ExhibitionEventRegistration> list = listOfRegisteredAttendees();
        List<ExhibitionEventRegistration> enabledRegisteredUser = new ArrayList<>();
        for(ExhibitionEventRegistration eventRegistration: list){
            if(eventRegistration.isEnabled()){
                enabledRegisteredUser.add(eventRegistration);
            }
        }
        if(!enabledRegisteredUser.isEmpty()){
            return enabledRegisteredUser;
        }
        throw new NoListOfEnabledAttendeesFound("No enabled attendees found");
    }


}
