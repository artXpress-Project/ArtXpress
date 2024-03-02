package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Media;
import com.group5.ArtExpress.dto.requestDto.UploadArtRequest;
import com.group5.ArtExpress.dto.responseDto.MediaRepository;
import com.group5.ArtExpress.dto.responseDto.UploadArtResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtXpressMediaServiceImpl implements ArtXpressMediaService {

    private final ModelMapper modelMapper;
    private final CloudService cloudService;
    private final MediaRepository mediaRepository;
    @Override
    public UploadArtResponse uploadArt(UploadArtRequest uploadArtRequest) {
        StringBuffer links = new StringBuffer();
        uploadArtRequest.getArtWork().forEach((file) -> {
            String url = cloudService.upload(file);
            links.append(url).append("{}");
        });

//        String url = cloudService.upload();
//        Media media = modelMapper.map(uploadArtRequest, Media.class);
//        media.setUrl(url);
//        Media savedMedia = mediaRepository.save(media);
//        return buildAddMediaResponse(savedMedia);
        return buildAddMediaResponse(links);
    }

    private UploadArtResponse buildAddMediaResponse(StringBuffer links) {
        UploadArtResponse uploadArtResponse = new UploadArtResponse();
        uploadArtResponse.setMessage("Images Uploaded successfully");
        uploadArtResponse.setUrl(String.valueOf(links));
//        uploadArtResponse.setMediaId();
        return uploadArtResponse;
    }
}
