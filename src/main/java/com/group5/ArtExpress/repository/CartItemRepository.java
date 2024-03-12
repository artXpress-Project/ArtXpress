package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
