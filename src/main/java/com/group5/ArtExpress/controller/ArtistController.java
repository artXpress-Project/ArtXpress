package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Artist;

import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;

import com.group5.ArtExpress.dto.requestDto.LoginRequest;

import com.group5.ArtExpress.dto.requestDto.*;

import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.http.HttpResponse;






import com.group5.ArtExpress.service.ArtistService;
import com.group5.ArtExpress.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtworkService artworkService;


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

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody LoginRequest loginRequest){
        MessageResponse messageResponse = artistService.login(loginRequest);
        try {
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .data(Map.of(messageResponse.getMessage(), messageResponse.getStatusCode()))
                            .build()
            );
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    HttpResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .data(Map.of("error", HttpStatus.UNAUTHORIZED.value()))
                            .status(HttpStatus.UNAUTHORIZED)
                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                            .build()
            );
        }
     }



     @PostMapping("/logout")
     public ResponseEntity<HttpResponse> logout(@RequestBody LogoutRequest logoutRequest){
         MessageResponse messageResponse = artistService.logout(logoutRequest);
            return ResponseEntity.ok().body(
                     HttpResponse.builder()
                             .timeStamp(LocalDateTime.now().toString())
                             .data(Map.of(messageResponse.getMessage(), messageResponse.getStatusCode()))
                             .build()
            );
     }



    @PostMapping("/upload")
    public ResponseEntity<HttpResponse> uploadArtworkByAnArtist(@RequestBody ArtworkRequest artworkRequest,
                                                                @RequestHeader Long id){
        Artist artist = artistService.findArtistById(id);
        Artwork artworks =  artistService.uploadArtworkByAnArtist(artworkRequest,artist);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("upload", artworks))
                        .message("Artwork uploaded successfully")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );

    }

    @PostMapping("/update/{artworkId}")
    public ResponseEntity<HttpResponse> updateArtworkByAnArtist(@PathVariable Long artworkId,
                                                                @RequestBody ArtworkRequest artworkRequest,
                                                                @RequestHeader Long id){
        Artist artist = artistService.findArtistById(id);
        Artwork artwork = artistService.updateArtworkByAnArtist(artworkId,artworkRequest);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("updated", artwork))
                        .message("Artwork updated successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }
    @DeleteMapping("/deleted/{artworkId}")
    public ResponseEntity<HttpResponse> deleteArtwork(@PathVariable Long artworkId
                                                      ){
        MessageResponse artwork = artistService.deleteArtworkByAnArtist(artworkId);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("updated", artwork))
                        .message("deleted successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }
    @GetMapping("/artist")
    public ResponseEntity<HttpResponse> findArtworkByArtistId( @RequestHeader Long id){
        List<Artwork> artwork = artistService.findArtWorkByArtist(id);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("found", artwork))
                        .message("Found successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());



    }

    @PutMapping("/{artworkId}")
    public ResponseEntity<HttpResponse> updateArtworkAvailability(@PathVariable Long artworkId,
                                                                  @RequestHeader Long artistId
                                                                  ) {
        Artist artist = artistService.findArtistById(artistId);
        Artwork artwork = artworkService.updateAvailabilityStatus(artworkId);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("found", artwork))
                        .message("Found successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    }
