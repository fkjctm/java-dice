package net.forkjoin.dice.exception;

public abstract class DiceGameException extends RuntimeException {
  public DiceGameException() {
    super();
  }

  public DiceGameException(Exception e) {
    super(e);
  }
}
