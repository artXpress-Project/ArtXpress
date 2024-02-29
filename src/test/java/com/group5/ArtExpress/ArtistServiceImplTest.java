package com.group5.ArtExpress;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.State;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.repository.ArtistConfirmationRepo;
import com.group5.ArtExpress.repository.ArtistRepo;
import com.group5.ArtExpress.service.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ArtistServiceImplTest {

    @Autowired
    ArtistRepo artistRepo;

    @Autowired
    ArtistConfirmationRepo artistConfirmationRepo;

    @Autowired
    ArtistService artistService;

    ArtistRequest artistRequest;

    @BeforeEach
    public void tearDown(){
        artistConfirmationRepo.deleteAll();
        artistRepo.deleteAll();
    }

    @BeforeEach
    public void startWith(){
        artistRequest = new ArtistRequest();
    }

    @Test
    public void canRegisterAnArtist(){
        artistRequest.setFirstName("kelvin");
        artistRequest.setLastName("ekene");
        artistRequest.setPassword("kens");
        artistRequest.setEmail("ekenekelvin@gmail.com");
        artistRequest.setBio("errrtttgfd");
        artistRequest.setState(State.ABIA);
        artistRequest.setAddress("NO 98 ");
        artistRequest.setLga("ghf rhrgfgd sd");
        artistRequest.setBusinessName("ghfyrhdd");
        artistRequest.setPhoneNumber("09567456382");
        Artist artist = artistService.register(artistRequest);
        assertNotNull(artist);
    }

}
