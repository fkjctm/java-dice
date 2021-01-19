package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.exception.AskToContinueException;
import net.forkjoin.dice.impl.AskToContinueImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("AskToContinueImpl")
public class TestAskToContinueImpl {
  private PrintStream printStream;
  private InputStream inputStream;
  private AskToContinue service;
  private ContestState contestState;

  @BeforeEach
  void Setup() {
    printStream = mock(PrintStream.class);
    inputStream = mock(InputStream.class);
    service = new AskToContinueImpl(printStream, inputStream);
    contestState = mock(ContestState.class);
    when(contestState.getGameCount()).thenReturn(3);
  }

  @Nested @DisplayName("On input error")
  class InputError {
    Exception thrownError;
    Exception caughtError;

    @BeforeEach
    void Setup() throws IOException {
      thrownError = new IOException("cannot read user input");
      when(inputStream.readNBytes(1)).thenThrow(thrownError);
      try {
        service.ask(contestState);
      }
      catch(Exception e) {
        caughtError = e;
      }
    }

    @Test @DisplayName("Expect caught error to be of type AskToContinueException")
    void ErrorType() {
      assertEquals(AskToContinueException.class, caughtError.getClass());
    }

    @Test @DisplayName("Expect caught error to have thrown error as cause")
    void ErrorCause() {
      assertEquals(thrownError, caughtError.getCause());
    }
  }

  @Nested @DisplayName("With user input of 's' or 'S'")
  class NegativeResponse {

    void Setup(String userInput) throws IOException {
      when(inputStream.readNBytes(1)).thenReturn(userInput.getBytes());
    }

    @ParameterizedTest @ValueSource(strings = {"s", "S"})
    @DisplayName("Expect false return value")
    void ExpectFalse(String userInput) throws IOException {
      Setup(userInput);

      var result = service.ask(contestState);

      var expectedMessage = String.format(AskToContinueImpl.message, contestState.getGameCount());
      verify(printStream).print(expectedMessage);
      assertFalse(result);
    }
  }

  @Nested @DisplayName("With user input not 's' or 'S'")
  class PositiveResponse {
    void Setup(String userInput) throws IOException {
      when(inputStream.readNBytes(1)).thenReturn(userInput.getBytes());
    }

    @ParameterizedTest @ValueSource(strings = {"a", "z"})
    @DisplayName("Expect true return value")
    void ExpectTrue(String userInput) throws IOException {
      Setup(userInput);

      var result = service.ask(contestState);

      var expectedMessage = String.format(AskToContinueImpl.message, contestState.getGameCount());
      verify(printStream).print(expectedMessage);
      assertTrue(result);
    }
  }
}
