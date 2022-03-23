/* (C) 2022 */
package io.aroundij.tennisleague.service.impl;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.Match;
import io.aroundij.tennisleague.domain.Player;
import io.aroundij.tennisleague.domain.Score;
import io.aroundij.tennisleague.service.PlayGame;
import io.aroundij.tennisleague.util.RelativeGameScore;
import java.util.Objects;

public class PlayClassicalGameImpl implements PlayGame {
    /**
     * Start a Match between two player, a match is a deque of Games.
     *
     * @return new Match
     */
    @Override
    public Match startMatch() {
        Match match = new Match();
        match.getGames().addLast(new Game());
        return match;
    }

    /**
     * Increment the score of the first parameters, depending on the global score.
     *
     * @param relativeGameScore
     * @return the incremented Score
     */
    @Override
    public void incrementScore(RelativeGameScore relativeGameScore) {
        Score scoreToIncrement = relativeGameScore.getCurrentPlayerScore();
        Score scoreOfAdversary = relativeGameScore.getOpponentScore();
        switch (scoreToIncrement) {
            case SCORE_0:
                relativeGameScore.setCurrentPlayerScore(Score.SCORE_15);
                break;
            case SCORE_15:
                relativeGameScore.setCurrentPlayerScore(Score.SCORE_30);
                break;
            case SCORE_30:
                switch (scoreOfAdversary) {
                    case SCORE_40:
                        relativeGameScore.setCurrentPlayerScore(Score.SCORE_DEUCE);
                        relativeGameScore.setOpponentScore(Score.SCORE_DEUCE);
                        break;
                    default:
                        relativeGameScore.setCurrentPlayerScore(Score.SCORE_40);
                        break;
                }
                break;
            case SCORE_DEUCE:
                relativeGameScore.setCurrentPlayerScore(Score.SCORE_DEUCE_ADV);
                relativeGameScore.setOpponentScore(Score.SCORE_DEUCE_DOWN_TO_ADV);
                break;
            case SCORE_DEUCE_DOWN_TO_ADV:
                relativeGameScore.setCurrentPlayerScore(Score.SCORE_DEUCE);
                relativeGameScore.setOpponentScore(Score.SCORE_DEUCE);
                break;
            case SCORE_40:
            case SCORE_DEUCE_ADV:
                relativeGameScore.setCurrentPlayerScore(Score.SCORE_WINNER);
                relativeGameScore.setOpponentScore(Score.SCORE_LOSER);
                break;
            default:
                relativeGameScore.setCurrentPlayerScore(Score.SCORE_ERR);
                relativeGameScore.setOpponentScore(Score.SCORE_ERR);
        }
    }

    /**
     * Called when the player scores. It increments the score of the scoring player and persists the
     * score to the last game in the match.
     *
     * @param player The scoring player
     * @param match The current match containing the current game
     */
    @Override
    public void playerScored(Player player, Match match) {
        if (Objects.isNull(match)) {
            return;
        }
        Game game = match.getGames().peekLast();
        if (Objects.nonNull(game.getWinner())) {
            game = new Game();
            match.getGames().addLast(game);
        }

        Score scorePlayerA = game.getGameScore().getScoreA();
        Score scorePlayerB = game.getGameScore().getScoreB();

        switch (player) {
            case PLAYER_A:
                {
                    RelativeGameScore relativeGameScore =
                            new RelativeGameScore(scorePlayerA, scorePlayerB);
                    incrementScore(relativeGameScore);
                    game.getGameScore().setScoreA(relativeGameScore.getCurrentPlayerScore());
                    game.getGameScore().setScoreB(relativeGameScore.getOpponentScore());
                    game.setWinner(
                            Score.SCORE_WINNER.equals(relativeGameScore.getCurrentPlayerScore())
                                    ? Player.PLAYER_A
                                    : null);
                    break;
                }
            case PLAYER_B:
                {
                    RelativeGameScore relativeGameScore =
                            new RelativeGameScore(scorePlayerB, scorePlayerA);
                    relativeGameScore.setCurrentPlayerScore(scorePlayerB);
                    relativeGameScore.setOpponentScore(scorePlayerA);
                    incrementScore(relativeGameScore);
                    game.getGameScore().setScoreB(relativeGameScore.getCurrentPlayerScore());
                    game.getGameScore().setScoreA(relativeGameScore.getOpponentScore());
                    game.setWinner(
                            Score.SCORE_WINNER.equals(relativeGameScore.getCurrentPlayerScore())
                                    ? Player.PLAYER_B
                                    : null);
                    break;
                }
        }
    }
}
