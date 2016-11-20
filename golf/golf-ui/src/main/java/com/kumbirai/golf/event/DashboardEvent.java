/*
 com.kumbirai.golf.event.DashboardEvent<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.event;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> DashboardEvent<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 06 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public abstract class DashboardEvent
{
	public static final class UserLoginRequestedEvent
	{
		private final String userName;
		private final String password;

		public UserLoginRequestedEvent(final String userName, final String password)
		{
			this.userName = userName;
			this.password = password;
		}

		public String getUserName()
		{
			return userName;
		}

		public String getPassword()
		{
			return password;
		}

		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("UserLoginRequestedEvent [userName=%s, password=%s]", this.userName, this.password);
		}
	}

	public static class BrowserResizeEvent
	{
		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("BrowserResizeEvent [class=%s]", this.getClass());
		}
	}

	public static class UserLoggedOutEvent
	{
		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("UserLoggedOutEvent [class=%s]", this.getClass());
		}
	}

	public static class NotificationsCountUpdatedEvent
	{
		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("NotificationsCountUpdatedEvent [class=%s]", this.getClass());
		}
	}

	public static final class ReportsCountUpdatedEvent
	{
		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("ReportsCountUpdatedEvent [class=%s]", this.getClass());
		}
	}

	public static final class TransactionReportEvent
	{
		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("TransactionReportEvent [class=%s]", this.getClass());
		}
	}

	public static final class PostViewChangeEvent
	{
		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("PostViewChangeEvent [class=%s]", this.getClass());
		}
	}

	public static class CloseOpenWindowsEvent
	{
		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("CloseOpenWindowsEvent [class=%s]", this.getClass());
		}
	}

	public static class ProfileUpdatedEvent
	{
		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("ProfileUpdatedEvent [class=%s]", this.getClass());
		}
	}

	public static class EventUpdatedEvent
	{
		/** (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("EventUpdatedEvent [class=%s]", this.getClass());
		}
	}
}