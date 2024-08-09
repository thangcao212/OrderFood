package com.orderFood.ThangOrderFood.request;

import com.orderFood.ThangOrderFood.model.Address;
import com.orderFood.ThangOrderFood.model.ContactInformation;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;



}
