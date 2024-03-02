package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.UploadArtRequest;
import com.group5.ArtExpress.dto.responseDto.UploadArtResponse;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.service.ArtXpressMediaService;
import com.group5.ArtExpress.service.ArtXpressMediaServiceImpl;
import com.group5.ArtExpress.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private ArtXpressMediaService artXpressMediaService;

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

    @PostMapping(path = "api/v1/uploads")
    public ResponseEntity<String> uploadArtwork(@ModelAttribute UploadArtRequest uploadArtRequest) {
         try {
             List<MultipartFile> artWorkFiles = uploadArtRequest.getArtWork();

             for (MultipartFile file : artWorkFiles) {
                 // Access file properties such as file.getOriginalFilename(), file.getSize(), etc.
                 // Perform operations like saving the file to the server or processing its content
             }
             artXpressMediaService.uploadArt(uploadArtRequest);
             return ResponseEntity.ok("Art uploaded successfully");
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading art");
         }
//        UploadArtResponse response = artXpressMediaService.uploadArt(uploadArtRequest);
//        return ResponseEntity.
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
}
