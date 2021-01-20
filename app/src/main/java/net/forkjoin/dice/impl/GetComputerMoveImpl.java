package net.forkjoin.dice.impl;

import net.forkjoin.dice.GetComputerMove;
import net.forkjoin.dice.RandomNumberGenerator;
import net.forkjoin.dice.TerminalService;
import net.forkjoin.dice.data.GameState;

public class GetComputerMoveImpl implements GetComputerMove {
  public static final String result = "The computer rolled a %s";
  public static final int diceRollMin = 1;
  public static final int diceRollMax = 6;

  private RandomNumberGenerator randomNumberGenerator;
  private TerminalService terminalService;

  public GetComputerMoveImpl(TerminalService terminalService,
                             RandomNumberGenerator randomNumberGenerator) {
    this.terminalService = terminalService;
    this.randomNumberGenerator = randomNumberGenerator;
  }

  @Override
  public GameState get(GameState gameState) {
    var newGameState = (GameState) gameState.clone();
    var diceRoll = randomNumberGenerator.generate(diceRollMin,diceRollMax);
    newGameState.setComputerScore(diceRoll);
    terminalService.printLine(String.format(result, diceRoll));
    return newGameState;
  }

}
