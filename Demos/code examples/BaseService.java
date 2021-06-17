package com.frantishex.urbo.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
class BaseService<E> {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Class<E> getParametrizedClass() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<E> at = (Class<E>) pt.getActualTypeArguments()[0];
		return at;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hbl.hblconnect.model.service.BaseService#getAll()
	 */
	public List<E> getAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getParametrizedClass());
		Root<E> rootEntry = cq.from(getParametrizedClass());
		CriteriaQuery<E> all = cq.select(rootEntry);
		TypedQuery<E> query = getEntityManager().createQuery(all);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hbl.hblconnect.model.service.BaseService#get(int)
	 */
	public E get(long id) {
		E found = entityManager.find(getParametrizedClass(), id);
		return found;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hbl.hblconnect.model.service.BaseService#saveOrUpdate(E)
	 */
	
	public E saveOrUpdate(E entity) {
		E merge = entityManager.merge(entity);
		return merge;
	}

	public void delete(E entity) {
		entityManager.remove(entity);
	}

	
	public void delete(long id) {
		E entity = entityManager.find(getParametrizedClass(), id);
		entityManager.remove(entity);
	}

}
