package com.group5.ArtExpress.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;


import java.util.List;

@Data
@Embeddable
public class ArtworkDto {
    private Long id;
    private String title;
    @Column(length = 1000)
    @CollectionTable(name="artwork_images")
    private List<String> images;
    private String description;


}
