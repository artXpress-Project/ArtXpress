package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.Order;
import com.group5.ArtExpress.dto.requestDto.OrderRequest;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.service.CartService;
import com.group5.ArtExpress.service.CollectorService;
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
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CollectorService collectorService;

    @PutMapping("/order")
    public ResponseEntity<HttpResponse> createOrder(
            @RequestBody OrderRequest req,
            @RequestHeader Long id
            ) {
        Collector collector = collectorService.findById(id);
        Order order = orderService.createOrder(req, collector);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Order", order))
                        .message("Order created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }


        @GetMapping("/order/user")
        public ResponseEntity<HttpResponse>   getOrderHistory(@RequestHeader Long collectorId) {
            Collector collector = collectorService.findById(collectorId);
            List<Order> order = orderService.getUserOrder(collector.getId());
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


            @DeleteMapping("/order/cancel/{id}")

            public ResponseEntity<HttpResponse> cancelOrder(@PathVariable Long id,
                                                            @RequestHeader Long collectorId){
                Collector collector = collectorService.findById(collectorId);
                orderService.cancelOrder(id);
                return ResponseEntity.created(URI.create("")).body(
                        HttpResponse.builder()
                                .timeStamp(LocalDateTime.now().toString())
                                .data(Map.of("OrderHistory", "canceled"))
                                .message("List of order history")
                                .status(HttpStatus.CREATED)
                                .statusCode(HttpStatus.CREATED.value())
                                .build()
                );

            }

}
