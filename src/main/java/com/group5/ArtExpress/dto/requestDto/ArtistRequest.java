package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Location;
import com.group5.ArtExpress.data.models.State;
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
    private State state;
    private String lga;
    private String address;
    private List<Artwork> artworks;
    private boolean isEnabled;
    private LocalDateTime dateTime;
    private String city;
    private String Country;
    private List<String> profileImages ;

}
