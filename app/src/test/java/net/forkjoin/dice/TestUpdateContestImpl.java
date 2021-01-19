package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameResult;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.impl.UpdateContestImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("UpdateContestImpl")
public class TestUpdateContestImpl {

  @Test @DisplayName("Expect game state to be added to a new contest state")
  void ContestStateUpdated() {
    var oldGameState = new GameState(){{
      setHumanScore(5);
      setComputerScore(3);
      setGameResult(GameResult.HumanWin);
    }};
    var oldContestState = new ContestState(){{
      addGame(oldGameState);
    }};
    var newGameState = new GameState(){{
      setHumanScore(2);
      setComputerScore(6);
      setGameResult(GameResult.ComputerWin);
    }};

    var service = new UpdateContestImpl();
    var newContestState = service.update(newGameState, oldContestState);

    assertNotEquals(oldContestState, newContestState);
    assertEquals(2, newContestState.getGameCount());
    assertEquals(oldGameState, newContestState.getGameHistory().toArray()[0]);
    assertEquals(newGameState, newContestState.getGameHistory().toArray()[1]);
  }
}
