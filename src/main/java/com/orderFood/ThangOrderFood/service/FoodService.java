package com.orderFood.ThangOrderFood.service;

import com.orderFood.ThangOrderFood.model.Category;
import com.orderFood.ThangOrderFood.model.Food;
import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

     void deleteFood(long foodId) throws Exception;

     public List<Food> getRestaurantsFood(long restaurantId,
                                          boolean isVegetarian,
                                          boolean isNonveg,
                                          boolean isSeasonal,
                                          String foodCategory)  ;


     public List<Food> searchFood(String keyword);

     public Food findFoodById(long foodId) throws Exception ;

     public Food updateAvaillbiityStatus(long foodId) throws Exception;


}
