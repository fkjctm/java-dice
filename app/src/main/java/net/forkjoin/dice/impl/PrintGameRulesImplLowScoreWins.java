package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintGameRules;
import net.forkjoin.dice.TerminalService;

public class PrintGameRulesImplLowScoreWins implements PrintGameRules {
  public static final String rules = "\n\nGame Rules: In this game, a human and computer player will \n" +
                                     "alternate rolls of the dice. In the case of a \n" +
                                     "non-tie, the player with the lower roll wins.";
  private TerminalService terminalService;

  public PrintGameRulesImplLowScoreWins(TerminalService terminalService) {
    this.terminalService = terminalService;
  }

  @Override
  public void print() {
    this.terminalService.printLine(rules);
  }
}
