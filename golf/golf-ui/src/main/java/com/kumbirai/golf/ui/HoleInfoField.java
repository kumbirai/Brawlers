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
import com.vaadin.ui.Grid.CellReference;
import com.vaadin.ui.Grid.CellStyleGenerator;
import com.vaadin.ui.Grid.RowStyleGenerator;
import com.vaadin.ui.VerticalLayout;

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

	private static final String HOLE_NUMBER = "holeNumber";
	private static final String MEN_PAR_RATING = "menParRating";
	private static final String MEN_STROKE_RATING = "menStrokeRating";
	private static final String LADIES_PAR_RATING = "ladiesParRating";
	private static final String LADIES_STROKE_RATING = "ladiesStrokeRating";

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
		grid.setWidth("350");

		BeanItemContainer<HoleInfo> container = new BeanItemContainer<>(HoleInfo.class);
		grid.setContainerDataSource(container);
		grid.setColumns(HOLE_NUMBER, MEN_PAR_RATING, MEN_STROKE_RATING, LADIES_PAR_RATING, LADIES_STROKE_RATING);

		// grid.addStyleName(ValoTheme.TABLE_BORDERLESS)
		// grid.addStyleName(ValoTheme.TABLE_COMPACT)

		grid.addStyleName("holeinfo");

		// Set nicer header names
		grid.getDefaultHeaderRow().getCell(HOLE_NUMBER).setText("Hole");
		grid.getDefaultHeaderRow().getCell(MEN_PAR_RATING).setText("Par");
		grid.getDefaultHeaderRow().getCell(MEN_STROKE_RATING).setText("Stroke");
		grid.getDefaultHeaderRow().getCell(LADIES_PAR_RATING).setText("Par");
		grid.getDefaultHeaderRow().getCell(LADIES_STROKE_RATING).setText("Stroke");

		for (Grid.Column column : grid.getColumns())
		{
			column.setMaximumWidth(10);
			column.setSortable(false);
			if (HOLE_NUMBER.equalsIgnoreCase(column.getPropertyId() + ""))
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
		grid.setCellStyleGenerator(new CellStyleGenerator()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public String getStyle(CellReference cellReference)
			{
				int holeNum = ((Integer) cellReference.getItem().getItemProperty(HOLE_NUMBER).getValue()).intValue();
				if (cellReference.getPropertyId().equals(HOLE_NUMBER))
				{
					return "hole";
				}
				if (cellReference.getPropertyId().equals(MEN_PAR_RATING) || cellReference.getPropertyId().equals(MEN_STROKE_RATING))
				{
					return (holeNum % 2 == 0) ? "men" : "men-odd";
				}
				if (cellReference.getPropertyId().equals(LADIES_PAR_RATING) || cellReference.getPropertyId().equals(LADIES_STROKE_RATING))
				{
					return (holeNum % 2 == 0) ? "ladies" : "ladies-odd";
				}
				return null;
			}
		});

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