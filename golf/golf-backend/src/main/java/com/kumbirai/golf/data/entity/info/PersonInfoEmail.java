package com.kumbirai.golf.data.entity.info;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoEmail<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 04 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
@Entity
public class PersonInfoEmail extends PersonInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String emailAddress = "";

	/**
	 * Constructor:
	 */
	public PersonInfoEmail()
	{
		super();
	}

	/** Getter for the <code>emailAddress</code> attribute.<br>
	 * @return String - value of the attribute <code>emailAddress</code>.
	 */
	public String getEmailAddress()
	{
		return this.emailAddress;
	}

	/** Setter for the <code>emailAddress</code> attribute.<br>
	 * @param String emailAddress
	 */
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("PersonInfoEmail [%s, %s %s, %s]", this.getPersonInfoNo(), this.getPerson().getFirstName(), this.getPerson().getLastName(),
				this.emailAddress);
	}
}