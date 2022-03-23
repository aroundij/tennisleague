/* (C) 2022 */
package io.aroundij.tennisleague.domain;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class represents a Set of Games between players. The last game is found by calling
 * peekLast() on the Deque of Games.
 */
public class GameSet {
    private Deque<Game> games = new ArrayDeque<>();

    private GameSetScore gameSetScore;
    private Player winner;

    public Deque<Game> getGames() {
        return games;
    }

    public void setGames(Deque<Game> games) {
        this.games = games;
    }

    public GameSetScore getGameSetScore() {
        return gameSetScore;
    }

    public void setGameSetScore(GameSetScore gameSetScore) {
        this.gameSetScore = gameSetScore;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
