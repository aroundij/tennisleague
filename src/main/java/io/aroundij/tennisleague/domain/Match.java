/* (C) 2022 */
package io.aroundij.tennisleague.domain;

import java.util.ArrayDeque;
import java.util.Deque;

public class Match {
    private Deque<GameSet> gameSets = new ArrayDeque<>();

    public Deque<GameSet> getGameSets() {
        return gameSets;
    }

    public void setGameSets(Deque<GameSet> gameSets) {
        this.gameSets = gameSets;
    }
}
