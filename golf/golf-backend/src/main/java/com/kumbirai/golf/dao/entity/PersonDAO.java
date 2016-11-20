/*
 com.kumbirai.golf.dao.entity.PersonDAO<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.dao.entity;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.dao.GenericDAO;
import com.kumbirai.golf.data.EStatus;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.entity.Person_;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile_;
import com.kumbirai.golf.data.security.SecurityRole;
import com.kumbirai.golf.data.security.SecurityRole_;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonDAO<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonDAO extends GenericDAO<Person>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonDAO.class.getName());

	/**
	 * Constructor:
	 */
	public PersonDAO()
	{
		super(Person.class);
	}

	/**
	 * Purpose:
	 * <br>
	 * findAllRoleForStatus<br>
	 * <br>
	 * @param role
	 * @param status
	 * @return<br>
	 */
	public List<Person> findAllRoleForStatus(String role, EStatus status)
	{
		LOGGER.debug(String.format("role - %s, status - %s", role, status));
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);
		//
		Root<Person> root = criteriaQuery.from(Person.class);
		//
		criteriaQuery.select(root);
		//
		// --
		Subquery<PersonInfoLoginProfile> subquery = criteriaQuery.subquery(PersonInfoLoginProfile.class);
		Root<PersonInfoLoginProfile> subqueryRoot = subquery.from(PersonInfoLoginProfile.class);
		//
		Join<PersonInfoLoginProfile, SecurityRole> securityRoles = subqueryRoot.join(PersonInfoLoginProfile_.securityRoles);
		//
		subquery.select(subqueryRoot);
		subquery.distinct(true);
		//
		Predicate personNum = cb.equal(root.get(Person_.personNo), subqueryRoot.get(PersonInfoLoginProfile_.person).get(Person_.personNo));
		Predicate secRole = cb.equal(securityRoles.get(SecurityRole_.role), role);
		//
		subquery.where(personNum, secRole);
		// --
		//
		criteriaQuery.where(cb.equal(root.get(Person_.status), status));
		//
		TypedQuery<Person> typedQuery = em.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
}