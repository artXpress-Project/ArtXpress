package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.dto.requestDto.CollectorRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.dto.requestDto.LogoutRequest;
import com.group5.ArtExpress.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/collector")
public class CollectorController {
    @Autowired
    private CollectorService collectorService;

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



}
