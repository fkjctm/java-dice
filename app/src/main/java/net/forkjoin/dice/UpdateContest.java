package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameState;

public interface UpdateContest {
  ContestState update(GameState gameState, ContestState contestState);
}
