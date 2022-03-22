/* (C) 2022 */
package io.aroundij.tennisleague.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represents a single game between players. the game is concluded when a winner is
 * declared.
 */
public class Game {

    private LocalDateTime startDate = LocalDateTime.now();
    private GameScore gameScore = new GameScore();
    private Player winner;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    public void setGameScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(startDate, game.startDate)
                && Objects.equals(gameScore, game.gameScore)
                && winner == game.winner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, gameScore, winner);
    }
}
