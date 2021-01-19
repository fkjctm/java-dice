package net.forkjoin.dice;

import net.forkjoin.dice.data.GameState;

public interface DetermineGameResult {
  GameState determine(GameState gameState);
}
