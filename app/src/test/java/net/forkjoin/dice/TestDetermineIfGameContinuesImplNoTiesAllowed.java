package net.forkjoin.dice;

import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.impl.DetermineIfGameContinuesImplNoTiesAllowed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("DetermineIfGameContinuesImplNoTiesAllowed")
public class TestDetermineIfGameContinuesImplNoTiesAllowed {
  private TerminalService terminalService;
  private DetermineIfGameContinues service;

  @BeforeEach
  void Setup() {
    terminalService = mock(TerminalService.class);
    service = new DetermineIfGameContinuesImplNoTiesAllowed(terminalService);
  }

  @Test @DisplayName("If the game state is not a tie, expect false result")
  void NoTie() {
    var gameState = new GameState(){{
      setGameResult(GameResult.HumanWin);
    }};

    var result = service.determine(gameState);
    assertFalse(result);
  }

  @Test @DisplayName("If the game state is a tie, expect true result with message in the terminal")
  void WithTie() {
    var gameState = new GameState(){{
      setGameResult(GameResult.Tie);
    }};

    var result = service.determine(gameState);
    assertTrue(result);
    verify(terminalService).printLine(DetermineIfGameContinuesImplNoTiesAllowed.tieMessage);
  }
}
