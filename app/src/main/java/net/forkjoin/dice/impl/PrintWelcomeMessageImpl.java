package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintWelcomeMessage;
import net.forkjoin.dice.TerminalService;

public class PrintWelcomeMessageImpl implements PrintWelcomeMessage {
  public static final String message = "*** Console Dice Contest ***";

  private TerminalService terminalService;

  public PrintWelcomeMessageImpl(TerminalService terminalService){
    this.terminalService = terminalService;
  }

  @Override
  public void print() {
    terminalService.printLine(message);
  }

}
