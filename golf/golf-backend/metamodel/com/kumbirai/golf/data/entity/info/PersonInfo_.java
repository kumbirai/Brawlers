package com.kumbirai.golf.data.entity.info;

import com.kumbirai.golf.data.EStatus;
import com.kumbirai.golf.data.entity.Person;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-20T12:49:17.413+0200")
@StaticMetamodel(PersonInfo.class)
public class PersonInfo_ {
	public static volatile SingularAttribute<PersonInfo, Long> personInfoNo;
	public static volatile SingularAttribute<PersonInfo, Person> person;
	public static volatile SingularAttribute<PersonInfo, EStatus> status;
	public static volatile SingularAttribute<PersonInfo, Date> statusDate;
}
