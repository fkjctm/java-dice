package net.forkjoin.dice;

import net.forkjoin.dice.impl.RandomNumberGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("RandomNumberGeneratorImpl")
public class TestRandomNumberGeneratorImpl {
  private int iterationCount = 100;
  private int minVal = 1;
  private int maxVal = 6;
  private IntStream producedSeries;
  private RandomNumberGenerator service;

  @BeforeEach
  void Setup() {
    service = new RandomNumberGeneratorImpl();
    producedSeries = IntStream.range(0, iterationCount)
      .map(n -> service.generate(minVal, maxVal));
  }

  @ParameterizedTest
  @ValueSource(ints = {1,2,3,4,5,6})
  @DisplayName("Produced series should contain all values in between min and max")
  void AllValues(int value) {
    assertTrue(producedSeries.anyMatch(n -> n == value));
  }

  @ParameterizedTest
  @ValueSource(ints = {-2, 8, 50})
  @DisplayName("Produced series should not contain all values outside min and max")
  void OtherValues(int value) {
    assertTrue(producedSeries.noneMatch(n -> n == value));
  }
}
