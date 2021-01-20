package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintTieRules;
import net.forkjoin.dice.TerminalService;

public class PrintTieRulesImplTiesAllowed implements PrintTieRules {
  public static final String rules = "If both players roll the same number, the game ends in a tie.";
  private TerminalService terminalService;

  public PrintTieRulesImplTiesAllowed(TerminalService terminalService) {
    this.terminalService = terminalService;
  }

  @Override
  public void print() {
    terminalService.printLine(rules);
  }
}
