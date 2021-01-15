package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.impl.DiceContestRunnerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@DisplayName("DiceContestRunnerImpl")
public class TestDiceContestRunner {
  private PrintWelcomeMessage printWelcomeMessage;
  private ContestInitializer contestInitializer;
  private GameRunner gameRunner;
  private AskToContinue askToContinue;
  private PrintEndOfContestMessage printEndOfContestMessage;

  @BeforeEach()
  private void init() {
    printWelcomeMessage = mock(PrintWelcomeMessage.class);
    contestInitializer = mock(ContestInitializer.class);
    gameRunner = mock(GameRunner.class);
    askToContinue = mock(AskToContinue.class);
    printEndOfContestMessage = mock(PrintEndOfContestMessage.class);
  }

  private DiceContestRunner buildService() {
    return new DiceContestRunnerImpl(printWelcomeMessage, contestInitializer,
      gameRunner, askToContinue, printEndOfContestMessage);
  }

  @Test
  @DisplayName("When the only a single game is played")
  public void SingleLoopTest() {
    var stateInit = new ContestState();
    when(contestInitializer.initialize()).thenReturn(stateInit);

    var stateAfterFirstGame = new ContestState();
    when(gameRunner.run(stateInit)).thenReturn(stateAfterFirstGame);

    when(askToContinue.ask(stateAfterFirstGame)).thenReturn(false);

    buildService().run();

    verify(printWelcomeMessage).print();
    verify(contestInitializer).initialize();
    verify(gameRunner).run(stateInit);
    verify(askToContinue).ask(stateAfterFirstGame);
  }

  @Test
  @DisplayName("When multiple games are played")
  public void MultiGameTest() {
    var stateInit = new ContestState();
    when(contestInitializer.initialize()).thenReturn(stateInit);

    var stateAfterFirstGame = new ContestState();
    var stateAfterSecondGame = new ContestState();
    when(gameRunner.run(stateInit)).thenReturn(stateAfterFirstGame);
    when(gameRunner.run(stateAfterFirstGame)).thenReturn(stateAfterSecondGame);

    when(askToContinue.ask(stateAfterFirstGame)).thenReturn(true);
    when(askToContinue.ask(stateAfterSecondGame)).thenReturn(false);

    buildService().run();

    verify(printWelcomeMessage).print();
    verify(contestInitializer).initialize();
    verify(gameRunner).run(stateInit);
    verify(askToContinue).ask(stateAfterFirstGame);

    verify(gameRunner).run(stateAfterFirstGame);
    verify(askToContinue).ask(stateAfterSecondGame);
  }
}
