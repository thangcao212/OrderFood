package com.orderFood.ThangOrderFood.service;

import com.orderFood.ThangOrderFood.model.Cart;
import com.orderFood.ThangOrderFood.model.CartItem;
import com.orderFood.ThangOrderFood.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemtoCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeCartItemFromCart(Long cartItemId,String jwt) throws Exception;

    public Long calculateCartTotal(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;


}
