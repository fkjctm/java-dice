package net.forkjoin.dice;

public interface TerminalService {
  void printLine(String output);

  void waitForReturn(String message);

  char readCharOrNull(String message);
}
