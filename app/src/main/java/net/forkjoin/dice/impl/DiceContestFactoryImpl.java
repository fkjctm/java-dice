package net.forkjoin.dice.impl;

import net.forkjoin.dice.*;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class DiceContestFactoryImpl implements DiceContestFactory {
  private static TextIO textIO = null;

  public DiceContestFactoryImpl(String[] args) {}

  public static void setTextIO(TextIO value) {
    textIO = value;
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
    return new DiceContestRunnerImpl(printWelcomeMessage(), contestInitializer(),
      gameRunner(), askToContinue(), printEndOfContestMessage());
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
    return new DetermineIfGameContinuesImpl();
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
    return new DetermineGameResultImpl();
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

}
