package com.a2.LinkedLists;

public class NewLinkedListReversed {
  private Node listHead;

  public NewLinkedListReversed() {
    this.listHead = null;
  }

  public void appendToList(int value_Node) {
    if (listHead == null) {
      listHead = new Node(value_Node);
    } else {
      Node tempNode = listHead;
      while (tempNode.next != null) {
        tempNode = tempNode.next;
      }
      tempNode.next = new Node(value_Node);
    }
  }

  /* we want to replace where the pointers point */
  public void reverseThisList() {
    Node currNode = listHead;
    Node storeNodeBefore = null;
    Node next = null;
    while (currNode != null) {
      next = currNode.next;
      currNode.next = storeNodeBefore;
      storeNodeBefore = currNode;
      currNode = next;
    }

    listHead = storeNodeBefore;
  }

  public String fullList() {
    Node temp = this.listHead;
    String s = "";
    while (temp != null) {
      s += String.valueOf(temp.valueInside);
      s += "|";
      temp = temp.next;
    }
    return s;
  }

}
