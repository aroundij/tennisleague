/* (C) 2022 */
package io.aroundij.tennisleague.service;

import io.aroundij.tennisleague.domain.Match;
import io.aroundij.tennisleague.domain.Player;
import io.aroundij.tennisleague.util.RelativeGameScore;

public interface PlayGame {

    Match startMatch();

    void incrementScore(RelativeGameScore relativeGameScore);

    void playerScored(Player player, Match match);
}
