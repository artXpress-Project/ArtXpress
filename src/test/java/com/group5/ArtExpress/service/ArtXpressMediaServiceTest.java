package com.group5.ArtExpress.service;

import com.group5.ArtExpress.dto.requestDto.UploadArtRequest;
import com.group5.ArtExpress.dto.responseDto.UploadArtResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.group5.ArtExpress.utils.MediaUtils.IMAGE_LOCATION;
import static com.group5.ArtExpress.utils.TestUtils.getTestImage;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtXpressMediaServiceTest {

    @Autowired
    private ArtXpressMediaService mediaService;

    @Test
    public void testThatArtistCanAddMedia() throws IOException {
        MultipartFile file = getTestImage(IMAGE_LOCATION);
        UploadArtRequest uploadArtRequest = new UploadArtRequest();
        assert file != null;
        uploadArtRequest.setArtWork(List.of(file));
        uploadArtRequest.setTitle("Nature");
        uploadArtRequest.setArtist("Tomide");
        uploadArtRequest.setDescription("Nature is Beautiful");
        uploadArtRequest.setMedium("Oil on canvass");
        uploadArtRequest.setSize("45\" x 70\"");
        uploadArtRequest.setPrice(BigDecimal.valueOf(750000));
        uploadArtRequest.setGenre("Nigerian Ancestry");
        uploadArtRequest.setUploadDateTime(LocalDateTime.now());

        UploadArtResponse uploadArtResponse = mediaService.uploadArt(uploadArtRequest);

        assertNotNull(uploadArtResponse);
        assertNotNull(uploadArtResponse.getMessage());
    }
}