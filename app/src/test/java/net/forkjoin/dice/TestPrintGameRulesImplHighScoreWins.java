package net.forkjoin.dice;

import net.forkjoin.dice.impl.PrintGameRulesImplHighScoreWins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("PrintGameRulesImplHighScoreWins")
public class TestPrintGameRulesImplHighScoreWins {
  private TerminalService terminalService;

  @Test
  @DisplayName("Should print the game rules")
  void PrintRules() {
    terminalService = mock(TerminalService.class);
    var service = new PrintGameRulesImplHighScoreWins(terminalService);
    service.print();

    verify(terminalService).printLine(PrintGameRulesImplHighScoreWins.rules);
  }
}
