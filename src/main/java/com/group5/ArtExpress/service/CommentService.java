package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.dto.requestDto.CommentRequest;
import com.group5.ArtExpress.dto.requestDto.LikeCommentRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment createComment(CommentRequest commentResponse);

    public Comment findCommentById(Long commentId);

    public Comment likeComment (LikeCommentRequest likeCommentRequest);

    public Comment UnlikeComment (LikeCommentRequest likeCommentRequest);
}
