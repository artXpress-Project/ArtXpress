package com.group5.ArtExpress.dto.requestDto;

import lombok.Data;

@Data
public class LikeCommentRequest {
    public Long commentId;
    public Long collectorId;
}
