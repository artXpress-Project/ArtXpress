package com.group5.ArtExpress.utils;

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
        location.setAddress(request.getAddress());
        artist.setLocation(location);
        artist.setEnabled(false);
        artist.setDateTime(LocalDateTime.now());
        artist.setArtworks(new ArrayList<>());
    }

    public static Comment mapComment(CommentRequest commentRequest, Collector foundCollector, Artwork foundArtwork) {
        Comment newComment = new Comment();
        newComment.setCollectorId(foundCollector.getCollectorId());
        newComment.setArtworkId(foundArtwork.getArtworkId());
        newComment.setCommentMessage(commentRequest.getComment());
        newComment.setDateTime(LocalDateTime.now());

        return newComment;
    }

}
