package com.kumbirai.golf.data.score;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import com.kumbirai.golf.data.ValueObject;
import com.kumbirai.golf.data.course.HoleInfo;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> Score<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 06 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
@Entity
public class Score extends ValueObject implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = TABLE, generator = "ScoreSeq")
	@TableGenerator(name = "ScoreSeq", allocationSize = 3, initialValue = 1)
	private Long scoreNo;
	private Integer holeNumber = 0;
	@OneToOne
	@JoinColumn(name = "scoreCardNo", referencedColumnName = "scoreCardNo")
	private ScoreCard scoreCard;
	@OneToOne
	@JoinColumn(name = "holeInfoNo", referencedColumnName = "holeInfoNo")
	private HoleInfo holeInfo;
	private Integer strokes = 0;
	@Enumerated(STRING)
	private IPSResult ipsResult;

	/**
	 * Constructor:
	 */
	public Score()
	{
		super();
	}

	/** Getter for the <code>scoreNo</code> attribute.<br>
	 * @return Long - value of the attribute <code>scoreNo</code>.
	 */
	public Long getScoreNo()
	{
		return this.scoreNo;
	}

	/** Setter for the <code>scoreNo</code> attribute.<br>
	 * @param Long scoreNo
	 */
	public void setScoreNo(Long scoreNo)
	{
		this.scoreNo = scoreNo;
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

	/** Getter for the <code>scoreCard</code> attribute.<br>
	 * @return ScoreCard - value of the attribute <code>scoreCard</code>.
	 */
	public ScoreCard getScoreCard()
	{
		return this.scoreCard;
	}

	/** Setter for the <code>scoreCard</code> attribute.<br>
	 * @param ScoreCard scoreCard
	 */
	public void setScoreCard(ScoreCard scoreCard)
	{
		this.scoreCard = scoreCard;
	}

	/** Getter for the <code>holeInfo</code> attribute.<br>
	 * @return HoleInfo - value of the attribute <code>holeInfo</code>.
	 */
	public HoleInfo getHoleInfo()
	{
		return this.holeInfo;
	}

	/** Setter for the <code>holeInfo</code> attribute.<br>
	 * @param HoleInfo holeInfo
	 */
	public void setHoleInfo(HoleInfo holeInfo)
	{
		this.holeInfo = holeInfo;
	}

	/** Getter for the <code>strokes</code> attribute.<br>
	 * @return Integer - value of the attribute <code>strokes</code>.
	 */
	public Integer getStrokes()
	{
		return this.strokes;
	}

	/** Setter for the <code>strokes</code> attribute.<br>
	 * @param Integer strokes
	 */
	public void setStrokes(Integer strokes)
	{
		this.strokes = strokes;
	}

	/** Getter for the <code>ipsResult</code> attribute.<br>
	 * @return IPSResult - value of the attribute <code>ipsResult</code>.
	 */
	public IPSResult getIpsResult()
	{
		return this.ipsResult;
	}

	/** Setter for the <code>ipsResult</code> attribute.<br>
	 * @param IPSResult ipsResult
	 */
	public void setIpsResult(IPSResult ipsResult)
	{
		this.ipsResult = ipsResult;
	}
}