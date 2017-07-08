package com.kumbirai.golf.data.score;

import com.kumbirai.golf.data.ValueObject_;
import com.kumbirai.golf.data.entity.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-02-11T08:52:30.668+0200")
@StaticMetamodel(ScoreCard.class)
public class ScoreCard_ extends ValueObject_ {
	public static volatile SingularAttribute<ScoreCard, Long> scoreCardNo;
	public static volatile SingularAttribute<ScoreCard, Person> person;
	public static volatile CollectionAttribute<ScoreCard, Score> scores;
	public static volatile SingularAttribute<ScoreCard, Match> matchUp;
	public static volatile SingularAttribute<ScoreCard, Integer> lineNumber;
	public static volatile SingularAttribute<ScoreCard, Integer> handicap;
	public static volatile SingularAttribute<ScoreCard, Integer> totalStrokes;
	public static volatile SingularAttribute<ScoreCard, Integer> netStrokes;
	public static volatile SingularAttribute<ScoreCard, Integer> classicPoints;
	public static volatile SingularAttribute<ScoreCard, Integer> standardPoints;
	public static volatile SingularAttribute<ScoreCard, Boolean> verified;
	public static volatile SingularAttribute<ScoreCard, Boolean> complete;
}
