package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintEndOfContestMessage;
import net.forkjoin.dice.TerminalService;
import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameResult;

public class PrintEndOfContestMessageImpl implements PrintEndOfContestMessage {
  public static final String results = "\n\nThe contest has ended. Wins: %s  Losses: %s  Ties: %s";
  private TerminalService terminalService;

  public PrintEndOfContestMessageImpl(TerminalService terminalService) {
    this.terminalService = terminalService;
  }

  @Override
  public void print(ContestState contestState) {
    var wins = contestState.getGameHistory().filter(g -> g.getGameResult().equals(GameResult.HumanWin)).count();
    var losses = contestState.getGameHistory().filter(g -> g.getGameResult().equals(GameResult.ComputerWin)).count();
    var ties = contestState.getGameHistory().filter(g -> g.getGameResult().equals(GameResult.Tie)).count();

    var message = String.format(results, wins, losses, ties);
    terminalService.printLine(message);
  }
}
