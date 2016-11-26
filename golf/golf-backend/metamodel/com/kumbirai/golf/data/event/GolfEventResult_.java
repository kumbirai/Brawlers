package com.kumbirai.golf.data.event;

import com.kumbirai.golf.data.score.ScoreCard;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-26T12:00:28.107+0200")
@StaticMetamodel(GolfEventResult.class)
public class GolfEventResult_ {
	public static volatile SingularAttribute<GolfEventResult, Long> eventNo;
	public static volatile SingularAttribute<GolfEventResult, Integer> position;
	public static volatile SingularAttribute<GolfEventResult, GolfEvent> golfEvent;
	public static volatile SingularAttribute<GolfEventResult, ScoreCard> scoreCard;
	public static volatile SingularAttribute<GolfEventResult, Boolean> tieBreaker;
}
