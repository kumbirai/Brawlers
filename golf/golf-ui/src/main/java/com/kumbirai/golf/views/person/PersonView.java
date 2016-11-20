/*
 com.kumbirai.golf.views.person.PersonView<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> PersonView<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 04 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class PersonView extends CssLayout implements View
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PersonView.class.getName());
	public static final String VIEW_NAME = "Person";
	private PersonForm form;

	private PersonCrudLogic viewLogic = new PersonCrudLogic(this);
	private Button newPerson;

	/**
	 * Constructor:
	 */
	public PersonView()
	{
		super();
		setSizeFull();
		addStyleName("crud-view");
		HorizontalLayout topLayout = createTopBar();

		VerticalLayout barAndGridLayout = new VerticalLayout();
		barAndGridLayout.addComponent(topLayout);
		barAndGridLayout.setMargin(true);
		barAndGridLayout.setSpacing(true);
		barAndGridLayout.setSizeFull();
		barAndGridLayout.setStyleName("crud-main-layout");

		addComponent(barAndGridLayout);

		viewLogic.init();
	}

	/**
	 * Purpose:
	 * <br>
	 * createTopBar<br>
	 * <br>
	 * @return<br>
	 */
	private HorizontalLayout createTopBar()
	{
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setSpacing(true);
		topLayout.setWidth("100%");
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
	 * showPerson<br>
	 * <br><br>
	 */
	public void showPerson()
	{
	}
}