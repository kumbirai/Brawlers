package com.kumbirai.golf.data.entity.info;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.TABLE;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;
import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

import com.kumbirai.golf.data.EStatus;
import com.kumbirai.golf.data.ValueObject;
import com.kumbirai.golf.data.entity.Person;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfo<br>
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
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class PersonInfo extends ValueObject implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = TABLE, generator = "PersonInfoSeq")
	@TableGenerator(name = "PersonInfoSeq", allocationSize = 3, initialValue = 1)
	private Long personInfoNo;
	@OneToOne(optional = false)
	@JoinColumn(name = "personNo", referencedColumnName = "personNo")
	private Person person;
	@Enumerated(STRING)
	@Column(nullable = false, length = 50)
	private EStatus status;
	@Column(nullable = false)
	@Temporal(DATE)
	private Date statusDate;

	/**
	 * Constructor:
	 */
	public PersonInfo()
	{
		super();
		Date date = new Date();
		this.setStatus(EStatus.ACTIVE);
		this.setStatusDate(date);
	}

	/** Getter for the <code>personInfoNo</code> attribute.<br>
	 * @return Long - value of the attribute <code>personInfoNo</code>.
	 */
	public Long getPersonInfoNo()
	{
		return this.personInfoNo;
	}

	/** Setter for the <code>personInfoNo</code> attribute.<br>
	 * @param Long personInfoNo
	 */
	public void setPersonInfoNo(Long personInfoNo)
	{
		this.personInfoNo = personInfoNo;
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
}