/*
 com.kumbirai.golf.event.EventScoreboardCmd<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.commandservice.AbstractCommand;
import com.kumbirai.golf.data.event.GolfEvent;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> EventScoreboardCmd<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 25 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class EventScoreboardCmd extends AbstractCommand
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(EventScoreboardCmd.class.getName());
	private GolfEvent golfEvent;
	private StringBuilder sb = new StringBuilder();

	/**
	 * Constructor: @param golfEvent
	 */
	public EventScoreboardCmd(GolfEvent golfEvent)
	{
		super();
		this.golfEvent = golfEvent;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.commandservice.ICommand#execute()
	 */
	@Override
	public void execute()
	{
	}

	/**
	 * Purpose:
	 * <br>
	 * getScoreboard<br>
	 * <br>
	 * @return<br>
	 */
	public String getScoreboard()
	{
		return new String(sb);
	}
}
