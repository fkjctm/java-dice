package net.forkjoin.dice;

import net.forkjoin.dice.impl.*;
import org.beryx.textio.TextIO;
import org.beryx.textio.mock.MockTextTerminal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DiceContestFactoryImpl")
public class TestDiceContestFactoryImpl {

  class GlobalTests {
    protected DiceContestFactoryImpl factory;

    protected GlobalTests(String[] args) {
      var mockTextTerminal = new MockTextTerminal();
      var mockTextIO = new TextIO(mockTextTerminal);
      DiceContestFactoryImpl.setTextIO(mockTextIO);
      factory = new DiceContestFactoryImpl(args);
    }

    @Test @DisplayName("PrintWelcomeMessage -> PrintWelcomeMessageImpl")
    void PrintWelcomeMessage() {
      var service = factory.printWelcomeMessage();
      assertEquals(PrintWelcomeMessageImpl.class, service.getClass());
    }

    @Test @DisplayName("ContestInitializer -> ContestInitializerImpl")
    void ContestInitializer() {
      var service = factory.contestInitializer();
      assertEquals(ContestInitializerImpl.class, service.getClass());
    }

    @Test @DisplayName("GameRunner -> GameRunnerImpl")
    void GameRunner() {
      var service = factory.gameRunner();
      assertEquals(GameRunnerImpl.class, service.getClass());
    }

    @Test @DisplayName("AskToContinue -> AskToContinueImpl")
    void AskToContinue() {
      var service = factory.askToContinue();
      assertEquals(AskToContinueImpl.class, service.getClass());
    }

    @Test @DisplayName("PrintEndOfContestMessage -> PrintEndOfContestMessageImpl")
    void PrintEndOfContestMessage() {
      var service = factory.printEndOfContestMessage();
      assertEquals(PrintEndOfContestMessageImpl.class, service.getClass());
    }

    @Test @DisplayName("DiceContestRunner -> DiceContestRunnerImpl")
    void DiceContestRunner() {
      var service = factory.diceContestRunner();
      assertEquals(DiceContestRunnerImpl.class, service.getClass());
    }

    @Test @DisplayName("GameInitializer -> DiceContestRunnerImpl")
    void GameInitializer() {
      var service = factory.gameInitializer();
      assertEquals(GameInitializerImpl.class, service.getClass());
    }

    @Test @DisplayName("GetHumanMove -> GetHumanMoveImpl")
    void GetHumanMove() {
      var service = factory.getHumanMove();
      assertEquals(GetHumanMoveImpl.class, service.getClass());
    }

    @Test @DisplayName("GetComputerMove -> GetComputerMoveImpl")
    void GetComputerMove() {
      var service = factory.getComputerMove();
      assertEquals(GetComputerMoveImpl.class, service.getClass());
    }

    @Test @DisplayName("UpdateContest -> UpdateContestImpl")
    void UpdateContest() {
      var service = factory.updateContest();
      assertEquals(UpdateContestImpl.class, service.getClass());
    }

    @Test @DisplayName("PrintGameResults -> PrintGameResultsImpl")
    void PrintGameResults() {
      var service = factory.printGameResults();
      assertEquals(PrintGameResultsImpl.class, service.getClass());
    }

    @Test @DisplayName("RandomNumberGenerator -> RandomNumberGeneratorImpl")
    void RandomNumberGenerator() {
      var service = factory.randomNumberGenerator();
      assertEquals(RandomNumberGeneratorImpl.class, service.getClass());
    }

    @Test @DisplayName("TerminalService -> TerminalServiceImpl")
    void TerminalService() {
      var service = factory.terminalService();
      assertEquals(TerminalServiceImpl.class, service.getClass());
    }
  }

  @Nested @DisplayName("Default factory: no command line arguments")
  class DefaultFactory extends GlobalTests {
    protected DefaultFactory() {
      super(new String[]{});
    }

    @Test @DisplayName("Low scoring system should be turned off")
    void LowScore() {
      assertFalse(factory.getLowScoreWins());
    }

