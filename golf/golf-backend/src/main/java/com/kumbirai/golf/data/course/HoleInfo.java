package com.kumbirai.golf.data.course;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> HoleInfo<br>
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
public class HoleInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = TABLE, generator = "HoleInfoSeq")
	@TableGenerator(name = "HoleInfoSeq", allocationSize = 3, initialValue = 1)
	private Long holeInfoNo;
	@OneToOne(fetch = EAGER)
	@JoinColumn(name = "golfCourseNo", referencedColumnName = "golfCourseNo")
	private GolfCourse course;
	private Integer holeNumber = 0;
	private Integer menParRating = 0;
	private Integer menStrokeRating = 0;
	private Integer ladiesParRating = 0;
	private Integer ladiesStrokeRating = 0;

	/**
	 * Constructor:
	 */
	public HoleInfo()
	{
		super();
	}

	/** Getter for the <code>holeInfoNo</code> attribute.<br>
	 * @return Long - value of the attribute <code>holeInfoNo</code>.
	 */
	public Long getHoleInfoNo()
	{
		return this.holeInfoNo;
	}

	/** Setter for the <code>holeInfoNo</code> attribute.<br>
	 * @param Long holeInfoNo
	 */
	public void setHoleInfoNo(Long holeInfoNo)
	{
		this.holeInfoNo = holeInfoNo;
	}

	/** Getter for the <code>course</code> attribute.<br>
	 * @return GolfCourse - value of the attribute <code>course</code>.
	 */
	public GolfCourse getCourse()
	{
		return this.course;
	}

	/** Setter for the <code>course</code> attribute.<br>
	 * @param GolfCourse course
	 */
	public void setCourse(GolfCourse course)
	{
		this.course = course;
	}

	/** Getter for the <code>holeNumber</code> attribute.<br>
	 * @return Integer - value of the attribute <code>holeNumber</code>.
	 */
	public Integer getHoleNumber()
	{
		return this.holeNumber;
	}

	/** Setter for the <code>holeNumber</code> attribute.<br>
	 * @param Integer holeNumber
	 */
	public void setHoleNumber(Integer holeNumber)
	{
		this.holeNumber = holeNumber;
	}

	/** Getter for the <code>menParRating</code> attribute.<br>
	 * @return Integer - value of the attribute <code>menParRating</code>.
	 */
	public Integer getMenParRating()
	{
		return this.menParRating;
	}

	/** Setter for the <code>menParRating</code> attribute.<br>
	 * @param Integer menParRating
	 */
	public void setMenParRating(Integer menParRating)
	{
		this.menParRating = menParRating;
	}

	/** Getter for the <code>menStrokeRating</code> attribute.<br>
	 * @return Integer - value of the attribute <code>menStrokeRating</code>.
	 */
	public Integer getMenStrokeRating()
	{
		return this.menStrokeRating;
	}

	/** Setter for the <code>menStrokeRating</code> attribute.<br>
	 * @param Integer menStrokeRating
	 */
	public void setMenStrokeRating(Integer menStrokeRating)
	{
		this.menStrokeRating = menStrokeRating;
	}

	/** Getter for the <code>ladiesParRating</code> attribute.<br>
	 * @return Integer - value of the attribute <code>ladiesParRating</code>.
	 */
	public Integer getLadiesParRating()
	{
		return this.ladiesParRating;
	}

	/** Setter for the <code>ladiesParRating</code> attribute.<br>
	 * @param Integer ladiesParRating
	 */
	public void setLadiesParRating(Integer ladiesParRating)
	{
		this.ladiesParRating = ladiesParRating;
	}

	/** Getter for the <code>ladiesStrokeRating</code> attribute.<br>
	 * @return Integer - value of the attribute <code>ladiesStrokeRating</code>.
	 */
	public Integer getLadiesStrokeRating()
	{
		return this.ladiesStrokeRating;
	}

	/** Setter for the <code>ladiesStrokeRating</code> attribute.<br>
	 * @param Integer ladiesStrokeRating
	 */
	public void setLadiesStrokeRating(Integer ladiesStrokeRating)
	{
		this.ladiesStrokeRating = ladiesStrokeRating;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("[course=%s (hole=%s, Men's Par=%s, Men's Stroke=%s, Ladies Par=%s, Ladies Stroke=%s)]", this.course.getCourseName(),
				this.holeNumber, this.menParRating, this.menStrokeRating, this.ladiesParRating, this.ladiesStrokeRating);
	}
}