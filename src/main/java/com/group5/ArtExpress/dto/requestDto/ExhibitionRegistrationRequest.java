package com.group5.ArtExpress.dto.requestDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExhibitionRegistrationRequest {
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String email;
    private boolean isEnabled;
    private LocalDateTime dateRegistered;
}
