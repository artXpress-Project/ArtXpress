package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByCollectorId(Long collectorId);
}
