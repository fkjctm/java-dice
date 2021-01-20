package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.impl.PrintEndOfContestMessageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("PrintEndOfContestMessageImpl")
public class TestPrintEndOfContestMessageImpl {
  private TerminalService terminalService;
  private PrintEndOfContestMessage service;

  @BeforeEach
  void Setup() {
    terminalService = mock(TerminalService.class);
    service = new PrintEndOfContestMessageImpl(terminalService);
  }

  @Test @DisplayName("Expect win, loss and tie info to be displayed")
  void DisplayStats() {
    var game1 = new GameState(){{ setGameResult(GameResult.HumanWin); }};
    var game2 = new GameState(){{ setGameResult(GameResult.Tie); }};
    var contestState = new ContestState(){{
      addGame(game1);
      addGame(game2);
    }};

    service.print(contestState);

    var expected = String.format(PrintEndOfContestMessageImpl.results, 1, 0, 1);
    verify(terminalService).printLine(expected);
  }

}
