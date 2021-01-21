package net.forkjoin.dice.impl;

import net.forkjoin.dice.DetermineIfGameContinues;
import net.forkjoin.dice.TerminalService;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;

public class DetermineIfGameContinuesImplNoTiesAllowed implements DetermineIfGameContinues {
  public static final String tieMessage = "Game cannot end in a tie. Please roll again";

  private TerminalService terminalService;

  public DetermineIfGameContinuesImplNoTiesAllowed(TerminalService terminalService){
    this.terminalService = terminalService;
  }

  @Override
  public boolean determine(GameState gameState) {
    if (gameState.getGameResult() != GameResult.Tie) return false;
    terminalService.printLine(tieMessage);
    return true;
  }
}
