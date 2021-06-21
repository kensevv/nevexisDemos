package com.frantishex.lwr.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.frantishex.lwr.controller.dto.LawsuiteListRequestDTO;
import com.frantishex.lwr.model.BaseEntity;
import com.frantishex.lwr.model.IzpulnitelnoDelo;
import com.frantishex.lwr.model.Lawsuite;
import com.frantishex.lwr.model.NakazatelnoDelo;
import com.frantishex.lwr.model.Person;
import com.frantishex.lwr.model.TaskFrx;
import com.frantishex.lwr.model.TraumaType;

@Service("LawsuiteService")
@Transactional
public class LawsuiteService extends BaseService {

	private static final String QUERY_SHEDULED_LAWSUITES = "SELECT law.id,ls.data_nasrochen dataNasrochen,  ls.time_nasrochen timeNasrochen,\r\n" + 
			"						GROUP_CONCAT(p.name) claimantList,GROUP_CONCAT(defendant.name) defendantList, law.case_number, court.name court ,lawyer.names lawyer,lawyer.username lawyerName, ls.note FROM lwr2.lawsuite law\r\n" + 
			"						join lawsuite_status ls on law.id = ls.lawsuite_id Left join lawsuite_claiment_list claimanList on  law.id = claimanList.lawsuite_id\r\n" + 
			"						Left join person p on p.id = claimanList.claiment_list_id Left join sudilishte court on  ls.court_id = court.id\r\n" + 
			"						Left join user lawyer on law.lawyer_id =  lawyer.id\r\n" + 
			"			Left join lawsuite_defendant_list defendantList on  law.id = defendantList.lawsuite_id \r\n" + 
			"			Left join person defendant on defendantList.defendant_list_id = defendant.id\r\n" + 
			"					WHERE	DATE(NOW())>= DATE(DATE(ls.data_nasrochen)- INTERVAL %d DAY) &&  DATE(NOW())  <= DATE(ls.data_nasrochen) group by ls.id";
	private static final String QUERY_SHEDULED_LAWSUITES_EXTENDED = "SELECT law.id,ls.data_nasrochen dataNasrochen,  ls.time_nasrochen timeNasrochen,\r\n" + 
			"						GROUP_CONCAT(p.name) claimantList,GROUP_CONCAT(defendant.name) defendantList, law.case_number, court.name court ,lawyer.names lawyer,lawyer.username lawyerName, ls.note FROM lwr2.lawsuite law\r\n" + 
			"						join lawsuite_status ls on law.id = ls.lawsuite_id Left join lawsuite_claiment_list claimanList on  law.id = claimanList.lawsuite_id\r\n" + 
			"						Left join person p on p.id = claimanList.claiment_list_id Left join sudilishte court on  ls.court_id = court.id\r\n" + 
			"						Left join user lawyer on law.lawyer_id =  lawyer.id\r\n" + 
			"			Left join lawsuite_defendant_list defendantList on  law.id = defendantList.lawsuite_id \r\n" + 
			"			Left join person defendant on defendantList.defendant_list_id = defendant.id\r\n" + 
			"					WHERE	DATE(NOW())  <= DATE(ls.data_nasrochen) group by ls.id";
	private static final String QUERY_FOR_SCHEDULED_PUNITIVE_LAWSUITES = "SELECT law.id,ls.data_nasrocheno dataNasrochen, ls.time_nasrocheno timeNasrochen,\r\n" + 
			"			GROUP_CONCAT(p.name) claimantList,GROUP_CONCAT(defendant.name) defendantList, law.case_number, court.name court,\r\n" + 
			"			lawyer.names lawyer,lawyer.username lawyerName, ls.note\r\n" + 
			"			 FROM lwr2.lawsuite law\r\n" + 
			"			Left join nakazatelno_delo nd on  law.nakazatelno_delo_id = nd.id\r\n" + 
			"			join lawsuite_nakazatelno_status ls on law.id = ls.lawsuite_id\r\n" + 
			"			Left join lawsuite_claiment_list claimanList on  law.id = claimanList.lawsuite_id \r\n" + 
			"			Left join lawsuite_defendant_list defendantList on  law.id = defendantList.lawsuite_id \r\n" + 
			"			Left join person p on claimanList.claiment_list_id = p.id\r\n" + 
			"			Left join person defendant on defendantList.defendant_list_id = defendant.id\r\n" + 
			"			Left join sudilishte court on  ls.court_id = court.id \r\n" + 
			"			Left join user lawyer on nd.lawyer_id =  lawyer.id\r\n" + 
			"			where DATE(NOW())>= DATE(DATE(ls.data_nasrocheno)- INTERVAL %d DAY) && DATE(NOW())  <= DATE(ls.data_nasrocheno)  group by ls.id";
	
