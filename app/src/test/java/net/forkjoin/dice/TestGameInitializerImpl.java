package net.forkjoin.dice;

import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.impl.GameInitializerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("GameInitializerImpl")
public class TestGameInitializerImpl {

  @Test @DisplayName("Initialized game state should be empty")
  void InitializeTest() {
    var service = new GameInitializerImpl();
    var gameState = service.initialize();

    assertEquals(GameResult.Empty, gameState.getGameResult());
    assertEquals(0, gameState.getHumanScore());
    assertEquals(0, gameState.getComputerScore());
  }
}
