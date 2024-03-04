package com.group5.ArtExpress.service;




import com.group5.ArtExpress.customException.ArtistNotEnabled;
import com.group5.ArtExpress.customException.ArtistNotFoundException;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Genre;
import com.group5.ArtExpress.dto.requestDto.UploadArtRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;

//import com.group5.ArtExpress.dto.requestDto.SendMailRequest;
import com.group5.ArtExpress.dto.responseDto.SendMailResponse;
//import com.group5.ArtExpress.emailService.BrevoMailService;

import com.group5.ArtExpress.dto.responseDto.UploadArtResponse;
import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.emailService.EmailVerificationService;
import com.group5.ArtExpress.confirmation.ArtistConfirmation;
import com.group5.ArtExpress.customException.TokenWasNotFoundException;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.repository.ArtistConfirmationRepo;
import com.group5.ArtExpress.repository.ArtistRepo;
import com.group5.ArtExpress.repository.ArtworkRepository;
import com.group5.ArtExpress.repository.GenreRepository;
import com.group5.ArtExpress.repository.LogoutRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.group5.ArtExpress.utils.Mapper.map;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Autowired
    private EmailService emailService;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private ArtistConfirmationRepo artistConfirmationRepo;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private GenreRepository genreRepository;

//    @Autowired
//    private BrevoMailService brevoMailService;

    @Override
    public Artist register(ArtistRequest request) {
        emailVerificationService.verifyEmailFormat(request.getEmail());
        emailVerificationService.ifArtistEmailAlreadyExist(request.getEmail());
        Artist artist = modelMapper.map(request, Artist.class);
        map(request, artist);
        Artist newArtist = artistRepo.save(artist);

//        SendMailToNewArtist(request);

        ArtistConfirmation artistConfirmation = new ArtistConfirmation(newArtist);

//        SendMailToNewArtist(request);

        artistConfirmationRepo.save(artistConfirmation);

        emailService.sendSimpleMailMessage(artist.getFirstName(),artist.getEmail(),artistConfirmation.getToken());
        return newArtist;

    }

//    private void SendMailToNewArtist(ArtistRequest request) {
//        SendMailRequest sendMailRequest = new SendMailRequest();
//        sendMailRequest.setHtmlContent("Dear " + request.getFirstName() + "\nYou're welcome on board. " +
//                "\nThank you for signing up on ArtXpress. It promises to be an exciting journey with us!" +
//                "\nKind Regards from the team at ArtXpress");
//        SendMailResponse mailResponse = brevoMailService.sendMail(sendMailRequest);
//    }


    @Override
    public Boolean verifyToken(String token) {

        ArtistConfirmation artistConfirmation = artistConfirmationRepo.findByToken(token);
        System.out.println(artistConfirmation);
        if(artistConfirmation == null) throw new TokenWasNotFoundException("Could not find token");
        Artist artist = artistRepo.findByEmailIgnoreCase(artistConfirmation.getArtist().getEmail());
        artist.setEnabled(true);
        artistRepo.save(artist);
        return Boolean.TRUE;

    }

    @Override
    public MessageResponse login(LoginRequest loginRequest) {
        Artist artist = emailVerificationService.findArtistEmail(loginRequest.getEmail());
        if(artist.getPassword().equals(loginRequest.getPassword())){
            if(artist.isEnabled()) {
                artist.setLocked(false);
                return new MessageResponse("Login successful.",
                                           200);
            }
            else return new MessageResponse("Account not verified. Please verify your email.", 401);
        }
        else return new MessageResponse("Login Unsuccessful, Account does not exist.", 401);
    }

    public UploadArtResponse uploadArt(UploadArtRequest uploadArtRequest) {
        Optional<Artist> foundArtist = artistRepo.findByBusinessName(uploadArtRequest.getArtist());
        if (foundArtist.isEmpty()) throw new ArtistNotFoundException("Artist not found!");

        boolean isUnlocked = !foundArtist.get().isLocked;
        
        if (foundArtist.get().isEnabled() && isUnlocked) {
            Artwork artwork = getArtwork(uploadArtRequest, foundArtist);
            artworkRepository.save(artwork);
            return getUploadArtResponse(artwork);
        }
        throw new ArtistNotEnabled("Not enabled");
    }

    private static UploadArtResponse getUploadArtResponse(Artwork artwork) {
        UploadArtResponse uploadArtResponse = new UploadArtResponse();
        uploadArtResponse.setMessage("Upload successful");
        uploadArtResponse.setStatusCode(200);
        uploadArtResponse.setUploadDateTime(artwork.getUploadDateTime());

        return uploadArtResponse;
    }

    private Artwork getArtwork(UploadArtRequest uploadArtRequest, Optional<Artist> foundArtist) {
        Genre genre = getGenre(uploadArtRequest);
        Artwork artwork = new Artwork();
        artwork.setArtist(foundArtist.get());
        mapArtwork(uploadArtRequest, artwork, genre);
        return artwork;
    }

    private Genre getGenre(UploadArtRequest uploadArtRequest) {
        Genre genre = new Genre();
        genre.setGenreName(uploadArtRequest.getGenre());
        genreRepository.save(genre);
        return genre;
    }

    private static void mapArtwork(UploadArtRequest uploadArtRequest, Artwork artwork, Genre genre) {
        artwork.setTitle(uploadArtRequest.getTitle());
        artwork.setDescription(uploadArtRequest.getDescription());
        artwork.setMedium(uploadArtRequest.getMedium());
        artwork.setPrice(uploadArtRequest.getPrice());
        artwork.setSize(uploadArtRequest.getSize());
        artwork.setGenre(genre);
        artwork.setUploadDateTime(LocalDateTime.now());
        artwork.setImageLinks("");
    }

    public MessageResponse logout(LogoutRequest logoutRequest) {
        Artist artist = emailVerificationService.findArtistEmail(logoutRequest.getEmail());
            if(artist.isEnabled()) {
                artist.setLocked(true);
                artistRepo.save(artist);
                return new MessageResponse("Logout successful",
                                        200);
            }else return new MessageResponse( "message: " + "Artist is not logged in yet",
                                            401);

    }

}
