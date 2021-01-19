package net.forkjoin.dice;

import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.impl.GetHumanMoveImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("GetHumanMoveImpl")
public class TestGetHumanMoveImpl {
  private TerminalService terminalService;
  private RandomNumberGenerator randomNumberGenerator;

  @BeforeEach
  void Setup() {
    terminalService = mock(TerminalService.class);
    randomNumberGenerator = mock(RandomNumberGenerator.class);
  }

  @Nested @DisplayName("User will be assigned a random dice value between 1 and 6")
  class Success {
    int diceRoll = 3;
    GameState result;

    @BeforeEach
    void Setup() throws IOException {
      when(randomNumberGenerator.generate(GetHumanMoveImpl.diceRollMin, GetHumanMoveImpl.diceRollMax))
        .thenReturn(diceRoll);
      var service = new GetHumanMoveImpl(terminalService, randomNumberGenerator);
      result = service.get(new GameState());
    }

    @Test @DisplayName("Expect print stream to have been called with message")
    public void PrintStreamTest() {
      verify(terminalService).waitForReturn(GetHumanMoveImpl.message);
    }

    @Test @DisplayName("Expect returned game state to contain the dice roll")
    public void DiceRoll() {
      assertEquals(diceRoll, result.getHumanScore());
    }

    @Test @DisplayName("Expect the player's roll to be displayed")
    public void DisplayRoll() {
      verify(terminalService).printLine(String.format(GetHumanMoveImpl.result, diceRoll));
    }
  }

}
