/* (C) 2022 */
package io.aroundij.tennisleague.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.Match;
import io.aroundij.tennisleague.domain.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayGameTest {

    @Test
    void startMatch() {

        // Given game stats

        // When
        Match match = PlayGame.startMatch();

        // Then
        assertNotNull(match);
        assertNotNull(match.getGames());
        assertThat(match.getGames().size() == 1);

        Game game = match.getGames().peek();
        assertNotNull(game);
        assertEquals(0, game.getScorePlayerA());
        assertEquals(0, game.getScorePlayerB());
    }

    @Test
    void incrementScore() {
        int expectedScore = 15;
        int score = 0;
        int scoreResult = 0;

        // Given
        expectedScore = 15;
        score = 0;
        // When
        scoreResult = PlayGame.incrementScore(score);
        // Then
        assertEquals(expectedScore, scoreResult);

        // Given
        expectedScore = 30;
        score = 15;
        // When
        scoreResult = PlayGame.incrementScore(score);
        // Then
        assertEquals(expectedScore, scoreResult);

        // Given
        expectedScore = 40;
        score = 30;
        // When
        scoreResult = PlayGame.incrementScore(score);
        // Then
        assertEquals(expectedScore, scoreResult);

        // Given
        expectedScore = 1;
        score = 40;
        // When
        scoreResult = PlayGame.incrementScore(score);
        // Then
        assertEquals(expectedScore, scoreResult);

        // Given
        expectedScore = -1;
        score = 10;
        // When
        scoreResult = PlayGame.incrementScore(score);
        // Then
        assertEquals(expectedScore, scoreResult);
    }

    @Test
    void playerScored() {
        // Given
        Match match = new Match();
        match.getGames().addAll(List.of(new Game(), new Game(), new Game()));
        Game lastGame = new Game();
        lastGame.setScorePlayerA(15);
        lastGame.setScorePlayerB(30);
        match.getGames().addFirst(lastGame);

        int expectedScorePlayerA = 30;
        int expectedScorePlayerB = 30;

        // When
        PlayGame.playerScored(Player.PLAYER_A, match);
        Game game = match.getGames().peek();

        // Then
        assertNotNull(game);
        assertEquals(expectedScorePlayerA, game.getScorePlayerA());
        assertEquals(expectedScorePlayerB, game.getScorePlayerB());
    }

    @Test
    void playerScoredWithWinner() {
        // Given
        Match match = new Match();
        match.getGames().addAll(List.of(new Game(), new Game(), new Game()));
        Game lastGame = new Game();
        lastGame.setScorePlayerA(40);
        lastGame.setScorePlayerB(30);
        match.getGames().addFirst(lastGame);

        int expectedScorePlayerA = 1;
        int expectedScorePlayerB = 0;

        Player expectedWinner = Player.PLAYER_A;

        // When
        PlayGame.playerScored(Player.PLAYER_A, match);
        Game game = match.getGames().peek();

        // Then
        assertNotNull(game);
        assertEquals(expectedScorePlayerA, game.getScorePlayerA(), "Score of Player A FAILED");
        assertEquals(expectedScorePlayerB, game.getScorePlayerB(), "Score of Player B FAILED");
        assertEquals(expectedWinner, game.getWinner(), "Winner Result FAILED");
    }
}
