package com.orderFood.ThangOrderFood.service.impl;

import com.orderFood.ThangOrderFood.model.IngredientCategory;
import com.orderFood.ThangOrderFood.model.IngredientsItem;
import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.rep.IngredientCategoryRepository;
import com.orderFood.ThangOrderFood.rep.IngredientItemRepository;
import com.orderFood.ThangOrderFood.service.IngredientService;
import com.orderFood.ThangOrderFood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public IngredientCategory create(String name, Long restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);

        IngredientCategory ingredientCategory=new IngredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategoryRepository.save(ingredientCategory);
        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
       Optional<IngredientCategory> ingredientCategory=ingredientCategoryRepository.findById(id);
       if(ingredientCategory.isEmpty()){
           throw new Exception("Ingredient Category not found");
       }
        return ingredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory=findIngredientCategoryById(categoryId);
        IngredientsItem item=new IngredientsItem();
        item.setRestaurant(restaurant);
        item.setName(ingredientName);
        item.setCategory(ingredientCategory);

        IngredientsItem ingredientsItem=ingredientItemRepository.save(item);
         ingredientCategory.getIngredients().add(ingredientsItem);
        return ingredientsItem;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {


        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {

        Optional<IngredientsItem> ingredientsItem=ingredientItemRepository.findById(id);
        if(ingredientsItem.isEmpty()){
            throw new Exception("ingredient not found");
        }
        IngredientsItem item=ingredientsItem.get();
        item.setInStoke(!item.isInStoke());
        return ingredientItemRepository.save(item);
    }
}
