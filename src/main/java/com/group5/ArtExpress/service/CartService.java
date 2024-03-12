package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Cart;
import com.group5.ArtExpress.data.models.CartItem;
import com.group5.ArtExpress.data.models.User;
import com.group5.ArtExpress.dto.requestDto.AddCartItemRequest;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, Long id);
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity);

    public Cart removeItemFromCart(Long cartItemId, Long id);

    public Long calculateCartTotals(Cart cart);

    public Cart findCartById(Long id);

    public Cart findCartByUserId(Long userId);

    public Cart clearCart(Long userId);

}
