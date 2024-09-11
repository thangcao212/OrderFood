package com.orderFood.ThangOrderFood.service;

import com.orderFood.ThangOrderFood.model.Order;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus ) throws Exception;

    public void calceOrder(Long orderId) throws Exception;

    public List<Order> getUserOrders(Long userId) throws Exception;

    public List<Order> getRestaurantOrders(Long restaurantId,String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;
}
