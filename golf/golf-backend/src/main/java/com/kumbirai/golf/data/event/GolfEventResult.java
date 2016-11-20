package com.kumbirai.golf.data.event;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;

import com.kumbirai.golf.data.ValueObject;
import com.kumbirai.golf.data.score.ScoreCard;

/**
 * Entity implementation class for Entity: GolfEventResult
 *
 */
@Entity

@IdClass(GolfEventResultPK.class)
public class GolfEventResult extends ValueObject implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(insertable = false, updatable = false, nullable = false)
	private Long eventNo;
	@Id
	@Column(nullable = false)
	private Integer position;
	@JoinColumn(name = "eventNo", referencedColumnName = "eventNo")
	private GolfEvent golfEvent;
	@JoinColumn(name = "scoreCardNo", referencedColumnName = "scoreCardNo")
	private ScoreCard scoreCard;
	private boolean tieBreaker = false;

	/**
	 * Constructor:
	 */
	public GolfEventResult()
	{
		super();
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

	/** Getter for the <code>golfEvent</code> attribute.<br>
	 * @return GolfEvent - value of the attribute <code>golfEvent</code>.
	 */
	public GolfEvent getEvent()
	{
		return this.golfEvent;
	}

	/** Setter for the <code>golfEvent</code> attribute.<br>
	 * @param GolfEvent golfEvent
	 */
	public void setEvent(GolfEvent golfEvent)
	{
		this.golfEvent = golfEvent;
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

	/** Getter for the <code>tieBreaker</code> attribute.<br>
	 * @return boolean - value of the attribute <code>tieBreaker</code>.
	 */
	public boolean isTieBreaker()
	{
		return this.tieBreaker;
	}

	/** Setter for the <code>tieBreaker</code> attribute.<br>
	 * @param boolean tieBreaker
	 */
	public void setTieBreaker(boolean tieBreaker)
	{
		this.tieBreaker = tieBreaker;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("GolfEventResult [golfEvent=%s, position=%s, player=%s, tieBreaker=%s]", this.eventNo, this.position, this.scoreCard.getPerson().getName(),
				this.tieBreaker);
	}
}