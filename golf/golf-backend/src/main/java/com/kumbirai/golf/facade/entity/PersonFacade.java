/*
 com.kumbirai.golf.facade.entity.PersonFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.entity.PersonDAO;
import com.kumbirai.golf.data.EStatus;
import com.kumbirai.golf.data.entity.Person;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonFacade extends GenericFacade<Person> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonFacade.class.getName());

	/**
	 * Constructor:
	 */
	public PersonFacade()
	{
		super();
		dao = new PersonDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public PersonDAO getDao()
	{
		return (PersonDAO) dao;
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
		getDao().beginTransaction();
		List<Person> result = getDao().findAllRoleForStatus(role, status);
		getDao().closeTransaction();
		return result;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Person entity)
	{
		return entity.getPersonNo() != null && entity.getPersonNo() > 0;
	}
}