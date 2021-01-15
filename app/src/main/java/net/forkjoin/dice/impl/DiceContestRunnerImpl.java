package net.forkjoin.dice.impl;

import net.forkjoin.dice.*;

public class DiceContestRunnerImpl implements DiceContestRunner {

  private PrintWelcomeMessage printWelcomeMessage;
  private ContestInitializer contestInitializer;
  private GameRunner gameRunner;
  private AskToContinue askToContinue;
  private PrintEndOfContestMessage printEndOfContestMessage;

  public DiceContestRunnerImpl(
    PrintWelcomeMessage printWelcomeMessage,
    ContestInitializer contestInitializer,
    GameRunner gameRunner,
    AskToContinue askToContinue,
    PrintEndOfContestMessage printEndOfContestMessage) {
    this.printWelcomeMessage = printWelcomeMessage;
    this.contestInitializer = contestInitializer;
    this.gameRunner = gameRunner;
    this.askToContinue = askToContinue;
    this.printEndOfContestMessage = printEndOfContestMessage;
  }

  @Override
  public void run() {
    printWelcomeMessage.print();
    var contestState = contestInitializer.initialize();

    do {
      contestState = gameRunner.run(contestState);
    } while(askToContinue.ask(contestState));

    printEndOfContestMessage.print(contestState);
  }
}
