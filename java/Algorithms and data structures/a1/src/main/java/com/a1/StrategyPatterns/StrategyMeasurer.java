package com.a1.StrategyPatterns;

import java.util.ArrayList;

// Every measurer must implement this file for it to run in the GTM
public interface StrategyMeasurer {
  ArrayList<String[]> run(int size, int amountOfIterations);
}
