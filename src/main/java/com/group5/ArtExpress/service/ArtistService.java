package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.dto.requestDto.*;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.dto.responseDto.UpdateArtworkResponse;
import com.group5.ArtExpress.dto.responseDto.UpdateUploadResponse;
import com.group5.ArtExpress.dto.responseDto.UploadArtResponse;
import org.springframework.stereotype.Service;

@Service
public interface ArtistService {
    Artist register(ArtistRequest request);
    Boolean verifyToken(String token);

    MessageResponse login(LoginRequest loginRequest);

    UploadArtResponse uploadArt(UploadArtRequest uploadArtRequest);
    MessageResponse logout(LogoutRequest logoutRequest);

    UpdateArtworkResponse updateUpload(Long artworkId, UpdateUploadRequest updateUploadRequest);

    UpdateUploadResponse getArtwork(long l);
}
