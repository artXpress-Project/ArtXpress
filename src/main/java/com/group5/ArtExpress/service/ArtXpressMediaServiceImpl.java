package com.group5.ArtExpress.service;

import com.group5.ArtExpress.dto.requestDto.UploadArtRequest;
import com.group5.ArtExpress.dto.responseDto.UploadArtResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtXpressMediaServiceImpl implements ArtXpressMediaService {

    private final CloudService cloudService;
    @Override
    public UploadArtResponse uploadArt(UploadArtRequest uploadArtRequest) {
        StringBuffer links = new StringBuffer();
        uploadArtRequest.getArtWork().forEach((file) -> {
            String url = cloudService.upload(file);
            links.append(url).append("{}");
        });

        return buildUploadArtResponse(links);
    }

    private UploadArtResponse buildUploadArtResponse(StringBuffer links) {
        UploadArtResponse uploadArtResponse = new UploadArtResponse();
        uploadArtResponse.setMessage("Images Uploaded successfully");
        uploadArtResponse.setUrl(String.valueOf(links));
        return uploadArtResponse;
    }
}
