package com.kumbirai.golf.data.entity.info;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: PersonInfoGolfDetails
 *
 */
@Entity
public class PersonInfoGolfDetails extends PersonInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(length = 50)
	private String memberNo = "";
	private Integer handicap = 0;

	/**
	 * Constructor:
	 */
	public PersonInfoGolfDetails()
	{
		super();
	}

	/** Getter for the <code>memberNo</code> attribute.<br>
	 * @return String - value of the attribute <code>memberNo</code>.
	 */
	public String getMemberNo()
	{
		return this.memberNo;
	}

	/** Setter for the <code>memberNo</code> attribute.<br>
	 * @param String memberNo
	 */
	public void setMemberNo(String memberNo)
	{
		this.memberNo = memberNo;
	}

	/** Getter for the <code>handicap</code> attribute.<br>
	 * @return Integer - value of the attribute <code>handicap</code>.
	 */
	public Integer getHandicap()
	{
		return this.handicap;
	}

	/** Setter for the <code>handicap</code> attribute.<br>
	 * @param Integer handicap
	 */
	public void setHandicap(Integer handicap)
	{
		this.handicap = handicap;
	}
}