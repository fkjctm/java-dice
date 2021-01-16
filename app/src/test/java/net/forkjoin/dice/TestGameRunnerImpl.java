package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.impl.GameRunnerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("TestGameRunnerImpl")
public class TestGameRunnerImpl {

  private GameInitializer gameInitializer;
  private GetHumanMove getHumanMove;
  private GetComputerMove getComputerMove;
  private DetermineIfGameContinues determineIfGameContinues;
  private UpdateContest updateContest;
  private PrintGameResults printGameResults;
  private GameRunnerImpl service;

  @BeforeEach
  void Init() {
    gameInitializer = mock(GameInitializer.class);
    getHumanMove = mock(GetHumanMove.class);
    getComputerMove = mock(GetComputerMove.class);
    determineIfGameContinues = mock(DetermineIfGameContinues.class);
    updateContest = mock(UpdateContest.class);
    printGameResults = mock(PrintGameResults.class);
    service = new GameRunnerImpl(gameInitializer, getHumanMove, getComputerMove,
      determineIfGameContinues, updateContest, printGameResults);
  }

  class GameSetup {
    GameState stateInit;
    GameState stateAfterHumanMove;
    GameState stateAfterComputerMove;
    ContestState updatedContestState;
    ContestState result;

    void Setup() {
      stateInit = new GameState();
      when(gameInitializer.initialize()).thenReturn(stateInit);

      stateAfterHumanMove = new GameState();
      when(getHumanMove.get(stateInit)).thenReturn(stateAfterHumanMove);

      stateAfterComputerMove = new GameState();
      when(getComputerMove.get(stateAfterHumanMove)).thenReturn(stateAfterComputerMove);
    }
  }

  @Nested @DisplayName("Single round game")
  class SingleRound extends GameSetup {

    @BeforeEach
    void Setup() {
      super.Setup();
      when(determineIfGameContinues.determine(stateAfterComputerMove)).thenReturn(false);

      updatedContestState = new ContestState();
      when(updateContest.update(eq(stateAfterComputerMove), any(ContestState.class))).thenReturn(updatedContestState);

      result = service.run(new ContestState());
    }

    @Test @DisplayName("The contest state should be updated")
    void TestContestState() {
      assertEquals(updatedContestState, result);
    }

    @Test @DisplayName("The result of the game should have been called with the game state after the computer move")
    void PrintGameState() {
      verify(printGameResults).print(stateAfterComputerMove);
    }

  }

  @Nested @DisplayName("Multiple round game")
  class MultiRound extends GameSetup {
    GameState stateAfterSecondHumanMove;
    GameState stateAfterSecondComputerMove;
    ContestState updatedContestState;

    @BeforeEach
    void Setup() {
      super.Setup();
      when(determineIfGameContinues.determine(stateAfterComputerMove)).thenReturn(true);

      stateAfterSecondHumanMove = new GameState();
      when(getHumanMove.get(stateAfterComputerMove)).thenReturn(stateAfterSecondHumanMove);

      stateAfterSecondComputerMove = new GameState();
      when(getComputerMove.get(stateAfterSecondHumanMove)).thenReturn(stateAfterSecondComputerMove);

      when(determineIfGameContinues.determine(stateAfterSecondComputerMove)).thenReturn(false);

      updatedContestState = new ContestState();
      when(updateContest.update(eq(stateAfterSecondComputerMove), any(ContestState.class))).thenReturn(updatedContestState);

      result = service.run(new ContestState());
    }

    @Test @DisplayName("Expect the contest state to updated ")
    void UpdatedContestState() {
      assertEquals(updatedContestState, result);
    }

    @Test @DisplayName("Expect the game results to be printed with state returned after the second computer move")
    void PrintResults() {
      verify(printGameResults).print(stateAfterSecondComputerMove);
    }

  }

}
