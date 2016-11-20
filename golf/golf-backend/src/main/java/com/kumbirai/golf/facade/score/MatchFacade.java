/*
 com.kumbirai.golf.facade.score.MatchFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.score;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.score.MatchDAO;
import com.kumbirai.golf.data.score.Match;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> MatchFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class MatchFacade extends GenericFacade<Match> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(MatchFacade.class.getName());

	/**
	 * Constructor:
	 */
	public MatchFacade()
	{
		super();
		dao = new MatchDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public MatchDAO getDao()
	{
		return (MatchDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Match entity)
	{
		return entity.getMatchNo() != null && entity.getMatchNo() > 0;
	}
}