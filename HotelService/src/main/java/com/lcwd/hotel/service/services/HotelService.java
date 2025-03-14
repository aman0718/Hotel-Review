package com.lcwd.hotel.service.services;

import java.util.List;

import com.lcwd.hotel.service.entities.Hotel;

public interface HotelService {

    public List<Hotel> getAllHotels();

    public Hotel getHotelById(String hotelId);

    public Hotel addHotel(Hotel hotel);

}
