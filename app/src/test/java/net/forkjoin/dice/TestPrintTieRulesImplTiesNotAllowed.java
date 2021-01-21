package net.forkjoin.dice;

import net.forkjoin.dice.impl.PrintTieRulesImplTiesNotAllowed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("PrintTieRulesImplTiesNotAllowed")
public class TestPrintTieRulesImplTiesNotAllowed {
  private TerminalService terminalService;

  @Test
  @DisplayName("User should be shown the rules when a tie occurs")
  void ShowRules() {
    terminalService = mock(TerminalService.class);
    var service = new PrintTieRulesImplTiesNotAllowed(terminalService);
    service.print();

    verify(terminalService).printLine(PrintTieRulesImplTiesNotAllowed.rules);
  }
}
