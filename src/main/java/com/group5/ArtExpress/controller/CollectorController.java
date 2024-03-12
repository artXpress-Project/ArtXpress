package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.Genre;
import com.group5.ArtExpress.dto.ArtworkDto;
import com.group5.ArtExpress.dto.requestDto.CollectorRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.dto.requestDto.LogoutRequest;
import com.group5.ArtExpress.repository.GenreRepo;
import com.group5.ArtExpress.service.ArtworkService;
import com.group5.ArtExpress.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/collector")
@CrossOrigin(origins = "http://localhost:3000")
public class CollectorController {
    @Autowired
    private CollectorService collectorService;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private ArtworkService artworkService;


    @PostMapping
    public ResponseEntity<HttpResponse> registerUser(@RequestBody CollectorRequest collectorRequest){
        Collector newCollector = collectorService.registerCollector(collectorRequest);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("collector", newCollector))
                        .message("collector created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }


    @GetMapping
    public ResponseEntity<HttpResponse> confirmAccount(@RequestParam("token") String token){
        boolean isSuccess = collectorService.verifyToken(token);
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
        MessageResponse messageResponse = collectorService.login(loginRequest);
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
        MessageResponse messageResponse = collectorService.logout(logoutRequest);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of(messageResponse.getMessage(), messageResponse.getStatusCode()))
                        .build()
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<HttpResponse> getCollectorById(@PathVariable Long id){
      Collector collector = collectorService.findById(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("successful", collector))
                        .message("Collector found successful")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/collector/id")
    public ResponseEntity <HttpResponse> getAllUploadedArtwork(@RequestHeader Long id){
        Collector collector = collectorService.findById(id);
        List<Artwork> list= collectorService.findAllArtwork();
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("found", list))
                        .message("Found successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());



    }
    @GetMapping("/collector/{businessName}")
    public ResponseEntity<HttpResponse> findArtworkByArtistBusinessName(@PathVariable String businessName) {
        List<Artwork> artwork = collectorService.findArtworkByArtistBusinessName(businessName);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("found", artwork))
                        .message("Found artwork by artist business name successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping("/collector/search/{genreName}")
    public ResponseEntity<HttpResponse> searchArtworkByGenre(@PathVariable String genreName) {
        List<Artwork> artworkList = collectorService.searchArtwork(genreName);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("artworkList", artworkList))
                        .message("Artwork list retrieved successfully for genre: " + genreName)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PutMapping("/{id}/add_favourites")
    public ResponseEntity<HttpResponse> addToFavourite(
                            @RequestHeader Long collectorId,
                            @PathVariable Long id

    ){
        Collector collector = collectorService.findById(collectorId);
        ArtworkDto artworkDto = artworkService.addToFavourite(id,collector);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("artworkList",artworkDto))
                        .message("Added successfully for genre: ")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }






}
