package net.forkjoin.dice;

import net.forkjoin.dice.impl.PrintWelcomeMessageImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("PrintWelcomeMessageImpl")
public class TestPrintWelcomeMessageImpl {
  TerminalService terminalServiceMock = mock(TerminalService.class);

  @Test @DisplayName("The user should see a message with the name of the game")
  void NameOfGameMessage() {
    var service = new PrintWelcomeMessageImpl(terminalServiceMock);
    service.print();

    verify(terminalServiceMock).printLine(PrintWelcomeMessageImpl.message);
  }
}
