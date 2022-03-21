package io.aroundij.tennisleague.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Game {

    private LocalDateTime startDate = LocalDateTime.now();
    private int scorePlayerA;
    private int scorePlayerB;
    private Player winner;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getScorePlayerA() {
        return scorePlayerA;
    }

    public void setScorePlayerA(int scorePlayerA) {
        this.scorePlayerA = scorePlayerA;
    }

    public int getScorePlayerB() {
        return scorePlayerB;
    }

    public void setScorePlayerB(int scorePlayerB) {
        this.scorePlayerB = scorePlayerB;
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
        return scorePlayerA == game.scorePlayerA && scorePlayerB == game.scorePlayerB && startDate.equals(game.startDate) && winner == game.winner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, scorePlayerA, scorePlayerB, winner);
    }
}
