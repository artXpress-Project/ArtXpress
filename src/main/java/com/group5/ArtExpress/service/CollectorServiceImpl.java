package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.dto.requestDto.CollectorRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.repository.CollectorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CollectorServiceImpl implements CollectorService{
    @Autowired
    private CollectorRepo collectorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Collector registerArtist(CollectorRequest collectorRequest) {
        Collector collector = new Collector();

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
