package net.forkjoin.dice;

import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.impl.DetermineIfGameContinuesImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("DetermineIfGameContinuesImpl")
public class TestDetermineIfGameContinuesImpl {

  @Test @DisplayName("If the game result has not been set, expect true")
  void NoGameResult() {
    var service = new DetermineIfGameContinuesImpl();
    var gameState = new GameState(){{
      setGameResult(GameResult.Empty);
    }};
    var result = service.determine(gameState);
    assertEquals(true, result);
  }

  @Test @DisplayName("If the game result has been set, expect false")
  void ValidGameResult() {
    var service = new DetermineIfGameContinuesImpl();
    var gameState = new GameState(){{
      setGameResult(GameResult.ComputerWin);
    }};
    var result = service.determine(gameState);
    assertEquals(false, result);
  }
}
