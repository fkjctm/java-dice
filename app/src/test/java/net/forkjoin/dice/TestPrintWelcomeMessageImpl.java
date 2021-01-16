package net.forkjoin.dice;

import net.forkjoin.dice.impl.PrintWelcomeMessageImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("PrintWelcomeMessageImpl")
public class TestPrintWelcomeMessageImpl {

  @Test
  @DisplayName("Should print the welcome message with rules to the print stream")
  void WriteMessage() {
    var printStreamMock = mock(PrintStream.class);
    var service = new PrintWelcomeMessageImpl(printStreamMock);

    service.print();

    verify(printStreamMock).println(PrintWelcomeMessageImpl.message);
  }
}
