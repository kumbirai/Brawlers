/*
 com.kumbirai.golf.views.event.GolfEventLogic<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.event;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.GolfUI;
import com.kumbirai.golf.authentication.CurrentUser;
import com.kumbirai.golf.backend.DataService;
import com.kumbirai.golf.data.event.GolfEvent;
import com.kumbirai.security.principal.ISecurityPrincipal;
import com.vaadin.server.Page;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfEventLogic<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfEventLogic implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfEventLogic.class.getName());
	private GolfEventView view;
	private ISecurityPrincipal securityPrincipal = CurrentUser.get();

	/**
	 * Constructor: @param view
	 */
	public GolfEventLogic(GolfEventView view)
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
		editGolfEvent(null);
		// Hide and disable if not admin
		if (!GolfUI.get().getAccessControl().isUserInRole("Administration"))
		{
			view.setNewGolfEventEnabled(false);
		}

		view.showGolfEvents(DataService.get().getAllEvents());
	}

	/**
	 * Purpose:
	 * <br>
	 * cancelGolfEvent<br>
	 * <br><br>
	 */
	public void cancelGolfEvent()
	{
		setFragmentParameter("");
		view.clearSelection();
		view.editGolfEvent(null);
	}

	/**
	 * Purpose: Update the fragment without causing navigator to change view
	 * <br>
	 * setFragmentParameter<br>
	 * <br>
	 * @param eventNo<br>
	 */
	private void setFragmentParameter(String eventNo)
	{
		String fragmentParameter;
		if (eventNo == null || eventNo.isEmpty())
		{
			fragmentParameter = "";
		}
		else
		{
			fragmentParameter = eventNo;
		}

		Page page = GolfUI.get().getPage();
		page.setUriFragment("!" + GolfEventView.VIEW_NAME + "/" + fragmentParameter, false);
	}

	/**
	 * Purpose:
	 * <br>
	 * enter<br>
	 * <br>
	 * @param eventNo<br>
	 */
	public void enter(String eventNo)
	{
		if (eventNo != null && !eventNo.isEmpty())
		{
			if ("new".equals(eventNo))
			{
				newGolfEvent();
			}
			else
			{
				// Ensure this is selected even if coming directly here from
				// login
				try
				{
					long eveNo = Long.parseLong(eventNo);
					GolfEvent golfEvent = findGolfEvent(eveNo);
					view.selectRow(golfEvent);
				}
				catch (NumberFormatException e)
				{
				}
			}
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * findGolfEvent<br>
	 * <br>
	 * @param eventNo
	 * @return<br>
	 */
	private GolfEvent findGolfEvent(Long eventNo)
	{
		return DataService.get().getGolfEventByNo(securityPrincipal, eventNo);
	}

	/**
	 * Purpose:
	 * <br>
	 * saveGolfEvent<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	public void saveGolfEvent(GolfEvent golfEvent)
	{
		// view.showSaveNotification(golfEvent.getEventDate() + " (" + golfEvent.getEventNo() + ") updated")
		view.clearSelection();
		view.editGolfEvent(null);
		view.refreshGolfEvent(golfEvent);
		setFragmentParameter("");
	}

	/**
	 * Purpose:
	 * <br>
	 * deleteGolfEvent<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	public void deleteGolfEvent(GolfEvent golfEvent)
	{
		DataService.get().delete(securityPrincipal, golfEvent);
		// view.showSaveNotification(golfEvent.getEventDate() + " (" + golfEvent.getEventNo() + ") removed")

		view.clearSelection();
		view.editGolfEvent(null);
		view.removeGolfEvent(golfEvent);
		setFragmentParameter("");
	}

	/**
	 * Purpose:
	 * <br>
	 * editGolfEvent<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	public void editGolfEvent(GolfEvent golfEvent)
	{
		if (golfEvent == null)
		{
			setFragmentParameter("");
		}
		else
		{
			setFragmentParameter(Long.toString(golfEvent.getEventNo()));
		}
		view.editGolfEvent(golfEvent);
	}

	/**
	 * Purpose:
	 * <br>
	 * newGolfEvent<br>
	 * <br><br>
	 */
	public void newGolfEvent()
	{
		view.clearSelection();
		setFragmentParameter("new");
		view.editGolfEvent(new GolfEvent());
	}

	/**
	 * Purpose:
	 * <br>
	 * rowSelected<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	public void rowSelected(GolfEvent golfEvent)
	{
		if (GolfUI.get().getAccessControl().isUserInRole("Administration"))
		{
			LOGGER.debug(String.format("%s is an administrator {%s}", securityPrincipal.getName(), golfEvent));
			view.editGolfEvent(golfEvent);
		}
	}
}