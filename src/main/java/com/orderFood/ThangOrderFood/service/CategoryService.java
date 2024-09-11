package com.orderFood.ThangOrderFood.service;

import com.orderFood.ThangOrderFood.model.Category;

import java.util.List;

public interface CategoryService {


    public Category findCategoryById(Long id) throws Exception;

    public Category createCategory(String name,Long userId) throws Exception;

    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception;


}
