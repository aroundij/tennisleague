/* (C) 2022 */
package io.aroundij.tennisleague.service.impl;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.Match;
import io.aroundij.tennisleague.domain.Player;
import io.aroundij.tennisleague.domain.Score;
import io.aroundij.tennisleague.service.PlayGame;
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
     * @param scoreToIncrement The score to be incremented
     * @param scoreOfAdversary The score of the opponent
     * @return the incremented Score
     */
    @Override
    public Score incrementScore(Score scoreToIncrement, Score scoreOfAdversary) {
        switch (scoreToIncrement) {
            case SCORE_0:
                return Score.SCORE_15;
            case SCORE_15:
                return Score.SCORE_30;
            case SCORE_30:
                switch (scoreOfAdversary) {
                    case SCORE_40:
                        return Score.SCORE_DEUCE;
                    default:
                        return Score.SCORE_40;
                }
            case SCORE_DEUCE:
                return Score.SCORE_DEUCE_ADV;
            case SCORE_DEUCE_DOWN_TO_ADV:
                return Score.SCORE_DEUCE;
            case SCORE_40:
            case SCORE_DEUCE_ADV:
                return Score.SCORE_WINNER;
            default:
                return Score.SCORE_ERR;
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
                    game.getGameScore().setScoreA(incrementScore(scorePlayerA, scorePlayerB));
                    if (Score.SCORE_WINNER.equals(game.getGameScore().getScoreA())) {
                        game.setWinner(Player.PLAYER_A);
                        game.getGameScore().setScoreB(Score.SCORE_LOSER);
                    } else if (Score.SCORE_DEUCE.equals(game.getGameScore().getScoreA())) {
                        game.getGameScore().setScoreB(Score.SCORE_DEUCE);
                    } else if (Score.SCORE_DEUCE_ADV.equals(game.getGameScore().getScoreA())) {
                        game.getGameScore().setScoreB(Score.SCORE_DEUCE_DOWN_TO_ADV);
                    }
                    break;
                }
            case PLAYER_B:
                {
                    game.getGameScore().setScoreB(incrementScore(scorePlayerB, scorePlayerA));
                    if (Score.SCORE_WINNER.equals(game.getGameScore().getScoreB())) {
                        game.setWinner(Player.PLAYER_B);
                        game.getGameScore().setScoreA(Score.SCORE_LOSER);
                    } else if (Score.SCORE_DEUCE.equals(game.getGameScore().getScoreB())) {
                        game.getGameScore().setScoreA(Score.SCORE_DEUCE);
                    } else if (Score.SCORE_DEUCE_ADV.equals(game.getGameScore().getScoreB())) {
                        game.getGameScore().setScoreA(Score.SCORE_DEUCE_DOWN_TO_ADV);
                    }
                    break;
                }
        }
    }
}
