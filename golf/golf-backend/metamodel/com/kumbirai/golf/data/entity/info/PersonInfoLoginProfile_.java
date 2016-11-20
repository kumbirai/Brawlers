package com.kumbirai.golf.data.entity.info;

import com.kumbirai.golf.data.security.SecurityRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-20T12:49:17.457+0200")
@StaticMetamodel(PersonInfoLoginProfile.class)
public class PersonInfoLoginProfile_ extends PersonInfo_ {
	public static volatile SingularAttribute<PersonInfoLoginProfile, String> username;
	public static volatile SingularAttribute<PersonInfoLoginProfile, String> password;
	public static volatile CollectionAttribute<PersonInfoLoginProfile, SecurityRole> securityRoles;
}
