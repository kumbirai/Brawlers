/*
 com.kumbirai.golf.authentication.LoginListener<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.authentication;

import java.io.Serializable;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> LoginListener<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 04 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
@FunctionalInterface
public interface LoginListener extends Serializable
{
	/**
	 * Purpose:
	 * <br>
	 * loginSuccessful<br>
	 * <br><br>
	 */
	void loginSuccessful();
}