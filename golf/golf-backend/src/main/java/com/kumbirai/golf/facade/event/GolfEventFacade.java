/*
 com.kumbirai.golf.facade.event.GolfEventFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.event;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.event.GolfEventDAO;
import com.kumbirai.golf.data.event.GolfEvent;
import com.kumbirai.golf.data.event.GolfEventResult;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfEventFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfEventFacade extends GenericFacade<GolfEvent> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfEventFacade.class.getName());

	/**
	 * Constructor:
	 */
	public GolfEventFacade()
	{
		super();
		dao = new GolfEventDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public GolfEventDAO getDao()
	{
		return (GolfEventDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#hasPrimaryKey(java.lang.Object)
	 */
	@Override
	protected boolean hasPrimaryKey(GolfEvent entity)
	{
		return entity.getEventNo() != null && entity.getEventNo() > 0;
	}

	/**
	 * Purpose:
	 * <br>
	 * clearEventResults<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	public void clearEventResults(GolfEvent golfEvent)
	{
		GolfEventResultFacade golfEventResultFacade = new GolfEventResultFacade();
		golfEventResultFacade.setSecurityPrincipal(this.securityPrincipal);
		Collection<GolfEventResult> golfEventResults = golfEvent.getEventResults();
		if (golfEventResults != null && !golfEventResults.isEmpty())
		{
			for (GolfEventResult golfEventResult : golfEventResults)
			{
				golfEventResultFacade.delete(golfEventResult);
			}
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * listPastEvents<br>
	 * <br>
	 * @return<br>
	 */
	public List<GolfEvent> listPastEvents()
	{
		getDao().beginTransaction();
		List<GolfEvent> golfEvents = getDao().listPastEvents();
		getDao().commitAndCloseTransaction();
		return golfEvents;
	}

	/**
	 * Purpose:
	 * <br>
	 * listEventsAfter<br>
	 * <br>
	 * @param date
	 * @return<br>
	 */
	public List<GolfEvent> listEventsAfter(Date date)
	{
		getDao().beginTransaction();
		List<GolfEvent> golfEvents = getDao().listEventsAfter(date);
		getDao().commitAndCloseTransaction();
		return golfEvents;
	}
}