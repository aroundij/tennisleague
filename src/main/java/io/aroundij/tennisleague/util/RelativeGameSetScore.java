/* (C) 2022 */
package io.aroundij.tennisleague.util;

public class RelativeGameSetScore {
    int currentPlayerSetScore;
    int opponentSetScore;
    boolean winner;

    public RelativeGameSetScore(int currentPlayerSetScore, int opponentSetScore) {
        this.currentPlayerSetScore = currentPlayerSetScore;
        this.opponentSetScore = opponentSetScore;
    }

    public RelativeGameSetScore(int currentPlayerSetScore, int opponentSetScore, boolean winner) {
        this.currentPlayerSetScore = currentPlayerSetScore;
        this.opponentSetScore = opponentSetScore;
        this.winner = winner;
    }

    public int getCurrentPlayerSetScore() {
        return currentPlayerSetScore;
    }

    public void setCurrentPlayerSetScore(int currentPlayerSetScore) {
        this.currentPlayerSetScore = currentPlayerSetScore;
    }

    public int getOpponentSetScore() {
        return opponentSetScore;
    }

    public void setOpponentSetScore(int opponentSetScore) {
        this.opponentSetScore = opponentSetScore;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
