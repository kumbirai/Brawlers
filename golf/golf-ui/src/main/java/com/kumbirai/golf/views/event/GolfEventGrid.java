/*
 com.kumbirai.golf.views.event.GolfEventGrid<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.component.GolfEventWindow;
import com.kumbirai.golf.converters.RandConverter;
import com.kumbirai.golf.data.event.GolfEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.renderers.ButtonRenderer;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> GolfEventGrid<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 09 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class GolfEventGrid extends Grid
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(GolfEventGrid.class.getName());

	/**
	 * Constructor:
	 */
	public GolfEventGrid()
	{
		super();
		setSizeFull();

		setSelectionMode(SelectionMode.SINGLE);

		BeanItemContainer<GolfEvent> container = new BeanItemContainer<>(GolfEvent.class);
		setContainerDataSource(container);
		setColumns("eventDate", "teeOff", "course", "attire", "greenFees", "eventSponsor", "availableSlots", "stableford");

		Grid.Column eventDate = getColumn("eventDate");
		eventDate.setConverter(new StringToDateConverter()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected DateFormat getFormat(Locale locale)
			{
				DateFormat f = new SimpleDateFormat("dd MMM yyyy");
				f.setLenient(true);
				return f;
			}
		});

		eventDate.setRenderer(new ButtonRenderer(e ->
		{
			GolfEventWindow.open((GolfEvent) e.getItemId(), false);
			Notification.show("Clicked " + e.getItemId());
		}));

		Grid.Column stableford = getColumn("stableford");
		stableford.setConverter(new StringToBooleanConverter("Yes", "No"));

		Grid.Column greenFees = getColumn("greenFees");
		greenFees.setConverter(new RandConverter());

		setCellStyleGenerator(new CellStyleGenerator()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getStyle(CellReference cellReference)
			{
				if (cellReference.getPropertyId().equals("greenFees") || cellReference.getPropertyId().equals("availableSlots"))
				{
					return "align-right";
				}
				return null;
			}
		});
	}

	/**
	 * Filter the grid based on a search string that is searched for in the
	 * golfEvent name, availability and category columns.
	 *
	 * @param filterString
	 *            string to look for
	 */
	public void setFilter(String filterString)
	{
		getContainer().removeAllContainerFilters();
		if (filterString.length() > 0)
		{
			SimpleStringFilter courseFilter = new SimpleStringFilter("course", filterString, true, false);
			SimpleStringFilter sponsorFilter = new SimpleStringFilter("eventSponsor", filterString, true, false);
			getContainer().addContainerFilter(new Or(courseFilter, sponsorFilter));
		}

	}

	/**
	 * Purpose:
	 * <br>
	 * getContainer<br>
	 * <br>
	 * @return<br>
	 */
	private BeanItemContainer<GolfEvent> getContainer()
	{
		return (BeanItemContainer<GolfEvent>) super.getContainerDataSource();
	}

	/** (non-Javadoc)
	 * @see com.vaadin.ui.Grid#getSelectedRow()
	 */
	@Override
	public GolfEvent getSelectedRow() throws IllegalStateException
	{
		return (GolfEvent) super.getSelectedRow();
	}

	/**
	 * Purpose:
	 * <br>
	 * setGolfEvents<br>
	 * <br>
	 * @param golfEvents<br>
	 */
	public void setGolfEvents(Collection<GolfEvent> golfEvents)
	{
		getContainer().removeAllItems();
		getContainer().addAll(golfEvents);
	}

	/**
	 * Purpose:
	 * <br>
	 * refresh<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	public void refresh(GolfEvent golfEvent)
	{
		// We avoid updating the whole table through the backend here so we can
		// get a partial update for the grid
		BeanItem<GolfEvent> item = getContainer().getItem(golfEvent);
		if (item != null)
		{
			// Updated golfEvent
			MethodProperty p = (MethodProperty) item.getItemProperty("eventNo");
			p.fireValueChange();
		}
		else
		{
			// New golfEvent
			getContainer().addBean(golfEvent);
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * remove<br>
	 * <br>
	 * @param golfEvent<br>
	 */
	public void remove(GolfEvent golfEvent)
	{
		getContainer().removeItem(golfEvent);
	}
}
