package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.impl.AskToContinueImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("AskToContinueImpl")
public class TestAskToContinueImpl {
  private TerminalService terminalService;
  private AskToContinue service;
  private ContestState contestState;

  @BeforeEach
  void Setup() {
    terminalService = mock(TerminalService.class);
    service = new AskToContinueImpl(terminalService);
    contestState = mock(ContestState.class);
    when(contestState.getGameCount()).thenReturn(3);
  }

  @Nested @DisplayName("With user input of 's' or 'S'")
  class NegativeResponse {

    void Setup(char userInput) {
      when(terminalService.readCharOrNull(anyString())).thenReturn(userInput);
    }

    @ParameterizedTest @ValueSource(chars = {'s', 'S'})
    @DisplayName("Expect false return value")
    void ExpectFalse(char userInput) {
      Setup(userInput);

      var result = service.ask(contestState);

      var expectedMessage = String.format(AskToContinueImpl.message, contestState.getGameCount());
      verify(terminalService).readCharOrNull(expectedMessage);
      assertFalse(result);
    }
  }

  @Nested @DisplayName("With user input not 's' or 'S'")
  class PositiveResponse {
    void Setup(char userInput) throws IOException {
      when(terminalService.readCharOrNull(anyString())).thenReturn(userInput);
    }

    @ParameterizedTest @ValueSource(chars = {'a', 'z'})
    @DisplayName("Expect true return value")
    void ExpectTrue(char userInput) throws IOException {
      Setup(userInput);

      var result = service.ask(contestState);

      var expectedMessage = String.format(AskToContinueImpl.message, contestState.getGameCount());
      verify(terminalService).readCharOrNull(expectedMessage);
      assertTrue(result);
    }
  }
}
