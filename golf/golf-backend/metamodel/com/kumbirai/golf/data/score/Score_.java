package com.kumbirai.golf.data.score;

import com.kumbirai.golf.data.course.HoleInfo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-20T12:49:17.507+0200")
@StaticMetamodel(Score.class)
public class Score_ {
	public static volatile SingularAttribute<Score, Long> scoreNo;
	public static volatile SingularAttribute<Score, ScoreCard> scoreCard;
	public static volatile SingularAttribute<Score, HoleInfo> holeInfo;
	public static volatile SingularAttribute<Score, Integer> strokes;
	public static volatile SingularAttribute<Score, IPSResult> ipsResult;
}
