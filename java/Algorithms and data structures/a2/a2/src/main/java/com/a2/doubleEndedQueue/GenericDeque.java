package com.a2.doubleEndedQueue;

import java.util.Iterator;

public class GenericDeque<obj> {
  int count;
  public Node<obj> first_Node;
  public Node<obj> last_Node;

  public GenericDeque() {
    this.first_Node = null;
    this.last_Node = null;
    this.count = 0;
  }

  public int size() {
    return this.count;
  }

  public boolean isEmpty() {
    return this.count == 0;
  }

  public obj checkTop() {
    return this.first_Node.obj;
  }

  public obj checkBottom() {
    return this.last_Node.obj;
  }

  public void addLast(obj value) {
    Node<obj> temp = new Node<obj>(value);

    if (this.last_Node == null) {
      this.last_Node = temp;
      this.first_Node = temp;
      this.count++;
    } else {
      temp.prev = last_Node;
      this.last_Node.next = temp;
      this.last_Node = temp;
      this.count++;
    }

  }

  public void addFirst(obj value) {
    Node<obj> temp = new Node<obj>(value);

    if (this.first_Node == null) {
      last_Node = temp;
      first_Node = temp;
    } else {
      temp.next = first_Node;
      first_Node.prev = temp;
      first_Node = temp;
    }
    this.count++;
  }

  public obj removeFirst() {
    if (isEmpty()) {
      System.out.println("Error trying to delete empty list");
      System.exit(1);
      return null;
    } else {
      obj temp = this.first_Node.obj;
      this.first_Node = this.first_Node.next;
      this.count -= 1;

      if (this.first_Node == null) {
        last_Node = null;
        return temp;
      } else {
        first_Node.prev = null;
        return temp;
      }
    }
  }

  public obj removeLast() {
    if (this.last_Node == null) {
      System.out.println("Error trying to delete empty list");
      return null;
    } else {
      obj temp = this.last_Node.obj;
      this.last_Node = this.last_Node.prev;
      this.count -= 1;
      if (this.last_Node == null) {
        first_Node = null;
        return temp;
      } else {
        last_Node.next = null;
        return temp;
      }
    }
  }

  public Iterator<obj> iterateFirstToBack() {

    return new DQIterator();
  }

  private class DQIterator implements Iterator<obj> {

    private Node<obj> position = first_Node;

    @Override
    public boolean hasNext() {
      return (position != null);
    }

    @Override
    public obj next() {
      obj val = position.obj;
      position = position.next;
      return val;
    }

  }

}
