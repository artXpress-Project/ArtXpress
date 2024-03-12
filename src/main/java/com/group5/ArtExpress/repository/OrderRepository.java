package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByCollectorId(Long collectorId);

    List<Order> findOrderByArtistId(Long artistId);


}
