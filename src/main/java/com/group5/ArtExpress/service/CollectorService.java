package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.dto.requestDto.CollectorRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.dto.requestDto.LogoutRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectorService {
    Collector registerCollector(CollectorRequest collectorRequest);
    Boolean verifyToken(String token);

    MessageResponse login(LoginRequest loginRequest);

    MessageResponse logout(LogoutRequest logoutRequest);

    Collector findById(Long collectorId);

    List<Artwork> findArtworkByArtistBusinessName(String businessName);

    List<Artwork> searchArtwork(String genre);

    List<Artwork> findAllArtwork();




}
