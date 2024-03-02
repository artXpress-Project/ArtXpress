package com.group5.ArtExpress.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {
    private String message;
    private int statusCode;
}
