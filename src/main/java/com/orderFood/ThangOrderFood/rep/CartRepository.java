package com.orderFood.ThangOrderFood.rep;

import com.orderFood.ThangOrderFood.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
