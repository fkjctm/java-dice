package net.forkjoin;

import net.forkjoin.dice.impl.DiceContestFactoryImpl;

public class App {

  public static void main(String[] args) {
    var contestFactory = new DiceContestFactoryImpl(args);
    contestFactory.diceContestRunner().run();
  }

}

