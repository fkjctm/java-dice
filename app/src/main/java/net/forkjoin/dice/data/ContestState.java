package net.forkjoin.dice.data;

import java.util.AbstractSequentialList;
import java.util.LinkedList;
import java.util.stream.Stream;

public class ContestState {
  private final AbstractSequentialList<GameState> gameHistory;

  public ContestState() {
    gameHistory = new LinkedList<>();
  }

  public void addGame(GameState gameState) {
    this.gameHistory.add(gameState);
  }

  public int getGameCount() {
    return gameHistory.size();
  }

  public Stream<GameState> getGameHistory() {
    return gameHistory.stream();
  }
}
