package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.Order;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.service.ArtistService;
import com.group5.ArtExpress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/artist")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtistOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ArtistService artistService;


    @GetMapping("/order/artist/")
    public ResponseEntity<HttpResponse> getOrderHistory(
                                                         @RequestParam(required = false) String order_status,
                                                        @RequestHeader Long id) {
        Artist artist = artistService.findArtistById(id);
        List<Order> order = orderService.getArtistOrder(artist.getId(),order_status );
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("OrderHistory", order))
                        .message("List of order history")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<HttpResponse> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String orderStatus,
            @RequestParam(required = false) String order_status,
            @RequestHeader Long artistId

    ){
        Artist artist = artistService.findArtistById(artistId);
        Order order = orderService.updateOrder(orderId,orderStatus);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("OrderHistory", order))
                        .message("List of order history")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


}
