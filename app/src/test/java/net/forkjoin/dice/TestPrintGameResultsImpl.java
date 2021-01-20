package net.forkjoin.dice;

import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.PrintGameResultsException;
import net.forkjoin.dice.impl.PrintGameResultsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("PrintGameResultsImpl")
public class TestPrintGameResultsImpl {
  private TerminalService terminalService;
  private PrintGameResultsImpl service;

  @BeforeEach
  void Setup() {
    terminalService = mock(TerminalService.class);
    service = new PrintGameResultsImpl(terminalService);
  }

  @Nested @DisplayName("With an invalid game result")
  class InvalidGameResult {

    @Test @DisplayName("expect error thrown with message")
    void ErrorThrown() {
      var gameState = new GameState(){{
        setGameResult(GameResult.Empty);
      }};

      var thrown = assertThrows(PrintGameResultsException.class, () -> service.print(gameState));
      assertNotNull(thrown);
      assertEquals(PrintGameResultsImpl.invalidGameResult, thrown.getMessage());
    }

  }

  @Nested @DisplayName("With a valid game result")
  class ValidGameResult {

    @ParameterizedTest
    @DisplayName("expect results to be printed with score and outcome")
    @CsvSource({
      "5,3,'You Won!'",
      "3,5,'You Lost'",
      "3,3,'You Tied'",
    })
    void PrintResults(int humanScore, int computerScore, String expectedWinString) {
      var gameState = new GameState(){{
        setHumanScore(humanScore);
        setComputerScore(computerScore);
        var diff = humanScore - computerScore;
        var result = diff == 0 ? GameResult.Tie :
          (diff > 0 ? GameResult.HumanWin : GameResult.ComputerWin);
        setGameResult(result);
      }};
      service.print(gameState);

      var expectedResultString = String.format(PrintGameResultsImpl.results, expectedWinString);
      verify(terminalService).printLine(expectedResultString);
    }

  }
}
