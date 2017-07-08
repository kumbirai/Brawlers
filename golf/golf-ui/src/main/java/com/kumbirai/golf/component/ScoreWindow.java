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
import com.kumbirai.golf.data.score.Score;
import com.kumbirai.golf.data.score.ScoreCard;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.TableFieldFactory;
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
	private HoleInfo[] courseInfo = new HoleInfo[18];

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
		carousel.setWidth(400.0f, Unit.PIXELS);
		carousel.setHeight(350.0f, Unit.PIXELS);

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
		carousel.addComponent(createPlayerSummary(match));
	}

	/**
	 * Purpose:
	 * <br>
	 * getCourseInfo<br>
	 * <br>
	 * @param match
	 * @return<br>
	 */
	private HoleInfo[] getCourseInfo(Match match)
	{
		courseInfo = new HoleInfo[18];
		List<HoleInfo> holes = new ArrayList<>(match.getGolfEvent().getCourse().getHoles());
		holes.sort((HoleInfo o1, HoleInfo o2) -> o1.getHoleNumber().compareTo(o2.getHoleNumber()));
		for (int i = 0; i < holes.size(); i++)
		{
			courseInfo[i] = holes.get(i);
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
	private Component getHole(int holeNumber, final Match match, HoleInfo holeInfo)
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
	private Component createHoleInfoGrid(HoleInfo holeInfo)
	{
		Table table = new Table();
		// Define two columns for the built-in container
		table.addContainerProperty("Description", String.class, null);
		table.addContainerProperty("Value", Integer.class, null);

		table.addItem(new Object[]
		{ "Men's Par", holeInfo.getMenParRating() }, 0);
		table.addItem(new Object[]
		{ "Men's Handicap", holeInfo.getMenStrokeRating() }, 1);
		table.addItem(new Object[]
		{ "Ladies' Par", holeInfo.getLadiesParRating() }, 2);
		table.addItem(new Object[]
		{ "Ladies' Handicap", holeInfo.getLadiesStrokeRating() }, 3);

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
				String propertyValue = String.valueOf(propertyId);
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
		table.setEditable(true);
		table.setStyleName("scorecard");
		table.addContainerProperty("Player", String.class, null);
		table.addContainerProperty("Gender", String.class, null);
		table.addContainerProperty("HCP", Integer.class, null);
		table.addContainerProperty("Stks", Integer.class, null);
		table.addContainerProperty("Pts", Integer.class, null);
		//
		List<ScoreCard> scoreCards = getScoreCards(match);
		for (ScoreCard card : scoreCards)
		{
			Score score = getHoleScore(card, holeNumber);
			table.addItem(new Object[]
			{ card.getPerson().getName(), card.getPerson().getGender().toString(), card.getHandicap(), score.getStrokes(),
					score.getIpsResult().getStandardPoints() }, score.getScoreNo());
		}
		// Show exactly the currently contained rows (items)
		table.setPageLength(table.size());
		//
		table.setCellStyleGenerator(new Table.CellStyleGenerator()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getStyle(Table source, Object itemId, Object propertyId)
			{
				Item row = source.getItem(itemId);
				Property<?> genderProp = row.getItemProperty("Gender");
				String genderValue = (String) genderProp.getValue();
				String propertyValue = String.valueOf(propertyId);
				boolean male = "male".equalsIgnoreCase(genderValue);
				if ("HCP".equalsIgnoreCase(propertyValue))
				{
					return male ? "menPar" : "ladiesPar";
				}
				return null;
			}
		});
		//
		table.setVisibleColumns("Player", "HCP", "Stks", "Pts");
		table.setTableFieldFactory(new TableFieldFactory()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			public Field createField(Container container, Object itemId, Object propertyId, Component uiContext)
			{
				// User can only edit the Strokes column
				if (!"Stks".equals(propertyId))
				{
					return null;
				}
				else
				{ // The Strokes column - Use a ComboBox
					ComboBox field = new ComboBox();
					field.setData(itemId);
					field.setInputPrompt("Strokes");
					field.setContainerDataSource(generateContainer());
					field.setNullSelectionAllowed(false);
					field.select(field.getItemIds().iterator().next());
					field.setWidth(60.0f, Unit.PIXELS);
					field.addValueChangeListener(new ValueChangeListener()
					{
						/**
						 * serialVersionUID
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(ValueChangeEvent event)
						{
							LOGGER.debug(String.format("(%s) New value for %s is %s", event.getProperty().getValue(), field.getData(), field.getValue()));
						}
					});
					return field;
				}
			}

			private Container generateContainer()
			{
				IndexedContainer container = new IndexedContainer();
				container.addContainerProperty("index", Integer.class, null);
				container.addContainerProperty("value", Integer.class, null);
				for (int i = 0; i <= 12; i++)
				{
					Item item = container.addItem(i);
					item.getItemProperty("index").setValue(i);
					item.getItemProperty("value").setValue(i);
				}
				return container;
			}
		});
		return table;
	}

	/**
	 * Purpose:
	 * <br>
	 * createPlayerSummary<br>
	 * <br>
	 * @param match
	 * @return<br>
	 */
	private Component createPlayerSummary(Match match)
	{
		VerticalLayout content = new VerticalLayout();

		Label lblCourse = new Label(match.getGolfEvent().getCourse().toString());
		lblCourse.addStyleName(ValoTheme.LABEL_BOLD);
		lblCourse.addStyleName(ValoTheme.LABEL_COLORED);
		content.addComponent(lblCourse);

		Label lblTotals = new Label("Totals");
		lblTotals.addStyleName(ValoTheme.LABEL_BOLD);
		lblTotals.addStyleName(ValoTheme.LABEL_COLORED);
		content.addComponent(lblTotals);

		Table table = new Table();
		table.setStyleName("scorecard");
		table.addContainerProperty("Player", String.class, null);
		table.addContainerProperty("Gender", String.class, null);
		table.addContainerProperty("HCP", Integer.class, null);
		table.addContainerProperty("Grs", Integer.class, null);
		table.addContainerProperty("Net", Integer.class, null);
		table.addContainerProperty("Pts", Integer.class, null);
		//
		List<ScoreCard> scoreCards = getScoreCards(match);
		for (ScoreCard card : scoreCards)
		{
			table.addItem(new Object[]
			{ card.getPerson().getName(), card.getPerson().getGender().toString(), card.getHandicap(), card.getTotalStrokes(), card.getNetStrokes(),
					card.getStandardPoints() }, card.getScoreCardNo());
		}
		// Show exactly the currently contained rows (items)
		table.setPageLength(table.size());
		//
		table.setCellStyleGenerator(new Table.CellStyleGenerator()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getStyle(Table source, Object itemId, Object propertyId)
			{
				Item row = source.getItem(itemId);
				Property<?> genderProp = row.getItemProperty("Gender");
				String genderValue = (String) genderProp.getValue();
				String propertyValue = String.valueOf(propertyId);
				boolean male = "male".equalsIgnoreCase(genderValue);
				if ("HCP".equalsIgnoreCase(propertyValue))
				{
					return male ? "menPar" : "ladiesPar";
				}
				return null;
			}
		});
		//
		table.setVisibleColumns("Player", "HCP", "Grs", "Net", "Pts");
		content.addComponent(table);
		return content;
	}

	/**
	 * Purpose:
	 * <br>
	 * getScoreCards<br>
	 * <br>
	 * @param match
	 * @return<br>
	 */
	private List<ScoreCard> getScoreCards(Match match)
	{
		List<ScoreCard> scoreCards = new ArrayList<>(match.getScoreCards());
		scoreCards.sort((ScoreCard o1, ScoreCard o2) -> o1.getLineNumber().compareTo(o2.getLineNumber()));
		return scoreCards;
	}

	/**
	 * Purpose:
	 * <br>
	 * getHoleScore<br>
	 * <br>
	 * @param scoreCard
	 * @param holeNumber
	 * @return<br>
	 */
	private Score getHoleScore(ScoreCard scoreCard, int holeNumber)
	{
		List<Score> scores = new ArrayList<>(scoreCard.getScores());
		scores.sort((Score o1, Score o2) -> o1.getHoleNumber().compareTo(o2.getHoleNumber()));
		return scores.get(holeNumber - 1);
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