package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
