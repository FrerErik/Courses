package com.a2;

import java.io.IOException;
import java.util.Iterator;

import com.a2.BinarySearchTree.BTNode;
import com.a2.BinarySearchTree.NewBSTree;
import com.a2.LinkedLists.NewLinkedListReversed;
import com.a2.P4DirectoryFinder.NewDirectoryDisplay;
import com.a2.RandomizedQueue.RandomizedQueue;
import com.a2.doubleEndedQueue.GenericDeque;

public class Main {
  public static void main(String[] args) throws CloneNotSupportedException, IOException {
    linkedListreversed(); // P1
    deque(); // P2
    randomizedQueue(); // P3
    directoryFinder(); // P4
    binarySearchTree(); // P5
  }

  private static void linkedListreversed() {
    System.out.println("SLLR Demonstration: \n");
    NewLinkedListReversed sllr = new NewLinkedListReversed();
    sllr.appendToList(0);
    sllr.appendToList(1);
    sllr.appendToList(2);
    System.out.println("Normal: " + sllr.fullList());

    sllr.reverseThisList();
    System.out.println("Reversed: " + sllr.fullList());
    sllr.reverseThisList();
    System.out.println("back to normal: " + sllr.fullList());
  }

  private static void deque() {
    System.out.println("GD demonstration: add and removing First\n");

    GenericDeque<Integer> gD = new GenericDeque<Integer>();
    // System.out.println("test if errors deleting empty lists show up:");
    // gD.removeFirst();
    // gD.removeLast();
    for (int i = 1; i <= 10; i++) {
      gD.addFirst(i);
    }

    Iterator<Integer> iterator = gD.iterateFirstToBack();
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + "|");
    }

    System.out.println("\nAdding back and First --------------------------------------");

    gD.addLast(100);
    gD.addLast(110);
    gD.addFirst(120);
    gD.addFirst(130);

    iterator = gD.iterateFirstToBack();
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + "|");
    }

    System.out.println("\nremoving back and First--------------------------------------");

    gD.removeFirst();
    gD.removeFirst();
    gD.removeLast();
    gD.removeLast();

    iterator = gD.iterateFirstToBack();
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + "|");
    }
    System.out.println("\nRemovingTests--------------------");
    while (!gD.isEmpty()) {
      gD.removeLast();
    }

    System.out.println("\nEmpty? " + gD.isEmpty());
    System.out.println("\n\nGD demonstration add last and removing last");
  }

  private static void randomizedQueue() {
    RandomizedQueue<Integer> rQ = new RandomizedQueue<Integer>();

    for (int i = 0; i <= 10; i++) {
      rQ.enqueue(i);
    }
    Iterator<Integer> itRQ = rQ.iterateFirstToBack();
    while (itRQ.hasNext()) {
      int i = itRQ.next();
      System.out.print(i + "| ");
    }

    System.out.println("\ndelete: " + rQ.dequeue());

    itRQ = rQ.iterateFirstToBack();
    while (itRQ.hasNext()) {
      int i = itRQ.next();
      System.out.print(i + "| ");
    }

    System.out.println("\n--------------------------------");
    System.out.println("RandomIterator Demonstration:");
    itRQ = rQ.RandomIterator();
    while (itRQ.hasNext()) {
      int i = itRQ.next();
      System.out.print(i + "| ");
    }

    System.out.println("\n--------------------------------");
    System.out.println("dequeuing until empty test: \n");
    while (!rQ.isEmpty()) {
      System.out.println("\nremoved: " + rQ.dequeue());
      itRQ = rQ.iterateFirstToBack();
      while (itRQ.hasNext()) {
        int i = itRQ.next();
        System.out.print(i + "| ");
      }
    }
    System.out.println("Empty?: " + rQ.isEmpty());

  }

  private static void directoryFinder() {
    NewDirectoryDisplay nDD;
    try {
      nDD = new NewDirectoryDisplay(new java.io.File(".").getCanonicalPath() + "/P4DTests");
      nDD.walkThrough();
      System.out.println("--------------------------------");
      System.out.println("Check if contains");
      nDD.searchObjectInDirectory("Test");
      System.out.println("--------------------------------");
      nDD.searchObjectInDirectory("test");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static void binarySearchTree() {
    NewBSTree nBSTree = new NewBSTree();
    nBSTree.add(4);
    nBSTree.add(3);
    nBSTree.add(2);
    nBSTree.add(6);
    nBSTree.add(5);
    nBSTree.add(9);
    nBSTree.add(7);
    nBSTree.add(11);

    System.out.println("height test");

    System.out.println(nBSTree.heightOfRoot());

    System.out.println("size test");

    System.out.println(nBSTree.sizeOfRoot());

    System.out.println("contains test");
    System.out.println(nBSTree.contains(11));

    nBSTree.remove(11);
    System.out.println(nBSTree.contains(11));
    nBSTree.add(11);
    System.out.println(nBSTree.contains(11));

    Iterator<BTNode> inorderIt = nBSTree.inorderIterator();
    Iterator<BTNode> preIt = nBSTree.preIterator();
    Iterator<BTNode> postIt = nBSTree.postIterator();

    System.out.print("In order iter [");
    while (inorderIt.hasNext()) {
      System.out.print(inorderIt.next().object + "| ");
    }
    System.out.println("]\n");

    System.out.print("Preorder iter [");
    while (preIt.hasNext()) {
      System.out.print(preIt.next().object + "| ");
    }
    System.out.println("]\n");

    System.out.print("Postorder iter [");
    while (postIt.hasNext()) {
      System.out.print(postIt.next().object + "| ");
    }
    System.out.println("]\n");

    System.out.println("kthLargestVal test");

    System.out.println(nBSTree.getKlargestVal(1));
    System.out.println(nBSTree.getKlargestVal(2));
    System.out.println(nBSTree.getKlargestVal(3));
    System.out.println(nBSTree.getKlargestVal(8));
    // System.out.println(nBSTree.getKlargestVal(9));

    nBSTree.removeKthLargest(1);
    System.out.println(nBSTree.contains(11));
  }

}
