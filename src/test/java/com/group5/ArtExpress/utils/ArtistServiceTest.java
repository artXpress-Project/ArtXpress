package com.group5.ArtExpress.utils;

import com.group5.ArtExpress.confirmation.ArtistConfirmation;
import com.group5.ArtExpress.customException.CouldNotFindEmailException;
import com.group5.ArtExpress.customException.EmailAlreadyExistException;
import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.State;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.emailService.EmailVerificationService;
import com.group5.ArtExpress.repository.ArtWorksRepository;
import com.group5.ArtExpress.repository.ArtistConfirmationRepo;
import com.group5.ArtExpress.repository.ArtistRepo;
import com.group5.ArtExpress.service.ArtistService;
import com.group5.ArtExpress.service.ArtworkService;
import org.aspectj.bridge.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArtistServiceTest {
    @Autowired
    private ArtistService artistService;

    private ArtistRequest artistRequest;
    private Artist artist;

    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private ArtWorksRepository artWorksRepository;

    @Autowired
    private ArtistConfirmationRepo artistConfirmationRepo;
    @Autowired
     private EmailVerificationService emailVerificationService;
    private LoginRequest loginRequest;

    @BeforeEach
    public void startWith() {
        artistRequest = new ArtistRequest();
        artist = new Artist();
        loginRequest = new LoginRequest();
    }

    @BeforeEach
    public void tearDown() {
        artWorksRepository.deleteAll();
        artistConfirmationRepo.deleteAll();
        artistRepo.deleteAll();

    }

    @Test
    public void artistCanRegister() {
        artistRequest.setPhoneNumber("081496611023");
        artistRequest.setBusinessName("Business");
        artistRequest.setAddress("erfjgjggd");
        artistRequest.setLga("ertdfsfsfs");
        artistRequest.setEmail("ikennajames03@gmail.com");
        artistRequest.setFirstName("John");
        artistRequest.setLastName("smith");
        artistRequest.setPassword("john");
        artistRequest.setArtworks(new ArrayList<>());
        artistRequest.setBio("BIO");
        artistRequest.setDateTime(LocalDateTime.now());
        artistRequest.setState("Anambra");
        artist.setLocked(true);
        Artist newArtist = artistService.register(artistRequest);
        assertNotNull(newArtist);


    }

    @Test
    public void canDeleteArtist() {
        artistRequest.setPhoneNumber("081496611023");
        artistRequest.setBusinessName("Business");
        artistRequest.setAddress("erfjgjggd");
        artistRequest.setLga("ertdfsfsfs");
        artistRequest.setEmail("ikennajames03@gmail.com");
        artistRequest.setFirstName("John");
        artistRequest.setLastName("smith");
        artistRequest.setPassword("john");
        artistRequest.setArtworks(new ArrayList<>());
        artistRequest.setBio("BIO");
        artistRequest.setDateTime(LocalDateTime.now());
        artistRequest.setState("Anambra");
        artist.setLocked(true);
        Artist artist1 = artistService.register(artistRequest);
        assertNotNull(artist1);
        artWorksRepository.deleteAll();
        artistConfirmationRepo.deleteAll();
        MessageResponse messageResponse = artistService.deleteArtist(artist1.getId());
        assertEquals("Deleted Successfully", messageResponse.getMessage());


    }

    @Test
    public void artistCanBeFound() {
        artistRequest.setPhoneNumber("081496611023");
        artistRequest.setBusinessName("Business");
        artistRequest.setAddress("erfjgjggd");
        artistRequest.setLga("ertdfsfsfs");
        artistRequest.setEmail("ikennajames03@gmail.com");
        artistRequest.setFirstName("John");
        artistRequest.setLastName("smith");
        artistRequest.setPassword("john");
        artistRequest.setArtworks(new ArrayList<>());
        artistRequest.setBio("BIO");
        artistRequest.setDateTime(LocalDateTime.now());
        artistRequest.setState("Anambra");
        artist.setLocked(true);
        Artist artist1 = artistService.register(artistRequest);
        assertNotNull(artist1);
        Artist findArtist = artistService.findArtistById(artist1.getId());
        assertEquals(artist1.getId(), findArtist.getId());
        assertNotNull(artist1);

    }

    @Test
    public void artistCanNotLoginIfNotEnabled() {
        artistRequest.setPhoneNumber("081496611023");
        artistRequest.setBusinessName("Business");
        artistRequest.setAddress("erfjgjggd");
        artistRequest.setLga("ertdfsfsfs");
        artistRequest.setEmail("ikennajames03@gmail.com");
        artistRequest.setFirstName("John");
        artistRequest.setLastName("smith");
        artistRequest.setPassword("john");
        artistRequest.setArtworks(new ArrayList<>());
        artistRequest.setBio("BIO");
        artistRequest.setDateTime(LocalDateTime.now());
        artistRequest.setState("Anambra");
        Artist artist1 = artistService.register(artistRequest);
        assertNotNull(artist1);
        loginRequest.setPassword("john");
        loginRequest.setEmail("ikennajames03@gmail.com");
        MessageResponse messageResponse = artistService.login(loginRequest);
        assertEquals("Account not verified. Please verify your email.", messageResponse.getMessage());

    }

    @Test
    public void artistCanNotLoginIfWithWrongPassword() {
        artistRequest.setPhoneNumber("081496611023");
        artistRequest.setBusinessName("Business");
        artistRequest.setAddress("erfjgjggd");
        artistRequest.setLga("ertdfsfsfs");
        artistRequest.setEmail("ikennajames03@gmail.com");
        artistRequest.setFirstName("John");
        artistRequest.setLastName("smith");
        artistRequest.setPassword("john");
        artistRequest.setArtworks(new ArrayList<>());
        artistRequest.setBio("BIO");
        artistRequest.setDateTime(LocalDateTime.now());
        artistRequest.setState("Anambra");
        Artist artist1 = artistService.register(artistRequest);
        assertNotNull(artist1);
        loginRequest.setPassword("johns");
        loginRequest.setEmail("ikennajames03@gmail.com");
        MessageResponse messageResponse = artistService.login(loginRequest);
        assertEquals("Login Unsuccessful, Account does not exist.", messageResponse.getMessage());

    }

    @Test
    public void ExceptionIsThrownWhenWrongPasswordIsUsedToFindEmail() {
        artistRequest.setPhoneNumber("081496611023");
        artistRequest.setBusinessName("Business");
        artistRequest.setAddress("erfjgjggd");
        artistRequest.setLga("ertdfsfsfs");
        artistRequest.setEmail("ikennajames03@gmail.com");
        artistRequest.setFirstName("John");
        artistRequest.setLastName("smith");
        artistRequest.setPassword("john");
        artistRequest.setArtworks(new ArrayList<>());
        artistRequest.setBio("BIO");
        artistRequest.setDateTime(LocalDateTime.now());
        artistRequest.setState("Anambra");
        Artist artist1 = artistService.register(artistRequest);
        assertNotNull(artist1);
        assertThrows(CouldNotFindEmailException.class, () -> emailVerificationService.findArtistEmail("jayson@gmail.com"));

    }

    @Test
    public void ExceptionIsThrownWhenTryingToRegisterMultipleTimesWithTheSameEmail(){
        artistRequest.setPhoneNumber("081496611023");
        artistRequest.setBusinessName("Business");
        artistRequest.setAddress("erfjgjggd");
        artistRequest.setLga("ertdfsfsfs");
        artistRequest.setEmail("ikennajames03@gmail.com");
        artistRequest.setFirstName("John");
        artistRequest.setLastName("smith");
        artistRequest.setPassword("john");
        artistRequest.setArtworks(new ArrayList<>());
        artistRequest.setBio("BIO");
        artistRequest.setDateTime(LocalDateTime.now());
        artistRequest.setState("Anambra");
        Artist artist1 = artistService.register(artistRequest);
        assertNotNull(artist1);
        artistRequest.setPhoneNumber("081486611023");
        artistRequest.setBusinessName("main");
        artistRequest.setAddress("erfjgjggd");
        artistRequest.setLga("ertdfsfsfs");
        artistRequest.setEmail("ikennajames03@gmail.com");
        artistRequest.setFirstName("John");
        artistRequest.setLastName("smith");
        artistRequest.setPassword("john");
        artistRequest.setArtworks(new ArrayList<>());
        artistRequest.setBio("BIO");
        artistRequest.setDateTime(LocalDateTime.now());
        artistRequest.setState("Anambra");
        assertThrows(DataIntegrityViolationException.class, () -> artistService.register(artistRequest));

    }


}
