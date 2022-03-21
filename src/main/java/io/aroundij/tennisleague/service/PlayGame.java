package io.aroundij.tennisleague.service;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.Match;
import io.aroundij.tennisleague.domain.Player;

import java.util.Objects;

public class PlayGame {

    public static Match startMatch() {
        Match match = new Match();
        match.getGames().addFirst(new Game());
        return match;
    }

    public static int incrementScore(int score) {
        switch (score) {
            case 0: return 15;
            case 15: return 30;
            case 30: return 40;
            case 40: return 1;
            default: return -1;
        }
    }

    public static void playerScored(Player player, Match match) {
        if(Objects.isNull(match)) {
            return;
        }
        Game game = match.getGames().peek();

        int scorePlayerA = game.getScorePlayerA();
        int scorePlayerB = game.getScorePlayerB();

        switch (player) {
            case PLAYER_A -> {
                game.setScorePlayerA(incrementScore(scorePlayerA));
                if(game.getScorePlayerA() == 1) {
                    game.setWinner(Player.PLAYER_A);
                    game.setScorePlayerB(0);
                }
            }
            case PLAYER_B -> {
                game.setScorePlayerB(incrementScore(scorePlayerB));
                if(game.getScorePlayerB() == 1) {
                    game.setWinner(Player.PLAYER_B);
                    game.setScorePlayerA(0);
                }
            }
        }
    }
}
