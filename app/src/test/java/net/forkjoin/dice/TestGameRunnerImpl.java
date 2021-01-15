package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameState;
import net.forkjoin.dice.impl.GameRunnerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("TestGameRunnerImpl")
public class TestGameRunnerImpl {

  private GameInitializer gameInitializer;
  private GetHumanMove getHumanMove;
  private GetComputerMove getComputerMove;
  private DetermineIfGameIsOver determineIfGameIsOver;
  private UpdateContest updateContest;
  private PrintGameResults printGameResults;
  private GameRunnerImpl service;

  @BeforeEach
  private void init() {
    gameInitializer = mock(GameInitializer.class);
    getHumanMove = mock(GetHumanMove.class);
    getComputerMove = mock(GetComputerMove.class);
    determineIfGameIsOver = mock(DetermineIfGameIsOver.class);
    updateContest = mock(UpdateContest.class);
    printGameResults = mock(PrintGameResults.class);
    service = new GameRunnerImpl(gameInitializer, getHumanMove, getComputerMove,
      determineIfGameIsOver, updateContest, printGameResults);
  }

  @Test
  @DisplayName("When a game only takes one round")
  public void SingleRound() {
    var stateInit = new GameState();
    when(gameInitializer.initialize()).thenReturn(stateInit);

    var stateAfterHumanMove = new GameState();
    when(getHumanMove.get(stateInit)).thenReturn(stateAfterHumanMove);

    var stateAfterComputerMove = new GameState();
    when(getComputerMove.get(stateAfterHumanMove)).thenReturn(stateAfterComputerMove);

    when(determineIfGameIsOver.determine(stateAfterComputerMove)).thenReturn(false);

    var updatedContestState = new ContestState();
    when(updateContest.update(eq(stateAfterComputerMove), any(ContestState.class))).thenReturn(updatedContestState);

    var result = service.run(new ContestState());

    assertEquals(updatedContestState, result);
    verify(printGameResults).print(stateAfterComputerMove);
  }

  @Test
  @DisplayName("When a game takes multiple rounds")
  public void MultiRound() {
    var stateInit = new GameState();
    when(gameInitializer.initialize()).thenReturn(stateInit);

    var stateAfterHumanMove = new GameState();
    when(getHumanMove.get(stateInit)).thenReturn(stateAfterHumanMove);

    var stateAfterComputerMove = new GameState();
    when(getComputerMove.get(stateAfterHumanMove)).thenReturn(stateAfterComputerMove);

    when(determineIfGameIsOver.determine(stateAfterComputerMove)).thenReturn(true);

    var stateAfterSecondHumanMove = new GameState();
    when(getHumanMove.get(stateAfterComputerMove)).thenReturn(stateAfterSecondHumanMove);

    var stateAfterSecondComputerMove = new GameState();
    when(getComputerMove.get(stateAfterSecondHumanMove)).thenReturn(stateAfterSecondComputerMove);

    when(determineIfGameIsOver.determine(stateAfterSecondComputerMove)).thenReturn(false);

    var updatedContestState = new ContestState();
    when(updateContest.update(eq(stateAfterSecondComputerMove), any(ContestState.class))).thenReturn(updatedContestState);

    var result = service.run(new ContestState());

    assertEquals(updatedContestState, result);
    verify(printGameResults).print(stateAfterSecondComputerMove);
  }

}
