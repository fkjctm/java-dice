package net.forkjoin.dice.impl;

import net.forkjoin.dice.*;
import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameState;

public class GameRunnerImpl implements GameRunner {

  private GameInitializer gameInitializer;
  private GetHumanMove getHumanMove;
  private GetComputerMove getComputerMove;
  private DetermineIfGameContinues determineIfGameContinues;
  private UpdateContest updateContest;
  private PrintGameResults printGameResults;

  public GameRunnerImpl(GameInitializer gameInitializer,
                        GetHumanMove getHumanMove,
                        GetComputerMove getComputerMove,
                        DetermineIfGameContinues determineIfGameContinues,
                        UpdateContest updateContest,
                        PrintGameResults printGameResults) {
    this.gameInitializer = gameInitializer;
    this.getHumanMove = getHumanMove;
    this.getComputerMove = getComputerMove;
    this.determineIfGameContinues = determineIfGameContinues;
    this.updateContest = updateContest;
    this.printGameResults = printGameResults;
  }

  @Override
  public ContestState run(ContestState contestState) {
    GameState gameState = gameInitializer.initialize();

    do {
      gameState = getHumanMove.get(gameState);
      gameState = getComputerMove.get(gameState);
    } while(determineIfGameContinues.determine(gameState));

    printGameResults.print(gameState);
    return updateContest.update(gameState, contestState);
  }
}