	private static final String QUERY_FOR_SCHEDULED_PUNITIVE_LAWSUITES_EXTENDED = "SELECT law.id,ls.data_nasrocheno dataNasrochen, ls.time_nasrocheno timeNasrochen,\r\n" + 
			"			GROUP_CONCAT(p.name) claimantList,GROUP_CONCAT(defendant.name) defendantList, law.case_number, court.name court,\r\n" + 
			"			lawyer.names lawyer,lawyer.username lawyerName, ls.note\r\n" + 
			"			 FROM lwr2.lawsuite law\r\n" + 
			"			Left join nakazatelno_delo nd on  law.nakazatelno_delo_id = nd.id\r\n" + 
			"			join lawsuite_nakazatelno_status ls on law.id = ls.lawsuite_id\r\n" + 
			"			Left join lawsuite_claiment_list claimanList on  law.id = claimanList.lawsuite_id \r\n" + 
			"			Left join lawsuite_defendant_list defendantList on  law.id = defendantList.lawsuite_id \r\n" + 
			"			Left join person defendant on defendantList.defendant_list_id = defendant.id\r\n" + 
			"			Left join person p on claimanList.claiment_list_id = p.id\r\n" + 
			"			Left join sudilishte court on  ls.court_id = court.id \r\n" + 
			"			Left join user lawyer on nd.lawyer_id =  lawyer.id\r\n" + 
			"			where DATE(NOW())  <= DATE(ls.data_nasrocheno) group by ls.id";
	@Autowired
	private LawsuiteRepository lawsuiteDao;

	@Autowired
	private DataSource dataSource;

	public Lawsuite getLawsuite(Long id) {
		return getEm().find(Lawsuite.class, id);
	}

	public void deleteLawsuite(Long id) {
		Lawsuite lawsuite = getLawsuite(id);
		lawsuiteDao.delete(lawsuite);
	}

	public Lawsuite createLawsuite(Lawsuite newL) {
		newL.setId(null);
		return getEm().merge(newL);
	}

	public Lawsuite updateLawsuite(Lawsuite l) {
		return getEm().merge(l);
	}

	public Page<Lawsuite> findPaginated(int page, int size) {
		return lawsuiteDao.findAll(PageRequest.of(page, size));
	}

