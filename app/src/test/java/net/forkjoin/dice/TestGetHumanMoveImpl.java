package net.forkjoin.dice;

import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.exception.GetHumanMoveException;
import net.forkjoin.dice.impl.GetHumanMoveImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("GetHumanMoveImpl")
public class TestGetHumanMoveImpl {
  private PrintStream printStream;
  private InputStream inputStream;
  private RandomNumberGenerator randomNumberGenerator;

  @BeforeEach
  void Setup() {
    printStream = mock(PrintStream.class);
    inputStream = mock(InputStream.class);
    randomNumberGenerator = mock(RandomNumberGenerator.class);
  }

  @Nested @DisplayName("With an error thrown, expect repackaged error thrown")
  class InputError {
    GetHumanMoveImpl service;
    Exception thrownException;
    Exception caughtException;

    @BeforeEach
    void Setup() throws IOException {
      thrownException = new IOException("cannot read character!");
      when(inputStream.readNBytes(1)).thenThrow(thrownException);
      service = new GetHumanMoveImpl(printStream, inputStream, randomNumberGenerator);

      try {
        service.get(new GameState());
      }
      catch (Exception e) {
        caughtException = e;
      }
    }

    @Test @DisplayName("Expect print stream to have been called with message")
    public void PrintStreamTest() {
      verify(printStream).println(GetHumanMoveImpl.message);
    }

    @Test @DisplayName("Expect the caught exception to be of type GetHumanMoveException")
    public void ExceptionType() {
      assertEquals(GetHumanMoveException.class, caughtException.getClass());
    }

    @Test @DisplayName("Expect the caught exception to contain the thrown exception")
    public void InnerException() {
      assertEquals(thrownException, caughtException.getCause());
    }
  }

  @Nested @DisplayName("User will be assigned a random dice value between 1 and 6")
  class Success {
    int diceRoll = 3;
    GameState result;

    @BeforeEach
    void Setup() throws IOException {
      when(inputStream.readNBytes(1)).thenReturn(new byte[]{1});
      when(randomNumberGenerator.generate(GetHumanMoveImpl.diceRollMin, GetHumanMoveImpl.diceRollMax))
        .thenReturn(diceRoll);
      var service = new GetHumanMoveImpl(printStream, inputStream, randomNumberGenerator);
      result = service.get(new GameState());
    }

    @Test @DisplayName("Expect print stream to have been called with message")
    public void PrintStreamTest() {
      verify(printStream).println(GetHumanMoveImpl.message);
    }

    @Test @DisplayName("Expect returned game state to contain the dice roll")
    public void DiceRoll() {
      assertEquals(diceRoll, result.getHumanScore());
    }

    @Test @DisplayName("Expect the player's roll to be displayed")
    public void DisplayRoll() {
      verify(printStream).println(String.format(GetHumanMoveImpl.result, diceRoll));
    }
  }

}
