package com.a4.ReusedClasses;

public class QuickFind {
  private int[] list;

  /* Constructor sets list length from the start */
  public QuickFind(int size) {
    int[] list = new int[size];
    for (int i = 0; i < size; i++) {
      list[i] = i;
    }
    this.list = list;
  }

  public QuickFind() {
    
  }

  public void setSize(int size) {
    int[] list = new int[size];
    for (int i = 0; i < size; i++) {
      list[i] = i;
    }
    this.list = list;
  }

  public void SetList(int[] list) {
    this.list = list;
  }

  public int[] getList() {
    return this.list;
  }

  /*
   * Quick Union find implementation: Represent the list as a collection of
   * components and each component will be represented as a tree
   * all info mostly gathered from slides
   * Separated from general list and self contained (just testing if there are
   * differences) plus less time thinking about
   */

  /* General */

  public int MakeRoot(int[] list, int object) {
    while (object != list[object]) {
      object = list[object];
    }
    return object;
  }

  public boolean isConnected(int[] list, int objectA, int objectB) {
    if (MakeRoot(list, objectA) == MakeRoot(list, objectB)) {
      return true;
    } else {
      return false;
    }
  }

  public void union(int[] list, int objectA, int objectB) {
    int rootA = MakeRoot(list, objectA);
    int rootB = MakeRoot(list, objectB);
    list[rootA] = rootB;
  }

  /* Self contained */

  public boolean isConnected(int objectA, int objectB) {
    if (MakeRoot(this.list, objectA) == MakeRoot(this.list, objectB)) {
      return true;
    } else {
      return false;
    }
  }

  public void union(int objectA, int objectB) {
    int rootA = MakeRoot(this.list, objectA);
    int rootB = MakeRoot(this.list, objectB);
    this.list[rootA] = rootB;
  }

}