	public Page<Lawsuite> searchPaginated(final LawsuiteListRequestDTO request, int page, int size) {
		return lawsuiteDao.findAll(new Specification<Lawsuite>() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public Predicate toPredicate(Root<Lawsuite> lawsuite, CriteriaQuery<?> query, CriteriaBuilder cb) {

				/*
				 * This avoids additional queries for each of the object's
				 * relationships, and ensures that the relationships have been
				 * fetched if they were LAZY.
				 */
				// query.from(Lawsuite.class);
				Join<Lawsuite, Person> claimentList = lawsuite.join("claimentList", JoinType.LEFT);
				Join<Lawsuite, Person> defendantList = lawsuite.join("defendantList", JoinType.LEFT);
				Join<Lawsuite, IzpulnitelnoDelo> izpulnitelnoDelo = lawsuite.join("izpulnitelnoDelo", JoinType.LEFT);
				Join<Lawsuite, NakazatelnoDelo> nakazatelnoDelo = lawsuite.join("nakazatelnoDelo", JoinType.LEFT);
				Join<Lawsuite, TraumaType> traumaType = lawsuite.join("traumaType", JoinType.LEFT);

				Predicate where = cb.conjunction();
				where = addSimpleLikeCriterion(cb, where, defendantList.get("name"), request.getDefendant(), true);
				where = addSimpleLikeCriterion(cb, where, claimentList.get("name"), request.getClaimant(), true);

				where = addSimpleLikeCriterion(cb, where, lawsuite.get("caseNumber"), request.getCaseNumber(), false);
				where = addSimpleLikeCriterion(cb, where, lawsuite.get("damageNumber"), request.getDamageNumber(), false);
				where = addSimpleLikeCriterion(cb, where, izpulnitelnoDelo.get("number"), request.getEnforcementCase(), false);
				where = addSimpleLikeCriterion(cb, where, nakazatelnoDelo.get("number"), request.getCriminalCase(), false);
				where = addSimpleLikeCriterion(cb, where, nakazatelnoDelo.get("zmMvrNomer"), request.getMvrNumber(), false);
				where = addSimpleLikeCriterion(cb, where, nakazatelnoDelo.get("prokusrorskaPrepiskaNomer"), request.getProsecutorsNotice(), false);
				where = addSimpleLikeCriterion(cb, where, traumaType.get("name"), request.getTraumaTypeName(), false);

				/*
				 * if (request.getClaimant() != null) { where = cb.and(where,
				 * cb.like(claimentList.get("name"), String.format("%%%s%%",
				 * request.getClaimant()))); } if (request.getClaimant() !=
				 * null) { where = cb.and(where,
				 * cb.like(defendantList.get("name"), String.format("%%%s%%",
				 * request.getDefendant()))); }
				 */

				CriteriaQuery<Lawsuite> cq = (CriteriaQuery<Lawsuite>) query;
				cq.distinct(true);

				return where;
			}

			private Predicate addSimpleLikeCriterion(CriteriaBuilder cb, Predicate where, Path<String> path, String text, boolean wholeString) {
				if (text != null && !text.isEmpty()) {
					return cb.and(where, wholeString ? cb.equal(path, text) : cb.like(path, String.format("%%%s%%", text)));
				}
				return where;
			}
		}, PageRequest.of(page, size));
	}

	public <T extends BaseEntity> T saveAttachedDelo(Long lawsuiteId, T object) {
		T merged = em.merge(object);
		Lawsuite l = em.find(Lawsuite.class, lawsuiteId);
		if (object instanceof IzpulnitelnoDelo) {
			l.setIzpulnitelnoDelo((IzpulnitelnoDelo) merged);
		} else if (object instanceof NakazatelnoDelo) {
			l.setNakazatelnoDelo((NakazatelnoDelo) merged);
		}
		return merged;
	}

	public ArrayList<Map<String, Object>> findNasrocheniDela(int fromDays) {
		List<Map<String, Object>> nakazatelniDelaList = nasrocheniNakazatelniiDela(fromDays);
		List<Map<String, Object>> grajdanskiDelaList = nasrocheniGrajdanskiiiDela(fromDays);
		ArrayList<Map<String, Object>> nasrocheniDelaList = new ArrayList<Map<String, Object>>();
		nasrocheniDelaList.addAll(nakazatelniDelaList);
		nasrocheniDelaList.addAll(grajdanskiDelaList);
		return nasrocheniDelaList;

	}
	public ArrayList<Map<String, Object>> findNasrocheniDelaExtended() {
		List<Map<String, Object>> nakazatelniDelaList = nasrocheniNakazatelniiDela();
		List<Map<String, Object>> grajdanskiDelaList = nasrocheniGrajdanskiiiDela();
		ArrayList<Map<String, Object>> nasrocheniDelaList = new ArrayList<Map<String, Object>>();
		nasrocheniDelaList.addAll(nakazatelniDelaList);
		nasrocheniDelaList.addAll(grajdanskiDelaList);
		return nasrocheniDelaList;

	}
	

	private List<Map<String, Object>> nasrocheniNakazatelniiDela(int fromDays) {
		String query = String.format(QUERY_FOR_SCHEDULED_PUNITIVE_LAWSUITES,fromDays);
		return createResultFromQuery(query);

	}
	
	private List<Map<String, Object>> nasrocheniNakazatelniiDela() {
		String query = String.format(QUERY_FOR_SCHEDULED_PUNITIVE_LAWSUITES_EXTENDED);
		return createResultFromQuery(query);

	}

	private List<Map<String, Object>> nasrocheniGrajdanskiiiDela(int fromDays) {
		String query = String.format(QUERY_SHEDULED_LAWSUITES,fromDays);
		return createResultFromQuery(query);
	}
	
	private List<Map<String, Object>> nasrocheniGrajdanskiiiDela() {
		String query = String.format(QUERY_SHEDULED_LAWSUITES_EXTENDED);
		return createResultFromQuery(query);
	}

	private List<Map<String, Object>> createResultFromQuery(String sql) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		SqlRowSet rows = executeQuery(sql);
		SqlRowSetMetaData metadata = rows.getMetaData();
		while (rows.next()) {
			Map<String, Object> entry = new HashMap<String, Object>();
			for (int i = 1; i <= metadata.getColumnCount(); i++) {
				String colName = metadata.getColumnLabel(i);
				Object obj = rows.getObject(i);
				entry.put(colName, obj);
			}
			result.add(entry);
		}
		return result;
	}

	private SqlRowSet executeQuery(String sql) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, paramSource);
		return rowSet;
	}

}
