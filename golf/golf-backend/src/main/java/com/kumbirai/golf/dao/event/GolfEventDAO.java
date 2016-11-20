/*
 com.kumbirai.golf.dao.event.GolfEventDAO<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.dao.event;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.common.lang.DateUtils;
import com.kumbirai.common.lang.StringUtils;
import com.kumbirai.dao.GenericDAO;
import com.kumbirai.golf.data.event.GolfEvent;
import com.kumbirai.golf.data.event.GolfEvent_;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfEventDAO<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfEventDAO extends GenericDAO<GolfEvent>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfEventDAO.class.getName());

	/**
	 * Constructor:
	 */
	public GolfEventDAO()
	{
		super(GolfEvent.class);
	}

	/**
	 * Purpose:
	 * <br>
	 * listPastEvents<br>
	 * <br>
	 * @return<br>
	 */
	public List<GolfEvent> listPastEvents()
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GolfEvent> criteriaQuery = cb.createQuery(GolfEvent.class);
		//
		Root<GolfEvent> root = criteriaQuery.from(GolfEvent.class);
		//
		criteriaQuery.select(root);
		//
		criteriaQuery.where(cb.lessThan(root.get(GolfEvent_.eventDate), DateUtils.killTime(new Date())));
		//
		TypedQuery<GolfEvent> typedQuery = em.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	/**
	 * Purpose:
	 * <br>
	 * listEventsAfter<br>
	 * <br>
	 * @param date
	 * @return<br>
	 */
	public List<GolfEvent> listEventsAfter(Date date)
	{
		LOGGER.debug(String.format("Using Date: %s", StringUtils.formatDate(date, "dd MMM yyyy")));
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GolfEvent> criteriaQuery = cb.createQuery(GolfEvent.class);
		//
		Root<GolfEvent> root = criteriaQuery.from(GolfEvent.class);
		//
		criteriaQuery.select(root);
		//
		criteriaQuery.where(cb.greaterThanOrEqualTo(root.get(GolfEvent_.eventDate), DateUtils.killTime(date)));
		//
		TypedQuery<GolfEvent> typedQuery = em.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
}