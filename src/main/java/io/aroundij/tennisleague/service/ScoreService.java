/* (C) 2022 */
package io.aroundij.tennisleague.service;

import io.aroundij.tennisleague.domain.Game;
import io.aroundij.tennisleague.domain.GameSet;
import io.aroundij.tennisleague.domain.Match;

public interface ScoreService {

    String getScore(Match match);

    String getScore(GameSet gameSet);

    String getScore(Game game);
}
