package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.dto.requestDto.*;
import com.group5.ArtExpress.dto.responseDto.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ArtistService {
    Artist register(ArtistRequest request);
    Boolean verifyToken(String token);

    MessageResponse login(LoginRequest loginRequest);

    Artist findArtistById(Long id);

//    UploadArtResponse uploadArt(UploadArtRequest uploadArtRequest);
    MessageResponse logout(LogoutRequest logoutRequest);

//    UpdateArtworkResponse updateUpload(Long artworkId, UpdateUploadRequest updateUploadRequest);
//
//    UpdateUploadResponse getArtwork(long l);

    Artwork uploadArtworkByAnArtist(ArtworkRequest request, Artist artist);

    Artwork updateArtworkByAnArtist(Long id,ArtworkRequest update);

    MessageResponse deleteArtworkByAnArtist(Long artWorkId);


    Artwork findArtWorkByArtist(Long artistId);

    UpdateUploadResponse getArtwork(long l);

    Artwork findArtworkById(Long artworkId);

    ReplyCommentResponse replyComment(long l, ReplyCommentRequest replyComment);

}
