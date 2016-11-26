package com.kumbirai.golf.data.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.TABLE;
import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

import com.kumbirai.common.lang.StringUtils;
import com.kumbirai.golf.data.EStatus;
import com.kumbirai.golf.data.ValueObject;
import com.kumbirai.golf.data.entity.info.PersonInfo;
import com.kumbirai.golf.data.score.ScoreCard;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> Person<br>
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
public class Person extends ValueObject implements Serializable, Comparable<Person>
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = TABLE, generator = "PersonSeq")
	@TableGenerator(name = "PersonSeq", allocationSize = 3, initialValue = 1)
	private Long personNo;
	@OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "person")
	private Collection<PersonInfo> personInfoCollection = new ArrayList<>();
	@Enumerated(STRING)
	@Column(length = 50, nullable = false)
	private EStatus status = EStatus.ACTIVE;
	@Temporal(DATE)
	@Column(nullable = false)
	private Date statusDate = new Date();
	@Enumerated(STRING)
	@Column(length = 20, nullable = false)
	private ETitle title;
	@Column(length = 10)
	private String initials = "";
	@Column(length = 50)
	private String firstName = "";
	@Column(length = 50)
	private String lastName = "";
	@Enumerated(STRING)
	@Column(length = 10, nullable = false)
	private EGender gender;
	@Lob
	private byte[] profilePic;
	@OneToMany(cascade = ALL, mappedBy = "person")
	private Collection<ScoreCard> scoreCards = new ArrayList<>();

	/**
	 * Constructor:
	 */
	public Person()
	{
		super();
		Date date = new Date();
		this.setStatus(EStatus.ACTIVE);
		this.setStatusDate(date);
	}

	public String getName()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(!"".equals(firstName) ? firstName : "").append(" ");
		sb.append(!"".equals(lastName) ? lastName : "");
		return sb.toString().trim();
	}

	/** Getter for the <code>personNo</code> attribute.<br>
	 * @return Long - value of the attribute <code>personNo</code>.
	 */
	public Long getPersonNo()
	{
		return this.personNo;
	}

	/** Setter for the <code>personNo</code> attribute.<br>
	 * @param Long personNo
	 */
	public void setPersonNo(Long personNo)
	{
		this.personNo = personNo;
	}

	/** Getter for the <code>personInfoCollection</code> attribute.<br>
	 * @return Collection<PersonInfo> - value of the attribute <code>personInfoCollection</code>.
	 */
	public Collection<PersonInfo> getPersonInfoCollection()
	{
		return this.personInfoCollection;
	}

	/** Setter for the <code>personInfoCollection</code> attribute.<br>
	 * @param Collection<PersonInfo> personInfoCollection
	 */
	public void setPersonInfoCollection(Collection<PersonInfo> personInfoCollection)
	{
		this.personInfoCollection = personInfoCollection;
	}

	/** Getter for the <code>status</code> attribute.<br>
	 * @return EStatus - value of the attribute <code>status</code>.
	 */
	public EStatus getStatus()
	{
		return this.status;
	}

	/** Setter for the <code>status</code> attribute.<br>
	 * @param EStatus status
	 */
	public void setStatus(EStatus status)
	{
		this.status = status;
	}

	/** Getter for the <code>statusDate</code> attribute.<br>
	 * @return Date - value of the attribute <code>statusDate</code>.
	 */
	public Date getStatusDate()
	{
		return this.statusDate;
	}

	/** Setter for the <code>statusDate</code> attribute.<br>
	 * @param Date statusDate
	 */
	public void setStatusDate(Date statusDate)
	{
		this.statusDate = statusDate;
	}

	/** Getter for the <code>title</code> attribute.<br>
	 * @return ETitle - value of the attribute <code>title</code>.
	 */
	public ETitle getTitle()
	{
		return this.title;
	}

	/** Setter for the <code>title</code> attribute.<br>
	 * @param ETitle title
	 */
	public void setTitle(ETitle title)
	{
		this.title = title;
	}

	/** Getter for the <code>initials</code> attribute.<br>
	 * @return String - value of the attribute <code>initials</code>.
	 */
	public String getInitials()
	{
		return this.initials;
	}

	/** Setter for the <code>initials</code> attribute.<br>
	 * @param String initials
	 */
	public void setInitials(String initials)
	{
		this.initials = initials;
	}

	/** Getter for the <code>firstName</code> attribute.<br>
	 * @return String - value of the attribute <code>firstName</code>.
	 */
	public String getFirstName()
	{
		return this.firstName;
	}

	/** Setter for the <code>firstName</code> attribute.<br>
	 * @param String firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/** Getter for the <code>lastName</code> attribute.<br>
	 * @return String - value of the attribute <code>lastName</code>.
	 */
	public String getLastName()
	{
		return this.lastName;
	}

	/** Setter for the <code>lastName</code> attribute.<br>
	 * @param String lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/** Getter for the <code>gender</code> attribute.<br>
	 * @return EGender - value of the attribute <code>gender</code>.
	 */
	public EGender getGender()
	{
		return this.gender;
	}

	/** Setter for the <code>gender</code> attribute.<br>
	 * @param EGender gender
	 */
	public void setGender(EGender gender)
	{
		this.gender = gender;
	}

	/** Getter for the <code>profilePic</code> attribute.<br>
	 * @return byte[] - value of the attribute <code>profilePic</code>.
	 */
	public byte[] getProfilePic()
	{
		return this.profilePic;
	}

	/** Setter for the <code>profilePic</code> attribute.<br>
	 * @param byte[] profilePic
	 */
	public void setProfilePic(byte[] profilePic)
	{
		this.profilePic = profilePic;
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
		result = prime * result + ((this.initials == null) ? 0 : this.initials.hashCode());
		result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		return result;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (this.firstName == null)
		{
			if (other.firstName != null)
				return false;
		}
		else if (!this.firstName.equals(other.firstName))
			return false;
		if (this.initials == null)
		{
			if (other.initials != null)
				return false;
		}
		else if (!this.initials.equals(other.initials))
			return false;
		if (this.lastName == null)
		{
			if (other.lastName != null)
				return false;
		}
		else if (!this.lastName.equals(other.lastName))
			return false;
		if (this.title != other.title)
			return false;
		return true;
	}

	/** (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Person other)
	{
		return (this.lastName.trim() + " " + this.firstName.trim()).compareToIgnoreCase(other.lastName.trim() + " " + other.firstName.trim());
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Person [%s, %s %s %s, %s (%s) - %s]", this.personNo, this.title, this.firstName, this.lastName, this.status,
				StringUtils.formatDate(this.statusDate, "dd MMM yyyy"), this.gender);
	}
}