/*
 com.kumbirai.golf.entity.PersonInfoCreatorCmd<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.entity;

import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.commandservice.AbstractCommand;
import com.kumbirai.golf.data.EStatus;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.entity.info.PersonInfo;
import com.kumbirai.golf.data.entity.info.PersonInfoEmail;
import com.kumbirai.golf.data.entity.info.PersonInfoGolfDetails;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;
import com.kumbirai.golf.data.entity.info.PersonInfoTel;
import com.kumbirai.golf.data.entity.info.PersonInfoWebAddress;
import com.kumbirai.security.principal.ISecurityPrincipal;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoCreatorCmd<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 27 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonInfoCreatorCmd extends AbstractCommand
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonInfoCreatorCmd.class.getName());
	private Person person;

	/**
	 * Constructor: @param person
	 */
	public PersonInfoCreatorCmd(Person person)
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
		person.setPersonInfoCollection(new ArrayList<>());
		//
		PersonInfoEmail personInfoEmail = new PersonInfoEmail();
		addPersonInfoMetaData(personInfoEmail);
		person.getPersonInfoCollection().add(personInfoEmail);
		//
		PersonInfoGolfDetails personInfoGolfDetails = new PersonInfoGolfDetails();
		addPersonInfoMetaData(personInfoGolfDetails);
		person.getPersonInfoCollection().add(personInfoGolfDetails);
		//
		PersonInfoLoginProfile personInfoLoginProfile = new PersonInfoLoginProfile();
		addPersonInfoMetaData(personInfoLoginProfile);
		person.getPersonInfoCollection().add(personInfoLoginProfile);
		//
		PersonInfoTel personInfoTel = new PersonInfoTel();
		addPersonInfoMetaData(personInfoTel);
		person.getPersonInfoCollection().add(personInfoTel);
		//
		PersonInfoWebAddress personInfoWebAddress = new PersonInfoWebAddress();
		addPersonInfoMetaData(personInfoWebAddress);
		person.getPersonInfoCollection().add(personInfoWebAddress);
		//
		LOGGER.debug(String.format("Finished updating person %s", person));
	}

	/**
	 * Purpose:
	 * <br>
	 * addPersonInfoMetaData<br>
	 * <br>
	 * @param personInfo<br>
	 */
	private void addPersonInfoMetaData(PersonInfo personInfo)
	{
		personInfo.setPerson(person);
		personInfo.setStatus(EStatus.ACTIVE);
		personInfo.setStatusDate(new Date());
		ISecurityPrincipal securityPrincipal = getSecurityPrincipal();
		if (securityPrincipal != null)
		{
			personInfo.setAudCreateUser(securityPrincipal.getEntityNo());
			personInfo.setAudModifyUser(securityPrincipal.getEntityNo());
		}
	}

	/** Getter for the <code>person</code> attribute.<br>
	 * @return Person - value of the attribute <code>person</code>.
	 */
	public Person getPerson()
	{
		return this.person;
	}
}