package com.orderFood.ThangOrderFood.service;

import com.orderFood.ThangOrderFood.dto.RestaurantDto;
import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.request.RestaurantRequest;

import java.util.List;

public interface RestaurantService {


   public Restaurant createRestaurant(RestaurantRequest req, User user);

   public Restaurant updateRestaurant(Long restaurantId, RestaurantRequest req) throws  Exception;

   public void deleteRestaurant(Long restaurantId) throws Exception;

   public List<Restaurant> getAllRestaurants();

   public List<Restaurant> searchRestaurant(String keyword);

   public Restaurant findRestaurantById(Long Id) throws Exception;

   public Restaurant getRestaurantByUserId(Long userId) throws Exception;

   public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

   public Restaurant updateRestaurantStatus(Long Id) throws Exception;




    
}
