/*
 com.kumbirai.golf.facade.entity.info.PersonInfoLoginProfileFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.entity.info;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.entity.info.PersonInfoLoginProfileDAO;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoLoginProfileFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonInfoLoginProfileFacade extends GenericFacade<PersonInfoLoginProfile> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonInfoLoginProfileFacade.class.getName());

	/**
	 * Constructor:
	 */
	public PersonInfoLoginProfileFacade()
	{
		super();
		dao = new PersonInfoLoginProfileDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public PersonInfoLoginProfileDAO getDao()
	{
		return (PersonInfoLoginProfileDAO) dao;
	}

	/**
	 * Purpose:
	 * <br>
	 * login<br>
	 * <br>
	 * @param username
	 * @param password
	 * @return<br>
	 */
	public PersonInfoLoginProfile login(String username, String password)
	{
		getDao().beginTransaction();
		PersonInfoLoginProfile user = getDao().findLoginProfileByUsername(username);
		getDao().closeTransaction();
		String logi = String.format("username: %s, Password: %s - %s", username, password, user);
		LOGGER.debug(logi);
		if (user == null || !user.getPassword().equals(password))
		{
			return null;
		}
		return user;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#hasPrimaryKey(java.lang.Object)
	 */
	@Override
	protected boolean hasPrimaryKey(PersonInfoLoginProfile entity)
	{
		return entity.getPersonInfoNo() != null && entity.getPersonInfoNo() > 0;
	}
}