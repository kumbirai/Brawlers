package com.kumbirai.golf.data.event;

import java.io.Serializable;

/**
 * ID class for entity: GolfEventResult
 *
 */
public class GolfEventResultPK implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long eventNo;
	private Integer position;

	/**
	 * Constructor:
	 */
	public GolfEventResultPK()
	{
		super();
	}

	/**
	 * Constructor: @param eventNo
	 * Constructor: @param position
	 */
	public GolfEventResultPK(Long eventNo, Integer position)
	{
		super();
		this.eventNo = eventNo;
		this.position = position;
	}

	/** Getter for the <code>eventNo</code> attribute.<br>
	 * @return Long - value of the attribute <code>eventNo</code>.
	 */
	public Long getEventNo()
	{
		return this.eventNo;
	}

	/** Setter for the <code>eventNo</code> attribute.<br>
	 * @param Long eventNo
	 */
	public void setEventNo(Long eventNo)
	{
		this.eventNo = eventNo;
	}

	/** Getter for the <code>position</code> attribute.<br>
	 * @return Integer - value of the attribute <code>position</code>.
	 */
	public Integer getPosition()
	{
		return this.position;
	}

	/** Setter for the <code>position</code> attribute.<br>
	 * @param Integer position
	 */
	public void setPosition(Integer position)
	{
		this.position = position;
	}

	/*
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object o)
	{
		if (o == this)
		{
			return true;
		}
		if (!(o instanceof GolfEventResultPK))
		{
			return false;
		}
		GolfEventResultPK other = (GolfEventResultPK) o;
		return true && (getEventNo() == null ? other.getEventNo() == null : getEventNo().equals(other.getEventNo()))
				&& (getPosition() == null ? other.getPosition() == null : getPosition().equals(other.getPosition()));
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (getEventNo() == null ? 0 : getEventNo().hashCode());
		result = prime * result + (getPosition() == null ? 0 : getPosition().hashCode());
		return result;
	}

}
