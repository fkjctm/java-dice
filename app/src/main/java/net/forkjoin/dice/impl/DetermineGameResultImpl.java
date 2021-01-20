package net.forkjoin.dice.impl;

import net.forkjoin.dice.DetermineGameResult;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.DetermineGameResultException;

public class DetermineGameResultImpl implements DetermineGameResult {
  public static final String invalidHumanScore = "Invalid human player score";
  public static final String invalidComputerScore = "Invalid computer player score";
  public static final String result = "You have %s this game";

  public DetermineGameResultImpl() { }

  @Override
  public GameState determine(GameState gameState) {
    if (gameState.getHumanScore() < 1 || gameState.getHumanScore() > 6) {
      throw new DetermineGameResultException(invalidHumanScore);
    }
    if (gameState.getComputerScore() < 1 || gameState.getComputerScore() > 6) {
      throw new DetermineGameResultException(invalidComputerScore);
    }

    var newGameState = (GameState)gameState.clone();
    var diff = gameState.getHumanScore() - gameState.getComputerScore();
    var result = diff == 0 ? GameResult.Tie : (diff > 0 ? GameResult.HumanWin : GameResult.ComputerWin);
    newGameState.setGameResult(result);

    return newGameState;
  }
}
