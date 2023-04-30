package com.a2.doubleEndedQueue;

public class Node<obj> {
  public obj obj;
  public Node<obj> next;
  public Node<obj> prev;

  public Node(obj obj) {
    this.obj = obj;
  }

}
