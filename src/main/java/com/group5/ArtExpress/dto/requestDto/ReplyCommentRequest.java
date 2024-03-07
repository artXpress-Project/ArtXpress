package com.group5.ArtExpress.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyCommentRequest {
    private Long commentId;
    private String commentReply;
}
