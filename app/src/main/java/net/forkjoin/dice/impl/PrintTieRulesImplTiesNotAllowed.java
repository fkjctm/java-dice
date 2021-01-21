package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintTieRules;
import net.forkjoin.dice.TerminalService;

public class PrintTieRulesImplTiesNotAllowed implements PrintTieRules {
  public static final String rules = "\nIf both players roll the same number, \n" +
                                     "the game continues until there is a winner.\n";
  private TerminalService terminalService;

  public PrintTieRulesImplTiesNotAllowed(TerminalService terminalService) {
    this.terminalService = terminalService;
  }

  @Override
  public void print() {
    terminalService.printLine(rules);
  }
}
