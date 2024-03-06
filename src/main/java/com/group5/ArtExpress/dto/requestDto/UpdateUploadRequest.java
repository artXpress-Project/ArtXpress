package com.group5.ArtExpress.dto.requestDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpdateUploadRequest {
    private String title;
    private String genre;
    private String description;
    private String artist;
    private String medium;
    private String size;
    private String imageLink;
    private BigDecimal price;
//    private LocalDateTime uploadDateTime;
}
