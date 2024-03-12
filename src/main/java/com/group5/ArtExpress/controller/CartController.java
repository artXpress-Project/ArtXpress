package com.group5.ArtExpress.controller;

import com.group5.ArtExpress.data.models.Cart;
import com.group5.ArtExpress.data.models.CartItem;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.dto.requestDto.AddCartItemRequest;
import com.group5.ArtExpress.dto.requestDto.UpdateCartIteRequest;
import com.group5.ArtExpress.http.HttpResponse;
import com.group5.ArtExpress.service.CartService;
import com.group5.ArtExpress.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CollectorService collectorService;

    @PutMapping("/cart/add")
    public ResponseEntity<HttpResponse> addItemToCart(@RequestBody AddCartItemRequest req,
                                                  @RequestHeader Long id){
        CartItem cartItem = cartService.addItemToCart(req, id);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Added", cartItem))
                        .message("CartItem added successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PutMapping("/cartItem/update")
    public ResponseEntity<HttpResponse> updateCartItemQuantity(
                                        @RequestBody UpdateCartIteRequest req,
                                        @RequestHeader Long id){
        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Updated", cartItem))
                        .message("Updated successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());


    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<HttpResponse> removeCartItem(
                                        @PathVariable Long id,
                                        @RequestHeader Long cartItemId
    ){
        Cart cartItem = cartService.removeItemFromCart(cartItemId,id);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Updated", cartItem))
                        .message("CartItem removed successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());

    }


    @PutMapping("/cart/clear")
    public ResponseEntity<HttpResponse> clearCart(
                                    @RequestHeader Long collectorId
    ){
        Cart cartItem = cartService.clearCart(collectorId);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Cleared", cartItem))
                        .message("Cart cleared successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());

    }

    @GetMapping ("/cart")
    public ResponseEntity<HttpResponse> findCollectorCart(
                            @RequestHeader Long collectorId
    ){
        Cart cartItem = cartService.findCartByUserId(collectorId);
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Found", cartItem))
                        .message("CollectorCart found successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }


}
