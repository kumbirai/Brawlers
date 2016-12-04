/*
 com.kumbirai.golf.data.ValueObject<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.data;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import com.kumbirai.data.IValueObject;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> ValueObject<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 04 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
@MappedSuperclass
public abstract class ValueObject implements IValueObject
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private long audCreateUser;
	@Temporal(TIMESTAMP)
	@Column(nullable = false)
	private Date audCreateDate = new Date();
	@Column(nullable = false)
	private long audModifyUser;
	@Temporal(TIMESTAMP)
	@Column(nullable = false)
	private Date audModifyDate = new Date();

	/**
	 * Constructor:
	 */
	public ValueObject()
	{
		super();
	}

	/** Getter for the <code>audCreateUser</code> attribute.<br>
	 * @return long - value of the attribute <code>audCreateUser</code>.
	 */
	@Override
	public long getAudCreateUser()
	{
		return this.audCreateUser;
	}

	/** Setter for the <code>audCreateUser</code> attribute.<br>
	 * @param long audCreateUser
	 */
	@Override
	public void setAudCreateUser(long audCreateUser)
	{
		this.audCreateUser = audCreateUser;
	}

	/** Getter for the <code>audCreateDate</code> attribute.<br>
	 * @return Date - value of the attribute <code>audCreateDate</code>.
	 */
	@Override
	public Date getAudCreateDate()
	{
		return this.audCreateDate;
	}

	/** Setter for the <code>audCreateDate</code> attribute.<br>
	 * @param Date audCreateDate
	 */
	@Override
	public void setAudCreateDate(Date audCreateDate)
	{
		this.audCreateDate = audCreateDate;
	}

	/** Getter for the <code>audModifyUser</code> attribute.<br>
	 * @return long - value of the attribute <code>audModifyUser</code>.
	 */
	@Override
	public long getAudModifyUser()
	{
		return this.audModifyUser;
	}

	/** Setter for the <code>audModifyUser</code> attribute.<br>
	 * @param long audModifyUser
	 */
	@Override
	public void setAudModifyUser(long audModifyUser)
	{
		this.audModifyUser = audModifyUser;
	}

	/** Getter for the <code>audModifyDate</code> attribute.<br>
	 * @return Date - value of the attribute <code>audModifyDate</code>.
	 */
	@Override
	public Date getAudModifyDate()
	{
		return this.audModifyDate;
	}

	/** Setter for the <code>audModifyDate</code> attribute.<br>
	 * @param Date audModifyDate
	 */
	@Override
	public void setAudModifyDate(Date audModifyDate)
	{
		this.audModifyDate = audModifyDate;
	}
}