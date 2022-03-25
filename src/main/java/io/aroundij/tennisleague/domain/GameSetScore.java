/* (C) 2022 */
package io.aroundij.tennisleague.domain;

/** This class represents the score of two player in a GameSet */
public class GameSetScore {
    private int scoreA = 0;
    private int scoreB = 0;

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    @Override
    public String toString() {
        return "GameSetScore{" + "scorePlayerA=" + scoreA + ", scorePlayerB=" + scoreB + '}';
    }
}
