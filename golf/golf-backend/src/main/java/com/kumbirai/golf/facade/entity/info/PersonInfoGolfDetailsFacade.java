/*
 com.kumbirai.golf.facade.entity.info.PersonInfoGolfDetailsFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.entity.info;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.entity.info.PersonInfoGolfDetailsDAO;
import com.kumbirai.golf.data.entity.info.PersonInfoGolfDetails;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoGolfDetailsFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 08 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonInfoGolfDetailsFacade extends GenericFacade<PersonInfoGolfDetails> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonInfoGolfDetailsFacade.class.getName());

	/**
	 * Constructor:
	 */
	public PersonInfoGolfDetailsFacade()
	{
		super();
		dao = new PersonInfoGolfDetailsDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public PersonInfoGolfDetailsDAO getDao()
	{
		return (PersonInfoGolfDetailsDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#hasPrimaryKey(java.lang.Object)
	 */
	@Override
	protected boolean hasPrimaryKey(PersonInfoGolfDetails entity)
	{
		return entity.getPersonInfoNo() != null && entity.getPersonInfoNo() > 0;
	}
}
