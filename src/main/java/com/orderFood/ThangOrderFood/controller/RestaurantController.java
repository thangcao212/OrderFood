package com.orderFood.ThangOrderFood.controller;

import com.orderFood.ThangOrderFood.dto.RestaurantDto;
import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.request.RestaurantRequest;
import com.orderFood.ThangOrderFood.service.RestaurantService;
import com.orderFood.ThangOrderFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(

            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant=  restaurantService.searchRestaurant(keyword);
        return  new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(

            @RequestHeader("Authorization") String jwt
           ) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant=  restaurantService.getAllRestaurants();
        return  new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=  restaurantService.findRestaurantById(id);
        return  new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> AddToFavorites(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        RestaurantDto restaurant=  restaurantService.addToFavorites(id,user);
        return  new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

}
