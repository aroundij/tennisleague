/* (C) 2022 */
package io.aroundij.tennisleague.service;

import io.aroundij.tennisleague.domain.GameSet;
import io.aroundij.tennisleague.domain.Match;
import io.aroundij.tennisleague.domain.Player;
import io.aroundij.tennisleague.util.RelativeGameScore;

public interface PlayGame {

    GameSet startGameSet();

    Match startMatch();

    void incrementScore(RelativeGameScore relativeGameScore);

    void playerScored(Player player, GameSet gameSet);

    void playerScored(Player player, Match mAtch);
}
