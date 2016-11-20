/*
 com.kumbirai.golf.facade.entity.info.PersonInfoTelFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.entity.info;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.entity.info.PersonInfoTelDAO;
import com.kumbirai.golf.data.entity.info.PersonInfoTel;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoTelFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonInfoTelFacade extends GenericFacade<PersonInfoTel> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonInfoTelFacade.class.getName());

	/**
	 * Constructor:
	 */
	public PersonInfoTelFacade()
	{
		super();
		dao = new PersonInfoTelDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public PersonInfoTelDAO getDao()
	{
		return (PersonInfoTelDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(PersonInfoTel entity)
	{
		return entity.getPersonInfoNo() != null && entity.getPersonInfoNo() > 0;
	}
}