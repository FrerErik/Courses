package com.a2.BinarySearchTree;

import java.util.Iterator;

import com.a2.doubleEndedQueue.GenericDeque;

public class NewBSTree {

  public BTNode root;

  public NewBSTree() {
    this.root = null;
  }

  public int height(BTNode node) {
    if (node == null) {
      return -1;
    } else {
      return 1 + maxVal(height(node.left_Node), height(node.right_Node));
    }
  }

  public int maxVal(int a, int b) {
    if (a < b) {
      return b;
    } else if (a == b) {
      return a;
    }
    return a;
  }

  public int heightOfRoot() {
    return height(root);
  }

  public int size(BTNode node) {
    if (node == null) {
      return 0;
    }
    return 1 + size(node.left_Node) + size(node.right_Node);
  }

  public int sizeOfRoot() {
    return size(root);
  }

  public BTNode add(BTNode node, int object) {
    if (node == null) {
      return new BTNode(object);
    }
    if (node.object > object) {
      node.left_Node = add(node.left_Node, object);
    } else if (node.object < object) {
      node.right_Node = add(node.right_Node, object);
    }
    return node;
  }

  public void add(int object) {
    root = add(root, object);
  }

  public void remove(int object) {
    root = remove(root, object);
  }

  public BTNode remove(BTNode node, int object) {
    if (node == null) {
      return null;
    }

    if (node.object > object) {
      node.left_Node = remove(node.left_Node, object);
    } else if (node.object < object) {
      node.right_Node = remove(node.right_Node, object);
    } else {
      if (node.right_Node == null) {
        return node.left_Node;
      }
      if (node.left_Node == null) {
        return node.right_Node;
      }
      node.object = minVal(node.right_Node);
      node.right_Node = remove(node.right_Node, node.object);
    }
    return node;

  }

  private int minVal(BTNode right_Node) {
    if (right_Node.left_Node == null) {
      return right_Node.object;
    } else {
      return minVal(right_Node.left_Node);
    }
  }

  public boolean contains(BTNode node, int object) {
    if (node == null) {
      return false;
    }
    if (node.object > object) {
      return contains(node.left_Node, object);
    } else if (node.object < object) {
      return contains(node.right_Node, object);
    } else {
      return true;
    }

  }

  public boolean contains(int object) {
    return contains(root, object);
  }

  public void removeKthLargest(int kVal) {
    int valToRemove = getKlargestVal(kVal);
    remove(valToRemove);
  }

  public int getKlargestVal(int kVal) {
    if (root == null) {
      System.out.println("Error empty root");
      System.exit(1);
      return -1;
    }
    GenericDeque<BTNode> stackDeque = new GenericDeque<BTNode>();

    BTNode temp = root;

    while (!stackDeque.isEmpty() || temp != null) {
      if (temp != null) {
        stackDeque.addFirst(temp);
        temp = temp.right_Node;
      } else {
        temp = stackDeque.removeFirst();
        kVal = kVal - 1;
        if (kVal == 0) {
          return temp.object;
        }
        temp = temp.left_Node;
      }
    }

    System.out.println("Error value input larger than root iself");
    System.exit(1);
    return -1;
  }

  public Iterator<BTNode> postIterator() {
    return new postIterator();
  }

  public Iterator<BTNode> preIterator() {
    return new preIterator();
  }

  public Iterator<BTNode> inorderIterator() {
    return new inorderIterator();
  }

  /*
   * pre: root, root.left, root.left.left, root.left.right, root.left.left.left
   * ... then root.right root.right.left root.right.right root.right.right.left
   * ...
   */

  private class preIterator implements Iterator<BTNode> {

    GenericDeque<BTNode> stackDeque;

    public preIterator() {
      stackDeque = new GenericDeque<BTNode>();
      stackDeque.addFirst(root);
    }

    public void recur(BTNode node) {
      stackDeque.addFirst(node);
    }

    @Override
    public boolean hasNext() {
      return !stackDeque.isEmpty();
    }

    @Override
    public BTNode next() {
      BTNode tempNod = stackDeque.removeFirst();
      if (tempNod.right_Node != null) {
        recur(tempNod.right_Node);
      }
      if (tempNod.left_Node != null) {
        recur(tempNod.left_Node);
      }
      return tempNod;
    }

  }
  /*
   * post: bottom left.left, bottom left.right, parent, bottom right.left, bottom
   * right.right, parent till root
   */

  private class postIterator implements Iterator<BTNode> {

    GenericDeque<BTNode> stackDeque;

    public postIterator() {
      stackDeque = new GenericDeque<BTNode>();
      recur(root);
    }

    public void recur(BTNode node) {
      while (node != null) {
        stackDeque.addFirst(node); // adds root, root.left, root.left.left ...
        if (node.left_Node != null) {
          node = node.left_Node;
        } else {
          node = node.right_Node;
        }
      }

    }

    @Override
    public boolean hasNext() {
      return !stackDeque.isEmpty();
    }

    @Override
    public BTNode next() {
      if (!stackDeque.isEmpty()) {
        BTNode tempNod = stackDeque.removeFirst();
        if (!stackDeque.isEmpty()) {
          if (tempNod == stackDeque.checkTop().left_Node) { // since provide
            recur(stackDeque.checkTop().right_Node);
          }
        }
        return tempNod;
      }
      System.out.println("error");
      return null;
    }

  }
  /*
   * In order : take away is that it takes the smallest value until it cannot find
   * more.
   */

  private class inorderIterator implements Iterator<BTNode> {
    GenericDeque<BTNode> stackDeque;

    public inorderIterator() {
      stackDeque = new GenericDeque<BTNode>();
      recursiveMin(root);
    }

    public void recursiveMin(BTNode node) {
      while (node != null) {
        stackDeque.addFirst(node); // adds root, root.left, root.left.left ...
        node = node.left_Node;
      }
    }

    @Override
    public boolean hasNext() {
      return !stackDeque.isEmpty();
    }

    @Override
    public BTNode next() {
      if (!stackDeque.isEmpty()) {
        BTNode tempNod = stackDeque.removeFirst();
        if (tempNod.right_Node != null) {
          recursiveMin(tempNod.right_Node);
        }
        return tempNod;
      }
      System.out.println("error");
      return null;
    }

  }

}
