package com.nevexis.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevexis.entities.City;
import com.nevexis.entities.Transition;

@Service
public class UserService {
	@Autowired
	private DBService dbService;

	public List<City> citiesPossibleToGetOn() {
		List<Transition> allTransitions = dbService.getAllTransitions();
		List<City> allCities = dbService.getAllCities();

		Set<String> endPointCities = new HashSet<String>(50);
		allTransitions.stream().forEach(transition -> endPointCities.add(transition.getTrip().getEndPoint()));

		List<City> result = allCities.stream().filter(city -> !endPointCities.contains(city.getName()))
				.collect(Collectors.toList());

		return result;
	}

	public List<City> accesibleCitiesFromLocation(City fromCity) {
		List<Transition> allTransitions = dbService.getAllTransitions();
		List<Transition> startingTransitions = allTransitions.stream().parallel()
				.filter(transition -> transition.getCity().getName().equals(fromCity.getName()))
				.collect(Collectors.toList());

		List<City> result = new ArrayList<City>(50);

		startingTransitions.stream().parallel()
				.forEach(startingTransition -> allTransitions.stream()
						.filter(transition -> transition.getTrip().getId().equals(startingTransition.getTrip().getId())
								&& transition.getOrderColumn() > startingTransition.getOrderColumn())
						.forEach(tran -> result.add(tran.getCity())));

		return result;
	}
}
