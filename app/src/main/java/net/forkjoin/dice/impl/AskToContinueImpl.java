package net.forkjoin.dice.impl;

import net.forkjoin.dice.AskToContinue;
import net.forkjoin.dice.TerminalService;
import net.forkjoin.dice.data.ContestState;

public class AskToContinueImpl implements AskToContinue {
  public static final String message = "Press 's' to stop or enter to continue: ";
  private TerminalService terminalService;

  public AskToContinueImpl(TerminalService terminalService) {
    this.terminalService = terminalService;
  }

  @Override
  public boolean ask(ContestState contestState) {
    var outputMessage = String.format(message, contestState.getGameCount());
    var continueChar = terminalService.readCharOrNull(outputMessage);
    return (continueChar == 'S' || continueChar == 's') ? false : true;
  }
}
