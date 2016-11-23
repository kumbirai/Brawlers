package com.kumbirai.golf.data.entity.info;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoWebAddress<br>
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
public class PersonInfoWebAddress extends PersonInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String webAddress = "";

	/**
	 * Constructor:
	 */
	public PersonInfoWebAddress()
	{
		super();
	}

	/** Getter for the <code>webAddress</code> attribute.<br>
	 * @return String - value of the attribute <code>webAddress</code>.
	 */
	public String getWebAddress()
	{
		return this.webAddress;
	}

	/** Setter for the <code>webAddress</code> attribute.<br>
	 * @param String webAddress
	 */
	public void setWebAddress(String webAddress)
	{
		this.webAddress = webAddress;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("PersonInfoEmail [%s, %s %s, %s]", this.getPersonInfoNo(), this.getPerson().getFirstName(), this.getPerson().getLastName(),
				this.webAddress);
	}
}