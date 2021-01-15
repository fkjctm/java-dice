package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;

public interface GameRunner {
  ContestState run(ContestState contestState);
}
