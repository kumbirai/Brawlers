/*
 com.kumbirai.golf.dao.entity.info.PersonInfoLoginProfileDAO<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.dao.entity.info;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.dao.GenericDAO;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoLoginProfileDAO<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonInfoLoginProfileDAO extends GenericDAO<PersonInfoLoginProfile>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonInfoLoginProfileDAO.class.getName());
	private static final String FIND_BY_USERNAME = "PersonInfoLoginProfile.findLoginProfileByUsername";

	/**
	 * Constructor:
	 */
	public PersonInfoLoginProfileDAO()
	{
		super(PersonInfoLoginProfile.class);
	}

	/**
	 * Purpose:
	 * <br>
	 * findLoginProfileByUsername<br>
	 * <br>
	 * @param username
	 * @return<br>
	 */
	public PersonInfoLoginProfile findLoginProfileByUsername(String username)
	{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("username", username);

		return super.findOneResult(FIND_BY_USERNAME, parameters);
	}
}