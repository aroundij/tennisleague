/* (C) 2022 */
package io.aroundij.tennisleague.service.impl;

import io.aroundij.tennisleague.domain.*;
import io.aroundij.tennisleague.service.GameSetService;
import io.aroundij.tennisleague.service.PlayGame;
import io.aroundij.tennisleague.util.RelativeGameScore;
import io.aroundij.tennisleague.util.RelativeGameSetScore;
import java.util.Objects;

public class PlayClassicalGameImpl implements PlayGame {

    private GameSetService gameSetService;

    public PlayClassicalGameImpl(GameSetService gameSetService) {
        this.gameSetService = gameSetService;
    }

    /**
     * Start a Match between two player, a match is a deque of Games.
     *
     * @return new Match
     */
    @Override
    public GameSet startGameSet() {
        GameSet gameSet = new GameSet();
        gameSet.getGames().addLast(new Game());
        return gameSet;
    }

    @Override
    public Match startMatch() {
        Match match = new Match();
        match.getGameSets().addLast(startGameSet());
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
     * score to the last game in the gameSet.
     *
     * @param player The scoring player
     * @param gameSet The current gameSet containing the current game
     */
    @Override
    public void playerScored(Player player, GameSet gameSet) {
        if (Objects.isNull(gameSet)) {
            return;
        }
        Game game = gameSet.getGames().peekLast();
        if (Objects.nonNull(game.getWinner())) {
            game = new Game();
            gameSet.getGames().addLast(game);
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
                    if (Score.SCORE_WINNER.equals(relativeGameScore.getCurrentPlayerScore())) {
                        game.setWinner(Player.PLAYER_A);
                    }
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

    @Override
    public void playerScored(Player player, Match match) {
        if (Objects.isNull(match)
                || Objects.isNull(match.getGameSets())
                || match.getGameSets().isEmpty()) return;
        GameSet currentGameSet = match.getGameSets().peekLast();

        if (Objects.nonNull(currentGameSet) && Objects.nonNull(currentGameSet.getWinner())) {
            currentGameSet = startGameSet();
            match.getGameSets().addLast(currentGameSet);
        }

        playerScored(player, currentGameSet);

        Game currentGame = currentGameSet.getGames().peekLast();

        switch (player) {
            case PLAYER_A:
                if (Score.SCORE_WINNER.equals(currentGame.getGameScore().getScoreA())) {
                    RelativeGameSetScore relativeGameSetScore =
                            new RelativeGameSetScore(
                                    currentGameSet.getGameSetScore().getScoreA(),
                                    currentGameSet.getGameSetScore().getScoreB());
                    gameSetService.incrementGameSetScore(relativeGameSetScore);
                    currentGameSet
                            .getGameSetScore()
                            .setScoreA(relativeGameSetScore.getCurrentPlayerSetScore());
                    currentGameSet
                            .getGameSetScore()
                            .setScoreB(relativeGameSetScore.getOpponentSetScore());
                    currentGameSet.setWinner(
                            relativeGameSetScore.isWinner() ? Player.PLAYER_A : null);
                }
                break;
            case PLAYER_B:
                if (Score.SCORE_WINNER.equals(currentGame.getGameScore().getScoreB())) {
                    RelativeGameSetScore relativeGameSetScore =
                            new RelativeGameSetScore(
                                    currentGameSet.getGameSetScore().getScoreB(),
                                    currentGameSet.getGameSetScore().getScoreA());
                    gameSetService.incrementGameSetScore(relativeGameSetScore);
                    currentGameSet
                            .getGameSetScore()
                            .setScoreB(relativeGameSetScore.getCurrentPlayerSetScore());
                    currentGameSet
                            .getGameSetScore()
                            .setScoreA(relativeGameSetScore.getOpponentSetScore());
                    currentGameSet.setWinner(
                            relativeGameSetScore.isWinner() ? Player.PLAYER_B : null);
                }
                break;
        }
    }
}
