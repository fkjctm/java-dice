package net.forkjoin.dice.impl;

import net.forkjoin.dice.AskToContinue;
import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.exception.AskToContinueException;

import java.io.InputStream;
import java.io.PrintStream;

public class AskToContinueImpl implements AskToContinue {
  public static final String message = "You have played %s games. Type 's' to stop: ";
  private PrintStream printStream;
  private InputStream inputStream;

  public AskToContinueImpl(PrintStream printStream, InputStream inputStream) {
    this.printStream = printStream;
    this.inputStream = inputStream;
  }

  @Override
  public boolean ask(ContestState contestState) {
    try {
      var outputMessage = String.format(message, contestState.getGameCount());
      printStream.print(outputMessage);
      var continueChar = (char)inputStream.readNBytes(1)[0];
      return (continueChar == 'S' || continueChar == 's') ? false : true;
    }
    catch(Exception e) {
      throw new AskToContinueException(e);
    }
  }
}
