/* (C) 2022 */
package io.aroundij.tennisleague.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.GameScore;
import io.aroundij.tennisleague.domain.Score;
import io.aroundij.tennisleague.service.ScoreService;
import org.junit.jupiter.api.Test;

class ScoreClassicalGameTest {

    private ScoreService scoreService = new ScoreClassicalGameImpl();

    @Test
    public void getGameScore() {
        // Given
        Game game = new Game();
        GameScore gameScore = new GameScore();
        gameScore.setScoreA(Score.SCORE_15);
        gameScore.setScoreB(Score.SCORE_0);
        game.setGameScore(gameScore);

        String expected = gameScore.toString();

        // When
        String result = scoreService.getScore(game);
        assertEquals(expected, result);
    }

    @Test
    public void getMatchScore() {
        // Given
        Game game = new Game();
        GameScore gameScore = new GameScore();
        gameScore.setScoreA(Score.SCORE_15);
        gameScore.setScoreB(Score.SCORE_0);
        game.setGameScore(gameScore);

        String expected = gameScore.toString();

        // When
        String result = scoreService.getScore(game);
        assertEquals(expected, result);
    }
}
