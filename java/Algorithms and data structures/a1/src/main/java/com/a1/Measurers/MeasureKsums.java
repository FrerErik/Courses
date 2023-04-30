package com.a1.Measurers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.a1.StrategyPatterns.StrategyMeasurer;
import com.a1.StrategyPatterns.StrategySums;

public class MeasureKsums implements StrategyMeasurer {
  double percentage;
  StrategySums sk;

  public MeasureKsums(double percentage, StrategySums sk) {
    this.percentage = (double) percentage;
    this.sk = sk;
  }

  public String[] Results(int size, int amountOfIterations) {
    double nano = Math.pow(10, -9);
    List<Double> times = new ArrayList<Double>();
    long actualTime = 0;
    long startTime = 0;
    sk.StrategySetSize(size, (int) Math.round(size * percentage));

    for (int i = 0; i < amountOfIterations; i++) {
      startTime = System.nanoTime();
      sk.StrategySumAlgorithm();
      actualTime += System.nanoTime() - startTime;
    }
    times.add(actualTime * nano);
    double time = Collections.min(times);
    String[] result = { String.valueOf(size), String.valueOf(Math.round(size * percentage)), String.valueOf(time) };
    return result;
  }

  @Override
  public ArrayList<String[]> run(int size, int amountOfIterations) {
    ArrayList<String[]> result = new ArrayList<String[]>();
    result.add(Results(size, amountOfIterations));
    return result;
  }

}
