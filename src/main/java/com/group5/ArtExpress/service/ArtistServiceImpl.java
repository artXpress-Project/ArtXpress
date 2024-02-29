package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Override
    public Artist register(ArtistRequest request) {
        return null;
    }

    @Override
    public Boolean verifyToken(String token) {
        return null;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        return null;
    }
}
