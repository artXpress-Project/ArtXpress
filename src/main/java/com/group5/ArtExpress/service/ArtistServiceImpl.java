package com.group5.ArtExpress.service;




import com.group5.ArtExpress.dto.responseDto.MessageResponse;

import com.group5.ArtExpress.dto.requestDto.SendMailRequest;
import com.group5.ArtExpress.dto.responseDto.SendMailResponse;
import com.group5.ArtExpress.emailService.BrevoMailService;

import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.emailService.EmailVerificationService;
import com.group5.ArtExpress.confirmation.ArtistConfirmation;
import com.group5.ArtExpress.customException.TokenWasNotFoundException;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.repository.ArtistConfirmationRepo;
import com.group5.ArtExpress.repository.ArtistRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    @Autowired
//    private BrevoMailService brevoMailService;
    @Override
    public Artist register(ArtistRequest request) {
        emailVerificationService.verifyEmailFormat(request.getEmail());
        emailVerificationService.ifEmailAlreadyExist(request.getEmail());
        Artist artist = modelMapper.map(request, Artist.class);
        map(request, artist);
        Artist newArtist = artistRepo.save(artist);

//        SendMailToNewArtist(request);

        ArtistConfirmation artistConfirmation = new ArtistConfirmation(newArtist);

//        SendMailToNewArtist(request);

//        ArtistConfirmation artistConfirmation = new ArtistConfirmation(artist);

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
        Artist artist = artistRepo.findByEmailIgnoreCase(loginRequest.getEmail());
        if(artist != null && artist.getPassword().equals(loginRequest.getPassword())){
            if(artist.isEnabled()) return new MessageResponse("Login successful.",200);
            else return new MessageResponse("Account not verified. Please verify your email.", 401);
        }
        else return new MessageResponse("Login Unsuccessful, Account does not exist.", 401);
    }

}
