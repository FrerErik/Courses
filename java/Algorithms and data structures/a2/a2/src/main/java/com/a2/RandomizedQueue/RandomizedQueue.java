package com.a2.RandomizedQueue;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizedQueue<obj> {
  public int count;
  private Node<obj> last;
  private Node<obj> first;

  public RandomizedQueue() {
    this.last = null;
    this.first = null;
  }

  public int size() {
    return count;
  }

  public boolean isEmpty() {
    return this.count == 0;
  }

  public void enqueue(obj value) {
    if (this.last == null) {
      this.last = new Node<obj>(value);
      this.first = this.last;
    } else {
      this.last.next = new Node<obj>(value);
      this.last = this.last.next;
    }
    this.count += 1;
  }

  public obj dequeue() {
    if (isEmpty()) {
      System.out.println("Cannot dequeue empty List");
      System.exit(1);
      return null;
    }
    int moves = ThreadLocalRandom.current().nextInt(0, this.count);
    obj val = null;
    Node<obj> temp = this.first;
    RandomizedQueue<obj> tempRQ = new RandomizedQueue<obj>();
    Node<obj> temp2 = null;

    if (this.count == 1) {
      val = temp.obj;
      this.first = tempRQ.first;
      this.last = tempRQ.last;
      this.count = tempRQ.count;

      return val;
    }

    for (int i = 0; i < count; i++) {

      if (i == moves) {

        temp2 = temp.next;
        if (temp2 != null) {
          val = temp2.obj;
        }
        if (i == 0) {
          val = temp.obj;
          tempRQ.enqueue(temp2.obj);
          i += 1;
        }

      } else if (i == 0) {
        tempRQ.enqueue(temp.obj);
      } else if (i > moves) {

        temp2 = temp2.next;
        tempRQ.enqueue(temp2.obj);
      } else {
        temp = temp.next;
        tempRQ.enqueue(temp.obj);
      }
    }

    this.first = tempRQ.first;
    this.last = tempRQ.last;
    this.count = tempRQ.count;

    return val;

  }

  public Iterator<obj> iterateFirstToBack() {

    return new RQIterator();
  }

  public Iterator<obj> RandomIterator() {
    return new RandomIterator();
  }

  private class RQIterator implements Iterator<obj> {

    private Node<obj> position = first;

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

  private class RandomIterator implements Iterator<obj> {

    private RandomizedQueue<obj> queue = new RandomizedQueue<obj>();
    private Node<obj> position = null;

    public RandomIterator() {
      queue.first = first;
      queue.last = last;
      queue.count = count;
      int shuffle = ThreadLocalRandom.current().nextInt(count, 100);
      for (int i = 0; i < shuffle; i++) {
        obj temp = queue.dequeue();
        queue.enqueue(temp);
      }
      position = queue.first;
    }

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
