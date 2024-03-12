package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.dto.requestDto.CommentRequest;
import com.group5.ArtExpress.dto.responseDto.CommentResponse;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/comment")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/addComment/{artworkId}/{collectorId}")
    public ResponseEntity<HttpResponse> addComment(@RequestBody CommentRequest comment, @PathVariable Long artworkId,@PathVariable Long collectorId){
        CommentResponse comments = commentService.createComment(artworkId,collectorId,comment);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Comment", comments))
                        .message("Comment created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );

    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<HttpResponse> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Comment", new MessageResponse("Deleted Successful", HttpStatus.CREATED.value())))
                        .status(HttpStatus.CREATED)
                        .build());

    }
    @GetMapping
    public ResponseEntity<HttpResponse> findComment(@RequestBody Long commentId){
       Comment comment = commentService.findById(commentId);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("found", comment))
                        .message("Comment" + commentId + "found")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());

    }
}
