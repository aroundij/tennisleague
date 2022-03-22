/* (C) 2022 */
package io.aroundij.tennisleague.domain;

import java.util.Objects;

/** This class represents the Score of a game in a single instant. */
public class GameScore {

    private Score scoreA = Score.SCORE_0;
    private Score scoreB = Score.SCORE_0;

    public Score getScoreA() {
        return scoreA;
    }

    public void setScoreA(Score scoreA) {
        this.scoreA = scoreA;
    }

    public Score getScoreB() {
        return scoreB;
    }

    public void setScoreB(Score scoreB) {
        this.scoreB = scoreB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameScore gameScore = (GameScore) o;
        return scoreA == gameScore.scoreA && scoreB == gameScore.scoreB;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreA, scoreB);
    }

    @Override
    public String toString() {
        return "Score Player A : " + scoreA.getValue() + " Score Player B : " + scoreB.getValue();
    }
}
