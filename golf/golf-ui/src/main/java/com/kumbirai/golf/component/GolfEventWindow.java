/*
 com.kumbirai.golf.component.GolfEventWindow<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.component;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.authentication.CurrentUser;
import com.kumbirai.golf.data.event.GolfEvent;
import com.kumbirai.golf.data.score.Match;
import com.kumbirai.golf.data.score.ScoreCard;
import com.kumbirai.golf.event.DashboardEvent.CloseOpenWindowsEvent;
import com.kumbirai.golf.event.DashboardEvent.EventUpdatedEvent;
import com.kumbirai.golf.event.DashboardEventBus;
import com.kumbirai.security.principal.ISecurityPrincipal;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfEventWindow<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 19 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfEventWindow extends Window
{
	/**
	 * <p><b>Purpose:</b><br>
	 * <br>
	 *
	 * <p><b>Title:</b> OkButtonClickListener<br>
	 * <b>Description:</b> </p>
	 *
	 * @author Kumbirai 'Coach' Mundangepfupfu<br>
	 * @date 08 Nov 2016<br>
	 * @version 1.0<br>
	 *
	 * <b>Revision:</b>
	 *
	 */
	private final class OkButtonClickListener implements ClickListener
	{
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event)
		{
			try
			{
				golfEventFieldGroup.commit();

				Notification success = new Notification("Event updated successfully");
				success.setDelayMsec(2000);
				success.setStyleName("bar success small");
				success.setPosition(Position.BOTTOM_CENTER);
				success.show(Page.getCurrent());

				DashboardEventBus.post(new EventUpdatedEvent());
				close();
			}
			catch (CommitException e)
			{
				LOGGER.error(e, e.getCause());
				Notification.show("Error while updating event", Type.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfEventWindow.class.getName());
	public static final String ID = "eventwindow";
	private final BeanFieldGroup<GolfEvent> golfEventFieldGroup;

	private ISecurityPrincipal securityPrincipal = CurrentUser.get();

	/**
	 * Constructor: @param golfEvent
	 * Constructor: @param preferencesTabOpen
	 */
	private GolfEventWindow(final GolfEvent golfEvent, final boolean preferencesTabOpen)
	{
		golfEventFieldGroup = new BeanFieldGroup<>(GolfEvent.class);
		golfEventFieldGroup.bindMemberFields(this);
		golfEventFieldGroup.setItemDataSource(golfEvent);
		golfEventFieldGroup.addCommitHandler(new CommitHandler()
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
				//
			}
		});

		addStyleName("event-window");
		setId(ID);
		Responsive.makeResponsive(this);

		setModal(true);
		addCloseShortcut(KeyCode.ESCAPE, null);
		setResizable(false);
		setClosable(true);
		setHeight(90.0f, Unit.PERCENTAGE);

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setMargin(new MarginInfo(true, false, false, false));
		setContent(content);

		TabSheet detailsWrapper = new TabSheet();
		detailsWrapper.setSizeFull();
		detailsWrapper.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		detailsWrapper.addStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
		detailsWrapper.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
		content.addComponent(detailsWrapper);
		content.setExpandRatio(detailsWrapper, 1f);

		detailsWrapper.addComponent(buildConfirmedPlayersTab());
		detailsWrapper.addComponent(buildMatchesTab());

		if (preferencesTabOpen)
		{
			detailsWrapper.setSelectedTab(1);
		}

		content.addComponent(buildFooter());
	}

	/**
	 * Purpose:
	 * <br>
	 * buildMatchesTab<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildMatchesTab()
	{
		VerticalLayout root = new VerticalLayout();
		root.setCaption("Matches");
		root.setIcon(FontAwesome.GROUP);
		root.setSpacing(true);
		root.setMargin(true);
		// root.setSizeFull()

		Collection<Match> matchCollection = golfEventFieldGroup.getItemDataSource().getBean().getMatches();

		if (matchCollection != null && !matchCollection.isEmpty())
		{
			Accordion matches = new Accordion();
			// matches.setHeight(100.0f, Unit.PERCENTAGE)
			root.addComponent(matches);

			for (Match match : matchCollection)
			{
				matches.addTab(getMatchComponent(match), "Match " + match.getEventMatchNo());
			}
			root.setComponentAlignment(matches, Alignment.MIDDLE_CENTER);
		}
		else
		{
			root.addComponent(new Label("There are no matches created..."));
		}
		Button create = new Button("Add Match");
		create.addStyleName(ValoTheme.BUTTON_PRIMARY);
		create.addClickListener(new OkButtonClickListener());
		create.focus();
		root.addComponent(create);

		return root;
	}

	/**
	 * Purpose:
	 * <br>
	 * getMatchComponent<br>
	 * <br>
	 * @param match
	 * @return<br>
	 */
	private Component getMatchComponent(final Match match)
	{
		VerticalLayout layout = new VerticalLayout();
		for (ScoreCard scoreCard : match.getScoreCards())
		{
			HorizontalLayout player = new HorizontalLayout();
			layout.addComponent(player);

			player.addComponent(new Label(scoreCard.getPerson().getName()));
		}
		HorizontalLayout buttons = new HorizontalLayout();
		layout.addComponent(buttons);

		Button edit = new Button("Edit");
		edit.addStyleName(ValoTheme.BUTTON_TINY);
		edit.addClickListener(new OkButtonClickListener());
		edit.focus();
		buttons.addComponent(edit);

		Button scores = new Button("Scores");
		scores.addStyleName(ValoTheme.BUTTON_TINY);
		scores.addClickListener(new ClickListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				ScoreCardWindow.open(match);
			}
		});
		scores.focus();
		buttons.addComponent(scores);

		layout.setSpacing(false);
		return layout;
	}

	/**
	 * Purpose:
	 * <br>
	 * buildConfirmedPlayersTab<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildConfirmedPlayersTab()
	{
		HorizontalLayout root = new HorizontalLayout();
		root.setCaption("Confirmed Players");
		root.setIcon(FontAwesome.USER);
		root.setWidth(100.0f, Unit.PERCENTAGE);
		root.setSpacing(true);
		root.setMargin(true);
		root.addStyleName("event-form");

		VerticalLayout pic = new VerticalLayout();
		pic.setSizeUndefined();
		pic.setSpacing(true);
		Image eventPic = new Image(null, new ThemeResource("img/event-pic-300px.jpg"));
		eventPic.setWidth(100.0f, Unit.PIXELS);
		pic.addComponent(eventPic);

		root.addComponent(pic);

		return root;
	}

	/**
	 * Purpose:
	 * <br>
	 * buildFooter<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildFooter()
	{
		HorizontalLayout footer = new HorizontalLayout();
		footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		footer.setWidth(100.0f, Unit.PERCENTAGE);

		Button ok = new Button("OK");
		ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
		ok.addClickListener(new OkButtonClickListener());
		ok.focus();
		footer.addComponent(ok);
		footer.setComponentAlignment(ok, Alignment.TOP_RIGHT);
		return footer;
	}

	/**
	 * Purpose:
	 * <br>
	 * open<br>
	 * <br>
	 * @param golfEvent
	 * @param preferencesTabActive<br>
	 */
	public static void open(final GolfEvent golfEvent, final boolean preferencesTabActive)
	{
		DashboardEventBus.post(new CloseOpenWindowsEvent());
		Window w = new GolfEventWindow(golfEvent, preferencesTabActive);
		UI.getCurrent().addWindow(w);
		w.focus();
	}
}
