package net.forkjoin.dice;

import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.DetermineGameResultException;
import net.forkjoin.dice.impl.DetermineGameResultImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("DetermineGameResultImpl")
public class TestDetermineGameResultImpl {
  private PrintStream printStream;
  private DetermineGameResult service;

  @BeforeEach
  void Setup() {
    printStream = mock(PrintStream.class);
    service = new DetermineGameResultImpl(printStream);
  }

  @Nested @DisplayName("When human score is out of range, expect error thrown")
  class InvalidHumanScore {

    @Test @DisplayName("With human score less than 1, expect error")
    void LessThanOne() {
      var gameState = mock(GameState.class);
      when(gameState.getHumanScore()).thenReturn(0);
      var thrown = assertThrows(DetermineGameResultException.class, () -> service.determine(gameState));
      assertEquals(DetermineGameResultImpl.invalidHumanScore, thrown.getMessage());
    }

    @Test @DisplayName("With human score greater than 6, expect error")
    void GreaterThanSix() {
      var gameState = mock(GameState.class);
      when(gameState.getHumanScore()).thenReturn(7);
      var thrown = assertThrows(DetermineGameResultException.class, () -> service.determine(gameState));
      assertEquals(DetermineGameResultImpl.invalidHumanScore, thrown.getMessage());
    }
  }

  @Nested @DisplayName("When computer score is out of range, expect error thrown")
  class InvalidComputerScore {

    @Test @DisplayName("With computer score less than 1, expect error")
    void LessThanOne() {
      var gameState = mock(GameState.class);
      when(gameState.getHumanScore()).thenReturn(3);
      when(gameState.getComputerScore()).thenReturn(0);
      var thrown = assertThrows(DetermineGameResultException.class, () -> service.determine(gameState));
      assertEquals(DetermineGameResultImpl.invalidComputerScore, thrown.getMessage());
    }

    @Test @DisplayName("With computer score greater than 6, expect error")
    void GreaterThanSix() {
      var gameState = mock(GameState.class);
      when(gameState.getHumanScore()).thenReturn(3);
      when(gameState.getComputerScore()).thenReturn(7);
      var thrown = assertThrows(DetermineGameResultException.class, () -> service.determine(gameState));
      assertEquals(DetermineGameResultImpl.invalidComputerScore, thrown.getMessage());
    }
  }

  @Nested @DisplayName("When human and computer scores are equal, expect tie result")
  class TieResult {
    GameState inputState;
    GameState resultState;

    @BeforeEach
    void Setup() {
      inputState = new GameState();
      inputState.setHumanScore(3);
      inputState.setComputerScore(3);
      resultState = service.determine(inputState);
    }

    @Test @DisplayName("Game result should be changed to a tie in the game state")
    void GameResultChanged() {
      assertEquals(GameResult.Tie, resultState.getGameResult());
    }

    @Test @DisplayName("Game result should be printed for the player to see")
    void GameResultPrinted() {
      verify(printStream).println(String.format(DetermineGameResultImpl.result, "TIED"));
    }
  }

  @Nested @DisplayName("When human score is greater than computer score, expect win result")
  class WinResult {
    GameState inputState;
    GameState resultState;

    @BeforeEach
    void Setup() {
      inputState = new GameState();
      inputState.setHumanScore(4);
      inputState.setComputerScore(3);
      resultState = service.determine(inputState);
    }

    @Test @DisplayName("Game result should be changed to a tie in the game state")
    void GameResultChanged() {
      assertEquals(GameResult.HumanWin, resultState.getGameResult());
    }

    @Test @DisplayName("Game result should be printed for the player to see")
    void GameResultPrinted() {
      verify(printStream).println(String.format(DetermineGameResultImpl.result, "WON"));
    }
  }

  @Nested @DisplayName("When human score is less than computer score, expect win result")
  class LossResult {
    GameState inputState;
    GameState resultState;

    @BeforeEach
    void Setup() {
      inputState = new GameState();
      inputState.setHumanScore(3);
      inputState.setComputerScore(4);
      resultState = service.determine(inputState);
    }

    @Test @DisplayName("Game result should be changed to a tie in the game state")
    void GameResultChanged() {
      assertEquals(GameResult.ComputerWin, resultState.getGameResult());
    }

    @Test @DisplayName("Game result should be printed for the player to see")
    void GameResultPrinted() {
      verify(printStream).println(String.format(DetermineGameResultImpl.result, "LOST"));
    }
  }
}
