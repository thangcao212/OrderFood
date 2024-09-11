package com.orderFood.ThangOrderFood.controller;

import com.orderFood.ThangOrderFood.model.IngredientCategory;
import com.orderFood.ThangOrderFood.model.IngredientsItem;
import com.orderFood.ThangOrderFood.request.IngredientCategoryRequest;
import com.orderFood.ThangOrderFood.request.IngredientRequest;
import com.orderFood.ThangOrderFood.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req

    ) throws Exception {

        IngredientCategory item= ingredientService.create(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping("/category/ingredientItem")
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest req

    ) throws Exception {

        IngredientsItem item= ingredientService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }


    @PutMapping ("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long id

    ) throws Exception {

        IngredientsItem item= ingredientService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping ("/restaurant/{id}")
    public ResponseEntity <List<IngredientsItem>> getRestaurantIngredient(
            @PathVariable Long id

    ) throws Exception {

        List<IngredientsItem> item= ingredientService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping ("/restaurant/{id}/category")
    public ResponseEntity <List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id

    ) throws Exception {

        List<IngredientCategory> item= ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }





}
