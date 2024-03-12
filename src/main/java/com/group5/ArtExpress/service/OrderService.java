package com.group5.ArtExpress.service;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.Order;
import com.group5.ArtExpress.dto.requestDto.OrderRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest order, Collector collector);

    Order updateOrder(Long orderId,String orderStatus);

    void cancelOrder(Long orderId);

    List<Order> getUserOrder(Long collectorId);

    List<Order> getArtistOrder(Long artistId, String orderStatus );

    Order findOrderById(Long id);


}
