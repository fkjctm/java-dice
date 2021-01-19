package net.forkjoin.dice.impl;

import net.forkjoin.dice.DetermineGameResult;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.DetermineGameResultException;

import java.io.PrintStream;

public class DetermineGameResultImpl implements DetermineGameResult {
  public static final String invalidHumanScore = "Invalid human player score";
  public static final String invalidComputerScore = "Invalid computer player score";
  public static final String result = "You have %s this game";
  private PrintStream printStream;

  public DetermineGameResultImpl(PrintStream printStream) {
    this.printStream = printStream;
  }

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
    String verb;
    if (diff == 0) {
      verb = "TIED";
      newGameState.setGameResult(GameResult.Tie);
    }
    else if (diff > 0) {
      verb = "WON";
      newGameState.setGameResult(GameResult.HumanWin);
    }
    else {
      verb = "LOST";
      newGameState.setGameResult(GameResult.ComputerWin);
    }

    printStream.println(String.format(result, verb));
    return newGameState;
  }
}
