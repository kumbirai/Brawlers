/*
 com.kumbirai.golf.score.CalculateScoreCmd<br>

 Copyright (c) 2016 - Kumbirai 'Coach' Mundangepfupfu (www.kumbirai.com)

 All rights reserved.
 */
package com.kumbirai.golf.score;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kumbirai.commandservice.AbstractCommand;
import com.kumbirai.common.utils.GolfUtils;
import com.kumbirai.golf.data.course.HoleInfo;
import com.kumbirai.golf.data.entity.EGender;
import com.kumbirai.golf.data.score.IPSResult;
import com.kumbirai.golf.data.score.Score;
import com.kumbirai.golf.data.score.ScoreCard;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> CalculateScoreCmd<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 11 Oct 2016<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *
 */
public class CalculateScoreCmd extends AbstractCommand
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CalculateScoreCmd.class.getName());
	private Collection<Score> scores;

	/**
	 * Constructor: @param score
	 */
	public CalculateScoreCmd(Score score)
	{
		super();
		scores = new ArrayList<>();
		scores.add(score);
	}

	/**
	 * Constructor: @param scores
	 */
	public CalculateScoreCmd(Collection<Score> scores)
	{
		super();
		this.scores = scores;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.commandservice.ICommand#execute()
	 */
	@Override
	public void execute()
	{
		for (Score score : scores)
		{
			int stablefordParScore = 0;
			ScoreCard scoreCard = score.getScoreCard();
			EGender gender = scoreCard.getPerson().getGender();
			HoleInfo holeInfo = score.getHoleInfo();
			if (gender == EGender.MALE)
				stablefordParScore = GolfUtils.calculateStablefordParScore(holeInfo.getMenParRating(), holeInfo.getMenStrokeRating(), scoreCard.getHandicap());
			if (gender == EGender.FEMALE)
				stablefordParScore = GolfUtils.calculateStablefordParScore(holeInfo.getLadiesParRating(), holeInfo.getLadiesStrokeRating(),
						scoreCard.getHandicap());

			calculateStablefordPoints(score, stablefordParScore);
			if (score.getIpsResult() == IPSResult.BLOW && scoreCard.getMatchUp().getEvent().isStableford())
				score.setStrokes(stablefordParScore + 2);
		}
		LOGGER.info("Scores Calculated");
	}

	/**
	 * Purpose:
	 * <br>
	 * calculateStablefordPoints<br>
	 * <br>
	 * @param score
	 * @param stablefordParScore<br>
	 */
	private void calculateStablefordPoints(Score score, int stablefordParScore)
	{
		int result = score.getStrokes() - stablefordParScore;

		switch (result)
		{
		case -3:
			score.setIpsResult(IPSResult.DOUBLE_EAGLE);
			break;
		case -2:
			score.setIpsResult(IPSResult.EAGLE);
			break;
		case -1:
			score.setIpsResult(IPSResult.BIRDIE);
			break;
		case 0:
			score.setIpsResult(IPSResult.PAR);
			break;
		case 1:
			score.setIpsResult(IPSResult.BOGGIE);
			break;
		default:
			score.setIpsResult(IPSResult.BLOW);
			break;
		}
	}

	/** Getter for the <code>scores</code> attribute.<br>
	 * @return Collection<Score> - value of the attribute <code>scores</code>.
	 */
	public Collection<Score> getScores()
	{
		return this.scores;
	}
}