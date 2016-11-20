package com.kumbirai.golf.data.score;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import com.kumbirai.golf.data.ValueObject;
import com.kumbirai.golf.data.entity.Person;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> ScoreCard<br>
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
public class ScoreCard extends ValueObject implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = TABLE, generator = "ScoreCardSeq")
	@TableGenerator(name = "ScoreCardSeq", allocationSize = 3, initialValue = 1)
	private Long scoreCardNo;
	@OneToOne
	@JoinColumn(name = "personNo", referencedColumnName = "personNo")
	private Person person;
	@OneToMany(fetch = EAGER, cascade = ALL, mappedBy = "scoreCard")
	private Collection<Score> scores;
	@OneToOne
	@JoinColumn(name = "matchNo", referencedColumnName = "matchNo")
	private Match matchUp;
	private Integer handicap;
	private Integer totalStrokes;
	private Integer netStrokes;
	private Integer classicPoints;
	private Integer standardPoints;
	private boolean verified = false;
	private boolean complete = false;

	/**
	 * Constructor:
	 */
	public ScoreCard()
	{
		super();
	}

	/** Getter for the <code>scoreCardNo</code> attribute.<br>
	 * @return Long - value of the attribute <code>scoreCardNo</code>.
	 */
	public Long getScoreCardNo()
	{
		return this.scoreCardNo;
	}

	/** Setter for the <code>scoreCardNo</code> attribute.<br>
	 * @param Long scoreCardNo
	 */
	public void setScoreCardNo(Long scoreCardNo)
	{
		this.scoreCardNo = scoreCardNo;
	}

	/** Getter for the <code>person</code> attribute.<br>
	 * @return Person - value of the attribute <code>person</code>.
	 */
	public Person getPerson()
	{
		return this.person;
	}

	/** Setter for the <code>person</code> attribute.<br>
	 * @param Person person
	 */
	public void setPerson(Person person)
	{
		this.person = person;
	}

	/** Getter for the <code>scores</code> attribute.<br>
	 * @return Collection<Score> - value of the attribute <code>scores</code>.
	 */
	public Collection<Score> getScores()
	{
		return this.scores;
	}

	/** Setter for the <code>scores</code> attribute.<br>
	 * @param Collection<Score> scores
	 */
	public void setScores(Collection<Score> scores)
	{
		this.scores = scores;
	}

	/** Getter for the <code>matchUp</code> attribute.<br>
	 * @return Match - value of the attribute <code>matchUp</code>.
	 */
	public Match getMatchUp()
	{
		return this.matchUp;
	}

	/** Setter for the <code>matchUp</code> attribute.<br>
	 * @param Match matchUp
	 */
	public void setMatchUp(Match matchUp)
	{
		this.matchUp = matchUp;
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

	/** Getter for the <code>totalStrokes</code> attribute.<br>
	 * @return Integer - value of the attribute <code>totalStrokes</code>.
	 */
	public Integer getTotalStrokes()
	{
		return this.totalStrokes;
	}

	/** Setter for the <code>totalStrokes</code> attribute.<br>
	 * @param Integer totalStrokes
	 */
	public void setTotalStrokes(Integer totalStrokes)
	{
		this.totalStrokes = totalStrokes;
	}

	/** Getter for the <code>netStrokes</code> attribute.<br>
	 * @return Integer - value of the attribute <code>netStrokes</code>.
	 */
	public Integer getNetStrokes()
	{
		return this.netStrokes;
	}

	/** Setter for the <code>netStrokes</code> attribute.<br>
	 * @param Integer netStrokes
	 */
	public void setNetStrokes(Integer netStrokes)
	{
		this.netStrokes = netStrokes;
	}

	/** Getter for the <code>classicPoints</code> attribute.<br>
	 * @return Integer - value of the attribute <code>classicPoints</code>.
	 */
	public Integer getClassicPoints()
	{
		return this.classicPoints;
	}

	/** Setter for the <code>classicPoints</code> attribute.<br>
	 * @param Integer classicPoints
	 */
	public void setClassicPoints(Integer classicPoints)
	{
		this.classicPoints = classicPoints;
	}

	/** Getter for the <code>standardPoints</code> attribute.<br>
	 * @return Integer - value of the attribute <code>standardPoints</code>.
	 */
	public Integer getStandardPoints()
	{
		return this.standardPoints;
	}

	/** Setter for the <code>standardPoints</code> attribute.<br>
	 * @param Integer standardPoints
	 */
	public void setStandardPoints(Integer standardPoints)
	{
		this.standardPoints = standardPoints;
	}

	/** Getter for the <code>verified</code> attribute.<br>
	 * @return boolean - value of the attribute <code>verified</code>.
	 */
	public boolean isVerified()
	{
		return this.verified;
	}

	/** Setter for the <code>verified</code> attribute.<br>
	 * @param boolean verified
	 */
	public void setVerified(boolean verified)
	{
		this.verified = verified;
	}

	/** Getter for the <code>complete</code> attribute.<br>
	 * @return boolean - value of the attribute <code>complete</code>.
	 */
	public boolean isComplete()
	{
		return this.complete;
	}

	/** Setter for the <code>complete</code> attribute.<br>
	 * @param boolean complete
	 */
	public void setComplete(boolean complete)
	{
		this.complete = complete;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("ScoreCard [cardNo=%s, %s, handicap=%s, strokes=%s, net=%s, points=%s]", this.scoreCardNo, this.person.getName(), this.handicap,
				this.totalStrokes, this.netStrokes, this.standardPoints);
	}
}