package com.group5.ArtExpress.service;

import com.group5.ArtExpress.customException.NotFoundExceptions;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Cart;
import com.group5.ArtExpress.data.models.CartItem;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.dto.requestDto.AddCartItemRequest;
import com.group5.ArtExpress.repository.CartItemRepository;
import com.group5.ArtExpress.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CollectorService collectorService;

    @Autowired
    private ArtworkService artworkService;
    @Override
    public CartItem addItemToCart(AddCartItemRequest req, Long id) {
        Collector collector = collectorService.findById(id);
        Artwork artwork = artworkService.findArtWorkById(req.getArtworkId());
        Cart cart = cartRepository.findCartByCollectorId(collector.getId());
        for(CartItem cartItem: cart.getItem()){
            if(cartItem.getArtwork().equals(artwork)){
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setArtwork(artwork);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setToolsUsed(req.getToolsUsed());
        newCartItem.setTotalPrice(req.getQuantity()*artwork.getPrice());

        CartItem savedCartItem =  cartItemRepository.save(newCartItem);
        cart.getItem().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new NotFoundExceptions("CartItem not found");
        }
        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getArtwork().getPrice()*quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, Long id) {
        Collector collector = collectorService.findById(id);
        Cart cart = cartRepository.findCartByCollectorId(collector.getId());

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new NotFoundExceptions("CartItem not found");
        }
        CartItem item = cartItem.get();
        cart.getItem().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) {
        long total = 0L;
        for(CartItem cartItem: cart.getItem()){
            total += cartItem.getArtwork().getPrice() * cartItem.getTotalPrice();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(()-> new NotFoundExceptions("CartItem not found"));
    }

    @Override
    public Cart findCartByUserId(Long userId) {
        Cart cart = cartRepository.findCartByCollectorId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) {
        Collector collector = collectorService.findById(userId);
        Cart cart = findCartByUserId(collector.getId());
        cart.getItem().clear();
        return cart;
    }
}
