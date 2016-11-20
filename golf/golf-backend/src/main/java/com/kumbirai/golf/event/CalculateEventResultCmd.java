/*
 com.kumbirai.golf.event.CalculateEventResultCmd<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.commandservice.AbstractCommand;
import com.kumbirai.golf.data.course.HoleInfo;
import com.kumbirai.golf.data.entity.EGender;
import com.kumbirai.golf.data.event.GolfEvent;
import com.kumbirai.golf.data.event.GolfEventResult;
import com.kumbirai.golf.data.score.Match;
import com.kumbirai.golf.data.score.Score;
import com.kumbirai.golf.data.score.ScoreCard;
import com.kumbirai.golf.facade.event.GolfEventFacade;
import com.kumbirai.golf.score.CalculateScoreCmd;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> CalculateEventResultCmd<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 24 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class CalculateEventResultCmd extends AbstractCommand
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CalculateEventResultCmd.class.getName());
	private GolfEvent golfEvent;
	private Collection<ScoreCard> scoreCards;
	private Map<Integer, Collection<ScoreCard>> resultMap;

	/**
	 * Constructor: @param golfEvent
	 */
	public CalculateEventResultCmd(GolfEvent golfEvent)
	{
		super();
		this.golfEvent = golfEvent;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.commandservice.ICommand#execute()
	 */
	@Override
	public void execute()
	{
		getEventScoreCards();
		for (ScoreCard card : scoreCards)
		{
			calculateScores(card);
		}
		determineEventResults();
		determinePositions();
	}

	/**
	 * Purpose:
	 * <br>
	 * getEventScoreCards<br>
	 * <br><br>
	 */
	private void getEventScoreCards()
	{
		scoreCards = new ArrayList<>();
		for (Match match : golfEvent.getMatches())
		{
			for (ScoreCard card : match.getScoreCards())
			{
				scoreCards.add(card);
			}
		}
		LOGGER.info(String.format("Score Cards retrieved - %s", scoreCards));
	}

	/**
	 * Purpose:
	 * <br>
	 * calculateScores<br>
	 * <br>
	 * @param card<br>
	 */
	private void calculateScores(ScoreCard card)
	{
		if (!card.isVerified())
		{
			CalculateScoreCmd scoreCmd = new CalculateScoreCmd(card.getScores());
			scoreCmd.execute();
			card.setScores(scoreCmd.getScores());
			updateScoreCard(card);
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * updateScoreCard<br>
	 * <br>
	 * @param card<br>
	 */
	private void updateScoreCard(ScoreCard card)
	{
		boolean complete = true;
		int totalStrokes = 0;
		int classicPoints = 0;
		int standardPoints = 0;
		for (Score score : card.getScores())
		{
			Integer strokes = score.getStrokes();
			if (strokes == 0)
				complete = false;
			totalStrokes += strokes;
			classicPoints += score.getIpsResult().getClassicPoints();
			standardPoints += score.getIpsResult().getStandardPoints();
		}
		card.setTotalStrokes(totalStrokes);
		card.setClassicPoints(classicPoints);
		card.setStandardPoints(standardPoints);
		card.setNetStrokes(totalStrokes - card.getHandicap());
		card.setComplete(complete);
	}

	/**
	 * Purpose:
	 * <br>
	 * determineEventResults<br>
	 * <br><br>
	 */
	private void determineEventResults()
	{
		resultMap = new TreeMap<>();
		for (ScoreCard card : scoreCards)
		{
			Integer standardPoints = card.getStandardPoints();
			Collection<ScoreCard> cards = resultMap.get(standardPoints);
			if (cards == null)
				cards = new ArrayList<>();
			cards.add(card);
			resultMap.put(standardPoints, cards);
		}
	}

	/**
	 * Purpose:
	 * <br>
	 * determinePositions<br>
	 * <br><br>
	 */
	private void determinePositions()
	{
		GolfEventFacade golfEventFacade = new GolfEventFacade();
		golfEventFacade.setSecurityPrincipal(getSecurityPrincipal());
		golfEventFacade.clearEventResults(golfEvent);
		//
		List<GolfEventResult> results = new LinkedList<>();
		//
		NavigableMap<Integer, Collection<ScoreCard>> reverseTreeMap = ((TreeMap<Integer, Collection<ScoreCard>>) resultMap).descendingMap();
		LOGGER.info(reverseTreeMap);
		int pos = 1;
		for (Map.Entry<Integer, Collection<ScoreCard>> entry : reverseTreeMap.entrySet())
		{
			Integer key = entry.getKey();
			Collection<ScoreCard> value = entry.getValue();
			int size = value.size();
			LOGGER.info(String.format("%s: %s record(s)", key, size));
			if (size == 1)
			{
				ScoreCard card = value.iterator().next();
				GolfEventResult golfEventResult = new GolfEventResult();
				golfEventResult.setEventNo(golfEvent.getEventNo());
				golfEventResult.setEvent(golfEvent);
				golfEventResult.setPosition(pos++);
				golfEventResult.setScoreCard(card);
				results.add(golfEventResult);
			}
			else
			{
				for (ScoreCard card : countOut(value))
				{
					GolfEventResult golfEventResult = new GolfEventResult();
					golfEventResult.setEventNo(golfEvent.getEventNo());
					golfEventResult.setEvent(golfEvent);
					golfEventResult.setPosition(pos++);
					golfEventResult.setScoreCard(card);
					golfEventResult.setTieBreaker(true);
					results.add(golfEventResult);
				}
			}
		}
		LOGGER.info(String.format("results - %s", results));
		golfEvent.setEventResults(results);
		golfEventFacade.save(golfEvent);
	}

	/**
	 * Purpose:
	 * <br>
	 * countOut<br>
	 * <br>
	 * @param cards
	 * @return<br>
	 */
	private Collection<ScoreCard> countOut(Collection<ScoreCard> cards)
	{
		List<ScoreCard> results = new LinkedList<>();
		Collection<ScoreCard> remaining = new LinkedList<>(cards);
		int strokeRating = 1;
		while (!remaining.isEmpty() && strokeRating <= 18)
		{
			Collection<ScoreCard> winner = determineBestResult(strokeRating++, remaining);
			if (winner.size() == 1)
			{
				ScoreCard card = winner.iterator().next();
				results.add(card);
				remaining.remove(card);
			}
		}
		if (!remaining.isEmpty())
		{
			LOGGER.info(String.format("Remaining has %s records - %s", remaining.size(), remaining));
			((LinkedList<ScoreCard>) remaining).sort((ScoreCard o1, ScoreCard o2) -> o2.getHandicap().compareTo(o1.getHandicap()));
			results.addAll(remaining);
		}
		return results;
	}

	/**
	 * Purpose:
	 * <br>
	 * determineBestResult<br>
	 * <br>
	 * @param strokeRating
	 * @param cards
	 * @return<br>
	 */
	private Collection<ScoreCard> determineBestResult(int strokeRating, Collection<ScoreCard> cards)
	{
		List<ScoreCard> best = new ArrayList<>();
		TreeMap<Integer, Collection<ScoreCard>> bestMap = new TreeMap<>();

		for (ScoreCard card : cards)
		{
			Integer points = getPointsForStrokeHole(strokeRating, card);
			Collection<ScoreCard> bestCards = bestMap.get(points);
			if (bestCards == null)
				bestCards = new ArrayList<>();
			bestCards.add(card);
			bestMap.put(points, bestCards);
		}

		NavigableMap<Integer, Collection<ScoreCard>> reverseTreeMap = bestMap.descendingMap();
		for (Map.Entry<Integer, Collection<ScoreCard>> entry : reverseTreeMap.entrySet())
		{
			best.addAll(entry.getValue());
			break;
		}
		return best;
	}

	/**
	 * Purpose:
	 * <br>
	 * getPointsForStrokeHole<br>
	 * <br>
	 * @param strokeRating
	 * @param card
	 * @return<br>
	 */
	private Integer getPointsForStrokeHole(int strokeRating, ScoreCard card)
	{
		EGender gender = card.getPerson().getGender();
		for (Score score : card.getScores())
		{
			HoleInfo holeInfo = score.getHoleInfo();
			if (gender == EGender.MALE && holeInfo.getMenStrokeRating() == strokeRating)
			{
				return score.getIpsResult().getStandardPoints();
			}
			if (gender == EGender.FEMALE && holeInfo.getLadiesStrokeRating() == strokeRating)
			{
				return score.getIpsResult().getStandardPoints();
			}
		}
		return 0;
	}

	/** Getter for the <code>golfEvent</code> attribute.<br>
	 * @return GolfEvent - value of the attribute <code>golfEvent</code>.
	 */
	public GolfEvent getEvent()
	{
		return this.golfEvent;
	}
}