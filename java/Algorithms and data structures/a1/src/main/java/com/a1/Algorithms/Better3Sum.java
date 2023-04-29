package com.a1.Algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.a1.StrategyPatterns.StrategySums;

public class Better3Sum implements StrategySums {
  List<Integer> list;
  int size;

  public Better3Sum() {

  }

  public Better3Sum(int size, int rangeOfSet) {
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < size; i++) {
      int randomVariableA = ThreadLocalRandom.current().nextInt(-rangeOfSet, rangeOfSet);
      list.add(randomVariableA);
    }
    this.list = list;
    this.size = size;
  }

  public void setSize(int size, int rangeOfSet) {
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < size; i++) {
      int randomVariableA = ThreadLocalRandom.current().nextInt(-rangeOfSet, rangeOfSet);
      list.add(randomVariableA);
    }
    this.list = list;
    this.size = size;
  }

  public Better3Sum(List<Integer> list) {
    this.list = list;
    size = list.size();
  }

  public List<Integer> getList() {
    return this.list;
  }

  public List<String[]> ThreeSum() {
    List<String[]> results = new ArrayList<String[]>();
    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; j++) {
        for (int k = j + 1; k < size; k++) {
          if (i == j || i == k || j == k) {
            continue;
          } else if ((list.get(i) + list.get(j) + list.get(k)) == 0) {
            String[] result = { String.valueOf(list.get(i)), String.valueOf(list.get(j)), String.valueOf(list.get(k)) };
            results.add(result);
          }
        }
      }
    }

    return results;
  }

  @Override
  public void StrategySetSize(int size, int rangeOfSet) {
    setSize(size, rangeOfSet);
  }

  @Override
  public List<String[]> StrategySumAlgorithm() {
    return ThreeSum();
  }

}
