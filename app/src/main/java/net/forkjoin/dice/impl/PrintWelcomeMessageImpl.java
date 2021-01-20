package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintWelcomeMessage;
import net.forkjoin.dice.TerminalService;

public class PrintWelcomeMessageImpl implements PrintWelcomeMessage {
  public static final String message =
    (new StringBuilder())
      .append("********************************************\n")
      .append("Console Dice Game \n")
      .append("\n")
      .append("Rules: \n")
      .append("You will compete against a computer by alternating  \n")
      .append("rolls of a 6-sided die. The player with the higher \n")
      .append("die value wins. If both players roll the same \n")
      .append("die, it is a tie.\n")
      .append("********************************************\n")
      .toString();

  private TerminalService terminalService;

  public PrintWelcomeMessageImpl(TerminalService terminalService){
    this.terminalService = terminalService;
  }

  @Override
  public void print() {
    terminalService.printLine(message);
  }
}
