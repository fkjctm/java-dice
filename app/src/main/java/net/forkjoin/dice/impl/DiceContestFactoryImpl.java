package net.forkjoin.dice.impl;

import net.forkjoin.dice.*;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.util.Arrays;

public class DiceContestFactoryImpl implements DiceContestFactory {
  private static TextIO textIO = null;
  private final boolean lowScoreWins;
  private final boolean tiesAllowed;

  public static void setTextIO(TextIO value) {
    textIO = value;
  }

  public DiceContestFactoryImpl(String[] args) {
    var argArray = Arrays.stream(args).map(a -> a.trim().toLowerCase()).toArray();
    lowScoreWins = Arrays.stream(argArray).filter(a -> a.equals("--low-wins")).findFirst().isPresent();
    tiesAllowed = !Arrays.stream(argArray).filter(a -> a.equals("--no-ties")).findFirst().isPresent();
  }

  public boolean getLowScoreWins() {
    return lowScoreWins;
  }

  public boolean getTiesAllowed() {
    return tiesAllowed;
  }

  @Override
  public PrintWelcomeMessage printWelcomeMessage() {
    return new PrintWelcomeMessageImpl(terminalService());
  }

  @Override
  public ContestInitializer contestInitializer() {
    return new ContestInitializerImpl();
  }

  @Override
  public GameRunner gameRunner() {
    return new GameRunnerImpl(gameInitializer(), getHumanMove(),
      getComputerMove(), determineIfGameContinues(), updateContest(),
      printGameResults(), determineGameResult());
  }

  @Override
  public AskToContinue askToContinue() {
    return new AskToContinueImpl(terminalService());
  }

  @Override
  public PrintEndOfContestMessage printEndOfContestMessage() {
    return new PrintEndOfContestMessageImpl(terminalService());
  }

  @Override
  public DiceContestRunner diceContestRunner() {
    return new DiceContestRunnerImpl(printWelcomeMessage(), contestInitializer(), gameRunner(),
      askToContinue(), printEndOfContestMessage(), printGameRules(), printTieRules());
  }

  @Override
  public GameInitializer gameInitializer() {
    return new GameInitializerImpl();
  }

  @Override
  public GetHumanMove getHumanMove() {
    return new GetHumanMoveImpl(terminalService(), randomNumberGenerator());
  }

  @Override
  public GetComputerMove getComputerMove() {
    return new GetComputerMoveImpl(terminalService(), randomNumberGenerator());
  }

  @Override
  public DetermineIfGameContinues determineIfGameContinues() {
    return tiesAllowed ? new DetermineIfGameContinuesImpl()
                       : new DetermineIfGameContinuesImplNoTiesAllowed(terminalService());
  }

  @Override
  public UpdateContest updateContest() {
    return new UpdateContestImpl();
  }

  @Override
  public PrintGameResults printGameResults() {
    return new PrintGameResultsImpl(terminalService());
  }

  @Override
  public DetermineGameResult determineGameResult() {
    return lowScoreWins ? new DetermineGameResultImplLowScoreWins() : new DetermineGameResultImpl();
  }

  @Override
  public RandomNumberGenerator randomNumberGenerator() {
    return new RandomNumberGeneratorImpl();
  }

  @Override
  public TerminalService terminalService() {
    textIO = textIO == null ? TextIoFactory.getTextIO() : textIO;
    return new TerminalServiceImpl(textIO);
  }

  @Override
  public PrintGameRules printGameRules() {
    return lowScoreWins ? new PrintGameRulesImplLowScoreWins(terminalService())
                        : new PrintGameRulesImplHighScoreWins(terminalService());
  }

  @Override
  public PrintTieRules printTieRules() {
    return tiesAllowed ? new PrintTieRulesImplTiesAllowed(terminalService())
                       : new PrintTieRulesImplTiesNotAllowed(terminalService());
  }
}