    @Test @DisplayName("Ties should be allowed")
    void TiesAllowed() {
      assertTrue(factory.getTiesAllowed());
    }

    @Test @DisplayName("DetermineIfGameContinues -> DetermineIfGameContinuesImpl")
    void DetermineIfGameContinues() {
      var service = factory.determineIfGameContinues();
      assertEquals(DetermineIfGameContinuesImpl.class, service.getClass());
    }

    @Test @DisplayName("DetermineGameResult -> DetermineGameResultImpl")
    void DetermineGameResult() {
      var service = factory.determineGameResult();
      assertEquals(DetermineGameResultImpl.class, service.getClass());
    }

    @Test @DisplayName("When the high score wins, expect appropriate game rules to be displayed")
    void GameRules() {
      var service = factory.printGameRules();
      assertEquals(PrintGameRulesImplHighScoreWins.class, service.getClass());
    }

    @Test @DisplayName("With ties allowed, expect appropriate tie rules to be displayed")
    void TieRules() {
      var service = factory.printTieRules();
      assertEquals(PrintTieRulesImplTiesAllowed.class, service.getClass());
    }
  }

  @Nested @DisplayName("Low Score enabled: --low-wins flag")
  class LowScoreFactory extends GlobalTests {
    protected LowScoreFactory() {
      super(new String[]{" --low-wins "});
    }

    @Test @DisplayName("Low scoring system should be turned on")
    void LowScore() {
      assertTrue(factory.getLowScoreWins());
    }

    @Test @DisplayName("Ties should be allowed")
    void TiesAllowed() {
      assertTrue(factory.getTiesAllowed());
    }

    @Test @DisplayName("DetermineGameResult -> DetermineGameResultImplLowScoreWins")
    void DetermineGameResult() {
      var service = factory.determineGameResult();
      assertEquals(DetermineGameResultImplLowScoreWins.class, service.getClass());
    }

    @Test @DisplayName("PrintGameRules -> PrintGameRulesImplLowScoreWins")
    void GameRules() {
      var service = factory.printGameRules();
      assertEquals(PrintGameRulesImplLowScoreWins.class, service.getClass());
    }

    @Test @DisplayName("PrintTieRules -> PrintTieRulesImplTiesAllowed")
    void TieRules() {
      var service = factory.printTieRules();
      assertEquals(PrintTieRulesImplTiesAllowed.class, service.getClass());
    }
  }

  @Nested @DisplayName("No Ties Allowed: --no-ties flag")
  class NoTiesFactory extends GlobalTests {
    protected NoTiesFactory() {
      super(new String[]{" --no-ties "});
    }

    @Test @DisplayName("High scoring system should be turned on")
    void HighScore() {
      assertFalse(factory.getLowScoreWins());
    }

    @Test @DisplayName("Ties should not be allowed")
    void TiesNotAllowed() {
      assertFalse(factory.getTiesAllowed());
    }

    @Test @DisplayName("DetermineIfGameContinues -> DetermineIfGameContinuesImplNoTiesAllowed")
    void DetermineIfGameContinues() {
      var service = factory.determineIfGameContinues();
      assertEquals(DetermineIfGameContinuesImplNoTiesAllowed.class, service.getClass());
    }

    @Test @DisplayName("DetermineGameResult -> DetermineGameResultImpl")
    void DetermineGameResult() {
      var service = factory.determineGameResult();
      assertEquals(DetermineGameResultImpl.class, service.getClass());
    }

    @Test @DisplayName("PrintGameRules -> PrintGameRulesImplHighScoreWins")
    void GameRules() {
      var service = factory.printGameRules();
      assertEquals(PrintGameRulesImplHighScoreWins.class, service.getClass());
    }

    @Test @DisplayName("PrintTieRules -> PrintTieRulesImplTiesNotAllowed")
    void TieRules() {
      var service = factory.printTieRules();
      assertEquals(PrintTieRulesImplTiesNotAllowed.class, service.getClass());
    }
  }
}
