package com.orderFood.ThangOrderFood.rep;

import com.orderFood.ThangOrderFood.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientItemRepository extends JpaRepository<IngredientsItem,Long> {

    List<IngredientsItem> findByRestaurantId(Long id);
}
