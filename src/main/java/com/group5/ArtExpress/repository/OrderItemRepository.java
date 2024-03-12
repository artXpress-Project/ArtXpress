package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
