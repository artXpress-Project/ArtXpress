package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.ExhibitionEventRegistration;
import com.group5.ArtExpress.dto.requestDto.ExhibitionRegistrationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExhibitionEventService {
    ExhibitionEventRegistration register(ExhibitionRegistrationRequest exhibitionRegistration);
    Boolean verifyToken(String token);

    List<ExhibitionEventRegistration> listOfRegisteredAttendees();

    List<ExhibitionEventRegistration> listOfRegisteredEnabledAttendees();
}
