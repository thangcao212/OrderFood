package com.orderFood.ThangOrderFood.controller;

import com.orderFood.ThangOrderFood.model.Order;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.request.OrderRequest;
import com.orderFood.ThangOrderFood.service.OrderService;
import com.orderFood.ThangOrderFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;



    @GetMapping("/orders/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders=orderService.getRestaurantOrders(id,order_status);
        return new ResponseEntity<>(orders,HttpStatus.OK);

    }

    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
          @PathVariable String orderStatus,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        Order orders=orderService.updateOrder(id,orderStatus);
        return new ResponseEntity<>(orders,HttpStatus.OK);

    }
}
