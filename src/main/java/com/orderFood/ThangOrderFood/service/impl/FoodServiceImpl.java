package com.orderFood.ThangOrderFood.service.impl;

import com.orderFood.ThangOrderFood.model.Category;
import com.orderFood.ThangOrderFood.model.Food;
import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.rep.FoodRepository;
import com.orderFood.ThangOrderFood.request.CreateFoodRequest;
import com.orderFood.ThangOrderFood.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;


    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food newFood = new Food();
        newFood.setFoodCategory(category);
        newFood.setRestaurant(restaurant);
        newFood.setDescription(req.getDescription());
        newFood.setPrice(req.getPrice());
        newFood.setImages(req.getImages());
        newFood.setName(req.getName());
        newFood.setVegetarian(req.isVegetarin());
        newFood.setSeasonal(req.isSesssional());
        newFood.setIngredients(req.getIngredients());

        Food savedFood = foodRepository.save(newFood);
        restaurant.getFoods().add(savedFood);
        return savedFood;


    }

    @Override
    public void deleteFood(long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantsFood(long restaurantId,
                                         boolean isVegetarian,
                                         boolean isNonveg,
                                         boolean isSeasonal,
                                         String foodCategory) {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if (isVegetarian) {
            foods=filterByVegetarian(foods,isVegetarian);
        }
        if (isNonveg) {
            foods=filterByNoveg(foods,isNonveg);
        }

        if (isSeasonal) {
            foods=filterBySeasonal(foods,isSeasonal);
        }

        if(foodCategory!=null && !foodCategory.equals("")){
            foods=filerByCategory(foods,foodCategory);
        }
        return List.of();
    }

    private List<Food> filerByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food->{
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNoveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian()==isVegetarian).collect(Collectors.toList());


    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(long foodId) throws Exception {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isEmpty()){
            throw new Exception("Food not exits");
        }
        return food.get();
    }

    @Override
    public Food updateAvaillbiityStatus(long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
