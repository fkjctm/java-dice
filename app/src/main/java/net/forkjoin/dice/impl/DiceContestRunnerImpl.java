package net.forkjoin.dice.impl;

import net.forkjoin.dice.*;

public class DiceContestRunnerImpl implements DiceContestRunner {

  private PrintWelcomeMessage printWelcomeMessage;
  private ContestInitializer contestInitializer;
  private GameRunner gameRunner;
  private AskToContinue askToContinue;
  private PrintEndOfContestMessage printEndOfContestMessage;
  private PrintGameRules printGameRules;
  private PrintTieRules printTieRules;

  public DiceContestRunnerImpl(
    PrintWelcomeMessage printWelcomeMessage,
    ContestInitializer contestInitializer,
    GameRunner gameRunner,
    AskToContinue askToContinue,
    PrintEndOfContestMessage printEndOfContestMessage,
    PrintGameRules printGameRules,
    PrintTieRules printTieRules) {
    this.printWelcomeMessage = printWelcomeMessage;
    this.contestInitializer = contestInitializer;
    this.gameRunner = gameRunner;
    this.askToContinue = askToContinue;
    this.printEndOfContestMessage = printEndOfContestMessage;
    this.printGameRules = printGameRules;
    this.printTieRules = printTieRules;
  }

  @Override
  public void run() {
    printWelcomeMessage.print();
    printGameRules.print();
    printTieRules.print();
    var contestState = contestInitializer.initialize();

    do {
      contestState = gameRunner.run(contestState);
    } while(askToContinue.ask(contestState));

    printEndOfContestMessage.print(contestState);
  }
}
