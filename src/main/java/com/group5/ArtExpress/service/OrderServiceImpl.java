package com.group5.ArtExpress.service;

import com.group5.ArtExpress.customException.InvalidException;
import com.group5.ArtExpress.data.models.*;
import com.group5.ArtExpress.dto.requestDto.OrderRequest;
import com.group5.ArtExpress.repository.AddressRepository;
import com.group5.ArtExpress.repository.CollectorRepo;
import com.group5.ArtExpress.repository.OrderItemRepository;
import com.group5.ArtExpress.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CollectorRepo collectorRepo;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private CartService cartService;
    @Override
    public Order createOrder(OrderRequest orderRequest, Collector collector) {
        DeliveryAddress  deliveryAddress = orderRequest.getDeliveryAddress();
        DeliveryAddress savedAddress = addressRepository.save(deliveryAddress);
        if(!collector.getAddresses().contains(savedAddress)){
            collector.getAddresses().add(savedAddress);
            collectorRepo.save(collector);
        }
        Artist artist = artistService.findArtistById(orderRequest.getArtistId());

        Order createdOrder = new Order();
        createdOrder.setCollector(collector);
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setCreatedAt(new Date());
        createdOrder.setDeliveryAddresses(savedAddress);
        createdOrder.setArtist(artist);

        Cart cart = cartService.findCartByUserId(collector.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem: cart.getItem()){
            OrderItem orderItem = new OrderItem();
            orderItem.setArtwork(cartItem.getArtwork());
            orderItem.setToolsUsed(cartItem.getToolsUsed());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        Long totalPrice = cartService.calculateCartTotals(cart);
        createdOrder.setItems(orderItems);
        createdOrder.setTotalAmount(totalPrice);

        Order savedOrder = orderRepository.save(createdOrder);
        artist.getOrders().add(savedOrder);
        return createdOrder;

    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) {
        Order order = findOrderById(orderId);
        if(orderStatus.equalsIgnoreCase("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING"))
        {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new InvalidException("Please select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = findOrderById(orderId);
        orderRepository.delete(order);

    }

    @Override
    public List<Order> getUserOrder(Long collectorId) {
        return orderRepository.findOrderByCollectorId(collectorId);
    }

    @Override
    public List<Order> getArtistOrder(Long artistId, String orderStatus) {
        List<Order> orders = orderRepository.findOrderByArtistId(artistId);
        if(orderStatus != null){
            orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new InvalidException("Order not found");
        }
        return optionalOrder.get();
    }
    }

