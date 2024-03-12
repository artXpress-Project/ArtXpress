package com.group5.ArtExpress.service;

import com.group5.ArtExpress.customException.ActionForbiddenAttempt;
import com.group5.ArtExpress.customException.CommentException;
import com.group5.ArtExpress.customException.IdNotFoundException;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.dto.requestDto.CommentRequest;
import com.group5.ArtExpress.dto.requestDto.LikeCommentRequest;
import com.group5.ArtExpress.dto.responseDto.CommentResponse;
import com.group5.ArtExpress.repository.ArtworkRepository;
import com.group5.ArtExpress.repository.CommentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static com.group5.ArtExpress.utils.Mapper.mapComment;

public class CommentServiceImpl implements CommentService{
     @Autowired
    private CommentRepo commentRepo;
     @Autowired
     private CollectorService collectorService;
     @Autowired
     private ModelMapper modelMapper;


     @Autowired
     private ArtworkService artworkService;
    @Override
    public CommentResponse createComment(Long collectorId, Long artworkId, CommentRequest commentRequest) {
      Collector foundCollector = collectorService.findById(collectorId);
      Artwork foundArtwork = artworkService.findArtWorkById(artworkId);

        checkCollectorStatus(commentRequest, foundCollector, foundArtwork);

        CommentResponse response = new CommentResponse();
      response.setComment(commentRequest.getMessage());

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
    public Comment findById(Long commentId) {
       return commentRepo.findById(commentId)
                .orElseThrow(()-> new IdNotFoundException("Comment with " + commentId + " was not found"));

    }

    @Override
    public void save(Comment foundComment) {
        commentRepo.save(foundComment);
    }

//    @Override
//    public CommentRequest createComment(CommentRequest request,Long artistId) {
//        Artwork artwork = artworkService.findArtWorkById(artistId);
//        Comment comment = modelMapper.map(request,Comment.class );
//        comment.setDateTime(LocalDateTime.now());
//        comment.setArtwork(artwork);
//        Comment savedComment = commentRepo.save(comment);
//        return modelMapper.map(savedComment, CommentRequest.class);
//    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment =findById(commentId);
        commentRepo.delete(comment);

    }
}
