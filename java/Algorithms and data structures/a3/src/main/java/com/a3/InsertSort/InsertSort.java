package com.a3.InsertSort;

import com.a3.StrategyPatterns.StrategySorter;

public class InsertSort<obj extends Comparable<? super obj>> implements StrategySorter<obj> {

  public InsertSort(obj[] array) {
    for (int p = 1; p < array.length; p++) {
      obj tmp = array[p];
      int i = p;
      for (i = p; i > 0 && tmp.compareTo(array[i - 1]) < 0; i--) {
        array[i] = array[i - 1];
      }
      array[i] = tmp;
    }
  }

  public void InsertSortDetached(obj[] array) {
    for (int p = 1; p < array.length; p++) {
      obj tmp = array[p];
      int i = p;
      for (i = p; i > 0 && tmp.compareTo(array[i - 1]) < 0; i--) {
        array[i] = array[i - 1];
      }
      array[i] = tmp;
    }
  }
  public void InsertSortDetachedWithLimits(obj[] array, int startIndex, int endIndex) {
    for (int p = startIndex + 1; p <= endIndex; p++) {
      obj tmp = array[p];
      int i = p;
      for (i = p; i > startIndex && tmp.compareTo(array[i - 1]) < 0; i--) {
        array[i] = array[i - 1];
      }
      array[i] = tmp;
    }
  }

  public InsertSort() {
  }

  @Override
  public void sort(obj[] array) {
    InsertSortDetached(array);
  }

  @Override
  public void sortWithLimits(obj[] array, int startIndex, int endIndex) {
    InsertSortDetachedWithLimits(array, startIndex, endIndex);
  }
  
}
