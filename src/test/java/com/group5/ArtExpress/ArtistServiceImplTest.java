package com.group5.ArtExpress;

import com.group5.ArtExpress.confirmation.ArtistConfirmation;
import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.State;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.repository.ArtistConfirmationRepo;
import com.group5.ArtExpress.repository.ArtistRepo;
import com.group5.ArtExpress.service.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArtistServiceImplTest {
    @Autowired
    EmailService emailService;


    @Autowired
    ArtistRepo artistRepo;

    @Autowired
    ArtistConfirmationRepo artistConfirmationRepo;

    @Autowired
    ArtistService artistService;

    ArtistRequest artistRequest;

    LoginRequest loginRequest;

    @BeforeEach
    public void tearDown(){
        artistConfirmationRepo.deleteAll();
        artistRepo.deleteAll();
    }

    @BeforeEach
    public void startWith(){
        artistRequest = new ArtistRequest();
        loginRequest = new LoginRequest();
    }

    @Test
    public void canRegisterAnArtist(){
        Artist artist = new Artist();
        artist.setEmail("ikennajames03@gmail.com");
        artistRequest.setFirstName("kelvin");
        artistRequest.setLastName("ekene");
        artistRequest.setPassword("kens");
        artistRequest.setEmail("ikennajames03@gmail.com");
        artistRequest.setBio("errrtttgfd");
        artistRequest.setState(State.ABIA);
        artistRequest.setAddress("NO 98");
        artistRequest.setLga("ghf rhrgfgd sd");
        artistRequest.setBusinessName("ghfyrhdd");
        artistRequest.setPhoneNumber("09567456382");
        ArtistConfirmation confirmation = new ArtistConfirmation(artist);
        emailService.sendHtmlEmailWithEmbeddedFiles(artist.getFirstName(), artist.getEmail(), confirmation.getToken());
        System.out.println(confirmation.getToken());
        System.out.println(artist.isEnabled());
        Artist artists = artistService.register(artistRequest);
        assertNotNull(artists);

    }


}
