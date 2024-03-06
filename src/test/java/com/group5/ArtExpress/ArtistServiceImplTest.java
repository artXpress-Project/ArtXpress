package com.group5.ArtExpress;

import com.group5.ArtExpress.confirmation.ArtistConfirmation;
import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Genre;
import com.group5.ArtExpress.data.models.State;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.dto.requestDto.UpdateUploadRequest;
import com.group5.ArtExpress.dto.requestDto.UploadArtRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.dto.responseDto.UpdateArtworkResponse;
import com.group5.ArtExpress.dto.responseDto.UpdateUploadResponse;
import com.group5.ArtExpress.dto.responseDto.UploadArtResponse;
import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.repository.ArtistConfirmationRepo;
import com.group5.ArtExpress.repository.ArtistRepo;
import com.group5.ArtExpress.service.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
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
        artistRequest.setFirstName("kelvin");
        artistRequest.setLastName("ekene");
        artistRequest.setPassword("kens");
        artistRequest.setEmail("joellegend582@gmail.com");
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

    @Test
    @DisplayName("test that registered and enabled user can login")
    public void testLogin() {
        Artist artist = new Artist();
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

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(artistRequest.getEmail());
        loginRequest.setPassword(artistRequest.getPassword());

        MessageResponse messageResponse = artistService.login(loginRequest);
        assertNotNull(messageResponse);
        assertTrue(artist.isEnabled());
    }

    @Test
    @DisplayName("test that artist can upload artworks")
    public void testUpload() {
        Genre genre = new Genre();
        genre.setGenreName("Nature");

        UploadArtRequest uploadArtRequest = new UploadArtRequest();
        uploadArtRequest.setArtist(artistRequest.getBusinessName());
        uploadArtRequest.setGenre("Nature");
        uploadArtRequest.setTitle("Nature is life");
        uploadArtRequest.setMedium("Palm oil on nylon");
        uploadArtRequest.setPrice(BigDecimal.valueOf(1000000));
        uploadArtRequest.setSize("45x75");
        uploadArtRequest.setUploadDateTime(LocalDateTime.now());
        uploadArtRequest.setDescription("Shoot bird, Chizaram carry am run! Wayray boy.");

        UploadArtResponse uploadArtResponse = artistService.uploadArt(uploadArtRequest);

        assertNotNull(uploadArtResponse);
        assertThat(uploadArtResponse.getMessage()).isEqualTo("Upload successful");
    }

    @Test
    @DisplayName("test that artwork data can be updated")
    public void testUpdate() {
        Genre genre = new Genre();
        genre.setGenreName("Nature");

        UploadArtRequest uploadArtRequest = new UploadArtRequest();
        uploadArtRequest.setArtist(artistRequest.getBusinessName());
        uploadArtRequest.setGenre("Nature");
        uploadArtRequest.setTitle("Nature is life");
        uploadArtRequest.setMedium("Palm oil on nylon");
        uploadArtRequest.setPrice(BigDecimal.valueOf(1000000));
        uploadArtRequest.setSize("45x75");
        uploadArtRequest.setUploadDateTime(LocalDateTime.now());
        uploadArtRequest.setDescription("Shoot bird, Chizaram carry am run! Wayray boy.");

        UploadArtResponse uploadArtResponse = artistService.uploadArt(uploadArtRequest);

        assertNotNull(uploadArtResponse);
        assertThat(uploadArtResponse.getMessage()).isEqualTo("Upload successful");

        UpdateUploadRequest updateUploadRequest = new UpdateUploadRequest();
        updateUploadRequest.setArtist(artistRequest.getBusinessName());
        updateUploadRequest.setGenre("Nature feels good");
        updateUploadRequest.setTitle("Nature is beautiful");
        updateUploadRequest.setMedium("Palm oil on caramel");
        updateUploadRequest.setPrice(BigDecimal.valueOf(2000000));
        updateUploadRequest.setSize("50x80");
//        updateUploadRequest.setUploadDateTime(LocalDateTime.now());
        updateUploadRequest.setDescription("Shoot bird, e mama run! Wayray boy.");

        UpdateArtworkResponse response = artistService.updateUpload(1L, updateUploadRequest);
        assertNotNull(response);

        UpdateUploadResponse updatedArtwork = artistService.getArtwork(1L);
        assertThat(updatedArtwork.getArtist()).isEqualTo(updateUploadRequest.getArtist());
    }
}
