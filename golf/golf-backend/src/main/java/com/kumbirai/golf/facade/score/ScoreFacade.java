/*
 com.kumbirai.golf.facade.score.ScoreFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.score;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.score.ScoreDAO;
import com.kumbirai.golf.data.score.Score;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> ScoreFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class ScoreFacade extends GenericFacade<Score> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ScoreFacade.class.getName());

	/**
	 * Constructor:
	 */
	public ScoreFacade()
	{
		super();
		dao = new ScoreDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public ScoreDAO getDao()
	{
		return (ScoreDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#hasPrimaryKey(java.lang.Object)
	 */
	@Override
	protected boolean hasPrimaryKey(Score entity)
	{
		return entity.getScoreNo() != null && entity.getScoreNo() > 0;
	}
}