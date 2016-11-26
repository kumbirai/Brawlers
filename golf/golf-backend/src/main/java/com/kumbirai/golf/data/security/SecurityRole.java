package com.kumbirai.golf.data.security;

import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;

import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> SecurityRole<br>
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
public class SecurityRole implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = TABLE, generator = "SecurityRoleSeq")
	@TableGenerator(name = "SecurityRoleSeq", allocationSize = 3, initialValue = 1)
	private Long securityRoleNo;
	@Column(length = 100, nullable = false)
	private String role = "";
	@ManyToMany(mappedBy = "securityRoles")
	private Collection<PersonInfoLoginProfile> loginProfiles = new ArrayList<>();

	public SecurityRole()
	{
		super();
	}

	/** Getter for the <code>securityRoleNo</code> attribute.<br>
	 * @return Long - value of the attribute <code>securityRoleNo</code>.
	 */
	public Long getSecurityRoleNo()
	{
		return this.securityRoleNo;
	}

	/** Setter for the <code>securityRoleNo</code> attribute.<br>
	 * @param Long securityRoleNo
	 */
	public void setSecurityRoleNo(Long securityRoleNo)
	{
		this.securityRoleNo = securityRoleNo;
	}

	/** Getter for the <code>role</code> attribute.<br>
	 * @return String - value of the attribute <code>role</code>.
	 */
	public String getRole()
	{
		return this.role;
	}

	/** Setter for the <code>role</code> attribute.<br>
	 * @param String role
	 */
	public void setRole(String role)
	{
		this.role = role;
	}

	/** Getter for the <code>loginProfiles</code> attribute.<br>
	 * @return Collection<PersonInfoLoginProfile> - value of the attribute <code>loginProfiles</code>.
	 */
	public Collection<PersonInfoLoginProfile> getLoginProfiles()
	{
		return this.loginProfiles;
	}

	/** Setter for the <code>loginProfiles</code> attribute.<br>
	 * @param Collection<PersonInfoLoginProfile> loginProfiles
	 */
	public void setLoginProfiles(Collection<PersonInfoLoginProfile> loginProfiles)
	{
		this.loginProfiles = loginProfiles;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Role [%s, %s]", this.securityRoleNo, this.role);
	}
}