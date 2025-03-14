package com.lcwd.hotel.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.hotel.service.entities.Hotel;
import com.lcwd.hotel.service.services.HotelService;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<?> addHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getHotelById(@PathVariable String hotelId) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelId));
    }

    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }
}
