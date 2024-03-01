package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.dto.requestDto.CollectorRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface CollectorService {
    Collector registerCollector(CollectorRequest collectorRequest);
    Boolean verifyToken(String token);

    String login(LoginRequest loginRequest);
}
