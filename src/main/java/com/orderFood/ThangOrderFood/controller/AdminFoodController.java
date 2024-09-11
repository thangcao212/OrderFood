package com.orderFood.ThangOrderFood.controller;

import com.orderFood.ThangOrderFood.model.Food;
import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.request.CreateFoodRequest;
import com.orderFood.ThangOrderFood.response.MessageResponse;
import com.orderFood.ThangOrderFood.service.FoodService;
import com.orderFood.ThangOrderFood.service.RestaurantService;
import com.orderFood.ThangOrderFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Food> addFood(@RequestBody CreateFoodRequest food,
                                        @RequestHeader("Authorization") String jwt
                                        )  throws Exception{
        User user =userService.findUserByJwtToken(jwt);
        Restaurant restaurant =restaurantService.findRestaurantById(food.getRestaurantId());
        Food food1=foodService.createFood(food,food.getCategory(),restaurant);

        return new ResponseEntity<>(food1, HttpStatus.CREATED);


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                   @RequestHeader("Authorization") String jwt
    )  throws Exception{
        User user =userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Xoa thanh cong");

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);


    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvaibilityStatus(@PathVariable Long id,
                                        @RequestHeader("Authorization") String jwt
    )  throws Exception{
        User user =userService.findUserByJwtToken(jwt);

        Food food1=foodService.updateAvaillbiityStatus(id);

        return new ResponseEntity<>(food1, HttpStatus.CREATED);


    }



}
