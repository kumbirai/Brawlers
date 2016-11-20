package com.kumbirai.golf.views.course;

import com.kumbirai.golf.ui.HoleInfoField;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class GolfCourseFormDesign extends CssLayout
{
	protected TextField courseName;
	protected HoleInfoField holes;
	protected Button save;
	protected Button cancel;
	protected Button delete;

	public GolfCourseFormDesign()
	{
		Design.read(this);
	}
}