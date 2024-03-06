package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.Comment;
import lombok.Data;

@Data
public class CommentRequest {
    private Comment comment;
    private Long artworkId;
    private Long collectorId;
}
