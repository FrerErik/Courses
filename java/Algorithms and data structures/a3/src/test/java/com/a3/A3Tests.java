package com.a3;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.a3.HeapSort.HeapSort;
import com.a3.InsertSort.InsertSort;
import com.a3.Problem1.HashTableWithQP;
import com.a3.QuickSort.QuickSort;

/**
 * Unit test for simple App.
 */
public class A3Tests {
  /* */
  @Test
  public void InsertSortTest() {
    Integer[] a = new Integer[] { 1, 3, 2, 6, 4, 0 };
    Integer[] realValue = new Integer[] { 0, 1, 2, 3, 4, 6 };
    assertFalse(Arrays.equals(a, realValue));

    InsertSort<Integer> is = new InsertSort<Integer>(a);

    assertTrue(Arrays.equals(a, realValue));

  }

  @Test
  public void HeapSortTests() {
    Integer[] a = new Integer[] { 1, 3, 2, 6, 4, 0 };

    Integer[] realValue = new Integer[] { 0, 1, 2, 3, 4, 6 };
    assertFalse(Arrays.equals(a, realValue));

    HeapSort<Integer> hs = new HeapSort<Integer>(a);
    assertTrue(Arrays.equals(a, realValue));

  }

  @Test
  public void QuickSortTestsHS() {
    Integer[] a = new Integer[1000000];
    for (int i = 0; i < a.length; i++) {
      int temp = ThreadLocalRandom.current().nextInt(a.length);
      a[i] = temp;
    }

    QuickSort<Integer> qS = new QuickSort<Integer>(a, 2, new HeapSort<Integer>());

    assertTrue("Check if its sorted", isSorted(a, a.length));

  }

  @Test
  public void QuickSortTestsIS() {
    Integer[] a = new Integer[1000000];
    for (int i = 0; i < a.length; i++) {
      int temp = ThreadLocalRandom.current().nextInt(a.length);
      a[i] = temp;
    }

    QuickSort<Integer> qS = new QuickSort<Integer>(a, 10, new InsertSort<Integer>());

    assertTrue("Check if its sorted", isSorted(a, a.length));
  }

  private static boolean isSorted(Integer arr[], int n) {
    if (n == 0 || n == 1)
      return true;
    for (int i = 1; i < n; i++)
      if (arr[i - 1] > arr[i])
        return false;
    return true;
  }

  @Test
  public void RemoveInsertAndContainsOperations() {
    HashTableWithQP<String> hT = new HashTableWithQP<String>();
    hT.insert("a");
    assertTrue("if its in hash table", hT.contains("a"));
    hT.delete("a");
    assertFalse("if its not in hash table", hT.contains("a"));

  }

  @Test
  public void TestCollision() {
    /*
     * input sample input values
     * hash each
     * put all results in a set
     * Count size of the results set and compare to the size of input set
     */

    HashTableWithQP<String> hT = new HashTableWithQP<String>(197);
    List<String> resultTable = new ArrayList<String>();
    String temp = "a";
    for (int i = 0; i < 100; i++) {

      resultTable.add(temp);
      hT.insert(temp);
      temp += "a";
    }
    assertEquals(resultTable.size(), hT.getTotalSize());
  }

}
