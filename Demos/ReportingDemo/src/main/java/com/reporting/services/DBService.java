package com.reporting.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.reporting.entities.Persons;

@Service
public class DBService extends BasicService {
	public Long getPersonsCount() {
		return em.createNamedQuery(Queries.getPersonsCount, Long.class).getSingleResult();
	}

	public List<Persons> getPersons(int limitPeronsPerPage, int offset) {
		return em.createNamedQuery(Queries.getPersons, Persons.class).setFirstResult(offset).setMaxResults(limitPeronsPerPage)
				.getResultList();
	}
}
