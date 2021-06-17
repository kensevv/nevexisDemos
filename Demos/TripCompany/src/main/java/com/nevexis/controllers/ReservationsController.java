package com.nevexis.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.entities.City;
import com.nevexis.entities.Reservation;
import com.nevexis.services.DBService;
import com.nevexis.services.UserService;

@RestController
public class ReservationsController {
	@Autowired
	private DBService dbService;
	@Autowired
	private UserService userService;

	@PostMapping("/create/reservation")
	public void createReservation(@RequestParam Long tripId, @RequestParam String fromCity, @RequestParam String toCity,
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate leavingDate,
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate arrivingDate, @RequestParam Integer seatNumber,
			@RequestParam BigDecimal price) {

		Reservation reservation = new Reservation(dbService.getTripById(tripId), dbService.getCityByName(fromCity),
				dbService.getCityByName(toCity), leavingDate, arrivingDate, seatNumber, price);
		dbService.persistReservation(reservation);
	}

	@GetMapping("/trips/citiesWhereICanGetOn")
	public List<City> getCitiesWhereICanGetOn(){
		return userService.citiesPossibleToGetOn();
	}
	
	@PostMapping("/trips/destination")
	public List<City> accessibleCitiesFromLocation(@RequestBody City fromCity){
		return userService.accesibleCitiesFromLocation(fromCity);
	}
}