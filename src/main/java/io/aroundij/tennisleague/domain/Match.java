/* (C) 2022 */
package io.aroundij.tennisleague.domain;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class represents a Set of Games between players. The last game is found by calling
 * peekLast() on the Deque of Games.
 */
public class Match {
    private Deque<Game> games = new ArrayDeque<>();

    public Deque<Game> getGames() {
        return games;
    }

    public void setGames(Deque<Game> games) {
        this.games = games;
    }
}
