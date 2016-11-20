/*
 com.kumbirai.golf.facade.entity.info.PersonInfoEmailFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.entity.info;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.entity.info.PersonInfoEmailDAO;
import com.kumbirai.golf.data.entity.info.PersonInfoEmail;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoEmailFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonInfoEmailFacade extends GenericFacade<PersonInfoEmail> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonInfoEmailFacade.class.getName());

	/**
	 * Constructor:
	 */
	public PersonInfoEmailFacade()
	{
		super();
		dao = new PersonInfoEmailDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public PersonInfoEmailDAO getDao()
	{
		return (PersonInfoEmailDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(PersonInfoEmail entity)
	{
		return entity.getPersonInfoNo() != null && entity.getPersonInfoNo() > 0;
	}
}