package com.group5.ArtExpress.dto.requestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Data
public class CollectorRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime dateTime;
    private boolean isEnabled;
}
