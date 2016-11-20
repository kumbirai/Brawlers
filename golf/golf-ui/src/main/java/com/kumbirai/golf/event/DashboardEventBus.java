/*
 com.kumbirai.golf.event.DashboardEventBus<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.event;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.kumbirai.golf.GolfUI;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> DashboardEventBus<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 06 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class DashboardEventBus implements SubscriberExceptionHandler, Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(DashboardEventBus.class.getName());

	private final transient EventBus eventBus = new EventBus(this);

	/**
	 * Purpose:
	 * <br>
	 * post<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	public static void post(final Object event)
	{
		GolfUI.getDashboardEventbus().eventBus.post(event);
	}

	/**
	 * Purpose:
	 * <br>
	 * register<br>
	 * <br>
	 * @param object<br>
	 */
	public static void register(final Object object)
	{
		LOGGER.debug(String.format("Registering %s", object));
		GolfUI.getDashboardEventbus().eventBus.register(object);
	}

	/**
	 * Purpose:
	 * <br>
	 * unregister<br>
	 * <br>
	 * @param object<br>
	 */
	public static void unregister(final Object object)
	{
		GolfUI.getDashboardEventbus().eventBus.unregister(object);
	}

	/** (non-Javadoc)
	 * @see com.google.common.eventbus.SubscriberExceptionHandler#handleException(java.lang.Throwable, com.google.common.eventbus.SubscriberExceptionContext)
	 */
	@Override
	public void handleException(Throwable exception, SubscriberExceptionContext context)
	{
		LOGGER.error(exception);
	}
}
