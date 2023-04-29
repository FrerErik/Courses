package com.a4.ReusedClasses;

import java.util.Arrays;

import com.a4.GraphADT.Edge;

public class MinHeap {

  private int capacity;
  public int size = 0;
  public Edge[] heap;

  public MinHeap(int capacity) {
    this.capacity = capacity;
    this.heap = new Edge[capacity];
  }

  public int getHeapSize() {
    return size;
  }

  private int getLeftChildIndex(int parentIndex) {
    return 2 * parentIndex + 1;
  }

  private int getRightChildIndex(int parentIndex) {
    return 2 * parentIndex + 2;
  }

  private int getParentIndex(int childIndex) {
    return (childIndex - 1) / 2;
  }

  private boolean hasLeftChild(int index) {
    return getLeftChildIndex(index) < size;
  }

  private boolean hasRightChild(int index) {
    return getRightChildIndex(index) < size;
  }

  private boolean hasParent(int index) {
    return getParentIndex(index) >= 0;
  }

  private Edge leftChild(int parentIndex) {
    return heap[getLeftChildIndex(parentIndex)];
  }

  private Edge rightChild(int parentIndex) {
    return heap[getRightChildIndex(parentIndex)];
  }

  private Edge parent(int childIndex) {
    return heap[getParentIndex(childIndex)];
  }

  private void swapReferences(int index1, int index2) {
    Edge edge = heap[index1];
    heap[index1] = heap[index2];
    heap[index2] = edge;
  }

  private void capacity() {
    if (size == capacity) {
      heap = Arrays.copyOf(heap, capacity * 2);
      capacity = capacity * 2;
    }
  }

  public Edge pop() {
    if (size == 0) {
      return null;
    }

    Edge edge = heap[0];

    heap[0] = heap[size - 1];
    size--;
    pD();
    return edge;
  }

  public void add(Edge e) {
    capacity();
    heap[size] = e;
    size++;
    pU();
  }

  private void pU() {
    int index = size - 1;

    while (hasParent(index) && parent(index).compareTo(heap[index]) > 0) {
      swapReferences(getParentIndex(index), index);
      index = getParentIndex(index);
    }
  }

  public void pD() {
    int index = 0;

    while (hasLeftChild(index)) {
      int smallestChildIndex = getLeftChildIndex(index);

      if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) < 0) {
        smallestChildIndex = getRightChildIndex(index);
      }

      if (heap[index].compareTo(heap[smallestChildIndex]) < 0) {
        break;
      } else {
        swapReferences(index, smallestChildIndex);
      }
      index = smallestChildIndex;
    }
  }

}