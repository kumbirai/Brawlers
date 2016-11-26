/*
 com.kumbirai.golf.views.course.GolfCourseView<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.course;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.data.course.GolfCourse;
import com.kumbirai.golf.samples.ResetButtonForTextField;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionModel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfCourseView<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfCourseView extends CssLayout implements View
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfCourseView.class.getName());
	public static final String VIEW_NAME = "Courses";

	private GolfCourseGrid grid;
	private GolfCourseForm form;

	private GolfCourseLogic viewLogic = new GolfCourseLogic(this);
	private Button newGolfCourse;

	/**
	 * Constructor:
	 */
	public GolfCourseView()
	{
		super();
		setSizeFull();
		addStyleName("crud-view");
		HorizontalLayout topLayout = createTopBar();

		grid = new GolfCourseGrid();
		grid.addSelectionListener(new SelectionListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void select(SelectionEvent event)
			{
				viewLogic.rowSelected(grid.getSelectedRow());
			}
		});

		form = new GolfCourseForm(viewLogic);

		VerticalLayout barAndGridLayout = new VerticalLayout();
		barAndGridLayout.addComponent(topLayout);
		barAndGridLayout.addComponent(grid);
		barAndGridLayout.setMargin(true);
		barAndGridLayout.setSpacing(true);
		barAndGridLayout.setSizeFull();
		barAndGridLayout.setExpandRatio(grid, 1);
		barAndGridLayout.setStyleName("crud-main-layout");

		addComponent(barAndGridLayout);
		addComponent(form);

		viewLogic.init();
	}

	/**
	 * Constructor: @param children
	 */
	public GolfCourseView(Component... children)
	{
		super(children);
	}

	/**
	 * Purpose:
	 * <br>
	 * createTopBar<br>
	 * <br>
	 * @return<br>
	 */
	public HorizontalLayout createTopBar()
	{
		TextField filter = new TextField();
		filter.setStyleName("filter-textfield");
		filter.setInputPrompt("Filter");
		ResetButtonForTextField.extend(filter);
		filter.setImmediate(true);
		filter.addTextChangeListener(new FieldEvents.TextChangeListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(FieldEvents.TextChangeEvent event)
			{
				grid.setFilter(event.getText());
			}
		});

		newGolfCourse = new Button("New Golf Course");
		newGolfCourse.addStyleName(ValoTheme.BUTTON_PRIMARY);
		newGolfCourse.setIcon(FontAwesome.PLUS_CIRCLE);
		newGolfCourse.addClickListener(new ClickListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				viewLogic.newGolfCourse();
			}
		});

		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setSpacing(true);
		topLayout.setWidth("100%");
		topLayout.addComponent(filter);
		topLayout.addComponent(newGolfCourse);
		topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
		topLayout.setExpandRatio(filter, 1);
		topLayout.setStyleName("top-bar");
		return topLayout;
	}

	/** (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeEvent event)
	{
		viewLogic.enter(event.getParameters());
	}

	/**
	 * Purpose:
	 * <br>
	 * showError<br>
	 * <br>
	 * @param msg<br>
	 */
	public void showError(String msg)
	{
		Notification.show(msg, Type.ERROR_MESSAGE);
	}

	/**
	 * Purpose:
	 * <br>
	 * showSaveNotification<br>
	 * <br>
	 * @param msg<br>
	 */
	public void showSaveNotification(String msg)
	{
		Notification.show(msg, Type.TRAY_NOTIFICATION);
	}

	/**
	 * Purpose:
	 * <br>
	 * setNewGolfCourseEnabled<br>
	 * <br>
	 * @param enabled<br>
	 */
	public void setNewGolfCourseEnabled(boolean enabled)
	{
		newGolfCourse.setEnabled(enabled);
	}

	/**
	 * Purpose:
	 * <br>
	 * clearSelection<br>
	 * <br><br>
	 */
	public void clearSelection()
	{
		grid.getSelectionModel().reset();
	}

	/**
	 * Purpose:
	 * <br>
	 * selectRow<br>
	 * <br>
	 * @param row<br>
	 */
	public void selectRow(GolfCourse row)
	{
		((SelectionModel.Single) grid.getSelectionModel()).select(row);
	}

	/**
	 * Purpose:
	 * <br>
	 * getSelectedRow<br>
	 * <br>
	 * @return<br>
	 */
	public GolfCourse getSelectedRow()
	{
		return grid.getSelectedRow();
	}

	/**
	 * Purpose:
	 * <br>
	 * editGolfCourse<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void editGolfCourse(GolfCourse golfCourse)
	{
		if (golfCourse != null)
		{
			form.addStyleName("visible");
			form.setEnabled(true);
		}
		else
		{
			form.removeStyleName("visible");
			form.setEnabled(false);
		}
		form.editGolfCourse(golfCourse);
	}

	/**
	 * Purpose:
	 * <br>
	 * showGolfCourses<br>
	 * <br>
	 * @param golfCourses<br>
	 */
	public void showGolfCourses(Collection<GolfCourse> golfCourses)
	{
		grid.setGolfCourses(golfCourses);
	}

	/**
	 * Purpose:
	 * <br>
	 * refreshGolfCourse<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void refreshGolfCourse(GolfCourse golfCourse)
	{
		grid.refresh(golfCourse);
		grid.scrollTo(golfCourse);
	}

	/**
	 * Purpose:
	 * <br>
	 * removeGolfCourse<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void removeGolfCourse(GolfCourse golfCourse)
	{
		grid.remove(golfCourse);
	}
}