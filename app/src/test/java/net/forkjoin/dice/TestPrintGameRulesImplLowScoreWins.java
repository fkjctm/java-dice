package net.forkjoin.dice;

import net.forkjoin.dice.impl.PrintGameRulesImplLowScoreWins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("PrintGameRulesImplLowScoreWins")
public class TestPrintGameRulesImplLowScoreWins {
  private TerminalService terminalService;

  @Test @DisplayName("Should print the game rules")
  void PrintRules() {
    terminalService = mock(TerminalService.class);
    var service = new PrintGameRulesImplLowScoreWins(terminalService);
    service.print();

    verify(terminalService).printLine(PrintGameRulesImplLowScoreWins.rules);
  }
}
