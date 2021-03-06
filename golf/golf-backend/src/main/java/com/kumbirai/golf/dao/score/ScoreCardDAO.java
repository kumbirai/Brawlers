/*
 com.kumbirai.golf.dao.score.ScoreCardDAO<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.dao.score;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.dao.GenericDAO;
import com.kumbirai.golf.data.score.ScoreCard;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> ScoreCardDAO<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class ScoreCardDAO extends GenericDAO<ScoreCard>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ScoreCardDAO.class.getName());

	/**
	 * Constructor:
	 */
	public ScoreCardDAO()
	{
		super(ScoreCard.class);
	}
}