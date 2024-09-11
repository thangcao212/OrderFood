package com.orderFood.ThangOrderFood.service.impl;

import com.orderFood.ThangOrderFood.model.Category;
import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.rep.CategoryRepository;
import com.orderFood.ThangOrderFood.service.CategoryService;
import com.orderFood.ThangOrderFood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isEmpty()){
            throw new Exception("Category not found");
        }
        return category.get();
    }

    @Override
    public Category createCategory(String name, Long userId) throws Exception {

       Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
       Category category = new Category();
       category.setName(name);
       category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.getRestaurantByUserId(restaurantId);

        return categoryRepository.findByRestaurantId(restaurant.getId());
    }
}
