package com.a3.HeapSort;

import com.a3.StrategyPatterns.StrategySorter;

public class HeapSort<obj extends Comparable<? super obj>> implements StrategySorter<obj> {
  private int leftChild(int i) {
    return 2 * i + 1;
  }

  private void pD(obj[] array, int i, int n) {

    int child;
    obj tmp;

    for (tmp = array[i]; leftChild(i) < n; i = child) {
      child = leftChild(i);
      if (child != n - 1 && array[child].compareTo(array[child + 1]) < 0) {
        child++;
      }
      if (tmp.compareTo(array[child]) < 0) {
        array[i] = array[child];
      } else {
        break;
      }
    }
    array[i] = tmp; // we change the value in array[i] to tmp
  }

  public HeapSort(obj[] array) {
    for (int i = array.length / 2 - 1; i >= 0; i--) {
      pD(array, i, array.length);
    }
    for (int i = array.length - 1; i > 0; i--) {
      swapReferences(array, 0, i);
      pD(array, 0, i);
    }
  }

  public void swapReferences(obj[] array, int index1, int index2) {
    obj tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
  }

  public HeapSort() {

  }

  public void HeapSortDetached(obj[] array) {
    for (int i = array.length / 2 - 1; i >= 0; i--) {
      pD(array, i, array.length);
    }
    for (int i = array.length - 1; i > 0; i--) {
      swapReferences(array, 0, i);
      pD(array, 0, i);
    }
  }

  public void HeapSortDetachedWithLimits(obj[] array, int startIndex, int endIndex) {
    int val = endIndex - startIndex + 1;
    obj[] temp = (obj[]) new Comparable[val];
    int tempIndex = 0;
    for (int i = startIndex; i <= endIndex; i++) {
      temp[tempIndex] = array[i];
      tempIndex++;
    }
    tempIndex = 0;
    HeapSortDetached(temp);
    for (int i = startIndex; i <= endIndex; i++) {
      array[i] = temp[tempIndex];
      tempIndex++;
    }

  }

  @Override
  public void sort(obj[] array) {
    HeapSortDetached(array);
  }

  @Override
  public void sortWithLimits(obj[] array, int startIndex, int endIndex) {
    HeapSortDetachedWithLimits(array, startIndex, endIndex);
  }
  
}
