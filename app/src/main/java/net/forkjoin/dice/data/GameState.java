package net.forkjoin.dice.data;

public class GameState {
  public static final String InvalidDieValue = "Die values must be between 1 and 6";
  private GameResult gameResult;
  private int humanScore;
  private int computerScore;

  public GameState() {
    gameResult = GameResult.Empty;
    humanScore = 0;
    computerScore = 0;
  }

  public GameResult getGameResult() {
    return gameResult;
  }

  public void setGameResult(GameResult gameResult) {
    this.gameResult = gameResult;
  }

  public int getHumanScore() {
    return humanScore;
  }

  public void setHumanScore(int humanScore) {
    if (humanScore < 1 || humanScore > 6) {
      throw new IllegalArgumentException(InvalidDieValue);
    }
    this.humanScore = humanScore;
  }

  public int getComputerScore() {
    return computerScore;
  }

  public void setComputerScore(int computerScore) {
    if (computerScore < 1 || computerScore > 6) {
      throw new IllegalArgumentException(InvalidDieValue);
    }
    this.computerScore = computerScore;
  }

  @Override
  public Object clone() {
    var newState = new GameState();
    newState.gameResult = this.gameResult;
    newState.humanScore = this.humanScore;
    newState.computerScore = this.computerScore;
    return newState;
  }
}
