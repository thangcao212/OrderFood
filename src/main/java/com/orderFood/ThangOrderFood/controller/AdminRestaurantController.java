package com.orderFood.ThangOrderFood.controller;

import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.request.RestaurantRequest;
import com.orderFood.ThangOrderFood.response.MessageResponse;
import com.orderFood.ThangOrderFood.service.RestaurantService;
import com.orderFood.ThangOrderFood.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/restaurant")
public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping()
      public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody RestaurantRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=  restaurantService.createRestaurant(req,user);
         return  new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody RestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=  restaurantService.updateRestaurant(id,req);
        return  new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(

            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id);

        MessageResponse ms= new MessageResponse();
        ms.setMessage("Deleted restaurant successfully with id: "+id);
        return  new ResponseEntity<>(ms, HttpStatus.OK);

    }

    @DeleteMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(

            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant= restaurantService.updateRestaurantStatus(id);
        return  new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(

            @RequestHeader("Authorization") String jwt
           ) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant= restaurantService.getRestaurantByUserId(user.getId());
        return  new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

}
