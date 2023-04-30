package com.a1.Measurers;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;

import com.a1.StrategyPatterns.StrategyFindAlgorithms;
import com.a1.StrategyPatterns.StrategyMeasurer;

public class MeasureFindAlgorithms implements StrategyMeasurer {
  int startUnionValue;
  int maxUnionValue;
  int unionIncreaseInterval;
  Double percentageForStart;
  Double percentageForEnd;
  Double percentageForInterval;
  StrategyFindAlgorithms FA;

  public MeasureFindAlgorithms(Double percentageForStart, Double percentageForEnd, Double percentageForInterval,
      StrategyFindAlgorithms FA) {
    this.FA = FA;
    this.percentageForStart = percentageForStart;
    this.percentageForEnd = percentageForEnd;
    this.percentageForInterval = percentageForInterval;
  }

  public ArrayList<String[]> Result(int size, int amountOfIterations) {
    startUnionValue = (int) Math.round(size * percentageForStart);
    maxUnionValue = (int) Math.round(size * percentageForEnd);
    unionIncreaseInterval = (int) Math.round(size * percentageForInterval);

    ArrayList<String[]> results = new ArrayList<String[]>();

    String s = "#";
    System.out.println(s.repeat(1 + (maxUnionValue - startUnionValue) / unionIncreaseInterval) + " ");

    for (int j = startUnionValue; j < maxUnionValue + 1; j += unionIncreaseInterval) {
      System.out.print("#");

      ArrayList<Double> times = new ArrayList<>();
      ArrayList<Double> connectionTimes = new ArrayList<>();
      for (int i = 0; i < amountOfIterations; i++) {
        FA.StrategySetSize(size + 1);

        int randomVariableA;
        int randomVariableB;
        long actualTime = 0;
        long startTime = 0;

        for (int f = 0; f < j; f++) {
          randomVariableA = ThreadLocalRandom.current().nextInt(0, size); // gets from 0 to size
          randomVariableB = ThreadLocalRandom.current().nextInt(0, size);
          startTime = System.nanoTime();
          FA.StrategyUnion(randomVariableA, randomVariableB);
          long endTime = System.nanoTime();
          actualTime += endTime - startTime;
        }
        double value = (double) actualTime / 1_000_000_000;

        times.add(value);

        actualTime = 0;
        startTime = 0;

        for (int f = 0; f < j; f++) {
          randomVariableA = ThreadLocalRandom.current().nextInt(0, size); // Absolutely necessary to skip
          randomVariableB = ThreadLocalRandom.current().nextInt(0, size);
          startTime = System.nanoTime();
          FA.StrategyIsConnected(randomVariableA, randomVariableB);
          actualTime += System.nanoTime() - startTime;
        }
        double value2 = (double) actualTime / 1_000_000_000;
        connectionTimes.add(value2);

      }
      String[] result = { String.valueOf(size), String.valueOf(j), String.valueOf(Collections.min(times)),
          String.valueOf(Collections.min(connectionTimes)) };
      results.add(result);
    }
    System.out.println("\n--------------------------------");
    return results;
  }

  @Override
  public ArrayList<String[]> run(int size, int amountOfIterations) {
    return Result(size, amountOfIterations);
  }

}
