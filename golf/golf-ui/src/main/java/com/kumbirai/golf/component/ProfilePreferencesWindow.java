/*
 com.kumbirai.golf.component.ProfilePreferencesWindow<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.component;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.authentication.CurrentUser;
import com.kumbirai.golf.backend.DataService;
import com.kumbirai.golf.data.entity.EGender;
import com.kumbirai.golf.data.entity.ETitle;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.entity.info.PersonInfo;
import com.kumbirai.golf.data.entity.info.PersonInfoEmail;
import com.kumbirai.golf.data.entity.info.PersonInfoGolfDetails;
import com.kumbirai.golf.data.entity.info.PersonInfoTel;
import com.kumbirai.golf.data.entity.info.PersonInfoWebAddress;
import com.kumbirai.golf.event.DashboardEvent.CloseOpenWindowsEvent;
import com.kumbirai.golf.event.DashboardEvent.ProfileUpdatedEvent;
import com.kumbirai.golf.event.DashboardEventBus;
import com.kumbirai.golf.ui.NumberField;
import com.kumbirai.security.principal.ISecurityPrincipal;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> ProfilePreferencesWindow<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 06 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class ProfilePreferencesWindow extends Window
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
				personFieldGroup.commit();
				Person person = personFieldGroup.getItemDataSource().getBean();

				emailFieldGroup.getItemDataSource().getBean().setPerson(person);
				emailFieldGroup.commit();

				golfFieldGroup.getItemDataSource().getBean().setPerson(person);
				golfFieldGroup.commit();

				telFieldGroup.getItemDataSource().getBean().setPerson(person);
				telFieldGroup.commit();

				webFieldGroup.getItemDataSource().getBean().setPerson(person);
				webFieldGroup.commit();

				Notification success = new Notification("Profile updated successfully");
				success.setDelayMsec(2000);
				success.setStyleName("bar success small");
				success.setPosition(Position.BOTTOM_CENTER);
				success.show(Page.getCurrent());

				DashboardEventBus.post(new ProfileUpdatedEvent());
				close();
			}
			catch (CommitException e)
			{
				LOGGER.error(e, e.getCause());
				Notification.show("Error while updating profile", Type.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ProfilePreferencesWindow.class.getName());
	public static final String ID = "profilepreferenceswindow";
	private final BeanFieldGroup<Person> personFieldGroup;
	private final BeanFieldGroup<PersonInfoEmail> emailFieldGroup;
	private final BeanFieldGroup<PersonInfoGolfDetails> golfFieldGroup;
	private final BeanFieldGroup<PersonInfoTel> telFieldGroup;
	private final BeanFieldGroup<PersonInfoWebAddress> webFieldGroup;

	private ISecurityPrincipal securityPrincipal = CurrentUser.get();

	@PropertyId("title")
	private ComboBox titleField;
	@PropertyId("firstName")
	private TextField firstNameField;
	@PropertyId("lastName")
	private TextField lastNameField;
	@PropertyId("gender")
	private ComboBox genderField;
	@PropertyId("emailAddress")
	private TextField emailField;
	@PropertyId("memberNo")
	private TextField memberNoField;
	@PropertyId("handicap")
	private NumberField handicapField;
	@PropertyId("countryCode")
	private TextField countryCodeField;
	@PropertyId("telCode")
	private TextField telCodeField;
	@PropertyId("telNo")
	private TextField telNoField;
	@PropertyId("webAddress")
	private TextField websiteField;

	/**
	 * Constructor: @param user
	 * Constructor: @param preferencesTabOpen
	 */
	private ProfilePreferencesWindow(final Person person, final boolean preferencesTabOpen)
	{
		PersonInfoEmail personInfoEmail = new PersonInfoEmail();
		PersonInfoGolfDetails personInfoGolfDetails = new PersonInfoGolfDetails();
		PersonInfoTel personInfoTel = new PersonInfoTel();
		PersonInfoWebAddress personInfoWebAddress = new PersonInfoWebAddress();

		for (PersonInfo info : person.getPersonInfoCollection())
		{
			if (info instanceof PersonInfoEmail)
				personInfoEmail = (PersonInfoEmail) info;
			if (info instanceof PersonInfoGolfDetails)
				personInfoGolfDetails = (PersonInfoGolfDetails) info;
			if (info instanceof PersonInfoTel)
				personInfoTel = (PersonInfoTel) info;
			if (info instanceof PersonInfoWebAddress)
				personInfoWebAddress = (PersonInfoWebAddress) info;
		}

		addStyleName("profile-window");
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

		detailsWrapper.addComponent(buildProfileTab());
		detailsWrapper.addComponent(buildPreferencesTab());

		if (preferencesTabOpen)
		{
			detailsWrapper.setSelectedTab(1);
		}

		content.addComponent(buildFooter());

		personFieldGroup = new BeanFieldGroup<>(Person.class);
		personFieldGroup.bindMemberFields(this);
		personFieldGroup.setItemDataSource(person);
		personFieldGroup.addCommitHandler(new CommitHandler()
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
				DataService.get().save(securityPrincipal, personFieldGroup.getItemDataSource().getBean());
				CurrentUser.updateUser(personFieldGroup.getItemDataSource().getBean().getPersonNo());
			}
		});

		emailFieldGroup = new BeanFieldGroup<>(PersonInfoEmail.class);
		emailFieldGroup.bindMemberFields(this);
		emailFieldGroup.setItemDataSource(personInfoEmail);
		emailFieldGroup.addCommitHandler(new CommitHandler()
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
				DataService.get().save(securityPrincipal, emailFieldGroup.getItemDataSource().getBean());
			}
		});

		golfFieldGroup = new BeanFieldGroup<>(PersonInfoGolfDetails.class);
		golfFieldGroup.bindMemberFields(this);
		golfFieldGroup.setItemDataSource(personInfoGolfDetails);
		golfFieldGroup.addCommitHandler(new CommitHandler()
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
				DataService.get().save(securityPrincipal, golfFieldGroup.getItemDataSource().getBean());
			}
		});

		telFieldGroup = new BeanFieldGroup<>(PersonInfoTel.class);
		telFieldGroup.bindMemberFields(this);
		telFieldGroup.setItemDataSource(personInfoTel);
		telFieldGroup.addCommitHandler(new CommitHandler()
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
				DataService.get().save(securityPrincipal, telFieldGroup.getItemDataSource().getBean());
			}
		});

		webFieldGroup = new BeanFieldGroup<>(PersonInfoWebAddress.class);
		webFieldGroup.bindMemberFields(this);
		webFieldGroup.setItemDataSource(personInfoWebAddress);
		webFieldGroup.addCommitHandler(new CommitHandler()
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
				DataService.get().save(securityPrincipal, webFieldGroup.getItemDataSource().getBean());
			}
		});
	}

	/**
	 * Purpose:
	 * <br>
	 * buildPreferencesTab<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildPreferencesTab()
	{
		VerticalLayout root = new VerticalLayout();
		root.setCaption("Preferences");
		root.setIcon(FontAwesome.COGS);
		root.setSpacing(true);
		root.setMargin(true);
		root.setSizeFull();

		Label message = new Label("Not yet implemented");
		message.setSizeUndefined();
		message.addStyleName(ValoTheme.LABEL_LIGHT);
		root.addComponent(message);
		root.setComponentAlignment(message, Alignment.MIDDLE_CENTER);

		return root;
	}

	/**
	 * Purpose:
	 * <br>
	 * buildProfileTab<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildProfileTab()
	{
		HorizontalLayout root = new HorizontalLayout();
		root.setCaption("Profile");
		root.setIcon(FontAwesome.USER);
		root.setWidth(100.0f, Unit.PERCENTAGE);
		root.setSpacing(true);
		root.setMargin(true);
		root.addStyleName("profile-form");

		VerticalLayout pic = new VerticalLayout();
		pic.setSizeUndefined();
		pic.setSpacing(true);
		Image profilePic = new Image(null, new ThemeResource("img/profile-pic-300px.jpg"));
		profilePic.setWidth(100.0f, Unit.PIXELS);
		pic.addComponent(profilePic);

		Button upload = new Button("Change...", new ClickListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				Notification.show("Not yet implemented");
			}
		});
		upload.addStyleName(ValoTheme.BUTTON_TINY);
		pic.addComponent(upload);

		root.addComponent(pic);

		FormLayout details = new FormLayout();
		details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		root.addComponent(details);
		root.setExpandRatio(details, 1);

		firstNameField = new TextField("First Name");
		details.addComponent(firstNameField);
		lastNameField = new TextField("Last Name");
		details.addComponent(lastNameField);

		titleField = new ComboBox("Title", Arrays.asList(ETitle.values()));
		details.addComponent(titleField);

		genderField = new ComboBox("Gender", Arrays.asList(EGender.values()));
		details.addComponent(genderField);

		Label section = new Label("Contact Info");
		section.addStyleName(ValoTheme.LABEL_H4);
		section.addStyleName(ValoTheme.LABEL_COLORED);
		details.addComponent(section);

		emailField = new TextField("Email");
		emailField.setWidth("100%");
		emailField.setRequired(true);
		details.addComponent(emailField);

		HorizontalLayout telephone = new HorizontalLayout();
		countryCodeField = new TextField("Mobile Number");
		countryCodeField.setWidth(3, Unit.EM);
		telephone.addComponent(countryCodeField);
		telCodeField = new TextField("");
		telCodeField.setWidth(3, Unit.EM);
		telephone.addComponent(telCodeField);
		telNoField = new TextField("");
		telNoField.setWidth(6, Unit.EM);
		telephone.addComponent(telNoField);
		details.addComponent(telephone);

		section = new Label("Additional Info");
		section.addStyleName(ValoTheme.LABEL_H4);
		section.addStyleName(ValoTheme.LABEL_COLORED);
		details.addComponent(section);

		memberNoField = new TextField("SAGA Member Number");
		details.addComponent(memberNoField);
		handicapField = new NumberField("Handicap");
		details.addComponent(handicapField);

		websiteField = new TextField("Website");
		websiteField.setWidth("100%");
		details.addComponent(websiteField);

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
	 * @param user
	 * @param preferencesTabActive<br>
	 */
	public static void open(final Person user, final boolean preferencesTabActive)
	{
		DashboardEventBus.post(new CloseOpenWindowsEvent());
		Window w = new ProfilePreferencesWindow(user, preferencesTabActive);
		UI.getCurrent().addWindow(w);
		w.focus();
	}
}