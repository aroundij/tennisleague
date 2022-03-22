/* (C) 2022 */
package io.aroundij.tennisleague.domain;

/** The possible values of the scores of a classical Tennis Game. */
public enum Score {
    SCORE_0("0"),
    SCORE_15("15"),
    SCORE_30("30"),
    SCORE_40("40"),
    SCORE_DEUCE("DEUCE"),
    SCORE_DEUCE_ADV("ADV"),
    SCORE_DEUCE_DOWN_TO_ADV(""),
    SCORE_WINNER("1"),
    SCORE_LOSER("0"),
    SCORE_ERR("-1");
    private String value;

    Score(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
