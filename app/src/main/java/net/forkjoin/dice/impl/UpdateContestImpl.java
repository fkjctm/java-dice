package net.forkjoin.dice.impl;

import net.forkjoin.dice.UpdateContest;
import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameState;

public class UpdateContestImpl implements UpdateContest {
  @Override
  public ContestState update(GameState gameState, ContestState contestState) {
    var newContestState = (ContestState)contestState.clone();
    newContestState.addGame(gameState);
    return newContestState;
  }
}
