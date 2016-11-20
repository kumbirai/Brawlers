/*
 com.kumbirai.golf.facade.course.GolfCourseFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.course;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.course.GolfCourseDAO;
import com.kumbirai.golf.data.course.GolfCourse;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfCourseFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfCourseFacade extends GenericFacade<GolfCourse> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfCourseFacade.class.getName());

	/**
	 * Constructor:
	 */
	public GolfCourseFacade()
	{
		super();
		dao = new GolfCourseDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public GolfCourseDAO getDao()
	{
		return (GolfCourseDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(GolfCourse entity)
	{
		return entity.getGolfCourseNo() != null && entity.getGolfCourseNo() > 0;
	}
}