package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintGameResults;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.PrintGameResultsException;

import java.io.PrintStream;

public class PrintGameResultsImpl implements PrintGameResults {
  public static final String invalidGameResult = "Invalid game result";
  public static final String printHeader = "Game Results:";
  public static final String results = "Your Score: %s, Computer Score: %s, Result: %s";

  private PrintStream printStream;

  public PrintGameResultsImpl(PrintStream printStream) {
    this.printStream = printStream;
  }

  @Override
  public void print(GameState gameState) {
    if (gameState.getGameResult() == GameResult.Empty) {
      throw new PrintGameResultsException(invalidGameResult);
    }

    printStream.println(printHeader);
    var winStr = gameState.getGameResult() == GameResult.Tie ? "You Tied" :
      (gameState.getGameResult() == GameResult.HumanWin ? "You Won!" : "You Lost");
    var resultStr = String.format(results, gameState.getHumanScore(), gameState.getComputerScore(), winStr);
    printStream.println(resultStr);

  }
}
