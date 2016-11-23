package com.kumbirai.golf.authentication;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.data.entity.EGender;
import com.kumbirai.golf.data.entity.ETitle;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.entity.info.PersonInfoEmail;
import com.kumbirai.golf.data.entity.info.PersonInfoGolfDetails;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;
import com.kumbirai.golf.data.entity.info.PersonInfoTel;
import com.kumbirai.golf.data.entity.info.PersonInfoWebAddress;
import com.kumbirai.golf.ui.NumberField;
import com.kumbirai.security.principal.ISecurityPrincipal;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * UI content when the user is not logged in yet.
 */
public class LoginScreen extends CssLayout
{
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
				person.setPersonInfoCollection(new ArrayList<>());

				emailFieldGroup.getItemDataSource().getBean().setPerson(person);
				emailFieldGroup.commit();
				person.getPersonInfoCollection().add(emailFieldGroup.getItemDataSource().getBean());

				golfFieldGroup.getItemDataSource().getBean().setPerson(person);
				golfFieldGroup.commit();
				person.getPersonInfoCollection().add(golfFieldGroup.getItemDataSource().getBean());

				telFieldGroup.getItemDataSource().getBean().setPerson(person);
				telFieldGroup.commit();
				person.getPersonInfoCollection().add(telFieldGroup.getItemDataSource().getBean());

				webFieldGroup.getItemDataSource().getBean().setPerson(person);
				webFieldGroup.commit();
				person.getPersonInfoCollection().add(webFieldGroup.getItemDataSource().getBean());

				loginFieldGroup.getItemDataSource().getBean().setPerson(person);
				loginFieldGroup.commit();
				person.getPersonInfoCollection().add(loginFieldGroup.getItemDataSource().getBean());

				Notification success = new Notification("Profile updated successfully");
				success.setDelayMsec(2000);
				success.setStyleName("bar success small");
				success.setPosition(Position.BOTTOM_CENTER);
				success.show(Page.getCurrent());
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
	private static final Logger LOGGER = LogManager.getLogger(LoginScreen.class.getName());
	private Button login;
	private Button forgotPassword;
	private LoginListener loginListener;
	private AccessControl accessControl;

	private ISecurityPrincipal securityPrincipal = CurrentUser.getSuperUser();

	private final BeanFieldGroup<Person> personFieldGroup;
	private final BeanFieldGroup<PersonInfoEmail> emailFieldGroup;
	private final BeanFieldGroup<PersonInfoGolfDetails> golfFieldGroup;
	private final BeanFieldGroup<PersonInfoTel> telFieldGroup;
	private final BeanFieldGroup<PersonInfoWebAddress> webFieldGroup;
	private final BeanFieldGroup<PersonInfoLoginProfile> loginFieldGroup;

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
	@PropertyId("username")
	private TextField username;
	@PropertyId("password")
	private PasswordField password;

