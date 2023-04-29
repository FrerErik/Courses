package com.a2.BinarySearchTree;

public class BTNode {
  public int object;
  public BTNode left_Node;
  public BTNode right_Node;

  public BTNode(int object) {
    this(object, null, null);
  }

  public BTNode(int object, BTNode left_Node, BTNode right_Node) {
    this.object = object;
    this.left_Node = left_Node;
    this.right_Node = right_Node;
  }
}
