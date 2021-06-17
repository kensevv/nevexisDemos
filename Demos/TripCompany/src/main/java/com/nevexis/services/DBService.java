package com.nevexis.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nevexis.entities.City;
import com.nevexis.entities.Reservation;
import com.nevexis.entities.Transition;
import com.nevexis.entities.Trip;

@Service
@Transactional
public class DBService extends BasicService {
	public void persistReservation(Reservation reservation) {
		em.persist(reservation);
	}

	public Trip getTripById(Long id) {
		return em.find(Trip.class, id);
	}

	public List<City> getAllCities(){
		return em.createNamedQuery(NamedQueries.getAllCities, City.class).setMaxResults(100).getResultList();
	}
	public City getCityById(Long id) {
		return em.find(City.class, id);
	}

	public City getCityByName(String name) {
		return em.createNamedQuery(NamedQueries.getCityByName, City.class).setParameter("cityName", name)
				.getSingleResult();
	}
	
	public List<Transition> getAllTransitions(){
		return em.createNamedQuery(NamedQueries.getAllTransitions, Transition.class).setMaxResults(100).getResultList();
	}
}
