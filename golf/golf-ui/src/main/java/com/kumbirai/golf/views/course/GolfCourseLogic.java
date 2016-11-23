/*
 com.kumbirai.golf.views.course.GolfCourseLogic<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.course;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.GolfUI;
import com.kumbirai.golf.authentication.CurrentUser;
import com.kumbirai.golf.backend.DataService;
import com.kumbirai.golf.data.course.GolfCourse;
import com.kumbirai.security.principal.ISecurityPrincipal;
import com.vaadin.server.Page;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfCourseLogic<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfCourseLogic implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfCourseLogic.class.getName());
	private GolfCourseView view;
	private ISecurityPrincipal securityPrincipal = CurrentUser.get();

	/**
	 * Constructor: @param view
	 */
	public GolfCourseLogic(GolfCourseView view)
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
		editGolfCourse(null);
		// Hide and disable if not admin
		if (!GolfUI.get().getAccessControl().isUserInRole("Administration"))
		{
			view.setNewGolfCourseEnabled(false);
		}

		view.showGolfCourses(DataService.get().getAllCourses());
	}

	/**
	 * Purpose:
	 * <br>
	 * cancelGolfCourse<br>
	 * <br><br>
	 */
	public void cancelGolfCourse()
	{
		setFragmentParameter("");
		view.clearSelection();
		view.editGolfCourse(null);
	}

	/**
	 * Purpose: Update the fragment without causing navigator to change view
	 * <br>
	 * setFragmentParameter<br>
	 * <br>
	 * @param golfCourseNo<br>
	 */
	private void setFragmentParameter(String golfCourseNo)
	{
		String fragmentParameter;
		if (golfCourseNo == null || golfCourseNo.isEmpty())
		{
			fragmentParameter = "";
		}
		else
		{
			fragmentParameter = golfCourseNo;
		}

		Page page = GolfUI.get().getPage();
		page.setUriFragment("!" + GolfCourseView.VIEW_NAME + "/" + fragmentParameter, false);
	}

	/**
	 * Purpose:
	 * <br>
	 * enter<br>
	 * <br>
	 * @param golfCourseNo<br>
	 */
	public void enter(String golfCourseNo)
	{
		if (golfCourseNo != null && !golfCourseNo.isEmpty())
		{
			if ("new".equals(golfCourseNo))
			{
				newGolfCourse();
			}
			else
			{
				// Ensure this is selected even if coming directly here from
				// login
				try
				{
					long eveNo = Long.parseLong(golfCourseNo);
					GolfCourse golfCourse = findGolfCourse(eveNo);
					view.selectRow(golfCourse);
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
	 * findGolfCourse<br>
	 * <br>
	 * @param golfCourseNo
	 * @return<br>
	 */
	private GolfCourse findGolfCourse(Long golfCourseNo)
	{
		return DataService.get().getGolfCourseByNo(securityPrincipal, golfCourseNo);
	}

	/**
	 * Purpose:
	 * <br>
	 * saveGolfCourse<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void saveGolfCourse(GolfCourse golfCourse)
	{
		view.showSaveNotification(golfCourse.getCourseName() + " (" + golfCourse.getGolfCourseNo() + ") updated");
		view.clearSelection();
		view.editGolfCourse(null);
		view.refreshGolfCourse(golfCourse);
		setFragmentParameter("");
	}

	/**
	 * Purpose:
	 * <br>
	 * deleteGolfCourse<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void deleteGolfCourse(GolfCourse golfCourse)
	{
		DataService.get().delete(securityPrincipal, golfCourse);
		view.showSaveNotification(golfCourse.getCourseName() + " (" + golfCourse.getGolfCourseNo() + ") removed");

		view.clearSelection();
		view.editGolfCourse(null);
		view.removeGolfCourse(golfCourse);
		setFragmentParameter("");
	}

	/**
	 * Purpose:
	 * <br>
	 * editGolfCourse<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void editGolfCourse(GolfCourse golfCourse)
	{
		if (golfCourse == null)
		{
			setFragmentParameter("");
		}
		else
		{
			setFragmentParameter(Long.toString(golfCourse.getGolfCourseNo()));
		}
		view.editGolfCourse(golfCourse);
	}

	/**
	 * Purpose:
	 * <br>
	 * newGolfCourse<br>
	 * <br><br>
	 */
	public void newGolfCourse()
	{
		view.clearSelection();
		setFragmentParameter("new");
		view.editGolfCourse(new GolfCourse());
	}

	/**
	 * Purpose:
	 * <br>
	 * rowSelected<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void rowSelected(GolfCourse golfCourse)
	{
		if (GolfUI.get().getAccessControl().isUserInRole("Administration"))
		{
			LOGGER.debug(String.format("%s is an administrator {%s}", securityPrincipal.getName(), golfCourse));
			view.editGolfCourse(golfCourse);
		}
	}
}