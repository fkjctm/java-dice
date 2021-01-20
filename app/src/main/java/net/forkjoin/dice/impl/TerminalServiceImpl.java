package net.forkjoin.dice.impl;

import net.forkjoin.dice.TerminalService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

public class TerminalServiceImpl implements TerminalService {
  private TextIO textIO;
  private TextTerminal textTerminal;


  public TerminalServiceImpl(TextIO textIO) {
    this.textIO = textIO;
    this.textTerminal = textIO.getTextTerminal();
  }

  @Override
  public void printLine(String output) {
    textTerminal.println(output);
  }

  @Override
  public void waitForReturn(String message) {
    textIO.newStringInputReader()
      .withMinLength(0)
      .read(message);
  }

  @Override
  public char readCharOrNull(String message) {
    var singleCharString = textIO
      .newStringInputReader()
      .withMinLength(0)
      .withMaxLength(1)
      .read(message);
    return singleCharString.length() == 0 ? '_' : (char)singleCharString.getBytes()[0];
  }
}
