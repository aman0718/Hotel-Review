package com.lcwd.rating.service.services;

import java.util.List;

import com.lcwd.rating.service.entities.Rating;

public interface RatingService {

    // create
    Rating createRating(Rating rating);

    // get all ratings
    List<Rating> getAllRatings();

    // get rating by user id
    List<Rating> getRatingByUserId(String userId);

    // get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);

}
