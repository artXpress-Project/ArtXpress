package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.dto.requestDto.CommentRequest;
import com.group5.ArtExpress.dto.requestDto.LikeCommentRequest;
import com.group5.ArtExpress.dto.responseDto.CommentResponse;
import com.group5.ArtExpress.repository.CollectorRepo;
import com.group5.ArtExpress.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class CommentServiceImpl implements CommentService{
     @Autowired
    private CommentRepo commentRepo;
     @Autowired
     private CollectorService collectorService;
     @Autowired
     private ArtistService artistService;
    @Override
    public CommentResponse createComment(Long collectorId, Long artworkId, CommentRequest commentRequest) {
      Collector foundCollector = collectorService.findById(collectorId);
      Artwork foundArtwork = artistService.findArtworkById(artworkId);

      Comment newComment = new Comment();
      newComment.setCollectorId(foundCollector.getCollectorId());
      newComment.setArtworkId(foundArtwork.getArtworkId());
      newComment.setCommentMessage(commentRequest.getComment());
      newComment.setDateTime(LocalDateTime.now());

      commentRepo.save(newComment);

      CommentResponse response = new CommentResponse();
      response.setComment(commentRequest.getComment());

      return response;
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return null;
    }

    @Override
    public Comment likeComment(LikeCommentRequest likeCommentRequest) {
        return null;
    }

    @Override
    public Comment UnlikeComment(LikeCommentRequest likeCommentRequest) {
        return null;
    }
}
