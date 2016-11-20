/*
 com.kumbirai.golf.views.course.GolfCourseGrid<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.course;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.data.course.GolfCourse;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Grid;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfCourseGrid<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfCourseGrid extends Grid
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfCourseGrid.class.getName());

	/**
	 * Constructor:
	 */
	public GolfCourseGrid()
	{
		super();
		setSizeFull();

		setSelectionMode(SelectionMode.SINGLE);

		BeanItemContainer<GolfCourse> container = new BeanItemContainer<>(GolfCourse.class);
		setContainerDataSource(container);
		setColumns("courseName");

	}

	/**
	 * Filter the grid based on a search string that is searched for in the
	 * golfCourse name, availability and category columns.
	 *
	 * @param filterString
	 *            string to look for
	 */
	public void setFilter(String filterString)
	{
		getContainer().removeAllContainerFilters();
		if (filterString.length() > 0)
		{
			SimpleStringFilter courseFilter = new SimpleStringFilter("courseName", filterString, true, false);
			getContainer().addContainerFilter(new Or(courseFilter));
		}

	}

	/**
	 * Purpose:
	 * <br>
	 * getContainer<br>
	 * <br>
	 * @return<br>
	 */
	private BeanItemContainer<GolfCourse> getContainer()
	{
		return (BeanItemContainer<GolfCourse>) super.getContainerDataSource();
	}

	/** (non-Javadoc)
	 * @see com.vaadin.ui.Grid#getSelectedRow()
	 */
	@Override
	public GolfCourse getSelectedRow() throws IllegalStateException
	{
		return (GolfCourse) super.getSelectedRow();
	}

	/**
	 * Purpose:
	 * <br>
	 * setGolfCourses<br>
	 * <br>
	 * @param golfCourses<br>
	 */
	public void setGolfCourses(Collection<GolfCourse> golfCourses)
	{
		getContainer().removeAllItems();
		getContainer().addAll(golfCourses);
	}

	/**
	 * Purpose:
	 * <br>
	 * refresh<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void refresh(GolfCourse golfCourse)
	{
		// We avoid updating the whole table through the backend here so we can
		// get a partial update for the grid
		BeanItem<GolfCourse> item = getContainer().getItem(golfCourse);
		if (item != null)
		{
			// Updated golfCourse
			MethodProperty p = (MethodProperty) item.getItemProperty("golfCourseNo");
			p.fireValueChange();
		}
		else
		{
			// New golfCourse
			getContainer().addBean(golfCourse);
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * remove<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void remove(GolfCourse golfCourse)
	{
		getContainer().removeItem(golfCourse);
	}
}
