package com.nevexis.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nevexis.models.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

	public Car findByModel(String model);
}
