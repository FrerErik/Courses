package com.a1.Measurers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.a1.Algorithms.MonteCarloSim;
import com.a1.Algorithms.QuickFind;
import com.a1.StrategyPatterns.StrategyMeasurer;

public class MonteCarloMeasurer implements StrategyMeasurer {

  /*
   * case n = 10
   * starts:
   * Connected component top = 0, connected to {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
   * Connected component bottom = 101 connected to {91, 92, 93, 94, 95, 96,
   * 97, 98, 99, 100}
   * all components start turned off (turned off = )
   * 
   * rules:
   * (1)
   * when turned on, block will connect towards all sides, except when located in
   * the most left or most right side.
   * Demo: number 82 is selected: 82 is located in the {80, 81, ..., 89} row
   * 82 will connect to 82 + 1, 82 - 1, 82 + 10 and 82 - 10
   * (2)
   * in case that is located in the side ie 89
   * 89 will only connect to 89 - 1, 89 - 10 and 89 + 10
   * (3)
   * sides are found by doing 1 + n*c and n*c - 1
   * 
   * this ends when CC 0 connects to 101 in some way
   */

  public ArrayList<String[]> Results(int n, int amountOfIterations) {
    ArrayList<String[]> results = new ArrayList<String[]>();
    for (int k = 0; k < amountOfIterations; k++) {
      MonteCarloSim a = new MonteCarloSim(n);
      List<Integer> testingValues = new ArrayList<Integer>();
      List<Integer> values = new ArrayList<Integer>();
      for (int l = 1; l < n * n + 1; l++) {
        values.add(l);
      }
      while (!a.isConnected(0, n * n + 1)) {
        int randomVariableA = ThreadLocalRandom.current().nextInt(1, n * n + 1);
        boolean val = true;
        while (val) {
          if (values.contains(randomVariableA)) {
            values.remove(Integer.valueOf(randomVariableA));
            testingValues.add(randomVariableA);
            val = false;
          } else {
            randomVariableA = ThreadLocalRandom.current().nextInt(1, n * n + 1);
          }
        }

        a.ActivateCC(randomVariableA);
      }
      String[] data = { String.valueOf(n), String.valueOf(n * n), String.valueOf(a.getNumberOfOpenComponents()),
          String.valueOf((double) (a.getNumberOfOpenComponents() / n * n)) };
      results.add(data);
    }

    return results;
  }

  @Override
  public ArrayList<String[]> run(int size, int amountOfIterations) {
    return Results(size, amountOfIterations);
  }

  // {1, 2, 3, 4}
  // {5, 6, 7 ,8}
  // {9, 10, 11, 12}
  // {13, 14, 15, 16}

}
