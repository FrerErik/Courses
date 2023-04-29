package com.a3.Problem1;

import com.a3.Problem2.Vehicle;

public class HashTableWithQP<obj> {
  private HashNode<obj>[] table;
  private int takenCells;
  private int totalSize;
  private int[] offset;
  public int totalColissions;
  private Vehicle car;

  public int getTotalColissions() {
    return this.totalColissions;
  }

  public int getTotalSize() {
    return this.totalSize;
  }

  public int[] getOffset() {
    return this.offset;
  }

  public int capacity() {
    return this.table.length;
  }

  /* Table always prime */

  public HashTableWithQP(int size) {
    this.table = new HashNode[size];
    this.takenCells = 0;
    this.totalSize = 0;
    this.offset = new int[size];
    this.totalColissions = 0;
    for (int i = 0; i < this.table.length; i++) {
      this.table[i] = null;
    }
  }

  public HashTableWithQP() {
    this(101);
  }

  public boolean insert(obj val) {
    if (val.getClass() == Vehicle.class) {
      this.car = (Vehicle) val;
      val = (obj) this.car.getLicensePlateNumber();
    }
    int currentIndex = findIndex(val);
    if (occupied(currentIndex)) {
      return false;
    }
    if (this.table[currentIndex] == null) {
      takenCells += 1;
    }
    this.table[currentIndex] = new HashNode<>(val, true);
    totalSize += 1;
    return true;
  }

  public int hash(obj val) {
    int hashVal = myHash(val);
    hashVal %= table.length;
    if (hashVal < 0) {
      hashVal += table.length;
    }
    return hashVal;
  }

  public int myHash(obj val) {
    int hashVal = 0;
    String a = val.toString();

    for (int i = 0; i < a.length(); i++) {
      hashVal = 37 * hashVal + a.charAt(i);
    }

    return hashVal;
  }

  public int findIndex(obj val) {
    int currentIndex = hash(val);
    int offset = 1;
    boolean collisions = false;
    while (this.table[currentIndex] != null && !this.table[currentIndex].val.equals(val)) {
      currentIndex += offset;
      offset += 2;
      collisions = true;
      if (currentIndex >= this.table.length) {
        currentIndex -= this.table.length;
      }
    }
    if (collisions) {
      this.totalColissions += 1;
    }
    offsetTracker(offset);
    return currentIndex;
  }

  public boolean contains(obj val) {
    int currentIndex = findIndex(val);
    return occupied(currentIndex);
  }

  private boolean occupied(int currentIndex) {
    return this.table[currentIndex] != null && this.table[currentIndex].isInside;
  }

  public void offsetTracker(int offset) {
    int index = (offset - 1) / 2;
    this.offset[index] += 1;
  }

  public boolean delete(obj val) {
    int currentIndex = findIndex(val);
    if (occupied(currentIndex)) {
      this.table[currentIndex].isInside = false;
      totalSize -= 1;
      return true;
    } else {
      return false;
    }
  }

}
