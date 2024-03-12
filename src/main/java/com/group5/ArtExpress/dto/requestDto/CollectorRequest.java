package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.DeliveryAddress;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<DeliveryAddress> addresses = new ArrayList<>();
}
