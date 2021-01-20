package net.forkjoin.dice;

import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.impl.GetComputerMoveImpl;
import net.forkjoin.dice.impl.GetHumanMoveImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("GetComputerMoveImpl")
public class TestGetComputerMoveImpl {
  private RandomNumberGenerator randomNumberGenerator;
  private TerminalService terminalService;

  @BeforeEach
  void Setup() {
    terminalService = mock(TerminalService.class);
    randomNumberGenerator = mock(RandomNumberGenerator.class);
  }

  @Nested @DisplayName("Computer will be assigned a random dice value between 1 and 6")
  class Success {
    int diceRoll = 5;
    GameState result;

    @BeforeEach
    void Setup() {
      when(randomNumberGenerator.generate(GetHumanMoveImpl.diceRollMin, GetHumanMoveImpl.diceRollMax))
        .thenReturn(diceRoll);
      var service = new GetComputerMoveImpl(terminalService, randomNumberGenerator);
      result = service.get(new GameState());
    }

    @Test @DisplayName("Expect returned game state to contain the dice roll")
    public void DiceRoll() {
      assertEquals(diceRoll, result.getComputerScore());
    }

    @Test @DisplayName("Expect the computer's roll to be displayed")
    public void DisplayRoll() {
      verify(terminalService).printLine(String.format(GetComputerMoveImpl.result, diceRoll));
    }
  }


}
