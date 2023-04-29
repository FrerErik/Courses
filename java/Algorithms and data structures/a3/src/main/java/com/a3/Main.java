package com.a3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.a3.HeapSort.HeapSort;
import com.a3.InsertSort.InsertSort;
import com.a3.Measurer.GTM;
import com.a3.Measurer.Measurers.QuickSortMeasurer;
import com.a3.Problem1.HashTableWithQP;
import com.a3.Problem2.Vehicle;

/*
 * In order to not make a big class like I did before I will instead create small tests
 * inside the classes and run them here, the throrough testing will come from the test class
 * called "A3Tests.java" I would recommend checking there as well.
 */

public class Main {
  public static void main(String[] args) {

    // testQS();
    // testHashTable();
    // runGTM();

    Integer[] primeNumbers = { 101, 211, 463, 809, 1523, 3083, 6481, 7919 };
    for (int i = 0; i < primeNumbers.length; i++) {
      int[] offset = new int[7919];
      int collisions = 0;
      for (int j = 0; j < 100; j++) {
        HashTableWithQP<Object> hT = testHashTable(primeNumbers[i]);

        collisions += hT.getTotalColissions();
        int[] temp = hT.getOffset();
        for (int p = 0; p < temp.length; p++) {
          offset[p] += temp[p];
        }
      }
      collisions = collisions / 100;
      System.out.println("Average 100 Collisions: " + collisions + " |TotalSize: " + primeNumbers[i] + "| Ratio: "
          + ((double) collisions / (double) Math.round(primeNumbers[i] - primeNumbers[i] * 0.5)));

      int range = 1;
      for (int j = 0; j < offset.length; j++) {
        if (offset[j] != 0) {
          System.out
              .println("offSet range: " + range + "| values within in average of 100: " + (double) offset[j] / 100);
        }
        range += 2;
      }
    }
  }

  private static HashTableWithQP<Object> testHashTable(int size) {
    List<Object> testList = new ArrayList<Object>();

    for (int i = 0; i < Math.round(size - size * 0.5); i++) {
      testList.add(new Vehicle());
    }

    HashTableWithQP<Object> hT = new HashTableWithQP<Object>(size);
    Iterator<Object> it = testList.iterator();
    while (it.hasNext()) {
      hT.insert(it.next());
    }

    int range = 1;
    // System.out.println("table size: " + size + " amount of inserted vehicles: " +
    // Math.round(size - size * 0.5)
    // + "-------------------------");
    for (int i = 0; i < hT.getOffset().length; i++) {
      int[] val = hT.getOffset();
      if (val[i] == 0) {

      } else {
        // System.out.println("Offset: " + range + " number of them: " + val[i]);
      }
      range += 2;
    }
    // System.out.println("Collisions: " + hT.getTotalColissions());
    return hT;
  }

  private void runGTM() {
    GTM m = new GTM(50000, 3, 100000, 10000,
        new String[] { "size", "depth", "timeTakenToSort", "MaxDepthInTheory", "isPerfectSorted" }, true);
    m.StartGTMProgressBar2(new QuickSortMeasurer<Integer>(new InsertSort<Integer>()), "InsertSortTests50ktill100k");
    m.StartGTMProgressBar2(new QuickSortMeasurer<Integer>(new HeapSort<Integer>()), "HeapSortTests50ktill100k");
  }

  private static void testHashTable() {
    HashTableWithQP<String> hT = new HashTableWithQP<String>();

    for (int i = 0; i < 70; i++) {
      System.out.println(hT.insert(new Vehicle().getLicensePlateNumber()));

    }
    System.out.println(hT.getTotalSize() + " " + hT.capacity());

  }

}
