package com.a1.StrategyPatterns;

public interface StrategyFindAlgorithms {
  public void runFindAlgorithm();

  public void StrategyUnion(int objectA, int objectB);

  public void StrategySetSize(int size);

  public boolean StrategyIsConnected(int objectA, int objectB);
}
