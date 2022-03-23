package io.aroundij.tennisleague.service;

import io.aroundij.tennisleague.util.RelativeGameSetScore;

public interface GameSetService {
    void incrementGameSetScore(RelativeGameSetScore relativeGameSetScore);
}
