/*
 com.kumbirai.golf.facade.course.GolfCourseDAO<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.dao.course;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.dao.GenericDAO;
import com.kumbirai.golf.data.course.GolfCourse;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfCourseDAO<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfCourseDAO extends GenericDAO<GolfCourse>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfCourseDAO.class.getName());

	/**
	 * Constructor:
	 */
	public GolfCourseDAO()
	{
		super(GolfCourse.class);
	}
}
