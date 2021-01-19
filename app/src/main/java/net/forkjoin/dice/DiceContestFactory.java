package net.forkjoin.dice;

public interface DiceContestFactory {

  PrintWelcomeMessage printWelcomeMessage();

  ContestInitializer contestInitializer();

  GameRunner gameRunner();

  AskToContinue askToContinue();

  PrintEndOfContestMessage printEndOfContestMessage();

  DiceContestRunner diceContestRunner();

  GameInitializer gameInitializer();

  GetHumanMove getHumanMove();

  GetComputerMove getComputerMove();

  DetermineIfGameContinues determineIfGameContinues();

  UpdateContest updateContest();

  PrintGameResults printGameResults();

  DetermineGameResult determineGameResult();

  RandomNumberGenerator randomNumberGenerator();

  TerminalService terminalService();
}
