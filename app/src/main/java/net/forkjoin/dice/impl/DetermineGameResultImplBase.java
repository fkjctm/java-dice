package net.forkjoin.dice.impl;

import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.DetermineGameResultException;

public abstract class DetermineGameResultImplBase {
  public static final String invalidHumanScore = "Invalid human player score";
  public static final String invalidComputerScore = "Invalid computer player score";
  public static final String result = "You have %s this game";

  protected void checkGameState(GameState gameState) {
    if (gameState.getHumanScore() < 1 || gameState.getHumanScore() > 6) {
      throw new DetermineGameResultException(invalidHumanScore);
    }
    if (gameState.getComputerScore() < 1 || gameState.getComputerScore() > 6) {
      throw new DetermineGameResultException(invalidComputerScore);
    }
  }

}
