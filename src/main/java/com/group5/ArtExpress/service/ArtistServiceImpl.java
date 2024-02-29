package com.group5.ArtExpress.service;

<<<<<<< HEAD
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

import static com.group5.ArtExpress.utils.Mapper.map;

public class ArtistServiceImpl implements ArtistService{


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private ArtistConfirmationRepo artistConfirmationRepo;

    @Autowired
    private EmailVerificationService emailVerificationService;
    @Override
    public Artist register(ArtistRequest request) {
        emailVerificationService.verifyEmailFormat(request.getEmail());
        emailVerificationService.ifEmailAlreadyExist(request.getEmail());
        Artist artist = modelMapper.map(request, Artist.class);
        map(request, artist);
        Artist newArtist = artistRepo.save(artist);
        ArtistConfirmation artistConfirmation = new ArtistConfirmation(artist);
        artistConfirmationRepo.save(artistConfirmation);
        return newArtist;
=======

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Override
    public Artist register(ArtistRequest request) {
        return null;
>>>>>>> 182ff3f188c343322988404bc00dac423f66048e
    }

    @Override
    public Boolean verifyToken(String token) {
<<<<<<< HEAD
        ArtistConfirmation artistConfirmation = artistConfirmationRepo.findByToken(token);
        if(artistConfirmation == null) throw new TokenWasNotFoundException("Could not find token");
        Artist artist = artistRepo.findByEmailIgnoreCase(artistConfirmation.getArtist().getEmail());
        artist.setEnabled(true);
        artistRepo.save(artist);
        return Boolean.TRUE;
=======
        return null;
>>>>>>> 182ff3f188c343322988404bc00dac423f66048e
    }

    @Override
    public String login(LoginRequest loginRequest) {
        return null;
    }
<<<<<<< HEAD
=======

>>>>>>> 182ff3f188c343322988404bc00dac423f66048e
}
