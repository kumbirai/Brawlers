package com.kumbirai.golf.data.score;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.kumbirai.golf.data.ValueObject;
import com.kumbirai.golf.data.event.GolfEvent;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> Match<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 08 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
@Entity
@Table(name = "MatchUp")
public class Match extends ValueObject implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = TABLE, generator = "MatchSeq")
	@TableGenerator(name = "MatchSeq", allocationSize = 3, initialValue = 1)
	private Long matchNo;
	@OneToOne
	@JoinColumn(name = "eventNo", referencedColumnName = "eventNo")
	private GolfEvent golfEvent;
	private Integer eventMatchNo = 0;
	@OneToMany(fetch = EAGER, cascade = ALL, mappedBy = "matchUp")
	private Collection<ScoreCard> scoreCards = new ArrayList<>();

	/**
	 * Constructor:
	 */
	public Match()
	{
		super();
	}

	/** Getter for the <code>matchNo</code> attribute.<br>
	 * @return Long - value of the attribute <code>matchNo</code>.
	 */
	public Long getMatchNo()
	{
		return this.matchNo;
	}

	/** Setter for the <code>matchNo</code> attribute.<br>
	 * @param Long matchNo
	 */
	public void setMatchNo(Long matchNo)
	{
		this.matchNo = matchNo;
	}

	/** Getter for the <code>golfEvent</code> attribute.<br>
	 * @return GolfEvent - value of the attribute <code>golfEvent</code>.
	 */
	public GolfEvent getGolfEvent()
	{
		return this.golfEvent;
	}

	/** Setter for the <code>golfEvent</code> attribute.<br>
	 * @param GolfEvent golfEvent
	 */
	public void setGolfEvent(GolfEvent golfEvent)
	{
		this.golfEvent = golfEvent;
	}

	/** Getter for the <code>eventMatchNo</code> attribute.<br>
	 * @return Integer - value of the attribute <code>eventMatchNo</code>.
	 */
	public Integer getEventMatchNo()
	{
		return this.eventMatchNo;
	}

	/** Setter for the <code>eventMatchNo</code> attribute.<br>
	 * @param Integer eventMatchNo
	 */
	public void setEventMatchNo(Integer eventMatchNo)
	{
		this.eventMatchNo = eventMatchNo;
	}

	/** Getter for the <code>scoreCards</code> attribute.<br>
	 * @return Collection<ScoreCard> - value of the attribute <code>scoreCards</code>.
	 */
	public Collection<ScoreCard> getScoreCards()
	{
		return this.scoreCards;
	}

	/** Setter for the <code>scoreCards</code> attribute.<br>
	 * @param Collection<ScoreCard> scoreCards
	 */
	public void setScoreCards(Collection<ScoreCard> scoreCards)
	{
		this.scoreCards = scoreCards;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Match [%s, %s %s, scoreCards=%s]", this.matchNo, this.golfEvent.getEventDate(), this.golfEvent.getCourse().getCourseName(),
				this.scoreCards);
	}
}