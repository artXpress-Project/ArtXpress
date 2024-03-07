package com.group5.ArtExpress.service;

import com.group5.ArtExpress.customException.ActionForbiddenAttempt;
import com.group5.ArtExpress.customException.IdNotFoundException;
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

import static com.group5.ArtExpress.utils.Mapper.mapComment;

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

        checkCollectorStatus(commentRequest, foundCollector, foundArtwork);

        CommentResponse response = new CommentResponse();
      response.setComment(commentRequest.getComment());

      return response;
    }

    private void checkCollectorStatus(CommentRequest commentRequest, Collector foundCollector, Artwork foundArtwork) {
        boolean isUnlockedCollector = foundCollector.isLocked();
        if (foundCollector.isEnabled() && isUnlockedCollector) {
            Comment newComment = mapComment(commentRequest, foundCollector, foundArtwork);

            commentRepo.save(newComment);
        }
        else throw new ActionForbiddenAttempt("Unauthorized action");
    }

    @Override
    public Comment likeComment(LikeCommentRequest likeCommentRequest) {
        return null;
    }

    @Override
    public Comment UnlikeComment(LikeCommentRequest likeCommentRequest) {
        return null;
    }

    @Override
    public Long count() {
        return commentRepo.count();
    }

    @Override
    public Comment findById(long commentId) {
        Comment foundComment;
        foundComment = commentRepo.findById(commentId)
                .orElseThrow(()-> new IdNotFoundException("Comment with " + commentId + " was not found"));
        return foundComment;
    }

    @Override
    public void save(Comment foundComment) {
        commentRepo.save(foundComment);
    }
}
