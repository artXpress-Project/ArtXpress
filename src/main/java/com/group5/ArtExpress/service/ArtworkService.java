package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Genre;
import com.group5.ArtExpress.dto.requestDto.ArtworkRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ArtworkService {
    Artwork uploadArtwork(ArtworkRequest request, Artist artist);

    Artwork updateArtwork(Long id,ArtworkRequest update);

    MessageResponse deleteArtwork(Long artWorkId);

    Artwork findArtWorkByArtist(Long artistId);

    Artwork findArtWorkById(Long id);

    List<Artwork> searchArtwork(String genre);

    List<Artwork> findAllArtwork();

    List<Artwork> findArtworkByArtistBusinessName(String businessName);











}
