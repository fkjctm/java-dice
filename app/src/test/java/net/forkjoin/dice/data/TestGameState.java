package net.forkjoin.dice.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GameState")
public class TestGameState {

  @Nested @DisplayName("Newly created game state should have default values")
  class Construction {
    GameState state = new GameState();

    @Test @DisplayName("The game result should be empty")
    void Result() {
      assertEquals(GameResult.Empty, state.getGameResult());
    }

    @Test @DisplayName("The human score should be set to zero")
    void HumanScore() {
      assertEquals(0, state.getHumanScore());
    }

    @Test @DisplayName("The computer score should be set to zero")
    void ComputerScore() {
      assertEquals(0, state.getComputerScore());
    }
  }

  @Nested @DisplayName("Edit the game result property")
  class GameResultProperty {
    @Test @DisplayName("Should change game result to specified value")
    void ChangeGameResult() {
      var state = new GameState();
      assertEquals(GameResult.Empty, state.getGameResult());

      state.setGameResult(GameResult.ComputerWin);
      assertEquals(GameResult.ComputerWin, state.getGameResult());
    }
  }

  @Nested @DisplayName("Edit the human score property")
  class HumanScoreProperty {

    @ParameterizedTest @DisplayName("If value is less than 1 or greater than 6 expect error")
    @ValueSource(ints = {0, 7})
    void InvalidValue(int dieValue) {
      var state = new GameState();
      assertEquals(0, state.getHumanScore());

      var exception = assertThrows(IllegalArgumentException.class, () -> state.setHumanScore(dieValue));
      assertEquals(GameState.InvalidDieValue, exception.getMessage());
    }

    @ParameterizedTest @DisplayName("With valid die values, expect the score to be set")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void ValidValue(int dieValue) {
      var state = new GameState();
      assertEquals(0, state.getHumanScore());

      state.setHumanScore(dieValue);
      assertEquals(dieValue, state.getHumanScore());
    }

  }

  @Nested @DisplayName("Edit the computer score property")
  class ComputerScoreProperty {

    @ParameterizedTest @DisplayName("If value is less than 1 or greater than 6 expect error")
    @ValueSource(ints = {0, 7})
    void InvalidValue(int dieValue) {
      var state = new GameState();
      assertEquals(0, state.getComputerScore());

      var exception = assertThrows(IllegalArgumentException.class, () -> state.setComputerScore(dieValue));
      assertEquals(GameState.InvalidDieValue, exception.getMessage());
    }

    @ParameterizedTest @DisplayName("With valid die values, expect the score to be set")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void ValidValue(int dieValue) {
      var state = new GameState();
      assertEquals(0, state.getComputerScore());

      state.setComputerScore(dieValue);
      assertEquals(dieValue, state.getComputerScore());
    }

  }

  @Nested @DisplayName("Game state should be cloneable")
  class CloneableTest {

    @Test @DisplayName("Cloning game state should produce a state with the same properties")
    void Clone() {
      var gameState = new GameState();
      gameState.setGameResult(GameResult.HumanWin);
      gameState.setHumanScore(5);
      gameState.setComputerScore(2);

      var clone = (GameState)gameState.clone();
      assertNotEquals(gameState, clone);
      assertEquals(gameState.getGameResult(), clone.getGameResult());
      assertEquals(gameState.getHumanScore(), clone.getHumanScore());
      assertEquals(gameState.getComputerScore(), clone.getComputerScore());
    }

  }
}
