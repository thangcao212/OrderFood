package com.orderFood.ThangOrderFood.request;

import lombok.Data;

@Data
public class IngredientCategoryRequest {

    private String name;

    private Long restaurantId;
}
