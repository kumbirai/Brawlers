package com.kumbirai.golf.data.course;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-26T12:00:26.841+0200")
@StaticMetamodel(GolfCourse.class)
public class GolfCourse_ {
	public static volatile SingularAttribute<GolfCourse, Long> golfCourseNo;
	public static volatile SingularAttribute<GolfCourse, String> courseName;
	public static volatile CollectionAttribute<GolfCourse, HoleInfo> holes;
}
