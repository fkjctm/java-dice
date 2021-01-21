package net.forkjoin.dice;

import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.impl.DiceContestRunnerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@DisplayName("DiceContestRunnerImpl")
public class TestDiceContestRunner {
  private PrintWelcomeMessage printWelcomeMessage;
  private ContestInitializer contestInitializer;
  private GameRunner gameRunner;
  private AskToContinue askToContinue;
  private PrintEndOfContestMessage printEndOfContestMessage;
  private PrintGameRules printGameRules;
  private PrintTieRules printTieRules;

  @BeforeEach()
  void Init() {
    printWelcomeMessage = mock(PrintWelcomeMessage.class);
    contestInitializer = mock(ContestInitializer.class);
    gameRunner = mock(GameRunner.class);
    askToContinue = mock(AskToContinue.class);
    printEndOfContestMessage = mock(PrintEndOfContestMessage.class);
    printGameRules = mock(PrintGameRules.class);
    printTieRules = mock(PrintTieRules.class);
  }

  private DiceContestRunner buildService() {
    return new DiceContestRunnerImpl(printWelcomeMessage, contestInitializer, gameRunner,
      askToContinue, printEndOfContestMessage, printGameRules, printTieRules);
  }

  class CommonSetup {
    ContestState stateInit;
    ContestState stateAfterFirstGame;

    void Setup() {
      stateInit = new ContestState();
      when(contestInitializer.initialize()).thenReturn(stateInit);

      stateAfterFirstGame = new ContestState();
      when(gameRunner.run(stateInit)).thenReturn(stateAfterFirstGame);
    }
  }

  @Nested @DisplayName("When the player plays a single game")
  class SingleGameSuite extends CommonSetup {

    @BeforeEach
    void Setup() {
      super.Setup();
      when(askToContinue.ask(stateAfterFirstGame)).thenReturn(false);
      buildService().run();
    }

    @Test @DisplayName("Welcome message should have been displayed")
    void WelcomeMessage() {
      verify(printWelcomeMessage).print();
    }

    @Test @DisplayName("Game rules should have been displayed")
    void GameRules() {
      verify(printGameRules).print();
    }

    @Test @DisplayName("User should be informed what happens in the case of a tie")
    void TieRules() {
      verify(printTieRules).print();
    }

    @Test @DisplayName("Contest state should have been initialized")
    void Initializer() {
      verify(contestInitializer).initialize();
    }

    @Test @DisplayName("The game runner should have been called with initial game state")
    void GameRunner() {
      verify(gameRunner).run(stateInit);
    }

    @Test @DisplayName("The player should be asked if they want to continue")
    void PlayerAskedToContinue() {
      verify(askToContinue).ask(stateAfterFirstGame);
    }
  }

  @Nested @DisplayName("When the player plays multiple games")
  class MultiGameSuite extends CommonSetup {
    ContestState stateAfterSecondGame;

    @BeforeEach
    void Setup() {
      super.Setup();
      when(askToContinue.ask(stateAfterFirstGame)).thenReturn(true);

      stateAfterSecondGame = new ContestState();
      when(gameRunner.run(stateAfterFirstGame)).thenReturn(stateAfterSecondGame);

      when(askToContinue.ask(stateAfterSecondGame)).thenReturn(false);

      buildService().run();
    }

    @Test @DisplayName("Game runner should be called for second time with state returned from first game")
    void GameRunner() {
      verify(gameRunner).run(stateAfterFirstGame);
    }

    @Test @DisplayName("Player should be asked to continue with state returned from the second game")
    void AskToContinue() {
      verify(askToContinue).ask(stateAfterSecondGame);
    }
  }
}
