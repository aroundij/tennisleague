/* (C) 2022 */
package io.aroundij.tennisleague.service.impl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.Match;
import io.aroundij.tennisleague.domain.Player;
import io.aroundij.tennisleague.domain.Score;
import io.aroundij.tennisleague.service.PlayGame;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayClassicalGameTest {

    private PlayGame playGame = new PlayClassicalGameImpl();

    @Test
    void startMatch() {

        // Given game stats

        // When
        Match match = playGame.startMatch();

        // Then
        assertNotNull(match);
        assertNotNull(match.getGames());
        assertThat(match.getGames().size() == 1);

        Game game = match.getGames().peekLast();
        assertNotNull(game);
        assertEquals(Score.SCORE_0, game.getGameScore().getScoreA());
        assertEquals(Score.SCORE_0, game.getGameScore().getScoreB());
    }

    @Test
    void incrementScore() {
        Score expectedScore = Score.SCORE_15;
        Score score = Score.SCORE_0;
        Score scoreAdversary = Score.SCORE_0;
        Score scoreResult = Score.SCORE_0;

        // Given Player A scored
        expectedScore = Score.SCORE_15;
        score = Score.SCORE_0;
        scoreAdversary = Score.SCORE_0;
        // When
        scoreResult = playGame.incrementScore(score, scoreAdversary);
        // Then
        assertEquals(expectedScore, scoreResult);

        // Given
        expectedScore = Score.SCORE_30;
        score = Score.SCORE_15;
        scoreAdversary = Score.SCORE_0;
        // When
        scoreResult = playGame.incrementScore(score, scoreAdversary);
        // Then
        assertEquals(expectedScore, scoreResult);

        // Given
        expectedScore = Score.SCORE_WINNER;
        score = Score.SCORE_40;
        scoreAdversary = Score.SCORE_30;
        // When
        scoreResult = playGame.incrementScore(score, scoreAdversary);
        // Then
        assertEquals(expectedScore, scoreResult);

        // Given
        expectedScore = Score.SCORE_ERR;
        score = Score.SCORE_WINNER;
        scoreAdversary = Score.SCORE_0;
        // When
        scoreResult = playGame.incrementScore(score, scoreAdversary);
        // Then
        assertEquals(expectedScore, scoreResult);
    }

    @Test
    void incrementScoreDEUCE() {
        Score expectedScore = Score.SCORE_15;
        Score score = Score.SCORE_0;
        Score scoreAdversary = Score.SCORE_0;
        Score scoreResult = Score.SCORE_0;

        // Given
        expectedScore = Score.SCORE_DEUCE;
        score = Score.SCORE_DEUCE_DOWN_TO_ADV;
        scoreAdversary = Score.SCORE_DEUCE_ADV;
        // When
        scoreResult = playGame.incrementScore(score, scoreAdversary);
        // Then
        assertEquals(expectedScore, scoreResult);

        // Given
        expectedScore = Score.SCORE_DEUCE;
        score = Score.SCORE_30;
        scoreAdversary = Score.SCORE_40;
        // When
        scoreResult = playGame.incrementScore(score, scoreAdversary);
        // Then
        assertEquals(expectedScore, scoreResult);
    }

    @Test
    void incrementScoreDEUCE_ADV() {
        Score expectedScore = Score.SCORE_15;
        Score score = Score.SCORE_0;
        Score scoreAdversary = Score.SCORE_0;
        Score scoreResult = Score.SCORE_0;

        // Given
        expectedScore = Score.SCORE_WINNER;
        score = Score.SCORE_DEUCE_ADV;
        scoreAdversary = Score.SCORE_DEUCE_DOWN_TO_ADV;
        // When
        scoreResult = playGame.incrementScore(score, scoreAdversary);
        // Then
        assertEquals(expectedScore, scoreResult);
    }

    @Test
    void playerScored() {
        // Given
        Match match = new Match();
        match.getGames().addAll(List.of(new Game(), new Game(), new Game()));
        Game lastGame = new Game();
        lastGame.getGameScore().setScoreA(Score.SCORE_15);
        lastGame.getGameScore().setScoreB(Score.SCORE_30);
        match.getGames().addLast(lastGame);

        Score expectedScoreA = Score.SCORE_30;
        Score expectedScoreB = Score.SCORE_30;

        // When
        playGame.playerScored(Player.PLAYER_A, match);
        Game game = match.getGames().peekLast();

        // Then
        assertNotNull(game);
        assertEquals(expectedScoreA, game.getGameScore().getScoreA());
        assertEquals(expectedScoreB, game.getGameScore().getScoreB());
    }

    @Test
    void playerScoredDEUCE() {
        // Given
        Match match = new Match();
        match.getGames().addAll(List.of(new Game(), new Game(), new Game()));
        Game lastGame = new Game();
        lastGame.getGameScore().setScoreA(Score.SCORE_30);
        lastGame.getGameScore().setScoreB(Score.SCORE_40);
        match.getGames().addLast(lastGame);

        Score expectedScoreA = Score.SCORE_DEUCE;
        Score expectedScoreB = Score.SCORE_DEUCE;

        // When
        playGame.playerScored(Player.PLAYER_A, match);
        Game game = match.getGames().peekLast();

        // Then
        assertNotNull(game);
        assertEquals(expectedScoreA, game.getGameScore().getScoreA());
        assertEquals(expectedScoreB, game.getGameScore().getScoreB());

        // Given
        match = new Match();
        match.getGames().addAll(List.of(new Game(), new Game(), new Game()));
        lastGame = new Game();
        lastGame.getGameScore().setScoreA(Score.SCORE_DEUCE_ADV);
        lastGame.getGameScore().setScoreB(Score.SCORE_DEUCE);
        match.getGames().addLast(lastGame);

        expectedScoreA = Score.SCORE_WINNER;
        expectedScoreB = Score.SCORE_LOSER;

        // When
        playGame.playerScored(Player.PLAYER_A, match);
        game = match.getGames().peekLast();

        // Then
        assertNotNull(game);
        assertEquals(expectedScoreA, game.getGameScore().getScoreA());
        assertEquals(expectedScoreB, game.getGameScore().getScoreB());
    }

    @Test
    void playerScoredWithWinner() {
        // Given
        Match match = new Match();
        match.getGames().addAll(List.of(new Game(), new Game(), new Game()));
        Game lastGame = new Game();
        lastGame.getGameScore().setScoreA(Score.SCORE_40);
        lastGame.getGameScore().setScoreB(Score.SCORE_30);
        match.getGames().addLast(lastGame);

        Score expectedScoreA = Score.SCORE_WINNER;
        Score expectedScoreB = Score.SCORE_LOSER;

        Player expectedWinner = Player.PLAYER_A;

        // When
        playGame.playerScored(Player.PLAYER_A, match);
        Game game = match.getGames().peekLast();

        // Then
        assertNotNull(game);
        assertEquals(expectedScoreA, game.getGameScore().getScoreA(), "Score of Player A FAILED");
        assertEquals(expectedScoreB, game.getGameScore().getScoreB(), "Score of Player B FAILED");
        assertEquals(expectedWinner, game.getWinner(), "Winner Result FAILED");
    }
}
