package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.dto.requestDto.CommentRequest;
import com.group5.ArtExpress.dto.requestDto.LikeCommentRequest;
import com.group5.ArtExpress.dto.responseDto.CommentResponse;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    CommentResponse createComment(Long commentId, Long artworkId, CommentRequest commentRequest);

    public Comment likeComment (LikeCommentRequest likeCommentRequest);

    public Comment UnlikeComment (LikeCommentRequest likeCommentRequest);

    Long count();

    Comment findById(Long commentId);

    void save(Comment foundComment);
//    CommentRequest createComment(CommentRequest request, Long artistId);
    void deleteComment(Long commentId);
}
