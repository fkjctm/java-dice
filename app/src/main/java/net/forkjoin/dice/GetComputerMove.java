package net.forkjoin.dice;

import net.forkjoin.dice.data.GameState;

public interface GetComputerMove {
  GameState get(GameState gameState);
}
