/* (C) 2022 */
package io.aroundij.tennisleague.service;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.Match;

public interface ScoreService {
    String getScore(Match match);

    String getScore(Game game);
}
