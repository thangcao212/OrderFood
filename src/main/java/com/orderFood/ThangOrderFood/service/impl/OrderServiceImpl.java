package com.orderFood.ThangOrderFood.service.impl;

import com.orderFood.ThangOrderFood.model.*;
import com.orderFood.ThangOrderFood.rep.*;
import com.orderFood.ThangOrderFood.request.OrderRequest;
import com.orderFood.ThangOrderFood.service.CartService;
import com.orderFood.ThangOrderFood.service.OrderService;
import com.orderFood.ThangOrderFood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private RestaurantService restaurantService;
    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {

        Address shippingAddress = order.getDeliveryAddress();

        Address savedShippingAddress = addressRepository.save(shippingAddress);

        if(!user.getAddresses().contains(savedShippingAddress)) {
            user.getAddresses().add(savedShippingAddress);
            userRepository.save(user);
        }

        Restaurant restaurant=restaurantService.findRestaurantById(order.getRestaurantId());

        Order createdOrder=new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setDeliveryAddress(shippingAddress);
        createdOrder.setRestaurant(restaurant);
        createdOrder.setOrderStatus("PENDING");

        Cart cart=cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems=new ArrayList<OrderItem>();
        for(CartItem cartItem:cart.getItem()){
            OrderItem orderItem=new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem=orderItemRepository.save(orderItem);

            orderItems.add(savedOrderItem);
        }
        Long totalPrice= cartService.calculateCartTotal(cart);

        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        Order savedOrder=orderRepository.save(createdOrder);

        restaurant.getOrders().add(savedOrder);

      return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        Order order=findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")



        ){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Invalid order status");
    }

    @Override
    public void calceOrder(Long orderId) throws Exception {

        Order order=findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders= orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus!=null){
           orders= orders.stream().filter(order
                   -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
       Optional<Order> order=orderRepository.findById(orderId);
       if(order.isEmpty()){
           throw new Exception("Order not found");
       }
       return order.get();
    }
}
