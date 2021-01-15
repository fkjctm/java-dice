package net.forkjoin.dice;

import net.forkjoin.dice.data.GameState;

public interface DetermineIfGameContinues {
  boolean determine(GameState gameState);
}
