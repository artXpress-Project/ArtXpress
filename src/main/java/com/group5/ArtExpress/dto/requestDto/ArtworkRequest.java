package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.data.models.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ArtworkRequest {
    private Artist artist;
    private String email;
    private String title;

    private String medium;

    private String size;

    private Long price;

    private Genre genre;
    private int likes;
    private String description;
    private List<Comment> comments;

    private List<String> images;


}
