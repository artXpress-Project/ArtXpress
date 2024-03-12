package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentRequest {
    private Long id;
    private String message;
    private String comment;


}
