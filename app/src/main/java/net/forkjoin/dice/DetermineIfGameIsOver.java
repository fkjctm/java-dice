package net.forkjoin.dice;

import net.forkjoin.dice.data.GameState;

public interface DetermineIfGameIsOver {
  boolean determine(GameState gameState);
}
