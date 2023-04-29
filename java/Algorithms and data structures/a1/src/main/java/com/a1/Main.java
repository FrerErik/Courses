package com.a1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.a1.Algorithms.Better3Sum;
import com.a1.Algorithms.BruteForce3Sum;
import com.a1.Algorithms.QuickFind;
import com.a1.Algorithms.UnionFind;
import com.a1.Measurers.MeasureFindAlgorithms;
import com.a1.Measurers.MeasureKsums;
import com.a1.Measurers.MonteCarloMeasurer;

public class Main {
  public static void main(String[] args) throws IOException {
    List<Integer> numbers = new ArrayList<Integer>();
    numbers.add(-1);
    numbers.add(0);
    numbers.add(1);
    numbers.add(2);
    numbers.add(-1);
    numbers.add(-4);
    Better3Sum better3Sum = new Better3Sum(numbers);
    System.out.println(better3Sum.getList());
    for (String[] list : better3Sum.ThreeSum()) {
      System.out.println(Arrays.toString(list));
    }
    BruteForce3Sum B3Sum = new BruteForce3Sum(numbers);
    System.out.println(B3Sum.getList());
    for (String[] list : B3Sum.ThreeSum()) {
      System.out.println(Arrays.toString(list));
    }
    QuickFind QuickFind = new QuickFind(8);
    UnionFind UnionFind = new UnionFind(8);

    /*
     * ----------------------------------------------------------------
     * UnionFind implementation demonstration
     * ----------------------------------------------------------------
     */

    int[] Ulist = UnionFind.getList();

    System.out.println("UnionFind list: " + Arrays.toString(Ulist));
    UnionFind.union(Ulist, 0, 1);
    UnionFind.union(Ulist, 6, 7);
    System.out.println("UnionFind list: " + Arrays.toString(Ulist));
    UnionFind.union(Ulist, 1, 2);
    System.out.println("UnionFind list: " + Arrays.toString(Ulist));
    System.out.println("--------------------------");

    /*
     * ----------------------------------------------------------------
     * QuickFind implementation demonstration
     * ----------------------------------------------------------------
     */

    int[] Qlist = QuickFind.getList();
    System.out.println("QuickFind List: " + Arrays.toString(Qlist));

    QuickFind.union(Qlist, 0, 1);
    QuickFind.union(Qlist, 1, 2);
    System.out.println("QuickFind List: " + Arrays.toString(Qlist));
    QuickFind.union(Qlist, 3, 4);
    System.out.println("QuickFind List: " + Arrays.toString(Qlist));
    System.out.println("--------------------------");
    /* I recommend max size 10k for UnionFind, 3K for Sum and 150 for n */
    GTM gtmUF = new GTM(
        100000, 5,
        250000, 18750, new String[] { "Size",
            "NumberOfUnions", "Time(s)", "IsConnectTime(s)" },
        true);

    gtmUF.StartGTMProgressBar2(new MeasureFindAlgorithms(0.1, 0.9, 0.05, new UnionFind()),
        "UFtest");
    gtmUF.StartGTMProgressBar2(new MeasureFindAlgorithms(0.1, 0.9, 0.05, new QuickFind()),
        "QUF");

    // GTM gtmKSum = new GTM(0, 5,
    // 3000, 100, new String[] { "Size",
    // "RangeOfValues", "Time(s)" },
    // false);
    // gtmKSum.StartGTMProgressBar2(new MeasureKsums(0.8, new BruteForce3Sum()),
    // "BruteForce3Sum300K");
    // gtmKSum.StartGTMProgressBar2(new MeasureKsums(0.8, new Better3Sum()),
    // "Better3Sum300K");

    // GTM gtmMCM = new GTM(10, 10, 300, 10, new String[] { "n", "Total",
    // "NumberOfOpenComponents", "Percolation Rate" });
    // gtmMCM.StartGTMProgressBar(new MonteCarloMeasurer(),
    // "percolationRateRawData");
  }
}
