package com.kumbirai.golf.data.event;

import com.kumbirai.golf.data.ValueObject_;
import com.kumbirai.golf.data.course.GolfCourse;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.score.Match;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-02-11T08:52:30.641+0200")
@StaticMetamodel(GolfEvent.class)
public class GolfEvent_ extends ValueObject_ {
	public static volatile SingularAttribute<GolfEvent, Long> eventNo;
	public static volatile SingularAttribute<GolfEvent, Date> eventDate;
	public static volatile SingularAttribute<GolfEvent, GolfCourse> course;
	public static volatile SingularAttribute<GolfEvent, String> teeOff;
	public static volatile SingularAttribute<GolfEvent, String> attire;
	public static volatile SingularAttribute<GolfEvent, BigDecimal> greenFees;
	public static volatile SingularAttribute<GolfEvent, String> eventSponsor;
	public static volatile SingularAttribute<GolfEvent, Integer> availableSlots;
	public static volatile CollectionAttribute<GolfEvent, Person> confirmedPlayers;
	public static volatile CollectionAttribute<GolfEvent, Match> matches;
	public static volatile CollectionAttribute<GolfEvent, GolfEventResult> golfEventResults;
	public static volatile SingularAttribute<GolfEvent, Boolean> complete;
	public static volatile SingularAttribute<GolfEvent, Boolean> stableford;
}
