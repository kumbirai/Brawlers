/*
 com.kumbirai.golf.views.person.TitleField<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.views.person;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.golf.data.entity.ETitle;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> TitleField<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 04 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class TitleField extends CustomField<Set<ETitle>>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(TitleField.class.getName());
	private VerticalLayout options;

	/**
	 * Constructor:
	 */
	public TitleField()
	{
		super();
		options = new VerticalLayout();
		setOptions();
	}

	/**
	 * Constructor: @param caption
	 */
	public TitleField(String caption)
	{
		this();
		setCaption(caption);
	}

	/**
	 * Purpose:
	 * <br>
	 * setOptions<br>
	 * <br><br>
	 */
	private void setOptions()
	{
		options.removeAllComponents();
		for (ETitle title : ETitle.values())
		{
			options.addComponent(new Label(title.name()));
		}
	}

	/** (non-Javadoc)
	 * @see com.vaadin.ui.CustomField#initContent()
	 */
	@Override
	protected Component initContent()
	{
		return options;
	}

	/** (non-Javadoc)
	 * @see com.vaadin.ui.AbstractField#getType()
	 */
	@Override
	public Class getType()
	{
		return Set.class;
	}
}