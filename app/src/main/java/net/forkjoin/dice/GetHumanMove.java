package net.forkjoin.dice;

import net.forkjoin.dice.data.GameState;

public interface GetHumanMove {
  GameState get(GameState gameState);
}
