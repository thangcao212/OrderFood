package com.orderFood.ThangOrderFood.controller;

import com.orderFood.ThangOrderFood.model.Category;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.service.CategoryService;
import com.orderFood.ThangOrderFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
   @Autowired
    private CategoryService categoryService;

   @Autowired
   private UserService userService;


   @PostMapping("/admin/category")
   public ResponseEntity<Category> addCategory(@RequestBody Category category,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
       User user =userService.findUserByJwtToken(jwt);

       Category createCategory= categoryService.createCategory(category.getName(), user.getId());
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);

   }


    @GetMapping("/category/restaurant")
    public ResponseEntity <List<Category>> getRestaurantCategory(
                                                                @RequestHeader("Authorization") String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);

        List<Category> Categories= categoryService.findCategoryByRestaurantId(user.getId());
        return new ResponseEntity<>(Categories, HttpStatus.CREATED);

    }


}
