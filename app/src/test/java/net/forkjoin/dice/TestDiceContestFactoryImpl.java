package net.forkjoin.dice;

import net.forkjoin.dice.impl.*;
import org.beryx.textio.TextIO;
import org.beryx.textio.mock.MockTextTerminal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("DiceContestFactoryImpl")
public class TestDiceContestFactoryImpl {
  private static DiceContestFactory factory;

  @BeforeAll
  static void Setup() {
    var mockTextTerminal = new MockTextTerminal();
    var mockTextIO = new TextIO(mockTextTerminal);
    DiceContestFactoryImpl.setTextIO(mockTextIO);
    factory = new DiceContestFactoryImpl(new String[]{});
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

  @Test @DisplayName("DetermineIfGameContinues -> DetermineIfGameContinuesImpl")
  void DetermineIfGameContinues() {
    var service = factory.determineIfGameContinues();
    assertEquals(DetermineIfGameContinuesImpl.class, service.getClass());
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

  @Test @DisplayName("DetermineGameResult -> DetermineGameResultImpl")
  void DetermineGameResult() {
    var service = factory.determineGameResult();
    assertEquals(DetermineGameResultImpl.class, service.getClass());
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
