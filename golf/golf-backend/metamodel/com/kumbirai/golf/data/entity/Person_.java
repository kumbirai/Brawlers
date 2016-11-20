package com.kumbirai.golf.data.entity;

import com.kumbirai.golf.data.EStatus;
import com.kumbirai.golf.data.entity.info.PersonInfo;
import com.kumbirai.golf.data.score.ScoreCard;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-20T12:49:17.476+0200")
@StaticMetamodel(Person.class)
public class Person_ {
	public static volatile SingularAttribute<Person, Long> personNo;
	public static volatile CollectionAttribute<Person, PersonInfo> personInfoCollection;
	public static volatile SingularAttribute<Person, EStatus> status;
	public static volatile SingularAttribute<Person, Date> statusDate;
	public static volatile SingularAttribute<Person, ETitle> title;
	public static volatile SingularAttribute<Person, String> initials;
	public static volatile SingularAttribute<Person, String> firstName;
	public static volatile SingularAttribute<Person, String> lastName;
	public static volatile SingularAttribute<Person, EGender> gender;
	public static volatile CollectionAttribute<Person, ScoreCard> scoreCards;
}
