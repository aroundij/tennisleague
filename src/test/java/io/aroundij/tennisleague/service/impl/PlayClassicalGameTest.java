/* (C) 2022 */
package io.aroundij.tennisleague.service.impl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.GameSet;
import io.aroundij.tennisleague.domain.Player;
import io.aroundij.tennisleague.domain.Score;
import io.aroundij.tennisleague.service.PlayGame;
import io.aroundij.tennisleague.util.RelativeGameScore;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayClassicalGameTest {

    private PlayGame playGame = new PlayClassicalGameImpl();

    @Test
    void startMatch() {

        // Given game stats

        // When
        GameSet match = playGame.startMatch();

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

        // Given
        List<List<RelativeGameScore>> givenData =
                List.of(
                        List.of(
                                new RelativeGameScore(Score.SCORE_0, Score.SCORE_0),
                                new RelativeGameScore(Score.SCORE_15, Score.SCORE_0)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_15, Score.SCORE_0),
                                new RelativeGameScore(Score.SCORE_30, Score.SCORE_0)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_30, Score.SCORE_0),
                                new RelativeGameScore(Score.SCORE_40, Score.SCORE_0)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_40, Score.SCORE_0),
                                new RelativeGameScore(Score.SCORE_WINNER, Score.SCORE_LOSER)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_0, Score.SCORE_15),
                                new RelativeGameScore(Score.SCORE_15, Score.SCORE_15)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_15, Score.SCORE_15),
                                new RelativeGameScore(Score.SCORE_30, Score.SCORE_15)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_30, Score.SCORE_15),
                                new RelativeGameScore(Score.SCORE_40, Score.SCORE_15)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_40, Score.SCORE_15),
                                new RelativeGameScore(Score.SCORE_WINNER, Score.SCORE_LOSER)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_0, Score.SCORE_30),
                                new RelativeGameScore(Score.SCORE_15, Score.SCORE_30)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_15, Score.SCORE_30),
                                new RelativeGameScore(Score.SCORE_30, Score.SCORE_30)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_30, Score.SCORE_30),
                                new RelativeGameScore(Score.SCORE_40, Score.SCORE_30)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_40, Score.SCORE_30),
                                new RelativeGameScore(Score.SCORE_WINNER, Score.SCORE_LOSER)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_0, Score.SCORE_40),
                                new RelativeGameScore(Score.SCORE_15, Score.SCORE_40)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_15, Score.SCORE_40),
                                new RelativeGameScore(Score.SCORE_30, Score.SCORE_40)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_30, Score.SCORE_40),
                                new RelativeGameScore(Score.SCORE_DEUCE, Score.SCORE_DEUCE)),
                        List.of(
                                new RelativeGameScore(Score.SCORE_DEUCE, Score.SCORE_DEUCE),
                                new RelativeGameScore(
                                        Score.SCORE_DEUCE_ADV, Score.SCORE_DEUCE_DOWN_TO_ADV)),
                        List.of(
                                new RelativeGameScore(
                                        Score.SCORE_DEUCE_ADV, Score.SCORE_DEUCE_DOWN_TO_ADV),
                                new RelativeGameScore(Score.SCORE_WINNER, Score.SCORE_LOSER)),
                        List.of(
                                new RelativeGameScore(
                                        Score.SCORE_DEUCE_DOWN_TO_ADV, Score.SCORE_DEUCE_ADV),
                                new RelativeGameScore(Score.SCORE_DEUCE, Score.SCORE_DEUCE)));

        for (List<RelativeGameScore> givenRelativeGameAndExpectedRelativeGame : givenData) {
            // When
            RelativeGameScore inputRelativeGameScore =
                    givenRelativeGameAndExpectedRelativeGame.get(0);
            RelativeGameScore expectedRelativeGameScore =
                    givenRelativeGameAndExpectedRelativeGame.get(1);

            playGame.incrementScore(inputRelativeGameScore);

            // Then
            assertEquals(
                    expectedRelativeGameScore.getCurrentPlayerScore(),
                    inputRelativeGameScore.getCurrentPlayerScore());
            assertEquals(
                    expectedRelativeGameScore.getOpponentScore(),
                    inputRelativeGameScore.getOpponentScore());
        }
    }

    @Test
    void playerScored() {
        // Given
        GameSet match = new GameSet();
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
        GameSet match = new GameSet();
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
        match = new GameSet();
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
    void playerScoredWithWinnerPlayerA() {
        // Given
        GameSet match = new GameSet();
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

    @Test
    void playerScoredWithWinnerPlayerB() {
        // Given
        GameSet match = new GameSet();
        match.getGames().addAll(List.of(new Game(), new Game(), new Game()));
        Game lastGame = new Game();
        lastGame.getGameScore().setScoreA(Score.SCORE_30);
        lastGame.getGameScore().setScoreB(Score.SCORE_40);
        match.getGames().addLast(lastGame);

        Score expectedScoreA = Score.SCORE_LOSER;
        Score expectedScoreB = Score.SCORE_WINNER;

        Player expectedWinner = Player.PLAYER_B;

        // When
        playGame.playerScored(Player.PLAYER_B, match);
        Game game = match.getGames().peekLast();

        // Then
        assertNotNull(game);
        assertEquals(expectedScoreA, game.getGameScore().getScoreA(), "Score of Player A FAILED");
        assertEquals(expectedScoreB, game.getGameScore().getScoreB(), "Score of Player B FAILED");
        assertEquals(expectedWinner, game.getWinner(), "Winner Result FAILED");
    }

    @Test
    void playerScoredNewGame() {
        // Given
        GameSet match = new GameSet();
        match.getGames().addAll(List.of(new Game(), new Game(), new Game()));
        Game lastGame = new Game();
        lastGame.getGameScore().setScoreA(Score.SCORE_WINNER);
        lastGame.getGameScore().setScoreB(Score.SCORE_LOSER);
        lastGame.setWinner(Player.PLAYER_A);
        match.getGames().addLast(lastGame);

        Score expectedScoreA = Score.SCORE_15;
        Score expectedScoreB = Score.SCORE_0;


        // When
        playGame.playerScored(Player.PLAYER_A, match);
        Game game = match.getGames().peekLast();

        // Then
        assertNotNull(game);
        assertEquals(expectedScoreA, game.getGameScore().getScoreA(), "Score of Player A FAILED");
        assertEquals(expectedScoreB, game.getGameScore().getScoreB(), "Score of Player B FAILED");
    }
}
