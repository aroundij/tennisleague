/* (C) 2022 */
package io.aroundij.tennisleague.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import io.aroundij.tennisleague.service.GameSetService;
import io.aroundij.tennisleague.util.RelativeGameSetScore;
import java.util.List;
import org.junit.jupiter.api.Test;

class GameSetClassicalGameTest {

    private final GameSetService gameSetService = new GameSetClassicalGame();

    @Test
    void incrementGameSetScore() {
        List<List<RelativeGameSetScore>> inputData =
                List.of(
                        List.of(new RelativeGameSetScore(0, 0), new RelativeGameSetScore(1, 0)),
                        List.of(new RelativeGameSetScore(1, 0), new RelativeGameSetScore(2, 0)),
                        List.of(new RelativeGameSetScore(2, 0), new RelativeGameSetScore(3, 0)),
                        List.of(new RelativeGameSetScore(3, 0), new RelativeGameSetScore(4, 0)),
                        List.of(new RelativeGameSetScore(4, 0), new RelativeGameSetScore(5, 0)),
                        List.of(
                                new RelativeGameSetScore(5, 0),
                                new RelativeGameSetScore(6, 0, true)),
                        List.of(new RelativeGameSetScore(0, 1), new RelativeGameSetScore(1, 1)),
                        List.of(new RelativeGameSetScore(1, 1), new RelativeGameSetScore(2, 1)),
                        List.of(new RelativeGameSetScore(2, 1), new RelativeGameSetScore(3, 1)),
                        List.of(new RelativeGameSetScore(3, 1), new RelativeGameSetScore(4, 1)),
                        List.of(new RelativeGameSetScore(4, 1), new RelativeGameSetScore(5, 1)),
                        List.of(
                                new RelativeGameSetScore(5, 1),
                                new RelativeGameSetScore(6, 1, true)),
                        List.of(new RelativeGameSetScore(0, 2), new RelativeGameSetScore(1, 2)),
                        List.of(new RelativeGameSetScore(1, 2), new RelativeGameSetScore(2, 2)),
                        List.of(new RelativeGameSetScore(2, 2), new RelativeGameSetScore(3, 2)),
                        List.of(new RelativeGameSetScore(3, 2), new RelativeGameSetScore(4, 2)),
                        List.of(new RelativeGameSetScore(4, 2), new RelativeGameSetScore(5, 2)),
                        List.of(
                                new RelativeGameSetScore(5, 2),
                                new RelativeGameSetScore(6, 2, true)),
                        List.of(new RelativeGameSetScore(0, 3), new RelativeGameSetScore(1, 3)),
                        List.of(new RelativeGameSetScore(1, 3), new RelativeGameSetScore(2, 3)),
                        List.of(new RelativeGameSetScore(2, 3), new RelativeGameSetScore(3, 3)),
                        List.of(new RelativeGameSetScore(3, 3), new RelativeGameSetScore(4, 3)),
                        List.of(new RelativeGameSetScore(4, 3), new RelativeGameSetScore(5, 3)),
                        List.of(
                                new RelativeGameSetScore(5, 3),
                                new RelativeGameSetScore(6, 3, true)),
                        List.of(new RelativeGameSetScore(0, 4), new RelativeGameSetScore(1, 4)),
                        List.of(new RelativeGameSetScore(1, 4), new RelativeGameSetScore(2, 4)),
                        List.of(new RelativeGameSetScore(2, 4), new RelativeGameSetScore(3, 4)),
                        List.of(new RelativeGameSetScore(3, 4), new RelativeGameSetScore(4, 4)),
                        List.of(new RelativeGameSetScore(4, 4), new RelativeGameSetScore(5, 4)),
                        List.of(
                                new RelativeGameSetScore(5, 4),
                                new RelativeGameSetScore(6, 4, true)),
                        List.of(new RelativeGameSetScore(0, 5), new RelativeGameSetScore(1, 5)),
                        List.of(new RelativeGameSetScore(1, 5), new RelativeGameSetScore(2, 5)),
                        List.of(new RelativeGameSetScore(2, 5), new RelativeGameSetScore(3, 5)),
                        List.of(new RelativeGameSetScore(3, 5), new RelativeGameSetScore(4, 5)),
                        List.of(new RelativeGameSetScore(4, 5), new RelativeGameSetScore(5, 5)),
                        List.of(new RelativeGameSetScore(5, 5), new RelativeGameSetScore(6, 5)),
                        List.of(
                                new RelativeGameSetScore(6, 5),
                                new RelativeGameSetScore(7, 5, true)));

        for (List<RelativeGameSetScore> inputRelativeGameSetScoreAndExpectedGameSetScore :
                inputData) {
            RelativeGameSetScore input = inputRelativeGameSetScoreAndExpectedGameSetScore.get(0);
            RelativeGameSetScore expected = inputRelativeGameSetScoreAndExpectedGameSetScore.get(1);

            gameSetService.incrementGameSetScore(input);

            assertEquals(expected.getCurrentPlayerSetScore(), input.getCurrentPlayerSetScore());
            assertEquals(expected.getOpponentSetScore(), input.getOpponentSetScore());
            assertEquals(expected.isWinner(), input.isWinner());
        }
    }
}
