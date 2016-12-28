/*
 com.kumbirai.golf.component.ScoreCardWindow<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.common.lang.StringUtils;
import com.kumbirai.golf.data.course.HoleInfo;
import com.kumbirai.golf.data.score.Match;
import com.kumbirai.golf.data.score.Score;
import com.kumbirai.golf.data.score.ScoreCard;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> ScoreCardWindow<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 29 Nov 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> ScoreCardWindow<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 04 Dec 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class ScoreCardWindow extends Window
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ScoreCardWindow.class.getName());
	public static final String ID = "scorecardwindow";
	private BeanItemContainer<ScorecardLine> container;

	private ScoreCardWindow(final Match match)
	{
		addStyleName("scorecard-window");
		setId(ID);
		Responsive.makeResponsive(this);

		setModal(true);
		addCloseShortcut(KeyCode.ESCAPE, null);
		setResizable(false);
		setClosable(true);
		setHeight(90.0f, Unit.PERCENTAGE);
		setWidth(780.0f, Unit.PIXELS);

		VerticalLayout content = new VerticalLayout();
		// content.setHeight(100.0f, Unit.PERCENTAGE)
		content.setMargin(new MarginInfo(true, false, false, false));
		setContent(content);

		Component scoreCard = buildScoreCard(match);
		content.addComponent(scoreCard);
		content.setExpandRatio(scoreCard, 1f);
		content.addComponent(buildFooter());
	}

	/**
	 * Purpose:
	 * <br>
	 * buildScoreCard<br>
	 * <br>
	 * @param match
	 * @return<br>
	 */
	private Component buildScoreCard(Match match)
	{
		VerticalLayout content = new VerticalLayout();
		// content.setHeight(100.0f, Unit.PERCENTAGE)

		Label lblEventDetails = new Label(getEventDetails(match));
		lblEventDetails.addStyleName(ValoTheme.LABEL_H1);
		content.addComponent(lblEventDetails);

		Table scoreCard = createScoreCardTable();
		content.addComponent(scoreCard);
		// content.setExpandRatio(scoreCard, 1f)

		addScoreCardHeadings(match);

		addScoreCards(match);

		return content;
	}

	/**
	 * Purpose:
	 * <br>
	 * createScoreCardTable<br>
	 * <br>
	 * @return<br>
	 */
	private Table createScoreCardTable()
	{
		container = new BeanItemContainer<>(ScorecardLine.class);
		Table table = new Table(null, container);
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.setVisibleColumns("name", "handicap", "hole1", "hole2", "hole3", "hole4", "hole5", "hole6", "hole7", "hole8", "hole9", "hole10", "hole11",
				"hole12", "hole13", "hole14", "hole15", "hole16", "hole17", "hole18", "totalStrokes", "netStrokes", "standardPoints");
		table.setImmediate(true);
		table.setSortEnabled(false);
		table.setPageLength(10);

		table.addStyleName("scorecard");
		table.setWidth(100.0f, Unit.PERCENTAGE);
		table.setColumnWidth("handicap", 50);

		for (int i = 1; i <= 18; i++)
		{
			String fieldName = String.format("hole%s", i);
			table.setColumnWidth(fieldName, 40);
			table.addGeneratedColumn(fieldName, new ColumnGenerator()
			{
				/**
				* serialVersionUID
				*/
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(Table source, final Object itemId, Object columnId)
				{
					Item row = source.getItem(itemId);
					Property<?> item = row.getItemProperty(fieldName);
					Object value = item.getValue();
					if (value != null)
					{
						Class<?> clazz = value.getClass();
						Label lbl = new Label(value.toString());
						if (clazz == Integer.class)
						{
							lbl.addStyleName(ValoTheme.LABEL_SMALL);
							return lbl;
						}
						else if (clazz == String.class)
						{
							lbl.addStyleName(ValoTheme.LABEL_TINY);
							return lbl;
						}
						else if (clazz == Score.class)
						{
							return buildScore((Score) value);
						}
					}
					return null;
				}
			});
		}
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
				Property<?> styleProp = row.getItemProperty("style");
				String styleValue = (String) styleProp.getValue();
				String propertyValue = propertyId != null ? (String) propertyId : "";
				boolean header = "name".equalsIgnoreCase(propertyValue) || "handicap".equalsIgnoreCase(propertyValue);
				if ("person".equalsIgnoreCase(styleValue) && propertyId != null)
				{
					return header ? "name" : isStyledProperty(propertyValue.toUpperCase()) ? propertyValue : null;
				}
				if (!"person".equalsIgnoreCase(styleValue) && propertyId != null && header)
				{
					return "header";
				}
				if (styleValue != null)
					return styleValue;

				return null;
			}

			/**
			 * Purpose:
			 * <br>
			 * isStyledProperty<br>
			 * <br>
			 * @param propertyValue
			 * @return<br>
			 */
			private boolean isStyledProperty(String propertyValue)
			{
				String[] propertyValues = new String[]
				{ "standardPoints".toUpperCase() };
				return Arrays.asList(propertyValues).contains(propertyValue);
			}
		});
		return table;
	}

	/**
	 * Purpose:
	 * <br>
	 * getEventDetails<br>
	 * <br>
	 * @param match
	 * @return<br>
	 */
	private String getEventDetails(Match match)
	{
		return String.format("%s - %s", match.getGolfEvent().getCourse().getCourseName(),
				StringUtils.formatDate(match.getGolfEvent().getEventDate(), "d MMM yyyy"));
	}

	/**
	 * Purpose:
	 * <br>
	 * addScoreCardHeadings<br>
	 * <br>
	 * @param match
	 * @param scoreCard<br>
	 */
	private void addScoreCardHeadings(Match match)
	{
		Integer[][] courseInfo = getCourseInfo(match);
		ScorecardLine hole = new ScorecardLine("hole");
		hole.setName("Hole");
		ScorecardLine menPar = new ScorecardLine("menPar");
		menPar.setName("Men's Par");
		ScorecardLine menStroke = new ScorecardLine("menStroke");
		menStroke.setName("Men's Handicap");
		ScorecardLine ladiesPar = new ScorecardLine("ladiesPar");
		ladiesPar.setName("Ladies' Par");
		ScorecardLine ladiesStroke = new ScorecardLine("ladiesStroke");
		ladiesStroke.setName("Ladies' Handicap");

		int holeNum = 1;
		for (int i = 0; i < 18; i++)
		{
			Integer[] info = courseInfo[i];
			try
			{
				String fieldName = String.format("hole%s", holeNum);
				//
				Class<?> holeClass = hole.getClass();
				Field holeField = holeClass.getDeclaredField(fieldName);
				holeField.setAccessible(true);
				holeField.set(hole, Integer.valueOf(holeNum));
				//
				Class<?> menParClass = menPar.getClass();
				Field menParField = menParClass.getDeclaredField(fieldName);
				menParField.setAccessible(true);
				menParField.set(menPar, Integer.valueOf(info[0]));
				//
				Class<?> menStrokeClass = menStroke.getClass();
				Field menStrokeField = menStrokeClass.getDeclaredField(fieldName);
				menStrokeField.setAccessible(true);
				menStrokeField.set(menStroke, Integer.valueOf(info[1]));
				//
				Class<?> ladiesParClass = ladiesPar.getClass();
				Field ladiesParField = ladiesParClass.getDeclaredField(fieldName);
				ladiesParField.setAccessible(true);
				ladiesParField.set(ladiesPar, Integer.valueOf(info[2]));
				//
				Class<?> ladiesStrokeClass = ladiesStroke.getClass();
				Field ladiesStrokeField = ladiesStrokeClass.getDeclaredField(fieldName);
				ladiesStrokeField.setAccessible(true);
				ladiesStrokeField.set(ladiesStroke, Integer.valueOf(info[3]));
			}
			catch (NoSuchFieldException ex)
			{
				LOGGER.error("[NoSuchFieldException] has been caught.", ex);
			}
			catch (SecurityException ex)
			{
				LOGGER.error("[SecurityException] has been caught.", ex);
			}
			catch (IllegalArgumentException ex)
			{
				LOGGER.error("[IllegalArgumentException] has been caught.", ex);
			}
			catch (IllegalAccessException ex)
			{
				LOGGER.error("[IllegalAccessException] has been caught.", ex);
			}
			holeNum++;
		}
		container.addBean(hole);
		container.addBean(menPar);
		container.addBean(menStroke);
		container.addBean(ladiesPar);
		container.addBean(ladiesStroke);
		ScorecardLine blank = new ScorecardLine("blank");
		blank.setTotalStrokes("Grs");
		blank.setNetStrokes("Net");
		blank.setStandardPoints("Pts");
		container.addBean(blank);
		if (LOGGER.isDebugEnabled())
		{
			int idx = 0;
			for (int i = 0; i < container.size(); i++)
			{
				ScorecardLine bean = container.getIdByIndex(idx++);
				LOGGER.debug(String.format("%s", bean));
			}
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
	 * addScoreCards<br>
	 * <br>
	 * @param match
	 * @param scoreCard<br>
	 */
	private void addScoreCards(Match match)
	{
		List<ScoreCard> scoreCards = new ArrayList<>(match.getScoreCards());
		scoreCards.sort((ScoreCard o1, ScoreCard o2) -> o1.getLineNumber().compareTo(o2.getLineNumber()));
		for (ScoreCard card : scoreCards)
		{
			ScorecardLine scoreCardLine = new ScorecardLine("person");
			addScores(card, scoreCardLine);
			container.addBean(scoreCardLine);
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * addScores<br>
	 * <br>
	 * @param card
	 * @param scoreCard<br>
	 */
	private void addScores(ScoreCard card, ScorecardLine scoreCard)
	{
		scoreCard.setName(card.getPerson().getName());
		scoreCard.setHandicap(card.getHandicap());
		List<Score> scores = new ArrayList<>(card.getScores());
		scores.sort((Score o1, Score o2) -> o1.getHoleNumber().compareTo(o2.getHoleNumber()));
		int idx = 0;
		Class<?> scoreCardClass = scoreCard.getClass();
		try
		{
			for (int i = 1; i <= 18; i++)
			{
				Score score = scores.get(idx++);
				String fieldName = String.format("hole%s", i);
				//
				Field holeField = scoreCardClass.getDeclaredField(fieldName);
				holeField.setAccessible(true);
				holeField.set(scoreCard, score);
			}
		}
		catch (NoSuchFieldException ex)
		{
			LOGGER.error("[NoSuchFieldException] has been caught.", ex);
		}
		catch (SecurityException ex)
		{
			LOGGER.error("[SecurityException] has been caught.", ex);
		}
		catch (IllegalArgumentException ex)
		{
			LOGGER.error("[IllegalArgumentException] has been caught.", ex);
		}
		catch (IllegalAccessException ex)
		{
			LOGGER.error("[IllegalAccessException] has been caught.", ex);
		}
		scoreCard.setTotalStrokes(card.getTotalStrokes());
		scoreCard.setNetStrokes(card.getNetStrokes());
		scoreCard.setStandardPoints(card.getStandardPoints());
	}

	/**
	 * Purpose:
	 * <br>
	 * buildScore<br>
	 * <br>
	 * @param score
	 * @return<br>
	 */
	private Component buildScore(Score score)
	{
		VerticalLayout scoreLayout = new VerticalLayout();
		Button btnStrokes = new Button(String.valueOf(score.getStrokes()));
		btnStrokes.addClickListener(new ClickListener()
		{
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/** (non-Javadoc)
			 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
			 */
			@Override
			public void buttonClick(ClickEvent event)
			{
				ScoreWindow.open(score.getScoreCard().getMatchUp(), score.getHoleNumber() - 1);
			}
		});
		btnStrokes.setDescription("Click to edit scores");
		btnStrokes.addStyleName(ValoTheme.BUTTON_LINK);
		btnStrokes.addStyleName(ValoTheme.BUTTON_SMALL);
		scoreLayout.addComponent(btnStrokes);

		HorizontalLayout pointsLayout = new HorizontalLayout();
		Label lblEdit = new Label(String.valueOf(score.getIpsResult().getStandardPoints()));
		lblEdit.addStyleName(ValoTheme.LABEL_SMALL);
		pointsLayout.addComponent(lblEdit);

		scoreLayout.addComponent(pointsLayout);
		return scoreLayout;
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
	 * @param match<br>
	 */
	public static void open(final Match match)
	{
		Window w = new ScoreCardWindow(match);
		UI.getCurrent().addWindow(w);
		w.focus();
	}
}