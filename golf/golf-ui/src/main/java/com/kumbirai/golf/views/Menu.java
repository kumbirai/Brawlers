package com.kumbirai.golf.views;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;
import com.kumbirai.golf.authentication.CurrentUser;
import com.kumbirai.golf.backend.DataService;
import com.kumbirai.golf.component.ProfilePreferencesWindow;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.event.DashboardEvent.ProfileUpdatedEvent;
import com.kumbirai.golf.event.DashboardEvent.UserLoggedOutEvent;
import com.kumbirai.golf.event.DashboardEventBus;
import com.kumbirai.security.principal.ISecurityPrincipal;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Responsive navigation menu presenting a list of available views to the user.
 */
public class Menu extends CssLayout
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(Menu.class.getName());
	private static final String VALO_MENUITEMS = "valo-menuitems";
	private static final String VALO_MENU_TOGGLE = "valo-menu-toggle";
	private static final String VALO_MENU_VISIBLE = "valo-menu-visible";
	private Navigator navigator;
	private Map<String, Button> viewButtons = new HashMap<>();

	private CssLayout menuItemsLayout;
	private CssLayout menuPart;

	private MenuItem settingsItem;

	public Menu(Navigator navigator)
	{
		DashboardEventBus.register(this);

		this.navigator = navigator;
		setPrimaryStyleName(ValoTheme.MENU_ROOT);
		menuPart = new CssLayout();
		menuPart.addStyleName("sidebar");
		menuPart.addStyleName(ValoTheme.MENU_PART);
		menuPart.addStyleName("no-vertical-drag-hints");
		menuPart.addStyleName("no-horizontal-drag-hints");
		menuPart.setWidth(null);
		menuPart.setHeight("100%");

		menuPart.addComponent(buildTitle());
		menuPart.addComponent(buildUserMenu());

		// button for toggling the visibility of the menu when on a small screen
		final Button showMenu = new Button("Menu", new ClickListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event)
			{
				if (menuPart.getStyleName().contains(VALO_MENU_VISIBLE))
				{
					menuPart.removeStyleName(VALO_MENU_VISIBLE);
				}
				else
				{
					menuPart.addStyleName(VALO_MENU_VISIBLE);
				}
			}
		});
		showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
		showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
		showMenu.addStyleName(VALO_MENU_TOGGLE);
		showMenu.setIcon(FontAwesome.NAVICON);
		menuPart.addComponent(showMenu);

		// container for the navigation buttons, which are added by addView()
		menuItemsLayout = new CssLayout();
		menuItemsLayout.setPrimaryStyleName(VALO_MENUITEMS);
		menuPart.addComponent(menuItemsLayout);

		addComponent(menuPart);
	}

	/**
	 * Purpose:
	 * <br>
	 * buildTitle<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildTitle()
	{
		Label logo = new Label("Golf<strong>Playbook</strong>", ContentMode.HTML);
		logo.setSizeUndefined();
		HorizontalLayout logoWrapper = new HorizontalLayout(logo);
		logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
		logoWrapper.addStyleName(ValoTheme.MENU_TITLE);
		return logoWrapper;
	}

	/**
	 * Purpose:
	 * <br>
	 * buildUserMenu<br>
	 * <br>
	 * @return<br>
	 */
	private Component buildUserMenu()
	{
		final MenuBar settings = new MenuBar();
		settings.addStyleName("user-menu");
		final Person user = getCurrentUser();
		settingsItem = settings.addItem("", new ThemeResource("img/profile-pic-300px.jpg"), null);
		updateUserName(null);
		settingsItem.addItem("Edit Profile", new Command()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(final MenuItem selectedItem)
			{
				ProfilePreferencesWindow.open(user, false);
			}
		});
		settingsItem.addItem("Preferences", new Command()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(final MenuItem selectedItem)
			{
				ProfilePreferencesWindow.open(user, true);
			}
		});
		settingsItem.addSeparator();
		settingsItem.addItem("Sign Out", new Command()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(final MenuItem selectedItem)
			{
				DashboardEventBus.post(new UserLoggedOutEvent());

			}
		});
		return settings;
	}

	/**
	 * Purpose:
	 * <br>
	 * getCurrentUser<br>
	 * <br>
	 * @return<br>
	 */
	private Person getCurrentUser()
	{
		ISecurityPrincipal principal = CurrentUser.get();
		return DataService.get().getPerson(principal, principal.getEntityNo());
	}

	/**
	 * Register a pre-created view instance in the navigation menu and in the
	 * {@link Navigator}.
	 *
	 * @see Navigator#addView(String, View)
	 *
	 * @param view
	 *            view instance to register
	 * @param name
	 *            view name
	 * @param caption
	 *            view caption in the menu
	 * @param icon
	 *            view icon in the menu
	 */
	public void addView(View view, final String name, String caption, Resource icon)
	{
		navigator.addView(name, view);
		createViewButton(name, caption, icon);
	}

	/**
	 * Register a view in the navigation menu and in the {@link Navigator} based
	 * on a view class.
	 *
	 * @see Navigator#addView(String, Class)
	 *
	 * @param viewClass
	 *            class of the views to create
	 * @param name
	 *            view name
	 * @param caption
	 *            view caption in the menu
	 * @param icon
	 *            view icon in the menu
	 */
	public void addView(Class<? extends View> viewClass, final String name, String caption, Resource icon)
	{
		navigator.addView(name, viewClass);
		createViewButton(name, caption, icon);
	}

	/**
	 * Purpose:
	 * <br>
	 * createViewButton<br>
	 * <br>
	 * @param name
	 * @param caption
	 * @param icon<br>
	 */
	private void createViewButton(final String name, String caption, Resource icon)
	{
		Button button = new Button(caption, new ClickListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				navigator.navigateTo(name);

			}
		});
		button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		button.setIcon(icon);
		menuItemsLayout.addComponent(button);
		viewButtons.put(name, button);
	}

	/**
	 * Highlights a view navigation button as the currently active view in the
	 * menu. This method does not perform the actual navigation.
	 *
	 * @param viewName
	 *            the name of the view to show as active
	 */
	public void setActiveView(String viewName)
	{
		for (Button button : viewButtons.values())
		{
			button.removeStyleName("selected");
		}
		Button selected = viewButtons.get(viewName);
		if (selected != null)
		{
			selected.addStyleName("selected");
		}
		menuPart.removeStyleName(VALO_MENU_VISIBLE);
	}

	/**
	 * Purpose:
	 * <br>
	 * updateUserName<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	@Subscribe
	public void updateUserName(final ProfileUpdatedEvent event)
	{
		Person user = getCurrentUser();
		settingsItem.setText(user.getFirstName() + " " + user.getLastName());
	}
}