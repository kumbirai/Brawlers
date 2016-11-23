package com.kumbirai.golf.data.score;

import com.kumbirai.golf.data.event.GolfEvent;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-23T21:36:59.137+0200")
@StaticMetamodel(Match.class)
public class Match_ {
	public static volatile SingularAttribute<Match, Long> matchNo;
	public static volatile SingularAttribute<Match, GolfEvent> golfEvent;
	public static volatile SingularAttribute<Match, Integer> eventMatchNo;
	public static volatile CollectionAttribute<Match, ScoreCard> scoreCards;
}
