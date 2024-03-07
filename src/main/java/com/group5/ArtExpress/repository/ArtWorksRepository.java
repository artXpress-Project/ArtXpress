package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtWorksRepository extends JpaRepository<Artwork,Long> {
    Artwork findArtworkByArtistId(Long id);

    List<Artwork> findArtworkByArtistBusinessName(String businessName);

//    @Query("SELECT a FROM Artwork a WHERE a.genre = :genre")
    @Query("SELECT a FROM Artwork a JOIN a.genre g WHERE g.genreName = :genreName")
    List<Artwork> findByGenreName(@Param("genreName") String genreName);
}
