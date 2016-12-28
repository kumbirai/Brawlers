package com.kumbirai.golf.data.course;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-12-14T22:22:28.302+0200")
@StaticMetamodel(HoleInfo.class)
public class HoleInfo_ {
	public static volatile SingularAttribute<HoleInfo, Long> holeInfoNo;
	public static volatile SingularAttribute<HoleInfo, GolfCourse> course;
	public static volatile SingularAttribute<HoleInfo, Integer> holeNumber;
	public static volatile SingularAttribute<HoleInfo, Integer> menParRating;
	public static volatile SingularAttribute<HoleInfo, Integer> menStrokeRating;
	public static volatile SingularAttribute<HoleInfo, Integer> ladiesParRating;
	public static volatile SingularAttribute<HoleInfo, Integer> ladiesStrokeRating;
}
