package net.forkjoin.dice.impl;

import net.forkjoin.dice.GetHumanMove;
import net.forkjoin.dice.RandomNumberGenerator;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.GetHumanMoveException;

import java.io.InputStream;
import java.io.PrintStream;

public class GetHumanMoveImpl implements GetHumanMove {
  public static final String message = "When you are ready, press any key to roll the dice";
  public static final int diceRollMin = 1;
  public static final int diceRollMax = 6;
  private PrintStream printStream;
  private InputStream inputStream;
  private RandomNumberGenerator randomNumberGenerator;

  public GetHumanMoveImpl(PrintStream printStream,
                          InputStream inputStream,
                          RandomNumberGenerator randomNumberGenerator) {
    this.printStream = printStream;
    this.inputStream = inputStream;
    this.randomNumberGenerator = randomNumberGenerator;
  }

  @Override
  public GameState get(GameState gameState) {
    try {
      printStream.println(message);
      inputStream.readNBytes(1);
      var newGameState = (GameState) gameState.clone();
      newGameState.setHumanScore(randomNumberGenerator.generate(diceRollMin,diceRollMax));
      return newGameState;
    }
    catch (Exception e) {
      throw new GetHumanMoveException(e);
    }
  }
}
