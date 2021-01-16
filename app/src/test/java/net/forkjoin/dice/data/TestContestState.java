package net.forkjoin.dice.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ContestState")
public class TestContestState {

  @Test @DisplayName("A newly created state should have no game history")
  void NewState() {
    var newState = new ContestState();
    assertEquals(0, newState.getGameCount());
  }

  @Test @DisplayName("Adding a game to history will increase the game count by 1")
  void AddGame() {
    var newState = new ContestState();
    assertEquals(0, newState.getGameCount());

    newState.addGame(new GameState());
    assertEquals(1, newState.getGameCount());
  }

  @Test @DisplayName("Adding multiple games are guaranteed to stay in order of addition")
  void AddMultipleGames() {
    var newState = new ContestState();
    assertEquals(0, newState.getGameCount());

    var game1 = new GameState();
    var game2 = new GameState();
    var game3 = new GameState();
    newState.addGame(game1);
    newState.addGame(game2);
    newState.addGame(game3);

    assertEquals(game1, newState.getGameHistory().toArray()[0]);
    assertEquals(game2, newState.getGameHistory().toArray()[1]);
    assertEquals(game3, newState.getGameHistory().toArray()[2]);
  }
}
