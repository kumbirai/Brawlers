package com.kumbirai.golf.data.security;

import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-12-14T22:22:28.418+0200")
@StaticMetamodel(SecurityRole.class)
public class SecurityRole_ {
	public static volatile SingularAttribute<SecurityRole, Long> securityRoleNo;
	public static volatile SingularAttribute<SecurityRole, String> role;
	public static volatile CollectionAttribute<SecurityRole, PersonInfoLoginProfile> loginProfiles;
}
