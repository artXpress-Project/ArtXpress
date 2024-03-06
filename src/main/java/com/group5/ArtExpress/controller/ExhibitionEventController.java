package com.group5.ArtExpress.controller;


import com.group5.ArtExpress.data.models.ExhibitionEventRegistration;
import com.group5.ArtExpress.dto.requestDto.ExhibitionRegistrationRequest;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.service.ExhibitionEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/exhibition")
public class ExhibitionEventController {
    @Autowired
    private ExhibitionEventService exhibitionEventService;

    @PostMapping
    public ResponseEntity<HttpResponse> registerUser(@RequestBody ExhibitionRegistrationRequest request){
        ExhibitionEventRegistration eventRegistration = exhibitionEventService.register(request);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("eventRegistration", eventRegistration))
                        .message("Attendee created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<HttpResponse> confirmAccount(@RequestParam("token") String token){
        boolean isSuccess = exhibitionEventService.verifyToken(token);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("successful", isSuccess))
                        .message("""
                                Account Verified!
                                An email will be sent to you for further Notification or update on the upcoming Event""")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );


    }
    @GetMapping("/findAll")
    public ResponseEntity<HttpResponse> getAllRegisteredUsers(){
        List<ExhibitionEventRegistration> registrationList = exhibitionEventService.listOfRegisteredAttendees();
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("eventRegistrationList", registrationList))
                        .message("List")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );



    }

    @GetMapping("/findAllEnabled")
    public ResponseEntity<HttpResponse> getAllRegisteredEnabledUsers() {
        List<ExhibitionEventRegistration> registrationEnabledList = exhibitionEventService.listOfRegisteredEnabledAttendees();
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("eventRegistrationList", registrationEnabledList))
                        .message("ListOfEnabledAttendees")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

}
