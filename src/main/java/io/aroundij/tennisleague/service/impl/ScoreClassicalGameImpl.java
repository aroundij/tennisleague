/* (C) 2022 */
package io.aroundij.tennisleague.service.impl;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.Match;
import io.aroundij.tennisleague.service.ScoreService;
import java.util.Objects;

public class ScoreClassicalGameImpl implements ScoreService {

    /**
     * Renders the score of a match. The score of the match contains the score of all the games
     * played, including the current game.
     *
     * @param match the current match
     * @return a String representation of the score of a match
     */
    @Override
    public String getScore(Match match) {
        if (Objects.isNull(match)) return "ERROR : No match available !!";
        StringBuilder scoreBuilder = new StringBuilder();
        int i = 1;
        int size = match.getGames().size();
        for (Game game : match.getGames()) {
            if (size == i) {
                scoreBuilder.append("Current Game : " + getScore(game));
            } else {
                scoreBuilder.append("Game " + i + " : " + getScore(game));
            }

            scoreBuilder.append("\n");
            i++;
        }
        return scoreBuilder.toString();
    }

    /**
     * Get the score of a single game as a String.
     *
     * @param game The game to which the score is to be rendered
     * @return a String representation of the Game Score
     */
    @Override
    public String getScore(Game game) {
        if (Objects.isNull(game)) return "ERROR: No game available !!";

        return game.getGameScore().toString();
    }
}
