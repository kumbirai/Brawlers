package com.kumbirai.golf.authentication;

import java.util.ArrayList;

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
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;

/**
 * Class for retrieving and setting the name of the current user of the current
 * session (without using JAAS). All methods of this class require that a
 * {@link VaadinRequest} is bound to the current thread.
 *
 *
 * @see com.vaadin.server.VaadinService#getCurrentRequest()
 */
public final class CurrentUser
{
	/**
	 * The attribute key used to store the username in the session.
	 */
	public static final String CURRENT_USER_SESSION_ATTRIBUTE_KEY = CurrentUser.class.getCanonicalName();

	/**
	 * Constructor:
	 */
	private CurrentUser()
	{
		super();
	}

	/**
	 * Returns the name of the current user stored in the current session, or an
	 * empty string if no user name is stored.
	 *
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	public static ISecurityPrincipal get()
	{
		ISecurityPrincipal currentUser = (ISecurityPrincipal) getCurrentRequest().getWrappedSession().getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
		if (currentUser == null)
		{
			return null;
		}
		else
		{
			return currentUser;
		}
	}

	/**
	 * Sets the name of the current user and stores it in the current session.
	 * Using a {@code null} username will remove the username from the session.
	 *
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	public static void set(ISecurityPrincipal currentUser)
	{
		if (currentUser == null)
		{
			getCurrentRequest().getWrappedSession().removeAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
		}
		else
		{
			getCurrentRequest().getWrappedSession().setAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY, currentUser);
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * getCurrentRequest<br>
	 * <br>
	 * @return<br>
	 */
	private static VaadinRequest getCurrentRequest()
	{
		VaadinRequest request = VaadinService.getCurrentRequest();
		if (request == null)
		{
			throw new IllegalStateException("No request bound to current thread");
		}
		return request;
	}

	public static void updateUser(Long personNo)
	{
		Person person = DataService.get().getPerson(get(), personNo);
		SecurityPrincipal securityPrincipal = (SecurityPrincipal) SecurityPrincipalFactory.getInstance().getSecurityPrincipal(person.getPersonNo(),
				person.getFirstName(), person.getLastName());
		securityPrincipal.setUserRoles(new ArrayList<>());
		PersonInfoLoginProfile profile = getPersonInfoLoginProfile(person);
		for (SecurityRole role : profile.getSecurityRoles())
			securityPrincipal.getUserRoles().add(role.getRole());
		securityPrincipal.setTitle(person.getTitle().name());
		securityPrincipal.setEntityNo(person.getPersonNo());
		for (PersonInfo info : person.getPersonInfoCollection())
		{
			if (info instanceof PersonInfoEmail && ((PersonInfoEmail) info).getStatus() == EStatus.ACTIVE)
				securityPrincipal.setEmail(((PersonInfoEmail) info).getEmailAddress());
		}
		set(securityPrincipal);
	}

	/**
	 * Purpose:
	 * <br>
	 * getPersonInfoLoginProfile<br>
	 * <br>
	 * @param person
	 * @return<br>
	 */
	private static PersonInfoLoginProfile getPersonInfoLoginProfile(Person person)
	{
		for (PersonInfo info : person.getPersonInfoCollection())
		{
			if (info instanceof PersonInfoLoginProfile)
				return (PersonInfoLoginProfile) info;
		}
		PersonInfoLoginProfile personInfoLoginProfile = new PersonInfoLoginProfile();
		personInfoLoginProfile.setSecurityRoles(new ArrayList<>());
		return personInfoLoginProfile;
	}

	/**
	 * Purpose:
	 * <br>
	 * getSuperUser<br>
	 * <br>
	 * @return<br>
	 */
	public static ISecurityPrincipal getSuperUser()
	{
		PersonInfoLoginProfile profile = DataService.get().login("superuser", "superuser");
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
}