package net.forkjoin.dice.impl;

import net.forkjoin.dice.GetComputerMove;
import net.forkjoin.dice.RandomNumberGenerator;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.GetComputerMoveException;
import net.forkjoin.dice.exception.GetHumanMoveException;

import java.io.InputStream;
import java.io.PrintStream;

public class GetComputerMoveImpl implements GetComputerMove {
  public static final String result = "The computer rolled a %s";
  public static final int diceRollMin = 1;
  public static final int diceRollMax = 6;

  private PrintStream printStream;
  private RandomNumberGenerator randomNumberGenerator;

  public GetComputerMoveImpl(PrintStream printStream,
                             RandomNumberGenerator randomNumberGenerator) {
    this.printStream = printStream;
    this.randomNumberGenerator = randomNumberGenerator;
  }

  @Override
  public GameState get(GameState gameState) {
    var newGameState = (GameState) gameState.clone();
    var diceRoll = randomNumberGenerator.generate(diceRollMin,diceRollMax);
    newGameState.setComputerScore(diceRoll);
    printStream.println(String.format(result, diceRoll));
    return newGameState;
  }

}
