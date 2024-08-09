package com.orderFood.ThangOrderFood.service.impl;

import com.orderFood.ThangOrderFood.dto.RestaurantDto;
import com.orderFood.ThangOrderFood.model.Address;
import com.orderFood.ThangOrderFood.model.Restaurant;
import com.orderFood.ThangOrderFood.model.User;
import com.orderFood.ThangOrderFood.rep.AddressRepository;
import com.orderFood.ThangOrderFood.rep.RestaurantRepository;
import com.orderFood.ThangOrderFood.rep.UserRepository;
import com.orderFood.ThangOrderFood.request.RestaurantRequest;
import com.orderFood.ThangOrderFood.service.RestaurantService;
import com.orderFood.ThangOrderFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

   @Autowired
   private RestaurantRepository restaurantRepository;
  @Autowired
   private AddressRepository addressRepository;
  @Autowired
  private UserRepository userRepository;


    @Override
    public Restaurant createRestaurant(RestaurantRequest req, User user) {


        Address address = addressRepository.save(req.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setName(req.getName());
        restaurant.setImages(req.getImages());
       restaurant.setCuisineType(req.getCuisineType());
       restaurant.setDescription(req.getDescription());
       restaurant.setRegistrationDate(LocalDateTime.now());
       restaurant.setOpingHours(req.getOpeningHours());
       restaurant.setOwner(user);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, RestaurantRequest updateReq) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if (updateReq.getCuisineType() != null) {
            restaurant.setCuisineType(updateReq.getCuisineType());
        }
        if (updateReq.getDescription() != null) {
            restaurant.setDescription(updateReq.getDescription());
        }
        if (updateReq.getName() != null) {
            restaurant.setName(updateReq.getName());
        }
        if (updateReq.getImages() != null) {
            restaurant.setImages(updateReq.getImages());
        }
        if (updateReq.getContactInformation() != null) {
            restaurant.setContactInformation(updateReq.getContactInformation());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant =findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {

        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long Id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(Id);
        if (opt.isEmpty()) {
            throw new Exception("Restaurant not found");
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant= restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found with owner id : " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurantId);
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());

        boolean isFavorite = false;
        List<RestaurantDto> favorites=user.getFavorites();
        for(RestaurantDto favorite:favorites){
            if(favorite.getId().equals(restaurantId)){
                isFavorite = true;
                break;
            }

        }
        if(isFavorite){
            favorites.removeIf(favorite-> favorite.getId().equals(restaurantId));
        }else{
            favorites.add(restaurantDto);
        }

        userRepository.save(user);
        return  restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long Id) throws Exception {
        Restaurant restaurant = findRestaurantById(Id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
