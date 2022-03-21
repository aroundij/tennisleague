package io.aroundij.tennisleague.domain;

import java.util.ArrayDeque;
import java.util.Deque;

public class Match {
    private Deque<Game> games = new ArrayDeque<>();

    public Deque<Game> getGames() {
        return games;
    }

    public void setGames(Deque<Game> games) {
        this.games = games;
    }
}
