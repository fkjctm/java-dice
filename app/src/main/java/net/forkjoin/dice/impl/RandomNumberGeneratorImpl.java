package net.forkjoin.dice.impl;

import net.forkjoin.dice.RandomNumberGenerator;

public class RandomNumberGeneratorImpl implements RandomNumberGenerator {

  @Override
  public int generate(int min, int max) {
    var diff = Math.floor((max - min + 1) * Math.random());
    return min + (int)diff;
  }
}
