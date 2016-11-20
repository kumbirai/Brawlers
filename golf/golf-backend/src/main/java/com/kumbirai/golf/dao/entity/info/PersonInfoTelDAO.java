/*
 com.kumbirai.golf.dao.entity.info.PersonInfoTelDAO<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.dao.entity.info;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.dao.GenericDAO;
import com.kumbirai.golf.data.entity.info.PersonInfoTel;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoTelDAO<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonInfoTelDAO extends GenericDAO<PersonInfoTel>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonInfoTelDAO.class.getName());

	/**
	 * Constructor:
	 */
	public PersonInfoTelDAO()
	{
		super(PersonInfoTel.class);
	}
}
