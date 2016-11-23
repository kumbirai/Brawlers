package com.kumbirai.golf.data.entity.info;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import com.kumbirai.golf.data.security.SecurityRole;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonInfoLoginProfile<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
@Entity
@NamedQuery(name = "PersonInfoLoginProfile.findLoginProfileByUsername", query = "select e from PersonInfoLoginProfile e where e.username = :username")
public class PersonInfoLoginProfile extends PersonInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Column(nullable = false, length = 50, unique = true)
	private String username = "";
	@Column(nullable = false, length = 50)
	private String password = "";
	@ManyToMany(fetch = EAGER)
	@JoinTable(name = "LoginProfileSecurityRole", joinColumns = @JoinColumn(name = "personInfoNo", referencedColumnName = "personInfoNo"),
			inverseJoinColumns = @JoinColumn(name = "securityRoleNo", referencedColumnName = "securityRoleNo"))
	private Collection<SecurityRole> securityRoles;

	public PersonInfoLoginProfile()
	{
		super();
	}

	/** Getter for the <code>username</code> attribute.<br>
	 * @return String - value of the attribute <code>username</code>.
	 */
	public String getUsername()
	{
		return this.username;
	}

	/** Setter for the <code>username</code> attribute.<br>
	 * @param String username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/** Getter for the <code>password</code> attribute.<br>
	 * @return String - value of the attribute <code>password</code>.
	 */
	public String getPassword()
	{
		return this.password;
	}

	/** Setter for the <code>password</code> attribute.<br>
	 * @param String password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/** Getter for the <code>securityRoles</code> attribute.<br>
	 * @return Collection<SecurityRole> - value of the attribute <code>securityRoles</code>.
	 */
	public Collection<SecurityRole> getSecurityRoles()
	{
		return this.securityRoles;
	}

	/** Setter for the <code>securityRoles</code> attribute.<br>
	 * @param Collection<SecurityRole> securityRoles
	 */
	public void setSecurityRoles(Collection<SecurityRole> securityRoles)
	{
		this.securityRoles = securityRoles;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("PersonInfoLoginProfile [%s, %s %s, %s, %s]", this.getPersonInfoNo(), this.getPerson().getFirstName(),
				this.getPerson().getLastName(), this.username, this.securityRoles);
	}
}