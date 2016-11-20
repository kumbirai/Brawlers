package com.kumbirai.golf.data.event;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.TABLE;
import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.validation.constraints.Min;

import com.kumbirai.golf.data.ValueObject;
import com.kumbirai.golf.data.course.GolfCourse;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.score.Match;

@Entity
public class GolfEvent extends ValueObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = TABLE, generator = "EventSeq")
	@TableGenerator(name = "EventSeq", allocationSize = 3, initialValue = 1)
	private Long eventNo;
	@Temporal(DATE)
	private Date eventDate;
	@OneToOne
	@JoinColumn(name = "golfCourseNo", referencedColumnName = "golfCourseNo")
	private GolfCourse course;
	@Column(length = 50)
	private String teeOff;
	@Column(length = 250)
	private String attire;
	@Min(value = 0, message = "Can't have negative amount for Green Fees")
	private BigDecimal greenFees;
	@Column(length = 250)
	private String eventSponsor;
	private Integer availableSlots;
	@JoinTable(name = "EventConfirmedPlayers", joinColumns = @JoinColumn(name = "eventNo", referencedColumnName = "eventNo"),
			inverseJoinColumns = @JoinColumn(name = "personNo", referencedColumnName = "personNo"))
	@OneToMany(fetch = EAGER)
	private Collection<Person> confirmedPlayers;
	@OneToMany(mappedBy = "golfEvent", fetch = EAGER)
	private Collection<Match> matches;
	@OneToMany(fetch = EAGER, mappedBy = "golfEvent", cascade = ALL)
	private Collection<GolfEventResult> golfEventResults;
	@Column(nullable = false)
	private boolean complete = false;
	private boolean stableford = true;

	/**
	 * Constructor:
	 */
	public GolfEvent()
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

	/** Getter for the <code>eventDate</code> attribute.<br>
	 * @return Date - value of the attribute <code>eventDate</code>.
	 */
	public Date getEventDate()
	{
		return this.eventDate;
	}

	/** Setter for the <code>eventDate</code> attribute.<br>
	 * @param Date eventDate
	 */
	public void setEventDate(Date eventDate)
	{
		this.eventDate = eventDate;
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

	/** Getter for the <code>teeOff</code> attribute.<br>
	 * @return String - value of the attribute <code>teeOff</code>.
	 */
	public String getTeeOff()
	{
		return this.teeOff;
	}

	/** Setter for the <code>teeOff</code> attribute.<br>
	 * @param String teeOff
	 */
	public void setTeeOff(String teeOff)
	{
		this.teeOff = teeOff;
	}

	/** Getter for the <code>attire</code> attribute.<br>
	 * @return String - value of the attribute <code>attire</code>.
	 */
	public String getAttire()
	{
		return this.attire;
	}

	/** Setter for the <code>attire</code> attribute.<br>
	 * @param String attire
	 */
	public void setAttire(String attire)
	{
		this.attire = attire;
	}

	/** Getter for the <code>greenFees</code> attribute.<br>
	 * @return BigDecimal - value of the attribute <code>greenFees</code>.
	 */
	public BigDecimal getGreenFees()
	{
		return this.greenFees;
	}

	/** Setter for the <code>greenFees</code> attribute.<br>
	 * @param BigDecimal greenFees
	 */
	public void setGreenFees(BigDecimal greenFees)
	{
		this.greenFees = greenFees;
	}

	/** Getter for the <code>eventSponsor</code> attribute.<br>
	 * @return String - value of the attribute <code>eventSponsor</code>.
	 */
	public String getEventSponsor()
	{
		return this.eventSponsor;
	}

	/** Setter for the <code>eventSponsor</code> attribute.<br>
	 * @param String eventSponsor
	 */
	public void setEventSponsor(String eventSponsor)
	{
		this.eventSponsor = eventSponsor;
	}

	/** Getter for the <code>availableSlots</code> attribute.<br>
	 * @return Integer - value of the attribute <code>availableSlots</code>.
	 */
	public Integer getAvailableSlots()
	{
		return this.availableSlots;
	}

	/** Setter for the <code>availableSlots</code> attribute.<br>
	 * @param Integer availableSlots
	 */
	public void setAvailableSlots(Integer availableSlots)
	{
		this.availableSlots = availableSlots;
	}

	/** Getter for the <code>confirmedPlayers</code> attribute.<br>
	 * @return Collection<Person> - value of the attribute <code>confirmedPlayers</code>.
	 */
	public Collection<Person> getConfirmedPlayers()
	{
		return this.confirmedPlayers;
	}

	/** Setter for the <code>confirmedPlayers</code> attribute.<br>
	 * @param Collection<Person> confirmedPlayers
	 */
	public void setConfirmedPlayers(Collection<Person> confirmedPlayers)
	{
		this.confirmedPlayers = confirmedPlayers;
	}

	/** Getter for the <code>matches</code> attribute.<br>
	 * @return Collection<Match> - value of the attribute <code>matches</code>.
	 */
	public Collection<Match> getMatches()
	{
		return this.matches;
	}

	/** Setter for the <code>matches</code> attribute.<br>
	 * @param Collection<Match> matches
	 */
	public void setMatches(Collection<Match> matches)
	{
		this.matches = matches;
	}

	/** Getter for the <code>golfEventResults</code> attribute.<br>
	 * @return Collection<GolfEventResult> - value of the attribute <code>golfEventResults</code>.
	 */
	public Collection<GolfEventResult> getEventResults()
	{
		return this.golfEventResults;
	}

	/** Setter for the <code>golfEventResults</code> attribute.<br>
	 * @param Collection<GolfEventResult> golfEventResults
	 */
	public void setEventResults(Collection<GolfEventResult> golfEventResults)
	{
		this.golfEventResults = golfEventResults;
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

	/** Getter for the <code>stableford</code> attribute.<br>
	 * @return boolean - value of the attribute <code>stableford</code>.
	 */
	public boolean isStableford()
	{
		return this.stableford;
	}

	/** Setter for the <code>stableford</code> attribute.<br>
	 * @param boolean stableford
	 */
	public void setStableford(boolean stableford)
	{
		this.stableford = stableford;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("GolfEvent [No=%s, Date=%s, course=%s]", this.eventNo, this.eventDate, this.course.getCourseName());
	}
}