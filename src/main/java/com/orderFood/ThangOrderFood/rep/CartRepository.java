package com.orderFood.ThangOrderFood.rep;

import com.orderFood.ThangOrderFood.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    public Cart findByCustomerId(Long customerId);


    public Cart findCartByCustomerId(Long userId);
}
