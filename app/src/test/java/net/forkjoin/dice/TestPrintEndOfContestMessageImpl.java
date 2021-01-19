package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.impl.PrintEndOfContestMessageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("PrintEndOfContestMessageImpl")
public class TestPrintEndOfContestMessageImpl {
  private PrintStream printStream;
  private PrintEndOfContestMessage service;

  @BeforeEach
  void Setup() {
    printStream = mock(PrintStream.class);
    service = new PrintEndOfContestMessageImpl(printStream);
  }

  @Test @DisplayName("With only wins and losses, expect no ties in message to user")
  void NoTies() {
    var game1 = new GameState(){{ setGameResult(GameResult.HumanWin); }};
    var game2 = new GameState(){{ setGameResult(GameResult.ComputerWin); }};
    var contestState = new ContestState(){{
      addGame(game1);
      addGame(game2);
    }};

    service.print(contestState);

    var expectedOutput = String.format(PrintEndOfContestMessageImpl.winsAndLossesFormat, 1, 1);
    verify(printStream).println(expectedOutput);
  }

  @Test @DisplayName("With only wins and losses and ties, expect ties in message to user")
  void WithTies() {
    var game1 = new GameState(){{ setGameResult(GameResult.HumanWin); }};
    var game2 = new GameState(){{ setGameResult(GameResult.Tie); }};
    var contestState = new ContestState(){{
      addGame(game1);
      addGame(game2);
    }};

    service.print(contestState);

    var expectedWins = String.format(PrintEndOfContestMessageImpl.winsAndLossesFormat, 1, 0);
    var expectedTies = String.format(PrintEndOfContestMessageImpl.tiesFormat, 1);
    verify(printStream).println(expectedWins + " " + expectedTies);
  }

}
