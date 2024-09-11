package com.orderFood.ThangOrderFood.rep;

import com.orderFood.ThangOrderFood.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {


}
