package net.forkjoin.dice;

import net.forkjoin.dice.impl.ContestInitializerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ContestInitializerImpl")
public class TestContestInitializerImpl {

  @Test @DisplayName("Should create empty contest state")
  void CreateEmptyState() {
    var service = new ContestInitializerImpl();
    var state = service.initialize();

    assertEquals(0, state.getGameCount());
  }
}
