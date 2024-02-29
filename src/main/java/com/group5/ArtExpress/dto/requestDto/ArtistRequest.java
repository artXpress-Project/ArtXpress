package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Location;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArtistRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String businessName;
    private String bio;
    private String phoneNumber;
    private Location location;
    private List<Artwork> artworks;
    private boolean isEnabled;
    private LocalDateTime dateTime;
}