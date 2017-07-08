package com.kumbirai.golf.data.course;

import com.kumbirai.golf.data.ValueObject_;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-02-11T08:52:30.576+0200")
@StaticMetamodel(GolfCourse.class)
public class GolfCourse_ extends ValueObject_ {
	public static volatile SingularAttribute<GolfCourse, Long> golfCourseNo;
	public static volatile SingularAttribute<GolfCourse, String> courseName;
	public static volatile CollectionAttribute<GolfCourse, HoleInfo> holes;
}
