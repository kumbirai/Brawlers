/*
 com.kumbirai.golf.component.ScoreCardWindow<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.component;

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
import com.vaadin.data.util.BeanItemContainer;
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
	private static final String CENTERED_LABEL = "centeredLabel";
	private static final String OUTLINE = "outline";
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

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
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
		Label lblEventDetails = new Label(getEventDetails(match));
		lblEventDetails.addStyleName(ValoTheme.LABEL_H1);
		content.addComponent(lblEventDetails);

		Table scoreCard = createScoreCardTable();
		content.addComponent(scoreCard);

		// scoreCard.setMargin(false);
		// scoreCard.setSpacing(false);
		scoreCard.setWidth(100, Unit.PERCENTAGE);

		addScoreCardHeadings(match, scoreCard);

		addScoreCards(match, scoreCard);

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
		Table table = new Table();
		table.setImmediate(true);
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);

		container = new BeanItemContainer<>(ScorecardLine.class);
		table.setContainerDataSource(container);
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
	private void addScoreCardHeadings(Match match, Table scoreCard)
	{
		// Label hole = new Label("Hole");
		// hole.addStyleName(ValoTheme.LABEL_COLORED);
		// hole.addStyleName("holeLabel");
		// hole.addStyleName("outlined");
		// hole.setWidth(100, Unit.PIXELS);
		// scoreCard.addComponent(hole, 0, 0);
		// // scoreCard.setComponentAlignment(hole, Alignment.MIDDLE_RIGHT)
		//
		// Label menPar = new Label("Men's Par");
		// menPar.addStyleName(ValoTheme.LABEL_COLORED);
		// menPar.addStyleName("menParLabel");
		// scoreCard.addComponent(menPar, 0, 1);
		// // scoreCard.setComponentAlignment(menPar, Alignment.MIDDLE_RIGHT)
		//
		// Label menStroke = new Label("Men's Handicap");
		// menStroke.addStyleName(ValoTheme.LABEL_COLORED);
		// menStroke.addStyleName("menStrokeLabel");
		// scoreCard.addComponent(menStroke, 0, 2);
		// // scoreCard.setComponentAlignment(menStroke, Alignment.MIDDLE_RIGHT)
		//
		// Label ladiesPar = new Label("Ladies' Par");
		// ladiesPar.addStyleName(ValoTheme.LABEL_COLORED);
		// ladiesPar.addStyleName("ladiesParLabel");
		// scoreCard.addComponent(ladiesPar, 0, 3);
		// // scoreCard.setComponentAlignment(ladiesPar, Alignment.MIDDLE_RIGHT)
		//
		// Label ladiesStroke = new Label("Ladies' Handicap");
		// ladiesStroke.addStyleName(ValoTheme.LABEL_COLORED);
		// ladiesStroke.addStyleName("ladiesStrokeLabel");
		// ladiesStroke.setWidth(120, Unit.PIXELS);
		// scoreCard.addComponent(ladiesStroke, 0, 4);
		// // scoreCard.setComponentAlignment(ladiesStroke, Alignment.MIDDLE_RIGHT)
		//
		// Label total = new Label("Total");
		// total.addStyleName(ValoTheme.LABEL_COLORED);
		// total.addStyleName("holeLabel");
		// scoreCard.addComponent(total, 20, 0);
		// // scoreCard.setComponentAlignment(ladiesStroke, Alignment.MIDDLE_RIGHT)

		Integer[][] courseInfo = getCourseInfo(match);

		int holeNum = 1;
		int idx = 0;
		for (int i = 2; i <= 19; i++)
		{
			Integer[] info = courseInfo[idx++];
			LOGGER.debug(String.format("[%s]info-%s", idx, Arrays.asList(info)));

			Label lblHoleNo = new Label(String.valueOf(holeNum++));
			lblHoleNo.addStyleName(ValoTheme.LABEL_COLORED);
			lblHoleNo.addStyleName("hole");
			lblHoleNo.addStyleName(OUTLINE);
			// scoreCard.addComponent(lblHoleNo, i, 0);
			// scoreCard.setComponentAlignment(lblHoleNo, Alignment.MIDDLE_CENTER);

			Label lblMenParRating = new Label(String.valueOf(info[0]));
			lblMenParRating.addStyleName(ValoTheme.LABEL_SMALL);
			lblMenParRating.addStyleName("menPar");
			lblMenParRating.addStyleName(OUTLINE);
			// scoreCard.addComponent(lblMenParRating, i, 1);
			// scoreCard.setComponentAlignment(lblMenParRating, Alignment.MIDDLE_CENTER);

			Label lblMenStrokeRating = new Label(String.valueOf(info[1]));
			lblMenStrokeRating.addStyleName(ValoTheme.LABEL_SMALL);
			lblMenStrokeRating.addStyleName("menStroke");
			lblMenStrokeRating.addStyleName(OUTLINE);
			// scoreCard.addComponent(lblMenStrokeRating, i, 2);
			// scoreCard.setComponentAlignment(lblMenStrokeRating, Alignment.MIDDLE_CENTER);

			Label lblLadiesParRating = new Label(String.valueOf(info[2]));
			lblLadiesParRating.addStyleName(ValoTheme.LABEL_SMALL);
			lblLadiesParRating.addStyleName("ladiesPar");
			lblLadiesParRating.addStyleName(OUTLINE);
			// scoreCard.addComponent(lblLadiesParRating, i, 3);
			// scoreCard.setComponentAlignment(lblLadiesParRating, Alignment.MIDDLE_CENTER);

			Label lblLadiesStrokeRating = new Label(String.valueOf(info[3]));
			lblLadiesStrokeRating.addStyleName(ValoTheme.LABEL_SMALL);
			lblLadiesStrokeRating.addStyleName("ladiesStroke");
			lblLadiesStrokeRating.addStyleName(OUTLINE);
			// scoreCard.addComponent(lblLadiesStrokeRating, i, 4);
			// scoreCard.setComponentAlignment(lblLadiesStrokeRating, Alignment.MIDDLE_CENTER);
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
	private void addScoreCards(Match match, Table scoreCard)
	{
		List<ScoreCard> scoreCards = new ArrayList<>(match.getScoreCards());
		scoreCards.sort((ScoreCard o1, ScoreCard o2) -> o1.getLineNumber().compareTo(o2.getLineNumber()));
		int lineNumber = 5;
		for (ScoreCard card : scoreCards)
		{
			addPlayer(lineNumber, card, scoreCard);
			addScores(lineNumber, card, scoreCard);
			lineNumber++;
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * addPlayer<br>
	 * <br>
	 * @param lineNumber
	 * @param card
	 * @param scoreCard<br>
	 */
	private void addPlayer(int lineNumber, ScoreCard card, Table scoreCard)
	{
		Label lblPlayer = new Label(card.getPerson().getName());
		lblPlayer.addStyleName(ValoTheme.LABEL_SMALL);
		lblPlayer.addStyleName("playerLabel");
		lblPlayer.addStyleName(OUTLINE);
		lblPlayer.setWidth(175, Unit.PIXELS);
		// scoreCard.addComponent(lblPlayer, 0, lineNumber);
		// scoreCard.setComponentAlignment(lblPlayer, Alignment.MIDDLE_LEFT);

		Label lblHandicap = new Label(String.valueOf(card.getHandicap()));
		lblHandicap.addStyleName(ValoTheme.LABEL_SMALL);
		lblHandicap.addStyleName(CENTERED_LABEL);
		lblHandicap.addStyleName(OUTLINE);
		// scoreCard.addComponent(lblHandicap, 1, lineNumber);
		// scoreCard.setComponentAlignment(lblHandicap, Alignment.MIDDLE_CENTER);
	}

	/**
	 * Purpose:
	 * <br>
	 * addScores<br>
	 * <br>
	 * @param lineNumber
	 * @param card
	 * @param scoreCard<br>
	 */
	private void addScores(int lineNumber, ScoreCard card, Table scoreCard)
	{
		List<Score> scores = new ArrayList<>(card.getScores());
		scores.sort((Score o1, Score o2) -> o1.getHoleNumber().compareTo(o2.getHoleNumber()));
		int idx = 0;
		for (int i = 2; i <= 19; i++)
		{
			Score score = scores.get(idx++);
			Label lblScore = new Label(String.valueOf(score.getStrokes()));
			lblScore.addStyleName(ValoTheme.LABEL_SMALL);
			lblScore.addStyleName(CENTERED_LABEL);
			lblScore.addStyleName(OUTLINE);
			// scoreCard.addComponent(lblScore, i, lineNumber);
			// scoreCard.setComponentAlignment(lblScore, Alignment.MIDDLE_CENTER);
		}
		Label lblTotal = new Label(String.valueOf(card.getTotalStrokes()));
		lblTotal.addStyleName(ValoTheme.LABEL_SMALL);
		lblTotal.addStyleName(CENTERED_LABEL);
		lblTotal.addStyleName(OUTLINE);
		// scoreCard.addComponent(lblTotal, 20, lineNumber);
		// scoreCard.setComponentAlignment(lblTotal, Alignment.MIDDLE_CENTER);

		Label lblNet = new Label(String.valueOf(card.getNetStrokes()));
		lblNet.addStyleName(ValoTheme.LABEL_SMALL);
		lblNet.addStyleName(CENTERED_LABEL);
		lblNet.addStyleName(OUTLINE);
		// scoreCard.addComponent(lblNet, 21, lineNumber);
		// scoreCard.setComponentAlignment(lblNet, Alignment.MIDDLE_CENTER);

		Label lblPoints = new Label(String.valueOf(card.getStandardPoints()));
		lblPoints.addStyleName(ValoTheme.LABEL_SMALL);
		lblPoints.addStyleName(CENTERED_LABEL);
		lblPoints.addStyleName(OUTLINE);
		// scoreCard.addComponent(lblPoints, 22, lineNumber);
		// scoreCard.setComponentAlignment(lblPoints, Alignment.MIDDLE_CENTER);
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

	/**
	 * <p><b>Purpose:</b><br>
	 * <br>
	 *
	 * <p><b>Title:</b> ScorecardLine<br>
	 * <b>Description:</b> </p>
	 *
	 * @author Kumbirai 'Coach' Mundangepfupfu<br>
	 * @date 04 Dec 2016<br>
	 * @version 1.0<br>
	 *
	 * <b>Revision:</b>
	 *
	 */
	private class ScorecardLine
	{
		public String name;
		public Integer handicap;
		public Object hole01;
		public Object hole02;
		public Object hole03;
		public Object hole04;
		public Object hole05;
		public Object hole06;
		public Object hole07;
		public Object hole08;
		public Object hole09;
		public Object hole10;
		public Object hole11;
		public Object hole12;
		public Object hole13;
		public Object hole14;
		public Object hole15;
		public Object hole16;
		public Object hole17;
		public Object hole18;
		public Integer totalStrokes;
		public Integer netStrokes;
		public Integer standardPoints;
	}
}