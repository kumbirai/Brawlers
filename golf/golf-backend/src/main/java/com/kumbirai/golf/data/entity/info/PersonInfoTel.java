package com.kumbirai.golf.data.entity.info;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoTel<br>
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
public class PersonInfoTel extends PersonInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 3)
	private String countryCode = "27";
	@Column(nullable = false, length = 3)
	private String telCode = "";
	@Column(nullable = false, length = 7)
	private String telNo = "";

	/**
	 * Constructor:
	 */
	public PersonInfoTel()
	{
		super();
	}

	/** Getter for the <code>countryCode</code> attribute.<br>
	 * @return String - value of the attribute <code>countryCode</code>.
	 */
	public String getCountryCode()
	{
		return this.countryCode;
	}

	/** Setter for the <code>countryCode</code> attribute.<br>
	 * @param String countryCode
	 */
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	/** Getter for the <code>telCode</code> attribute.<br>
	 * @return String - value of the attribute <code>telCode</code>.
	 */
	public String getTelCode()
	{
		return this.telCode;
	}

	/** Setter for the <code>telCode</code> attribute.<br>
	 * @param String telCode
	 */
	public void setTelCode(String telCode)
	{
		this.telCode = telCode;
	}

	/** Getter for the <code>telNo</code> attribute.<br>
	 * @return String - value of the attribute <code>telNo</code>.
	 */
	public String getTelNo()
	{
		return this.telNo;
	}

	/** Setter for the <code>telNo</code> attribute.<br>
	 * @param String telNo
	 */
	public void setTelNo(String telNo)
	{
		this.telNo = telNo;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("PersonInfoTel [%s, %s %s, %s %s %s]", this.getPersonInfoNo(), this.getPerson().getFirstName(), this.getPerson().getLastName(),
				this.countryCode, this.telCode, this.telNo);
	}
}