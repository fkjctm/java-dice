package net.forkjoin.dice.impl;

import net.forkjoin.dice.DetermineIfGameContinues;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;

public class DetermineIfGameContinuesImpl implements DetermineIfGameContinues {
  @Override
  public boolean determine(GameState gameState) {
    return gameState.getGameResult() != GameResult.Empty ? false : true;
  }
}
