package com.group5.ArtExpress.dto.responseDto;

import com.group5.ArtExpress.data.models.Collector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    private String comment;
    private Collector collector;
}
