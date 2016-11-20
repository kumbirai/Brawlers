/*
 com.kumbirai.golf.facade.security.SecurityRoleFacade<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.facade.security;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.facade.GenericFacade;
import com.kumbirai.golf.dao.security.SecurityRoleDAO;
import com.kumbirai.golf.data.security.SecurityRole;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> SecurityRoleFacade<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class SecurityRoleFacade extends GenericFacade<SecurityRole> implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(SecurityRoleFacade.class.getName());

	/**
	 * Constructor:
	 */
	public SecurityRoleFacade()
	{
		super();
		dao = new SecurityRoleDAO();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#getDao()
	 */
	@Override
	public SecurityRoleDAO getDao()
	{
		return (SecurityRoleDAO) dao;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.facade.GenericFacade#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(SecurityRole entity)
	{
		return entity.getSecurityRoleNo() != null && entity.getSecurityRoleNo() > 0;
	}
}