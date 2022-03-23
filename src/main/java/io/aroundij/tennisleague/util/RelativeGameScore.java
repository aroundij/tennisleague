/* (C) 2022 */
package io.aroundij.tennisleague.util;

import io.aroundij.tennisleague.domain.Score;

public class RelativeGameScore {

    private Score currentPlayerScore;
    private Score opponentScore;

    public RelativeGameScore(Score currentPlayerScore, Score opponentScore) {
        this.currentPlayerScore = currentPlayerScore;
        this.opponentScore = opponentScore;
    }

    public Score getCurrentPlayerScore() {
        return currentPlayerScore;
    }

    public void setCurrentPlayerScore(Score currentPlayerScore) {
        this.currentPlayerScore = currentPlayerScore;
    }

    public Score getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(Score opponentScore) {
        this.opponentScore = opponentScore;
    }
}
