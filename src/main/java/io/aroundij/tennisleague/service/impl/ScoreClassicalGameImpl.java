/* (C) 2022 */
package io.aroundij.tennisleague.service.impl;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.GameSet;
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
        if (Objects.isNull(match)) return "ERROR : No Game Set available !!";
        StringBuilder scoreBuilder = new StringBuilder();
        int i = 1;
        int size = match.getGameSets().size();
        for (GameSet gameSet : match.getGameSets()) {
            if (size == i) {
                scoreBuilder.append("Current GameSet : " + getScore(gameSet));
                scoreBuilder.append("Current Game : " + getScore(gameSet.getGames().peekLast()));
            } else {
                scoreBuilder.append("GameSet " + i + " : " + getScore(gameSet));
            }

            scoreBuilder.append("\n");
            i++;
        }
        return scoreBuilder.toString();
    }

    /**
     * Renders the score of a match. The score of the match contains the score of all the games
     * played, including the current game.
     *
     * @param gameSet the current match
     * @return a String representation of the score of a match
     */
    @Override
    public String getScore(GameSet gameSet) {
        if (Objects.isNull(gameSet) || Objects.isNull(gameSet.getGameSetScore()))
            return "ERROR : No Game Set available !!";
        return gameSet.getGameSetScore().toString();
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
