package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintTieRules;
import net.forkjoin.dice.TerminalService;

public class PrintTieRulesImplTiesAllowed implements PrintTieRules {
  public static final String rules = "\nIf both players roll the same number, the game ends in a tie.\n";
  private TerminalService terminalService;

  public PrintTieRulesImplTiesAllowed(TerminalService terminalService) {
    this.terminalService = terminalService;
  }

  @Override
  public void print() {
    terminalService.printLine(rules);
  }
}
