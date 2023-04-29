package com.a1.Algorithms;

import com.a1.StrategyPatterns.StrategyFindAlgorithms;

public class UnionFind implements StrategyFindAlgorithms {
  private int[] list;

  public UnionFind(int size) {
    int[] list = new int[size];
    for (int i = 0; i < size; i++) {
      list[i] = i;
    }
    this.list = list;
  }

  public UnionFind() {
  }

  public void SetList(int[] list) {
    this.list = list;
  }

  public int[] getList() {
    return this.list;
  }

  /* Implement simple version of UnionFind, most of the info is in the slides */ // TODO Rename Object later
  public boolean isConnected(int[] list, int objectA, int objectB) {
    if (list[objectA] == list[objectB]) {
      return true;
    } else {
      return false;
    }
  }

  public void union(int[] list, int objectA, int objectB) {
    int A_id = list[objectA];
    int B_id = list[objectB];
    int listSize = list.length;
    for (int i = 0; i < listSize; i++) {
      int b = list[i];
      if (b == A_id) {
        list[i] = B_id;
      }
    }
  }

  /* Implement simple version of UnionFind, most of the info is in the slides */ // TODO Rename Object later
  public boolean isConnected(int objectA, int objectB) {
    if (list[objectA] == list[objectB]) {
      return true;
    } else {
      return false;
    }
  }

  public void union(int objectA, int objectB) {
    int A_id = list[objectA];
    int B_id = list[objectB];
    int listSize = list.length;
    for (int i = 0; i < listSize; i++) {
      int b = list[i];
      if (b == A_id) {
        list[i] = B_id;
      }
    }
  }

  @Override
  public void runFindAlgorithm() {
    // TODO Auto-generated method stub

  }

  @Override
  public void StrategyUnion(int objectA, int objectB) {
    if (objectA != objectB) {
      if (!isConnected(objectA, objectB)) {
        union(objectA, objectB);
      }

    }
  }

  @Override
  public void StrategySetSize(int size) {
    int[] list = new int[size];
    for (int i = 0; i < size; i++) {
      list[i] = i;
    }
    this.list = list;
  }

  @Override
  public boolean StrategyIsConnected(int objectA, int objectB) {
    return isConnected(objectA, objectB);
  }
}
