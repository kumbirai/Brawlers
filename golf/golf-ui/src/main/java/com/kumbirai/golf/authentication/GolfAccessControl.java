/*
 com.kumbirai.golf.authentication.GolfAccessControl<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.authentication;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.backend.DataService;
import com.kumbirai.golf.data.EStatus;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.entity.info.PersonInfo;
import com.kumbirai.golf.data.entity.info.PersonInfoEmail;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;
import com.kumbirai.golf.data.security.SecurityRole;
import com.kumbirai.security.principal.ISecurityPrincipal;
import com.kumbirai.security.principal.SecurityPrincipal;
import com.kumbirai.security.principal.SecurityPrincipalFactory;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfAccessControl<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 03 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfAccessControl implements AccessControl
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfAccessControl.class.getName());

	/**
	 * Constructor:
	 */
	public GolfAccessControl()
	{
		super();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.authentication.AccessControl#signIn(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean signIn(String username, String password)
	{
		PersonInfoLoginProfile profile = DataService.get().login(username, password);
		LOGGER.debug(String.format("Profile: %s", profile));
		if (profile == null)
			return false;

		CurrentUser.set(getSecurityPrincipal(profile));

		return true;
	}

	/**
	 * Purpose:
	 * <br>
	 * getSecurityPrincipal<br>
	 * <br>
	 * @param profile
	 * @return<br>
	 */
	private ISecurityPrincipal getSecurityPrincipal(PersonInfoLoginProfile profile)
	{
		Person person = profile.getPerson();
		SecurityPrincipal securityPrincipal = (SecurityPrincipal) SecurityPrincipalFactory.getInstance().getSecurityPrincipal(person.getPersonNo(),
				person.getFirstName(), person.getLastName());
		securityPrincipal.setUserRoles(new ArrayList<>());
		for (SecurityRole role : profile.getSecurityRoles())
			securityPrincipal.getUserRoles().add(role.getRole());
		securityPrincipal.setTitle(person.getTitle().name());
		securityPrincipal.setEntityNo(person.getPersonNo());
		for (PersonInfo info : person.getPersonInfoCollection())
		{
			if (info instanceof PersonInfoEmail && ((PersonInfoEmail) info).getStatus() == EStatus.ACTIVE)
				securityPrincipal.setEmail(((PersonInfoEmail) info).getEmailAddress());
		}
		return securityPrincipal;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.authentication.AccessControl#isUserSignedIn()
	 */
	@Override
	public boolean isUserSignedIn()
	{
		return CurrentUser.get() != null;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.authentication.AccessControl#isUserInRole(java.lang.String)
	 */
	@Override
	public boolean isUserInRole(String role)
	{
		ISecurityPrincipal currentUser = CurrentUser.get();
		if (currentUser != null)
			return currentUser.isUserInRole(role);
		return false;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.authentication.AccessControl#getSecurityPrincipal()
	 */
	@Override
	public ISecurityPrincipal getSecurityPrincipal()
	{
		return CurrentUser.get();
	}
}
