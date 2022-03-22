/* (C) 2022 */
package io.aroundij.tennisleague.service;

import io.aroundij.tennisleague.domain.Match;
import io.aroundij.tennisleague.domain.Player;
import io.aroundij.tennisleague.domain.Score;

public interface PlayGame {

    Match startMatch();

    Score incrementScore(Score scoreToIncrement, Score scoreOfAdversary);

    void playerScored(Player player, Match match);
}
