package net.forkjoin.dice.impl;

import net.forkjoin.dice.ContestInitializer;
import net.forkjoin.dice.data.ContestState;

public class ContestInitializerImpl implements ContestInitializer {

  @Override
  public ContestState initialize() {
    return new ContestState();
  }
}
