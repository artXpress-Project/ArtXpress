package com.group5.ArtExpress.service;

import com.group5.ArtExpress.customException.CollectorNotFoundException;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.dto.requestDto.CommentRequest;
import com.group5.ArtExpress.dto.responseDto.CommentResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("Test that enabled and logged in user can camment on artwork")
    public void testComment() {

        Artwork artwork = new Artwork();
        artwork.setArtworkId(1L);

        Comment comment = new Comment();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCollectorId(1L);
        commentRequest.setArtworkId(1L);
        commentRequest.setComment("Artwork mad gan");

        comment.setCollectorId(commentRequest.getCollectorId());
        comment.setArtworkId(artwork.getArtworkId());
        comment.setCommentMessage(comment.getCommentMessage());

        CommentResponse response = commentService.createComment(1L, 1L, commentRequest);

        assertNotNull(response);
        assertThat(commentService.count(), is(1L));
    }

    @Test
    @DisplayName("Test that comment can't be made when collector's id is not found")
    public void testNotFoundCollector() {
        Artwork artwork = new Artwork();
        artwork.setArtworkId(1L);

        Comment comment = new Comment();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCollectorId(11L);
        commentRequest.setArtworkId(1L);
        commentRequest.setComment("Artwork mad gan");

        comment.setCollectorId(commentRequest.getCollectorId());
        comment.setArtworkId(commentRequest.getArtworkId());
        comment.setCommentMessage(comment.getCommentMessage());

        assertThrows(CollectorNotFoundException.class, ()->
                commentService.createComment(1L, 1L, commentRequest));
    }

    @Test
    @DisplayName("Test that comment can't be made when collector's id is not found")
    public void testNotFoundArtwork() {

        Artwork artwork = new Artwork();
        artwork.setArtworkId(1L);

        Comment comment = new Comment();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCollectorId(11L);
        commentRequest.setArtworkId(1L);
        commentRequest.setComment("Artwork mad gan");

        comment.setCollectorId(commentRequest.getCollectorId());
        comment.setArtworkId(commentRequest.getArtworkId());
        comment.setCommentMessage(comment.getCommentMessage());

        assertThrows(CollectorNotFoundException.class, ()->
                commentService.createComment(1L, 1L, commentRequest));
    }
}