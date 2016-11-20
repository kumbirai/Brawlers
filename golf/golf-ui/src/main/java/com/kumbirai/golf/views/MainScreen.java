package com.kumbirai.golf.views;

import com.kumbirai.golf.GolfUI;
import com.kumbirai.golf.event.DashboardEvent.CloseOpenWindowsEvent;
import com.kumbirai.golf.event.DashboardEventBus;
import com.kumbirai.golf.views.about.AboutView;
import com.kumbirai.golf.views.course.GolfCourseView;
import com.kumbirai.golf.views.event.GolfEventView;
import com.kumbirai.golf.views.person.PersonView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * Content of the UI when the user is logged in.
 *
 *
 */
public class MainScreen extends HorizontalLayout
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu;

	// notify the view menu about view changes so that it can display which view
	// is currently active
	ViewChangeListener viewChangeListener = new ViewChangeListener()
	{
		private static final long serialVersionUID = 1L;

		@Override
		public boolean beforeViewChange(ViewChangeEvent event)
		{
			return true;
		}

		@Override
		public void afterViewChange(ViewChangeEvent event)
		{
			DashboardEventBus.post(new CloseOpenWindowsEvent());
			menu.setActiveView(event.getViewName());
		}
	};

	/**
	 * Constructor: @param ui
	 */
	public MainScreen(GolfUI ui)
	{

		setStyleName("main-screen");

		CssLayout viewContainer = new CssLayout();
		viewContainer.addStyleName("valo-content");
		viewContainer.setSizeFull();

		final Navigator navigator = new Navigator(ui, viewContainer);
		navigator.setErrorView(ErrorView.class);
		menu = new Menu(navigator);
		menu.addView(new GolfCourseView(), GolfCourseView.VIEW_NAME, GolfCourseView.VIEW_NAME, FontAwesome.EDIT);
		menu.addView(new GolfEventView(), GolfEventView.VIEW_NAME, GolfEventView.VIEW_NAME, FontAwesome.EDIT);
		menu.addView(new PersonView(), PersonView.VIEW_NAME, PersonView.VIEW_NAME, FontAwesome.EDIT);
		menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME, FontAwesome.INFO_CIRCLE);

		navigator.addViewChangeListener(viewChangeListener);

		addComponent(menu);
		addComponent(viewContainer);
		setExpandRatio(viewContainer, 1);
		setSizeFull();
	}
}
