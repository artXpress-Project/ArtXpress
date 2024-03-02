package com.group5.ArtExpress.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UploadArtResponse {
//    private List<MultipartFile> artWork;
//    private String title;
//    private String genre;
//    private String description;
//    private String artist;
//    private String medium;
//    private String size;
//    private BigDecimal price;
    private String message;
    private Long mediaId;
    private String url;
    private LocalDateTime uploadDateTime;
}
