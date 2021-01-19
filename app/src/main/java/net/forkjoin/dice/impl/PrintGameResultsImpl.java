package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintGameResults;
import net.forkjoin.dice.TerminalService;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.PrintGameResultsException;

public class PrintGameResultsImpl implements PrintGameResults {
  public static final String invalidGameResult = "Invalid game result";
  public static final String results = "*** %s ***\n\n\n";

  private TerminalService terminalService;

  public PrintGameResultsImpl(TerminalService terminalService) {
    this.terminalService = terminalService;
  }

  @Override
  public void print(GameState gameState) {
    if (gameState.getGameResult() == GameResult.Empty) {
      throw new PrintGameResultsException(invalidGameResult);
    }

    var winStr = gameState.getGameResult() == GameResult.Tie ? "You Tied" :
      (gameState.getGameResult() == GameResult.HumanWin ? "You Won!" : "You Lost");
    var resultStr = String.format(results, winStr);
    terminalService.printLine(resultStr);
  }
}
