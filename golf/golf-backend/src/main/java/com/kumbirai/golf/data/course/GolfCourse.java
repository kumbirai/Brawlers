package com.kumbirai.golf.data.course;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import com.kumbirai.golf.data.ValueObject;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfCourse<br>
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
public class GolfCourse extends ValueObject implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = TABLE, generator = "GolfCourseSeq")
	@TableGenerator(name = "GolfCourseSeq", allocationSize = 3, initialValue = 1)
	private Long golfCourseNo;
	@Column(length = 250, nullable = false)
	private String courseName = "";
	@OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "course")
	private Collection<HoleInfo> holes = new ArrayList<>();

	/**
	 * Constructor:
	 */
	public GolfCourse()
	{
		super();
		holes = new ArrayList<>();
		for (int i = 1; i <= 18; i++)
		{
			HoleInfo holeInfo = new HoleInfo();
			holeInfo.setHoleNumber(i);
			holeInfo.setCourse(this);
			holes.add(holeInfo);
		}
	}

	/** Getter for the <code>golfCourseNo</code> attribute.<br>
	 * @return Long - value of the attribute <code>golfCourseNo</code>.
	 */
	public Long getGolfCourseNo()
	{
		return this.golfCourseNo;
	}

	/** Setter for the <code>golfCourseNo</code> attribute.<br>
	 * @param Long golfCourseNo
	 */
	public void setGolfCourseNo(Long golfCourseNo)
	{
		this.golfCourseNo = golfCourseNo;
	}

	/** Getter for the <code>courseName</code> attribute.<br>
	 * @return String - value of the attribute <code>courseName</code>.
	 */
	public String getCourseName()
	{
		return this.courseName;
	}

	/** Setter for the <code>courseName</code> attribute.<br>
	 * @param String courseName
	 */
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}

	/** Getter for the <code>holes</code> attribute.<br>
	 * @return Collection<HoleInfo> - value of the attribute <code>holes</code>.
	 */
	public Collection<HoleInfo> getHoles()
	{
		return this.holes;
	}

	/** Setter for the <code>holes</code> attribute.<br>
	 * @param Collection<HoleInfo> holes
	 */
	public void setHoles(Collection<HoleInfo> holes)
	{
		this.holes = holes;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("%s", this.courseName);
	}
}