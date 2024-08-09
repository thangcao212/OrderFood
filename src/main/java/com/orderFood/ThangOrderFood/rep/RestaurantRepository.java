package com.orderFood.ThangOrderFood.rep;

import com.orderFood.ThangOrderFood.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


    @Query("SELECT r FROM Restaurant r WHERE  lower(r.name) LIKE  lower(concat('%',:query,'%'))"+
            "OR lower(r.cuisineType) LIKE lower (concat('%',:query,'%'))")
    List<Restaurant> findBySearchQuery(String query);

    Restaurant findByOwnerId(Long userId);


    Optional<Restaurant> findById(Long id);
}
