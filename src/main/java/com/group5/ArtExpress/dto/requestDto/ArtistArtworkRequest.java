package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Genre;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ArtistArtworkRequest {
    private Artist artist;

    private String title;

    private String medium;

    private String size;

    private BigDecimal price;

    private Genre genre;

    private String description;
    private String email;

    private List<String> images;
}
