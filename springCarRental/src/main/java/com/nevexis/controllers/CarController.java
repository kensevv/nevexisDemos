package com.nevexis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.models.Car;
import com.nevexis.services.CarService;

@RestController
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@GetMapping("/cars/{model}")
	public Car listCar(@PathVariable String model) {
		return carService.findByModel(model);
	}
	
}
