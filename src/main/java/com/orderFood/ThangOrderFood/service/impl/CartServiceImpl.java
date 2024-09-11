package com.orderFood.ThangOrderFood.service.impl;

import com.orderFood.ThangOrderFood.model.Cart;
import com.orderFood.ThangOrderFood.model.CartItem;
import com.orderFood.ThangOrderFood.model.Food;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.rep.CartItemRepository;
import com.orderFood.ThangOrderFood.rep.CartRepository;
import com.orderFood.ThangOrderFood.rep.FoodRepository;
import com.orderFood.ThangOrderFood.request.AddCartItemRequest;
import com.orderFood.ThangOrderFood.service.CartService;
import com.orderFood.ThangOrderFood.service.FoodService;
import com.orderFood.ThangOrderFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;



    @Override
    public CartItem addItemtoCart(AddCartItemRequest req, String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        Food food=foodService.findFoodById(req.getFoodId());

        Cart cart=cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem:cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int newQuantity=cartItem.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem=new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedCartItem=cartItemRepository.save(newCartItem);

        cart.getItem().add(savedCartItem);

        return  savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {

       Optional<CartItem> cartItem= cartItemRepository.findById(cartItemId);
       if(cartItem.isEmpty()){
           throw  new Exception("Cart Item not found");
       }

       CartItem item= cartItem.get();
       item.setQuantity(quantity);
       item.setTotalPrice(item.getQuantity()*item.getFood().getPrice());


        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeCartItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItem= cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw  new Exception("Cart Item not found");
        }

         CartItem cartItem1=cartItem.get();

         cart.getItem().remove(cartItem1);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {

       Long total=0L;
       for(CartItem cartItem:cart.getItem()){
           total+=cartItem.getQuantity()*cartItem.getFood().getPrice();

       }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {

      Optional<Cart> cart=cartRepository.findById(id);
      if(cart.isEmpty()){
          throw  new Exception("Cart not found");
      }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
     // User user=userService.findUserByJwtToken(jwt);
     Cart cart= cartRepository.findByCustomerId(userId);
     cart.setTotal(calculateCartTotal(cart));
     return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
     // User user=userService.findUserByJwtToken(jwt);
       Cart cart=cartRepository.findCartByCustomerId(userId);

       cart.getItem().clear();

        return cartRepository.save(cart);
    }
}
