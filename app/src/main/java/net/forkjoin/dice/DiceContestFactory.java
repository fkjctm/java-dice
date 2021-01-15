package net.forkjoin.dice;

public interface DiceContestFactory {

  PrintWelcomeMessage printWelcomeMessage();

  ContestInitializer contestInitializer();

  GameRunner gameRunner();

  AskToContinue askToContinue();

  PrintEndOfContestMessage printEndOfContestMessage();

  DiceContestRunner diceContestRunner();
}
