package com.nevexis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevexis.models.Car;
import com.nevexis.repos.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepo;
	
	public Car findByModel(String model) {
		return carRepo.findByModel(model);
	}
}
