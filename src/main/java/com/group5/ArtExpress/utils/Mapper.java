package com.group5.ArtExpress.utils;


import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Location;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.ArtworkRequest;

import com.group5.ArtExpress.data.models.*;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.CommentRequest;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Mapper {

    public static void map(ArtistRequest request, Artist artist) {
        Location location = new Location();
        location.setState(request.getState());
        location.setLga(request.getLga());
        location.setCountry(request.getCountry());
        location.setCity(request.getCity());
        location.setAddress(request.getAddress());
        artist.setLocation(location);
        artist.setEnabled(false);
        artist.setProfileImages(request.getProfileImages());
        artist.setLocked(true);
        artist.setDateTime(LocalDateTime.now());
        artist.setArtworks(new ArrayList<>());
    }



    public static Comment mapComment(CommentRequest commentRequest, Collector foundCollector, Artwork foundArtwork) {
        Comment newComment = new Comment();
//        newComment.setCollectorId(foundCollector.getCollectorId());
//        newComment.setArtworkId(foundArtwork.getArtworkId());
        newComment.setCommentMessage(commentRequest.getMessage());
        newComment.setDateTime(LocalDateTime.now());

        return newComment;
    }


}
