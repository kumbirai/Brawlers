package com.kumbirai.golf.authentication;

import java.io.Serializable;

import com.kumbirai.security.principal.ISecurityPrincipal;

/**
 * Simple interface for authentication and authorization checks.
 */
public interface AccessControl extends Serializable
{
	/**
	 * Purpose:
	 * <br>
	 * signIn<br>
	 * <br>
	 * @param username
	 * @param password
	 * @return<br>
	 */
	public boolean signIn(String username, String password);

	/**
	 * Purpose:
	 * <br>
	 * isUserSignedIn<br>
	 * <br>
	 * @return<br>
	 */
	public boolean isUserSignedIn();

	/**
	 * Purpose:
	 * <br>
	 * isUserInRole<br>
	 * <br>
	 * @param role
	 * @return<br>
	 */
	public boolean isUserInRole(String role);

	/**
	 * Purpose:
	 * <br>
	 * getSecurityPrincipal<br>
	 * <br>
	 * @return<br>
	 */
	public ISecurityPrincipal getSecurityPrincipal();
}
