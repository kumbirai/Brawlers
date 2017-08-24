/*
 com.kumbirai.golf.facade.course.HoleInfoFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.course;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.course.HoleInfoDAO;
import com.kumbirai.golf.data.course.HoleInfo;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> HoleInfoFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class HoleInfoFacade extends GenericFacade<HoleInfo>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(HoleInfoFacade.class.getName());

	/**
	 * Constructor:
	 */
	public HoleInfoFacade()
	{
		super();
		dao = new HoleInfoDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public HoleInfoDAO getDao()
	{
		return (HoleInfoDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#hasPrimaryKey(java.lang.Object)
	 */
	@Override
	protected boolean hasPrimaryKey(HoleInfo entity)
	{
		return entity.getHoleInfoNo() != null && entity.getHoleInfoNo() > 0;
	}
}