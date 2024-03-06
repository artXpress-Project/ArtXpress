package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.dto.requestDto.CommentRequest;
import com.group5.ArtExpress.dto.requestDto.LikeCommentRequest;
import com.group5.ArtExpress.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl implements CommentService{
     @Autowired
    private CommentRepo commentRepo;
     @Autowired
     private CollectorService collectorService;
    @Override
    public Comment createComment(CommentRequest commentResponse) {
      Collector collector = collectorService.findCollectorById(commentResponse.getCollectorId());
        return null;
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
