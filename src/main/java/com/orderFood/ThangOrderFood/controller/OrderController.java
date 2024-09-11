package com.orderFood.ThangOrderFood.controller;

import com.orderFood.ThangOrderFood.model.CartItem;
import com.orderFood.ThangOrderFood.model.Order;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.request.AddCartItemRequest;
import com.orderFood.ThangOrderFood.request.OrderRequest;
import com.orderFood.ThangOrderFood.service.OrderService;
import com.orderFood.ThangOrderFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        Order order=orderService.createOrder(req,user);
        return new ResponseEntity<>(order,HttpStatus.OK);

    }

    @GetMapping("/orders/user")
    public ResponseEntity<List<Order>> getOrderHistory(
                                             @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders=orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.OK);

    }



}
