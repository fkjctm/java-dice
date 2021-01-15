package net.forkjoin.dice;

import net.forkjoin.dice.data.GameState;

public interface GameInitializer {
  GameState initialize();
}
