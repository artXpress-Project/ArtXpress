package com.group5.ArtExpress.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateArtworkResponse {
    private String message;
    private String statusCode;
}
