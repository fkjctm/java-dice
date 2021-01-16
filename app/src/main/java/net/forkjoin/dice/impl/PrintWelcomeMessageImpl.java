package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintWelcomeMessage;

import java.io.PrintStream;

public class PrintWelcomeMessageImpl implements PrintWelcomeMessage {
  public static final String message =
    (new StringBuilder())
      .append("********************************************\n")
      .append("Console Dice Game \n")
      .append("\n")
      .append("Rules: \n")
      .append("You will compete against a computer by rolling  \n")
      .append("a single 6-sided die. The player with the higher \n")
      .append("die value wins. If both players roll the same \n")
      .append("die, it is tie.")
      .append("********************************************\n")
      .toString();

  private PrintStream printStream;

  public PrintWelcomeMessageImpl(PrintStream printStream){
    this.printStream = printStream;
  }

  @Override
  public void print() {
    this.printStream.println(message);
  }
}
