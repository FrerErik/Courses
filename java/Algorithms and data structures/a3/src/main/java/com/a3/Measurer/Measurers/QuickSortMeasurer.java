package com.a3.Measurer.Measurers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.a3.QuickSort.QuickSort;
import com.a3.StrategyPatterns.StrategySorter;
import com.a3.StrategyPatterns.StrategyMeasurer;

/* Will do many things at once. 
 * 1) given a size it will run depth until depth becomes unreasonable ie (total/2**depth < 1);
 * 2) will strategy would allow to change from insert to heap
 * returns: size|depth|time it took to sort|
 * title will contain what sorter is being switched to
*/
public class QuickSortMeasurer<obj> implements StrategyMeasurer {
  private StrategySorter<Integer> strategy;


  public QuickSortMeasurer(StrategySorter<Integer> a) {
    this.strategy = a;
  }

  @Override
  public ArrayList<String[]> run(int size, int amountOfIterations) {
    return measure(size, amountOfIterations);
  }
  

  public ArrayList<String[]> measure(int size, int amountOfIterations) {
    ArrayList<String[]> results = new ArrayList<String[]>();
    double nano = Math.pow(10, -9);
    
    int depth = calcMaxDepth(size) * 4;
    Integer[] originalVals = new Integer[size];
    Integer[] vals = new Integer[size];
    for (int i = 0; i < size; i++) {
      int temp =  ThreadLocalRandom.current().nextInt(vals.length);
      originalVals[i] = temp;
      vals[i] = temp;
    }

    for (int k = 0; k < depth; k++) {
      List<Double> times = new ArrayList<Double>();
      boolean isPerfectSorted = false;

      for (int i = 0; i < amountOfIterations; i++) {
        long actualTime = 0;
        long startTime = 0;
        startTime = System.nanoTime();
        QuickSort<Integer> qS = new QuickSort<Integer>(vals, k, this.strategy);
        isPerfectSorted = qS.isPerfectSorted();
        actualTime += System.nanoTime() - startTime;
        times.add(actualTime * nano);
        vals = Arrays.copyOfRange(originalVals, 0, originalVals.length);
      }
      double time = Collections.min(times);
      String[] result = {String.valueOf(size), String.valueOf(k), String.valueOf(time), String.valueOf(calcMaxDepth(size)), String.valueOf(isPerfectSorted)};
      results.add(result);
    }
    return results;
  }

  public int calcMaxDepth(int size) {
    int theoreticalDepth = 0;
    while (size > 1) {
      size = size/2;
      theoreticalDepth += 1;
    }
    return theoreticalDepth;
  }
}
