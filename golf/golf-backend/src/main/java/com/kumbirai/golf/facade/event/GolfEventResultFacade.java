/*
 com.kumbirai.golf.facade.event.GolfEventResultFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.event;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.event.GolfEventResultDAO;
import com.kumbirai.golf.data.event.GolfEventResult;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfEventResultFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 14 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfEventResultFacade extends GenericFacade<GolfEventResult> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfEventResultFacade.class.getName());

	/**
	 * Constructor:
	 */
	public GolfEventResultFacade()
	{
		super();
		dao = new GolfEventResultDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public GolfEventResultDAO getDao()
	{
		return (GolfEventResultDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(GolfEventResult entity)
	{
		return entity.getEventNo() != null && entity.getEventNo() > 0;
	}
}