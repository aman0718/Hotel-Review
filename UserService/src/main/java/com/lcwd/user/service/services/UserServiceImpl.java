package com.lcwd.user.service.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);

    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found of ID: " + userId));

        // Fetch Rating of user from rating service
        Rating[] ratingsOfUser = restTemplate
                .getForObject("http://RATING-SERVICE/api/v1/ratings/users/" + user.getUserId(), Rating[].class);

        logger.info("{}", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        // Fetch hotel details of each rating
        List<Rating> ratingsList = ratings.stream().map(rating -> {

            // API call to hotel service to get hotel details

            // ResponseEntity<Hotel> hotelEntity = restTemplate
            // .getForEntity("http://HOTELSERVICE/api/v1/hotels/" + rating.getHotelId(),
            // Hotel.class);
            // Hotel hotel = hotelEntity.getBody();

            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            // logger.info("Response status code: {}", hotelEntity.getStatusCode());

            // Set hotel details to rating
            rating.setHotel(hotel);

            // Return the rating
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingsList);

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        // Implement rating of users from rating service
        List<User> users = userRepository.findAll();
        return users;
    }
}
