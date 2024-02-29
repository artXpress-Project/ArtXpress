package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface ArtistService {
    Artist register(ArtistRequest request);
    Boolean verifyToken(String token);

    String login(LoginRequest loginRequest);
}
