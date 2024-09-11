package com.orderFood.ThangOrderFood.rep;

import com.orderFood.ThangOrderFood.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


}
