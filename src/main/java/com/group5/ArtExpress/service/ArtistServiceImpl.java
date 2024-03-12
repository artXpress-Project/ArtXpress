package com.group5.ArtExpress.service;

import com.group5.ArtExpress.customException.*;

import com.group5.ArtExpress.data.models.Artwork;

import com.group5.ArtExpress.data.models.*;

import com.group5.ArtExpress.dto.requestDto.*;
import com.group5.ArtExpress.dto.responseDto.*;

import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.emailService.EmailVerificationService;
import com.group5.ArtExpress.confirmation.ArtistConfirmation;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.repository.*;

import com.group5.ArtExpress.repository.ArtistConfirmationRepo;
import com.group5.ArtExpress.repository.ArtistRepo;
import com.group5.ArtExpress.repository.ArtworkRepository;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private ArtworkService artworkService;

    @Autowired
    private ArtistConfirmationRepo artistConfirmationRepo;


    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private ArtworkRepository artworkRepository;


    @Autowired
    private CommentService commentService;



    @Override
    public Artist register(ArtistRequest request) {
        emailVerificationService.verifyEmailFormat(request.getEmail());
        emailVerificationService.ifArtistEmailAlreadyExist(request.getEmail());
        Artist artist = modelMapper.map(request, Artist.class);
        map(request, artist);
        Artist newArtist = artistRepo.save(artist);

        ArtistConfirmation artistConfirmation = new ArtistConfirmation(newArtist);
        artistConfirmationRepo.save(artistConfirmation);

        emailService.sendSimpleMailMessage(artist.getFirstName(),artist.getEmail(),artistConfirmation.getToken());
        return newArtist;

    }


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
        artist.setLocked(false);
        if(artist.getPassword().equals(loginRequest.getPassword())){
            if(artist.isEnabled() ) {
                artistRepo.save(artist);
                return new MessageResponse("Login successful.",
                                           200);
            }
            else return new MessageResponse("Account not verified. Please verify your email.", 401);
        }
        else return new MessageResponse("Login Unsuccessful, Account does not exist.", 401);
    }

    @Override
    public Artist findArtistById(Long id) {
      return artistRepo.findById(id).orElseThrow(()-> new IdNotFoundException("Id" + " " + id + " " + "Does not Exist"));

    }


    public MessageResponse logout(LogoutRequest logoutRequest) {
        Artist artist = emailVerificationService.findArtistEmail(logoutRequest.getEmail());
        artist.setLocked(true);
            if(artist.isEnabled()) {
                artistRepo.save(artist);
                return new MessageResponse("Logout successful",
                                        200);
            }else return new MessageResponse( "message: " + "Artist is not logged in yet",
                                            401);
    }


    @Override
    public Artwork uploadArtworkByAnArtist(ArtworkRequest request, Artist artist) {
            isArtistValid(request.getEmail());
            return artworkService.uploadArtwork(request, artist);
    }




    @Override
    public Artwork updateArtworkByAnArtist(Long id, ArtworkRequest update) {
        isArtistValid(update.getEmail());
            return artworkService.updateArtwork(id,update);
    }

    private void isArtistValid(String email) {
        Artist artist = artistRepo.findByEmailIgnoreCase(email);
        if(artist == null){
            throw new CouldNotFindEmailException("email does not exist");
        }if(artist.isLocked()){
            throw new LockException("artist is Locked");
        }if(!artist.isEnabled()) {
            throw new NotEnabledException("Artist must be enabled");
        }
    }

    @Override
    public MessageResponse deleteArtworkByAnArtist(Long artWorkId) {
            return artworkService.deleteArtwork(artWorkId);
        }


    @Override
    public List<Artwork> findArtWorkByArtist(Long artistId) {
            return artworkService.findArtWorkByArtist(artistId);
        }



    @Override
    public Artwork findArtworkById(Long artworkId) {
        return artworkRepository.findById(artworkId).
                orElseThrow(()-> new IdNotFoundException("Id " + artworkId + " Does not Exist"));
    }




    @Override
    public ReplyCommentResponse replyComment(long commentId, ReplyCommentRequest replyComment) {
        Comment foundComment = commentService.findById(commentId);

        foundComment.setCommentMessage(replyComment.getCommentReply());
        commentService.save(foundComment);

        ReplyCommentResponse replyCommentResponse = new ReplyCommentResponse();
        replyCommentResponse.setResponseMessage(replyComment.getCommentReply());

        return replyCommentResponse;
    }

    @Override
    public MessageResponse deleteArtist(Long id) {
       Artist artist = artistRepo.findById(id).orElseThrow(()-> new ArtworkNotFoundException("Artist not found"));
        artistRepo.delete(artist);
        return new MessageResponse("Deleted Successfully",  200);
    }

}
