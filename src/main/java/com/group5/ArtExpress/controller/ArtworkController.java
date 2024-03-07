package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Genre;
import com.group5.ArtExpress.dto.requestDto.ArtworkRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.repository.GenreRepo;
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
@RequestMapping("/api/v1/artwork")
public class ArtworkController {
    @Autowired
    private ArtworkService artworkService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private GenreRepo genreRepo;
    @PostMapping
    public ResponseEntity<HttpResponse> uploadArtwork(@RequestBody ArtworkRequest artworkRequest,
                                                 @RequestHeader Long id){
        Artist artist = artistService.findArtistById(id);
        Artwork artworks = artworkService.uploadArtwork(artworkRequest,artist);
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

    @PutMapping("/{artworkId}")
    public ResponseEntity<HttpResponse> updateArtwork(@PathVariable Long artworkId,
                                                      @RequestBody ArtworkRequest artworkRequest,
                                                        @RequestHeader Long id) {
        Artist artist = artistService.findArtistById(id);

        Artwork updatedArtwork = artworkService.updateArtwork(artworkId, artworkRequest);

        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("updatedArtwork", updatedArtwork))
                        .message("Artwork updated successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @DeleteMapping("/{artworkId}")
    public ResponseEntity<HttpResponse> deleteArtwork(@PathVariable Long artworkId,
                                                      @RequestHeader Long id){
        Artist artist = artistService.findArtistById(id);
        MessageResponse messageResponse = artworkService.deleteArtwork(artworkId);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("DeleteArtwork", messageResponse))
                        .message("Deleted successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());

    }
    @GetMapping("/artist")
    public ResponseEntity<HttpResponse> findArtworkByArtistId( @RequestHeader Long id){
        Artist artist = artistService.findArtistById(id);
        Artwork artwork = artworkService.findArtWorkByArtist(artist.getId());
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("found", artwork))
                        .message("Found successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());



    }
    @GetMapping("/artist/id")
    public ResponseEntity <HttpResponse> getAllUploadedArtwork(@RequestHeader Long id){
        Artist artist = artistService.findArtistById(id);
        List<Artwork> list= artworkService.findAllArtwork();
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("found", list))
                        .message("Found successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());



    }

    @GetMapping("/artist/{businessName}")
    public ResponseEntity<HttpResponse> findArtworkByArtistBusinessName(@PathVariable String businessName) {
       List<Artwork> artwork = artworkService.findArtworkByArtistBusinessName(businessName);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("found", artwork))
                        .message("Found artwork by artist business name successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
    @GetMapping("/artwork/search/")
    public ResponseEntity<HttpResponse> searchArtworkByGenre(@RequestParam("genreName") String genreName) {
        Genre genre = genreRepo.findGenreByGenreName(genreName);
        System.out.println(genreName);
        List<Artwork> artworkList = artworkService.searchArtwork(genre.getGenreName());
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("artworkList", artworkList))
                        .message("Artwork list retrieved successfully for genre: " + genreName)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }





}
