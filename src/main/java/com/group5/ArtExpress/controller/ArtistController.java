package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;
import com.group5.ArtExpress.dto.requestDto.UploadArtRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.dto.responseDto.UploadArtResponse;
import com.group5.ArtExpress.http.HttpResponse;

import com.group5.ArtExpress.repository.LogoutRequest;
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

    @PostMapping("/uploads")
    public ResponseEntity<HttpResponse> uploadArtwork(@RequestBody UploadArtRequest uploadArtRequest) {
         UploadArtResponse uploadArtResponse = artistService.uploadArt(uploadArtRequest);
         try {
             return ResponseEntity.ok().body(
                     HttpResponse.builder()
                             .timeStamp(LocalDateTime.now().toString())
                             .data(Map.of(uploadArtResponse.getMessage(), uploadArtResponse.getStatusCode()))
                             .build()
             );
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                     .body(
                             HttpResponse.builder()
                                     .timeStamp(LocalDateTime.now().toString())
                                     .data(Map.of("Media type unsupported", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                                     .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                                     .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
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
}
