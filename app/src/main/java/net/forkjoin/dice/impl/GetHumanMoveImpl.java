package net.forkjoin.dice.impl;

import net.forkjoin.dice.GetHumanMove;
import net.forkjoin.dice.RandomNumberGenerator;
import net.forkjoin.dice.TerminalService;
import net.forkjoin.dice.data.GameState;

public class GetHumanMoveImpl implements GetHumanMove {
  public static final String message = "When you are ready, press enter to roll the dice";
  public static final String result = "Your dice roll was a %s";
  public static final int diceRollMin = 1;
  public static final int diceRollMax = 6;
  private RandomNumberGenerator randomNumberGenerator;
  private TerminalService terminalService;

  public GetHumanMoveImpl(TerminalService terminalService,
                          RandomNumberGenerator randomNumberGenerator) {
    this.terminalService = terminalService;
    this.randomNumberGenerator = randomNumberGenerator;
  }

  @Override
  public GameState get(GameState gameState) {
    terminalService.waitForReturn(message);
    var newGameState = (GameState) gameState.clone();
    var diceRoll = randomNumberGenerator.generate(diceRollMin,diceRollMax);
    newGameState.setHumanScore(diceRoll);
    terminalService.printLine(String.format(result, diceRoll));
    return newGameState;
  }
}
