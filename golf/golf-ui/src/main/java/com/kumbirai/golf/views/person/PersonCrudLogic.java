/*
 com.kumbirai.golf.views.person.PersonCrudLogic<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.person;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonCrudLogic<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 04 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonCrudLogic implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonCrudLogic.class.getName());
	private PersonView view;

	/**
	 * Constructor: @param view
	 */
	public PersonCrudLogic(PersonView view)
	{
		super();
		this.view = view;
	}

	/**
	 * Purpose:
	 * <br>
	 * init<br>
	 * <br><br>
	 */
	public void init()
	{
		view.showPerson();
	}

	/**
	 * Purpose:
	 * <br>
	 * enter<br>
	 * <br>
	 * @param parameters<br>
	 */
	public void enter(String parameters)
	{
	}
}