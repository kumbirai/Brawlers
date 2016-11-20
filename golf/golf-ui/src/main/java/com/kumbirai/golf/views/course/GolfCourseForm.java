/*
 com.kumbirai.golf.views.course.GolfCourseForm<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.course;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.authentication.CurrentUser;
import com.kumbirai.golf.backend.DataService;
import com.kumbirai.golf.data.course.GolfCourse;
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
 * <p><b>Title:</b> GolfCourseForm<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfCourseForm extends GolfCourseFormDesign
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
				GolfCourse golfCourse = fieldGroup.getItemDataSource().getBean();
				viewLogic.saveGolfCourse(golfCourse);
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
			viewLogic.cancelGolfCourse();
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
	private static final Logger LOGGER = LogManager.getLogger(GolfCourseForm.class.getName());
	private GolfCourseLogic viewLogic;
	private BeanFieldGroup<GolfCourse> fieldGroup;
	private ISecurityPrincipal securityPrincipal = CurrentUser.get();

	/**
	 * Constructor: @param viewLogic
	 */
	public GolfCourseForm(GolfCourseLogic viewLogic)
	{
		super();
		addStyleName("input-form");
		this.viewLogic = viewLogic;

		fieldGroup = new BeanFieldGroup<>(GolfCourse.class);
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
	 * editGolfCourse<br>
	 * <br>
	 * @param golfCourse<br>
	 */
	public void editGolfCourse(GolfCourse golfCourse)
	{
		if (golfCourse == null)
		{
			golfCourse = new GolfCourse();
		}
		fieldGroup.setItemDataSource(new BeanItem<>(golfCourse));

		holes.setHoles(golfCourse.getHoles());

		// before the user makes any changes, disable validation error indicator
		// of the relevant fields (which may be empty)
		courseName.setValidationVisible(false);

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
		courseName.setValidationVisible(true);

		// only products that have been saved should be removable
		boolean canRemoveGolfCourse = false;
		BeanItem<GolfCourse> item = fieldGroup.getItemDataSource();
		if (item != null)
		{
			GolfCourse golfCourse = item.getBean();
			canRemoveGolfCourse = golfCourse.getGolfCourseNo() != null && golfCourse.getGolfCourseNo() > 0;
		}
		delete.setEnabled(canRemoveGolfCourse);
	}
}