package net.forkjoin.dice.exception;

public class AskToContinueException extends DiceGameException {
  public AskToContinueException(Exception e) {
    super(e);
  }
}
