package com.orderFood.ThangOrderFood.controller;

import com.orderFood.ThangOrderFood.model.Food;
import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.request.CreateFoodRequest;
import com.orderFood.ThangOrderFood.service.FoodService;
import com.orderFood.ThangOrderFood.service.RestaurantService;
import com.orderFood.ThangOrderFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {


    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                        @RequestHeader("Authorization") String jwt
    )  throws Exception{
        User user =userService.findUserByJwtToken(jwt);

        List<Food> food1=foodService.searchFood(name);
        return new ResponseEntity<>(food1, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
                                             @RequestParam boolean vegetarian,
                                              @RequestParam boolean Seasonal,
                                              @RequestParam boolean nonveg,
                                              @RequestParam(required = false) String foodCategory,
                                              @PathVariable Long restaurantId,
                                              @RequestHeader("Authorization") String jwt
    )  throws Exception{
        User user =userService.findUserByJwtToken(jwt);

        List<Food> food1=foodService.getRestaurantsFood(restaurantId,vegetarian,Seasonal,nonveg,foodCategory);
        return new ResponseEntity<>(food1, HttpStatus.OK);
    }


}
