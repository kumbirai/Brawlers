/*
 com.kumbirai.golf.facade.entity.info.PersonInfoWebAddressFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.entity.info;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.entity.info.PersonInfoWebAddressDAO;
import com.kumbirai.golf.data.entity.info.PersonInfoWebAddress;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoWebAddressFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonInfoWebAddressFacade extends GenericFacade<PersonInfoWebAddress> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonInfoWebAddressFacade.class.getName());

	/**
	 * Constructor:
	 */
	public PersonInfoWebAddressFacade()
	{
		super();
		dao = new PersonInfoWebAddressDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public PersonInfoWebAddressDAO getDao()
	{
		return (PersonInfoWebAddressDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#hasPrimaryKey(java.lang.Object)
	 */
	@Override
	protected boolean hasPrimaryKey(PersonInfoWebAddress entity)
	{
		return entity.getPersonInfoNo() != null && entity.getPersonInfoNo() > 0;
	}
}