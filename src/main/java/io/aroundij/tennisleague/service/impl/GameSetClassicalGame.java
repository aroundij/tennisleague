package io.aroundij.tennisleague.service.impl;

import io.aroundij.tennisleague.service.GameSetService;
import io.aroundij.tennisleague.util.RelativeGameSetScore;

public class GameSetClassicalGame  implements GameSetService {

    @Override
    public void incrementGameSetScore(RelativeGameSetScore relativeGameSetScore) {
        if(relativeGameSetScore.getCurrentPlayerSetScore() > relativeGameSetScore.getOpponentSetScore()) {
            relativeGameSetScore.setCurrentPlayerSetScore(relativeGameSetScore.getCurrentPlayerSetScore() + 1);
            if(relativeGameSetScore.getCurrentPlayerSetScore() > 5) {
                relativeGameSetScore.setWinner(true);
            }
        }
        else if(relativeGameSetScore.getCurrentPlayerSetScore() < relativeGameSetScore.getOpponentSetScore()) {
            relativeGameSetScore.setCurrentPlayerSetScore(relativeGameSetScore.getCurrentPlayerSetScore() + 1);
        }
        else if(relativeGameSetScore.getCurrentPlayerSetScore() == relativeGameSetScore.getOpponentSetScore()) {
            relativeGameSetScore.setCurrentPlayerSetScore(relativeGameSetScore.getCurrentPlayerSetScore() + 1);
        }
    }
}
