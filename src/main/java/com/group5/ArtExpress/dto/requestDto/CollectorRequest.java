package com.group5.ArtExpress.dto.requestDto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CollectorRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDateTime dateTime;
    private boolean isEnabled;
}
