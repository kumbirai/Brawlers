/*
 com.kumbirai.golf.entity.AddSecurityRoleCmd<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.commandservice.AbstractCommand;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.entity.info.PersonInfo;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;
import com.kumbirai.golf.data.security.SecurityRole;
import com.kumbirai.golf.facade.security.SecurityRoleFacade;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> AddSecurityRoleCmd<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 25 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class AddSecurityRoleCmd extends AbstractCommand
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(AddSecurityRoleCmd.class.getName());
	private Person person;
	private boolean administrator;
	private Collection<String> adminRoles = Arrays.asList(new String[]
	{ "Administration", "GolfCourse Create", "GolfCourse Update", "HoleInfo Create", "HoleInfo Update", "SecurityRole Create", "SecurityRole Update",
			"GolfEvent Create", "GolfEvent Update" });

	/**
	 * Constructor: @param person
	 * Constructor: @param administrator
	 */
	public AddSecurityRoleCmd(Person person, boolean administrator)
	{
		super();
		this.person = person;
		this.administrator = administrator;
	}

	/**
	 * Constructor: @param person
	 */
	public AddSecurityRoleCmd(Person person)
	{
		super();
		this.person = person;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.commandservice.ICommand#execute()
	 */
	@Override
	public void execute()
	{
		SecurityRoleFacade securityRoleFacade = new SecurityRoleFacade();
		Collection<SecurityRole> securityRoles = securityRoleFacade.listAll();
		PersonInfoLoginProfile loginProfile = getPersonInfoLoginProfile();
		for (SecurityRole role : securityRoles)
		{
			if (!administrator && adminRoles.contains(role.getRole()))
				continue;
			loginProfile.getSecurityRoles().add(role);
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * getPersonInfoLoginProfile<br>
	 * <br>
	 * @return<br>
	 */
	private PersonInfoLoginProfile getPersonInfoLoginProfile()
	{
		PersonInfoLoginProfile loginProfile = null;
		Collection<PersonInfo> personInfoCollection = person.getPersonInfoCollection();
		if (personInfoCollection != null && !personInfoCollection.isEmpty())
		{
			for (PersonInfo info : personInfoCollection)
			{
				if (info instanceof PersonInfoLoginProfile)
				{
					loginProfile = (PersonInfoLoginProfile) info;
					break;
				}
			}
		}
		if (loginProfile == null)
		{
			loginProfile = new PersonInfoLoginProfile();
			if (personInfoCollection == null)
			{
				person.setPersonInfoCollection(new ArrayList<>());
			}
			person.getPersonInfoCollection().add(loginProfile);
		}
		loginProfile.setSecurityRoles(new ArrayList<>());
		return loginProfile;
	}
}
