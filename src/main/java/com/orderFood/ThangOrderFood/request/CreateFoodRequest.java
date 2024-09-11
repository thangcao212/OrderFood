package com.orderFood.ThangOrderFood.request;

import com.orderFood.ThangOrderFood.model.Category;
import com.orderFood.ThangOrderFood.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarin;

    private boolean  sesssional;

    private List<IngredientsItem> ingredients;

}
