package net.forkjoin.dice.impl;

import net.forkjoin.dice.PrintEndOfContestMessage;
import net.forkjoin.dice.data.ContestState;
import net.forkjoin.dice.data.GameResult;

import java.io.PrintStream;

public class PrintEndOfContestMessageImpl implements PrintEndOfContestMessage {
  public static final String winsAndLossesFormat = "The contest has ended. You won %s games and lost %s games";
  public static final String tiesFormat = "and tied %s games";
  private PrintStream printStream;

  public PrintEndOfContestMessageImpl(PrintStream printStream) {
    this.printStream = printStream;
  }

  @Override
  public void print(ContestState contestState) {
    var wins = contestState.getGameHistory().filter(g -> g.getGameResult().equals(GameResult.HumanWin)).count();
    var losses = contestState.getGameHistory().filter(g -> g.getGameResult().equals(GameResult.ComputerWin)).count();
    var ties = contestState.getGameHistory().filter(g -> g.getGameResult().equals(GameResult.Tie)).count();

    var messageBuilder = (new StringBuilder())
      .append(String.format(winsAndLossesFormat, wins, losses));
    if (ties > 0) messageBuilder.append(" ").append(String.format(tiesFormat, ties));

    printStream.println(messageBuilder.toString());
  }
}
