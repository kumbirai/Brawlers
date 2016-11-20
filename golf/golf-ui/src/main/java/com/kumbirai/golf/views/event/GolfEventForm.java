/*
 com.kumbirai.golf.views.event.GolfEventForm<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.authentication.CurrentUser;
import com.kumbirai.golf.backend.DataService;
import com.kumbirai.golf.data.course.GolfCourse;
import com.kumbirai.golf.data.event.GolfEvent;
import com.kumbirai.security.principal.ISecurityPrincipal;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfEventForm<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfEventForm extends GolfEventFormDesign
{
	/**
	 * <p><b>Purpose:</b><br>
	 * <br>
	 *
	 * <p><b>Title:</b> SaveClickListener<br>
	 * <b>Description:</b> </p>
	 *
	 * @author Kumbirai 'Coach' Mundangepfupfu<br>
	 * @date 14 Nov 2016<br>
	 * @version 1.0<br>
	 *
	 * <b>Revision:</b>
	 *
	 */
	private final class SaveClickListener implements ClickListener
	{
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event)
		{
			//
			try
			{
				fieldGroup.commit();
				GolfEvent golfEvent = fieldGroup.getItemDataSource().getBean();
				viewLogic.saveGolfEvent(golfEvent);
			}
			catch (CommitException ex)
			{
				Notification n = new Notification("Please re-check the fields", Type.ERROR_MESSAGE);
				n.setDelayMsec(500);
				n.show(getUI().getPage());
				LOGGER.error("[CommitException] has been caught." + ex, ex.getCause());
			}
		}
	}

	/**
	 * <p><b>Purpose:</b><br>
	 * <br>
	 *
	 * <p><b>Title:</b> CancelClickListener<br>
	 * <b>Description:</b> </p>
	 *
	 * @author Kumbirai 'Coach' Mundangepfupfu<br>
	 * @date 14 Nov 2016<br>
	 * @version 1.0<br>
	 *
	 * <b>Revision:</b>
	 *
	 */
	private final class CancelClickListener implements ClickListener
	{
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event)
		{
			//
			viewLogic.cancelGolfEvent();
		}
	}

	/**
	 * <p><b>Purpose:</b><br>
	 * <br>
	 *
	 * <p><b>Title:</b> DeleteClickListener<br>
	 * <b>Description:</b> </p>
	 *
	 * @author Kumbirai 'Coach' Mundangepfupfu<br>
	 * @date 14 Nov 2016<br>
	 * @version 1.0<br>
	 *
	 * <b>Revision:</b>
	 *
	 */
	private final class DeleteClickListener implements ClickListener
	{
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event)
		{//
		}
	}

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfEventForm.class.getName());
	private GolfEventLogic viewLogic;
	private BeanFieldGroup<GolfEvent> fieldGroup;
	private ISecurityPrincipal securityPrincipal = CurrentUser.get();

	/**
	 * Constructor: @param viewLogic
	 */
	public GolfEventForm(GolfEventLogic viewLogic)
	{
		super();
		addStyleName("input-form");
		this.viewLogic = viewLogic;

		// greenFees.setConverter(new RandConverter())

		for (GolfCourse golfCourse : DataService.get().getAllCourses())
		{
			course.addItem(golfCourse);
		}

		fieldGroup = new BeanFieldGroup<>(GolfEvent.class);
		fieldGroup.bindMemberFields(this);

		// perform validation and enable/disable buttons while editing
		ValueChangeListener valueListener = new ValueChangeListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event)
			{
				formHasChanged();
			}
		};
		for (Field f : fieldGroup.getFields())
		{
			f.addValueChangeListener(valueListener);
		}

		fieldGroup.addCommitHandler(new CommitHandler()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void preCommit(CommitEvent commitEvent) throws CommitException
			{
				//
			}

			@Override
			public void postCommit(CommitEvent commitEvent) throws CommitException
			{
				DataService.get().save(securityPrincipal, fieldGroup.getItemDataSource().getBean());
			}
		});

		save.addClickListener(new SaveClickListener());

		cancel.addClickListener(new CancelClickListener());

		delete.addClickListener(new DeleteClickListener());
	}

	/**
	 * Purpose:
	 * <br>
	 * editGolfEvent<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	public void editGolfEvent(GolfEvent golfEvent)
	{
		if (golfEvent == null)
		{
			golfEvent = new GolfEvent();
		}
		fieldGroup.setItemDataSource(new BeanItem<>(golfEvent));

		// before the user makes any changes, disable validation error indicator
		// of the relevant fields (which may be empty)
		eventDate.setValidationVisible(false);
		course.setValidationVisible(false);
		teeOff.setValidationVisible(false);

		// Scroll to the top
		// As this is not a Panel, using JavaScript
		String scrollScript = "window.document.getElementById('" + getId() + "').scrollTop = 0;";
		Page.getCurrent().getJavaScript().execute(scrollScript);
	}

	/**
	 * Purpose:
	 * <br>
	 * formHasChanged<br>
	 * <br><br>
	 */
	private void formHasChanged()
	{
		// show validation errors after the user has changed something
		eventDate.setValidationVisible(true);
		course.setValidationVisible(true);
		teeOff.setValidationVisible(true);

		// only products that have been saved should be removable
		boolean canRemoveGolfEvent = false;
		BeanItem<GolfEvent> item = fieldGroup.getItemDataSource();
		if (item != null)
		{
			GolfEvent golfEvent = item.getBean();
			canRemoveGolfEvent = golfEvent.getEventNo() != null && golfEvent.getEventNo() > 0;
		}
		delete.setEnabled(canRemoveGolfEvent);
	}
}