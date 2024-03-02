package com.group5.ArtExpress.service;

import com.group5.ArtExpress.dto.requestDto.UploadArtRequest;
import com.group5.ArtExpress.dto.responseDto.UploadArtResponse;

public interface ArtXpressMediaService {
    UploadArtResponse uploadArt(UploadArtRequest uploadArtRequest);
}
