package net.forkjoin.dice.impl;

import net.forkjoin.dice.GameInitializer;
import net.forkjoin.dice.data.GameState;

public class GameInitializerImpl implements GameInitializer {
  @Override
  public GameState initialize() {
    return new GameState();
  }
}
