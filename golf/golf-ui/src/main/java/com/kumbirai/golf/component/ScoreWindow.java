/*
 com.kumbirai.golf.component.ScoreWindow<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.virkki.carousel.HorizontalCarousel;
import org.vaadin.virkki.carousel.client.widget.gwt.ArrowKeysMode;
import org.vaadin.virkki.carousel.client.widget.gwt.CarouselLoadMode;

import com.kumbirai.golf.data.course.HoleInfo;
import com.kumbirai.golf.data.score.Match;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> ScoreWindow<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 18 Dec 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class ScoreWindow extends Window
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ScoreWindow.class.getName());
	public static final String ID = "scorewindow";
	private Integer[][] courseInfo = new Integer[18][4];

	/**
	 * Constructor:
	 * @param index
	 * @param match
	 */
	public ScoreWindow(final Match match, int index)
	{
		super();
		addStyleName("score-window");
		setId(ID);
		Responsive.makeResponsive(this);

		setModal(true);
		addCloseShortcut(KeyCode.ESCAPE, null);
		setResizable(false);
		setClosable(true);

		VerticalLayout content = new VerticalLayout();
		content.setMargin(new MarginInfo(true, false, false, false));
		setContent(content);

		Component carousel = buildCarousel(match, index);

		content.addComponent(carousel);
		content.setExpandRatio(carousel, 2.0f);
		content.addComponent(buildFooter());
	}

	/**
	 * Purpose:
	 * <br>
	 * buildCarousel<br>
	 * <br>
	 * @param match
	 * @param index
	 * @return<br>
	 */
	private HorizontalCarousel buildCarousel(final Match match, int index)
	{
		final HorizontalCarousel carousel = new HorizontalCarousel();

		// Only react to arrow keys when focused
		carousel.setArrowKeysMode(ArrowKeysMode.FOCUS);
		// Fetch children smartly
		carousel.setLoadMode(CarouselLoadMode.SMART);
		// Transition animations between the children run 800 milliseconds
		carousel.setTransitionDuration(800);

		populateCarousel(carousel, match);

		return carousel;
	}

	/**
	 * Purpose:
	 * <br>
	 * populateCarousel<br>
	 * <br>
	 * @param carousel
	 * @param match<br>
	 */
	private void populateCarousel(final HorizontalCarousel carousel, final Match match)
	{
		courseInfo = getCourseInfo(match);
		LOGGER.debug(String.format("courseInfo:%s", Arrays.asList(courseInfo)));
		// Add the child Components
		for (int holeNumber = 1; holeNumber <= 18; holeNumber++)
		{
			carousel.addComponent(getHole(holeNumber, match, courseInfo[holeNumber - 1]));
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * getCourseInfo<br>
	 * <br>
	 * @param match
	 * @return<br>
	 */
	private Integer[][] getCourseInfo(Match match)
	{
		Integer[][] courseInfo = new Integer[18][4];
		List<HoleInfo> holes = new ArrayList<>(match.getGolfEvent().getCourse().getHoles());
		holes.sort((HoleInfo o1, HoleInfo o2) -> o1.getHoleNumber().compareTo(o2.getHoleNumber()));
		for (int i = 0; i < holes.size(); i++)
		{
			HoleInfo holeInfo = holes.get(i);
			courseInfo[i] = new Integer[]
			{ holeInfo.getMenParRating(), holeInfo.getMenStrokeRating(), holeInfo.getLadiesParRating(), holeInfo.getLadiesStrokeRating() };
		}
		return courseInfo;
	}

	/**
	 * Purpose:
	 * <br>
	 * getHole<br>
	 * <br>
	 * @param holeNumber
	 * @param match
	 * @param holeInfo
	 * @return<br>
	 */
	private Component getHole(int holeNumber, final Match match, Integer[] holeInfo)
	{
		VerticalLayout content = new VerticalLayout();

		Label lblCourse = new Label(match.getGolfEvent().getCourse().toString());
		lblCourse.addStyleName(ValoTheme.LABEL_BOLD);
		lblCourse.addStyleName(ValoTheme.LABEL_COLORED);
		content.addComponent(lblCourse);

		Label lblHoleNumber = new Label(String.format("Hole # %s", holeNumber));
		lblHoleNumber.addStyleName(ValoTheme.LABEL_BOLD);
		lblHoleNumber.addStyleName(ValoTheme.LABEL_COLORED);
		content.addComponent(lblHoleNumber);

		Component holeInfos = createHoleInfoGrid(holeInfo);
		content.addComponent(holeInfos);

		Component playerScores = createPlayerScores(match, holeNumber);
		content.addComponent(playerScores);

		return content;
	}

	/**
	 * Purpose:
	 * <br>
	 * createHoleInfoGrid<br>
	 * <br>
	 * @param holeInfo
	 * @return<br>
	 */
	private Component createHoleInfoGrid(Integer[] holeInfo)
	{
		Table table = new Table();
		// Define two columns for the built-in container
		table.addContainerProperty("Description", String.class, null);
		table.addContainerProperty("Value", Integer.class, null);

		table.addItem(new Object[]
		{ "Men's Par", holeInfo[0] }, 0);
		table.addItem(new Object[]
		{ "Men's Handicap", holeInfo[1] }, 1);
		table.addItem(new Object[]
		{ "Ladies' Par", holeInfo[2] }, 2);
		table.addItem(new Object[]
		{ "Ladies' Handicap", holeInfo[3] }, 3);

		// Show exactly the currently contained rows (items)
		table.setPageLength(table.size());
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.addStyleName("scorecard");
		table.setCellStyleGenerator(new Table.CellStyleGenerator()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getStyle(Table source, Object itemId, Object propertyId)
			{
				LOGGER.debug(String.format("%s - %s - %s", source, itemId, propertyId));
				String propertyValue = propertyId != null ? (String) propertyId : "";
				if ("Description".equalsIgnoreCase(propertyValue))
					return "header";
				if ("0".equals(String.valueOf(itemId)))
					return "menPar";
				if ("1".equals(String.valueOf(itemId)))
					return "menStroke";
				if ("2".equals(String.valueOf(itemId)))
					return "ladiesPar";
				if ("3".equals(String.valueOf(itemId)))
					return "ladiesStroke";
				return null;
			}
		});
		return table;
	}

	/**
	 * Purpose:
	 * <br>
	 * createPlayerScores<br>
	 * <br>
	 * @param match
	 * @param holeNumber
	 * @return<br>
	 */
	private Component createPlayerScores(Match match, int holeNumber)
	{
		Table table = new Table();
		// Show exactly the currently contained rows (items)
		table.setPageLength(table.size());
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		return table;
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
		// ok.addClickListener(new OkButtonClickListener())
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
	 * @param match
	 * @param index<br>
	 */
	public static void open(final Match match, int index)
	{
		Window w = new ScoreWindow(match, index);
		UI.getCurrent().addWindow(w);
		w.focus();
	}
}