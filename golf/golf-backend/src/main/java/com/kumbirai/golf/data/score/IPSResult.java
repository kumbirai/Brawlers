/*
 com.kumbirai.golf.data.score.IPSResult<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.data.score;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> IPSResult<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 08 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public enum IPSResult
{
	DOUBLE_EAGLE(8, 5), EAGLE(5, 4), BIRDIE(2, 3), PAR(0, 2), BOGGIE(-1, 1), BLOW(-2, 0);

	private Integer classicPoints;
	private Integer standardPoints;

	/**
	 * Constructor: @param classicPoints
	 * Constructor: @param standardPoints
	 */
	private IPSResult(Integer classicPoints, Integer standardPoints)
	{
		this.classicPoints = classicPoints;
		this.standardPoints = standardPoints;
	}

	/** Getter for the <code>classicPoints</code> attribute.<br>
	 * @return Integer - value of the attribute <code>classicPoints</code>.
	 */
	public Integer getClassicPoints()
	{
		return this.classicPoints;
	}

	/** Getter for the <code>standardPoints</code> attribute.<br>
	 * @return Integer - value of the attribute <code>standardPoints</code>.
	 */
	public Integer getStandardPoints()
	{
		return this.standardPoints;
	}
}