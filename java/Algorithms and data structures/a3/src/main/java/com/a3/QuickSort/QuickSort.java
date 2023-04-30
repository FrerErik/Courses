package com.a3.QuickSort;


import com.a3.StrategyPatterns.StrategySorter;

public class QuickSort<obj extends Comparable<? super obj>> {

  private int depth;
  private StrategySorter<obj> strategy;
  private boolean isPerfectSorted;


  public boolean isPerfectSorted() {
    return this.isPerfectSorted;
  }

  public QuickSort(obj[] array, int depth, StrategySorter<obj> switchSort) {
    this.strategy = switchSort;
    this.depth = depth;
    this.isPerfectSorted = false;
    int leftSide = 0;
    int rightSide = array.length - 1;
    sort(array, leftSide, rightSide, 0);
  }


  private void sort(obj[] array, int leftSide, int rightSide, int depth) {
    if (depth >= this.depth) { 
      if (leftSide  + 1 >= rightSide && leftSide + 1 <= 100) {
        this.isPerfectSorted = true;
      }
      strategy.sortWithLimits(array, leftSide, rightSide);
    } else if(leftSide + 3 >= rightSide) {
      strategy.sortWithLimits(array, leftSide, rightSide);
    } else {
      obj pivot = median(array, leftSide , rightSide);
      int parti = partition(array, leftSide, rightSide, pivot);
      depth += 1; 
      sort(array, leftSide, parti - 1, depth);
      sort(array, parti + 1, rightSide, depth);
    }
  }


  private obj median(obj[] array, int leftSide, int rightSide) {
    int centerElement = (leftSide + rightSide) / 2;
    if (array[centerElement].compareTo(array[leftSide]) < 0) {
      swapReferences(array, leftSide, centerElement);
    }

    if (array[rightSide].compareTo(array[leftSide]) < 0) {
      swapReferences(array, leftSide, rightSide);
    }

    if (array[rightSide].compareTo(array[centerElement]) < 0) {
      swapReferences(array, centerElement, rightSide);
    }
    // System.out.println("centerElement: " + centerElement + " rightSide: " + (rightSide - 1));
    swapReferences(array, centerElement, rightSide - 1);
    return array[rightSide - 1];
  }

  private void swapReferences(obj[] array, int a, int b) {
    obj tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
  }

  public int partition(obj[] array, int leftSide, int rightSide, obj pivot) {
    int left = leftSide;
    int right = rightSide - 1;
    while (true) {
      while (array[++left].compareTo(pivot) < 0) {}
      while (array[--right].compareTo(pivot) > 0) {}
      if (left < right) {
        swapReferences(array, left, right);
      } else {
        break;
      }
    }
    // System.out.println("left: " + left + " right: " + (rightSide - 1));
    swapReferences(array, left, rightSide - 1);
    return left;
  }
}
