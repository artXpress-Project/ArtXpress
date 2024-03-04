package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.data.models.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UploadArtRequest {
    private String title;
    private String genre;
    private String description;
    private String artist;
    private String medium;
    private String size;
    private BigDecimal price;
    private LocalDateTime uploadDateTime;
}