	/**
	 * Constructor: @param accessControl
	 * Constructor: @param loginListener
	 */
	public LoginScreen(AccessControl accessControl, LoginListener loginListener)
	{
		this.loginListener = loginListener;
		this.accessControl = accessControl;
		buildUI();
		username.focus();

		personFieldGroup = new BeanFieldGroup<>(Person.class);
		personFieldGroup.bindMemberFields(this);
		personFieldGroup.setItemDataSource(new Person());
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
				//
			}
		});

		emailFieldGroup = new BeanFieldGroup<>(PersonInfoEmail.class);
		emailFieldGroup.bindMemberFields(this);
		emailFieldGroup.setItemDataSource(new PersonInfoEmail());
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
				//
			}
		});

		golfFieldGroup = new BeanFieldGroup<>(PersonInfoGolfDetails.class);
		golfFieldGroup.bindMemberFields(this);
		golfFieldGroup.setItemDataSource(new PersonInfoGolfDetails());
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
				//
			}
		});

		telFieldGroup = new BeanFieldGroup<>(PersonInfoTel.class);
		telFieldGroup.bindMemberFields(this);
		telFieldGroup.setItemDataSource(new PersonInfoTel());
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
				//
			}
		});

		webFieldGroup = new BeanFieldGroup<>(PersonInfoWebAddress.class);
		webFieldGroup.bindMemberFields(this);
		webFieldGroup.setItemDataSource(new PersonInfoWebAddress());
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
				//
			}
		});

		loginFieldGroup = new BeanFieldGroup<>(PersonInfoLoginProfile.class);
		loginFieldGroup.bindMemberFields(this);
		loginFieldGroup.setItemDataSource(new PersonInfoLoginProfile());
		loginFieldGroup.addCommitHandler(new CommitHandler()
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
	}

	/**
	 * Purpose:
	 * <br>
	 * buildUI<br>
	 * <br><br>
	 */
	private void buildUI()
	{
		addStyleName("login-screen");

		// login form, centered in the available part of the screen
		Component loginForm = buildLoginForm();

		// layout to center login form when there is sufficient screen space
		// - see the theme for how this is made responsive for various screen
		// sizes
		VerticalLayout centeringLayout = new VerticalLayout();
		centeringLayout.setStyleName("centering-layout");
		centeringLayout.addComponent(loginForm);
		centeringLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

		// information text about logging in
		CssLayout loginInformation = buildLoginInformation();

		addComponent(centeringLayout);
		addComponent(loginInformation);
	}

	/**
	 * Purpose:
	 * <br>
	 * buildLoginForm<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildLoginForm()
	{
		TabSheet detailsWrapper = new TabSheet();
		detailsWrapper.addStyleName("login-form");
		detailsWrapper.setSizeUndefined();

		detailsWrapper.addComponent(buildLoginTab());
		detailsWrapper.addComponent(buildRegisterTab());
		return detailsWrapper;
	}

	/**
	 * Purpose:
	 * <br>
	 * buildLoginTab<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildLoginTab()
	{
		FormLayout loginForm = new FormLayout();
		loginForm.setCaption("Login");
		loginForm.setIcon(FontAwesome.KEY);

		// loginForm.addStyleName("login-form")
		// loginForm.setSizeUndefined()
		// loginForm.setMargin(false)

		username = new TextField("Username");
		loginForm.addComponent(username);
		username.setWidth(15, Unit.EM);
		username.setDescription("Your username");

		password = new PasswordField("Password");
		loginForm.addComponent(password);
		password.setWidth(15, Unit.EM);
		password.setDescription("Your password");

		CssLayout buttons = new CssLayout();
		buttons.setStyleName("buttons");
		loginForm.addComponent(buttons);

		login = new Button("Login");
		buttons.addComponent(login);
		login.setDisableOnClick(true);
		login.addClickListener(new Button.ClickListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event)
			{
				try
				{
					login();
				}
				finally
				{
					login.setEnabled(true);
				}
			}
		});
		login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

		forgotPassword = new Button("Forgot password?");
		buttons.addComponent(forgotPassword);
		forgotPassword.addClickListener(new Button.ClickListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event)
			{
				showNotification(new Notification("Hint: Try anything"));
			}
		});
		forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);

		return loginForm;
	}

	/**
	 * Purpose:
	 * <br>
	 * buildRegisterTab<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildRegisterTab()
	{
		VerticalLayout root = new VerticalLayout();
		root.setCaption("Register");
		root.setIcon(FontAwesome.PENCIL);

		// root.addStyleName("login-form")
		// root.setSizeUndefined()
		// root.setMargin(false)

		FormLayout details = new FormLayout();

		// details.addStyleName("login-form")
		// details.setSizeUndefined()
		// details.setMargin(false)

		root.addComponent(details);
		details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

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

		HorizontalLayout footer = new HorizontalLayout();
		root.addComponent(footer);
		footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		footer.setWidth(100.0f, Unit.PERCENTAGE);

		Button ok = new Button("OK");
		ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
		ok.addClickListener(new OkButtonClickListener());
		ok.focus();
		footer.addComponent(ok);
		footer.setComponentAlignment(ok, Alignment.TOP_RIGHT);

		return root;
	}

	/**
	 * Purpose:
	 * <br>
	 * buildLoginInformation<br>
	 * <br>
	 * @return<br>
	 */
	private CssLayout buildLoginInformation()
	{
		CssLayout loginInformation = new CssLayout();
		loginInformation.setStyleName("login-information");
		Label loginInfoText = new Label("<h1>Brawlers Golf</h1>", ContentMode.HTML);
		loginInformation.addComponent(loginInfoText);
		return loginInformation;
	}

	/**
	 * Purpose:
	 * <br>
	 * login<br>
	 * <br><br>
	 */
	private void login()
	{
		if (accessControl.signIn(username.getValue(), password.getValue()))
		{
			loginListener.loginSuccessful();
		}
		else
		{
			showNotification(new Notification("Login failed", "Please check your username and password and try again.", Notification.Type.HUMANIZED_MESSAGE));
			username.focus();
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * showNotification<br>
	 * <br>
	 * @param notification<br>
	 */
	private void showNotification(Notification notification)
	{
		// keep the notification visible a little while after moving the
		// mouse, or until clicked
		notification.setDelayMsec(2000);
		notification.show(Page.getCurrent());
	}
}
