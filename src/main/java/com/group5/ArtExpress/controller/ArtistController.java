package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {
     @Autowired
    public ArtistService artistService;

     @PostMapping
    public ResponseEntity<HttpResponse> registerArtist(@RequestBody ArtistRequest artistRequest){
         Artist artist = artistService.register(artistRequest);
         return ResponseEntity.created(URI.create("")).body(
                 HttpResponse.builder()
                         .timeStamp(LocalDateTime.now().toString())
                         .data(Map.of("artist", artist))
                         .message("Artist account created")
                         .status(HttpStatus.CREATED)
                         .statusCode(HttpStatus.CREATED.value())
                         .build()
         );
     }

    @GetMapping
    public ResponseEntity<HttpResponse> confirmArtistAccount(@RequestParam("token") String token){
        boolean isSuccess = artistService.verifyToken(token);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("successful", isSuccess))
                        .message("Account Verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }
}
