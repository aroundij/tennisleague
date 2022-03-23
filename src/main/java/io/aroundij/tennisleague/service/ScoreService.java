/* (C) 2022 */
package io.aroundij.tennisleague.service;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.GameSet;

public interface ScoreService {
    String getScore(GameSet match);

    String getScore(Game game);
}
