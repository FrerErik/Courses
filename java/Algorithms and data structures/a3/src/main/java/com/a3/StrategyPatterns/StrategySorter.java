package com.a3.StrategyPatterns;

public interface StrategySorter<obj> {
  public void sort(obj[] array);
  public void sortWithLimits(obj[] array, int startIndex, int endIndex);
}
