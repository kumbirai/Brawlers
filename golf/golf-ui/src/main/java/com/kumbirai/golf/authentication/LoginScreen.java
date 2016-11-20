package com.kumbirai.golf.authentication;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * UI content when the user is not logged in yet.
 */
public class LoginScreen extends CssLayout
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private TextField username;
	private PasswordField password;
	private Button login;
	private Button forgotPassword;
	private Button register;
	private LoginListener loginListener;
	private AccessControl accessControl;

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
		FormLayout loginForm = new FormLayout();

		loginForm.addStyleName("login-form");
		loginForm.setSizeUndefined();
		loginForm.setMargin(false);

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

		register = new Button("Register");
		buttons.addComponent(register);
		register.addClickListener(new Button.ClickListener()
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
		register.addStyleName(ValoTheme.BUTTON_LINK);

		return loginForm;
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
