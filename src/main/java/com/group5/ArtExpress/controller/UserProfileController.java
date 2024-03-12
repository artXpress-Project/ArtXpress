package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.service.ArtistService;
import com.group5.ArtExpress.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    @Autowired
    private CollectorService collectorService;

    @Autowired
    private ArtistService artistService;


    @GetMapping("/collector/profile")
    public ResponseEntity<HttpResponse> findCollectorById(@RequestHeader Long id){
        Collector collector = collectorService.findById(id);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Order", collector))
                        .message("found")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping("/artist/profile")
    public ResponseEntity<HttpResponse> findArtistById(@RequestHeader Long id){
        Artist artist =  artistService.findArtistById(id);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Order", artist))
                        .message("found")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }
}
