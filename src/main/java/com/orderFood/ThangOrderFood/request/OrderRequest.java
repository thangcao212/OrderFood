package com.orderFood.ThangOrderFood.request;

import com.orderFood.ThangOrderFood.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;

}
