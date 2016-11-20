package com.kumbirai.golf;

import javax.servlet.annotation.WebServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;
import com.kumbirai.golf.authentication.AccessControl;
import com.kumbirai.golf.authentication.GolfAccessControl;
import com.kumbirai.golf.authentication.LoginScreen;
import com.kumbirai.golf.event.DashboardEvent.CloseOpenWindowsEvent;
import com.kumbirai.golf.event.DashboardEvent.UserLoggedOutEvent;
import com.kumbirai.golf.event.DashboardEventBus;
import com.kumbirai.golf.views.MainScreen;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Main UI class of the application that shows either the login screen or the
 * main view of the application depending on whether a user is signed in.
 *
 * The @Viewport annotation configures the viewport meta tags appropriately on
 * mobile devices. Instead of device based scaling (default), using responsive
 * layouts.
 */
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("golftheme")
@Widgetset("com.kumbirai.golf.GolfAppWidgetset")
public class GolfUI extends UI
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfUI.class.getName());
	private final DashboardEventBus dashboardEventbus = new DashboardEventBus();
	private AccessControl accessControl = new GolfAccessControl();

	/** (non-Javadoc)
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(VaadinRequest vaadinRequest)
	{
		DashboardEventBus.register(this);
		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		getPage().setTitle("Golf");
		if (!accessControl.isUserSignedIn())
		{
			setContent(new LoginScreen(accessControl, () -> showMainView()));
		}
		else
		{
			showMainView();
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * showMainView<br>
	 * <br><br>
	 */
	protected void showMainView()
	{
		addStyleName(ValoTheme.UI_WITH_MENU);
		setContent(new MainScreen(GolfUI.this));
		getNavigator().navigateTo(getNavigator().getState());
	}

	/**
	 * Purpose:
	 * <br>
	 * get<br>
	 * <br>
	 * @return<br>
	 */
	public static GolfUI get()
	{
		return (GolfUI) UI.getCurrent();
	}

	/**
	 * Purpose:
	 * <br>
	 * getAccessControl<br>
	 * <br>
	 * @return<br>
	 */
	public AccessControl getAccessControl()
	{
		return accessControl;
	}

	/**
	 * Purpose:
	 * <br>
	 * getDashboardEventbus<br>
	 * <br>
	 * @return<br>
	 */
	public static DashboardEventBus getDashboardEventbus()
	{
		return ((GolfUI) getCurrent()).dashboardEventbus;
	}

	/**
	 * Purpose:
	 * <br>
	 * userLoggedOut<br>
	 * <br>
	 * @param event<br>
	 */
	@Subscribe
	public void userLoggedOut(final UserLoggedOutEvent event)
	{
		LOGGER.debug(String.format("UserLoggedOutEvent %s registered", event));
		VaadinSession.getCurrent().getSession().invalidate();
		Page.getCurrent().setUriFragment("", false);
		Page.getCurrent().reload();
	}

	/**
	 * Purpose:
	 * <br>
	 * closeOpenWindows<br>
	 * <br>
	 * @param event<br>
	 */
	@Subscribe
	public void closeOpenWindows(final CloseOpenWindowsEvent event)
	{
		for (Window window : getWindows())
		{
			window.close();
		}
	}

	@WebServlet(urlPatterns = "/*", name = "GolfUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = GolfUI.class, productionMode = false)
	public static class GolfUIServlet extends VaadinServlet
	{
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;
	}
}
