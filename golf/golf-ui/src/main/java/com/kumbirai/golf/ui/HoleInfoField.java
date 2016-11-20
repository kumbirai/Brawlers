/*
 com.kumbirai.golf.ui.HoleInfoField<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.data.course.HoleInfo;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> HoleInfoField<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 16 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class HoleInfoField extends CustomField<Collection<HoleInfo>>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(HoleInfoField.class.getName());
	private VerticalLayout holeLayout;
	private Grid grid;

	/**
	 * Constructor:
	 */
	public HoleInfoField()
	{
		super();
		holeLayout = new VerticalLayout();
		grid = new Grid();
		grid.setEditorEnabled(true);
		grid.setHeightByRows(19);

		BeanItemContainer<HoleInfo> container = new BeanItemContainer<>(HoleInfo.class);
		grid.setContainerDataSource(container);
		grid.setColumns("holeNumber", "menParRating", "menStrokeRating", "ladiesParRating", "ladiesStrokeRating");

		grid.addStyleName(ValoTheme.TABLE_BORDERLESS);
		grid.addStyleName(ValoTheme.TABLE_COMPACT);

		// Set nicer header names
		grid.getDefaultHeaderRow().getCell("holeNumber").setText("Hole");
		grid.getDefaultHeaderRow().getCell("menParRating").setText("Par");
		grid.getDefaultHeaderRow().getCell("menStrokeRating").setText("Stroke");
		grid.getDefaultHeaderRow().getCell("ladiesParRating").setText("Par");
		grid.getDefaultHeaderRow().getCell("ladiesStrokeRating").setText("Stroke");

		for (Grid.Column column : grid.getColumns())
		{
			column.setMaximumWidth(10);
			column.setSortable(false);
			if ("holeNumber".equalsIgnoreCase(column.getPropertyId() + ""))
			{
				column.setEditable(false);
			}
			else
			{
				column.setConverter(new StringToIntegerConverter()
				{
					/**
					 * serialVersionUID
					 */
					private static final long serialVersionUID = 1L;

					/** (non-Javadoc)
					 * @see com.vaadin.data.util.converter.AbstractStringToNumberConverter#convertToPresentation(java.lang.Object, java.lang.Class, java.util.Locale)
					 */
					@Override
					public String convertToPresentation(Integer value, java.lang.Class<? extends String> targetType, Locale locale)
					{
						if (value == 0)
						{
							return "-";
						}

						return super.convertToPresentation(value, targetType, locale);
					}
				});
			}
		}

		holeLayout.addComponent(grid);
	}

	/** (non-Javadoc)
	 * @see com.vaadin.ui.CustomField#initContent()
	 */
	@Override
	protected Component initContent()
	{
		return holeLayout;
	}

	/** (non-Javadoc)
	 * @see com.vaadin.ui.AbstractField#getType()
	 */
	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	@Override
	public Class getType()
	{
		return Collection.class;
	}

	/**
	 * Purpose:
	 * <br>
	 * setOptions<br>
	 * <br>
	 * @param holes<br>
	 */
	public void setHoles(Collection<HoleInfo> holes)
	{
		grid.getContainerDataSource().removeAllItems();
		List<HoleInfo> listHoles = new ArrayList<>(holes);
		listHoles.sort((o1, o2) -> o1.getHoleNumber().compareTo(o2.getHoleNumber()));
		getContainer().addAll(listHoles);
		LOGGER.debug(String.format("Added %s item(s)", listHoles.size()));
	}

	/**
	 * Purpose:
	 * <br>
	 * getContainer<br>
	 * <br>
	 * @return<br>
	 */
	@SuppressWarnings("unchecked")
	public BeanItemContainer<HoleInfo> getContainer()
	{
		return (BeanItemContainer<HoleInfo>) grid.getContainerDataSource();
	}
}