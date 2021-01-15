package net.forkjoin.dice.impl;

import net.forkjoin.dice.*;
import net.forkjoin.dice.exception.NotImplementedYetException;

public class DiceContestFactoryImpl implements DiceContestFactory {
  public DiceContestFactoryImpl(String[] args) { }

  @Override
  public PrintWelcomeMessage printWelcomeMessage() {
    throw new NotImplementedYetException();
  }

  @Override
  public ContestInitializer contestInitializer() {
    throw new NotImplementedYetException();
  }

  @Override
  public GameRunner gameRunner() {
    throw new NotImplementedYetException();
  }

  @Override
  public AskToContinue askToContinue() {
    throw new NotImplementedYetException();
  }

  @Override
  public PrintEndOfContestMessage printEndOfContestMessage() {
    throw new NotImplementedYetException();
  }

  @Override
  public DiceContestRunner diceContestRunner() {
    throw new NotImplementedYetException();
  }
}
