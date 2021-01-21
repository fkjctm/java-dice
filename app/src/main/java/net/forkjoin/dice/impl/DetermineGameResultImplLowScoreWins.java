package net.forkjoin.dice.impl;

import net.forkjoin.dice.DetermineGameResult;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;

public class DetermineGameResultImplLowScoreWins extends DetermineGameResultImplBase implements DetermineGameResult {

  @Override
  public GameState determine(GameState gameState) {
    super.checkGameState(gameState);

    var newGameState = (GameState)gameState.clone();
    var diff = gameState.getHumanScore() - gameState.getComputerScore();
    var result = diff == 0 ? GameResult.Tie : (diff < 0 ? GameResult.HumanWin : GameResult.ComputerWin);
    newGameState.setGameResult(result);

    return newGameState;
  }
}
