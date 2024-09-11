package com.orderFood.ThangOrderFood.service;

import com.orderFood.ThangOrderFood.model.IngredientCategory;
import com.orderFood.ThangOrderFood.model.IngredientsItem;

import java.util.List;

public interface IngredientService {

    public IngredientCategory create(String name,Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientItem(Long restaurantId,String ingredientName ,Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) ;

    public IngredientsItem updateStock(Long id) throws Exception;


}
