package com.a2.P4DirectoryFinder;

public class LeftRightSiblingTreeNode<obj> {
  public obj objectToBeInserted;
  public LeftRightSiblingTreeNode<obj> left_Area;
  public LeftRightSiblingTreeNode<obj> right_Area;
  public String pathToObject;

  public LeftRightSiblingTreeNode(obj objectToBeInserted) {
    this.objectToBeInserted = objectToBeInserted;
    this.left_Area = null;
    this.right_Area = null;
    this.pathToObject = null;
  }

  public void walkThrough() {
    System.out.println(objectToBeInserted);
    if (left_Area != null) {
      left_Area.walkThrough();
    }

    if (right_Area != null) {
      right_Area.walkThrough();
    }
  }

  public LeftRightSiblingTreeNode<obj> appendChildToThisTree(obj objectToBeInserted, String pathToObject) {

    if (left_Area == null) {
      left_Area = new LeftRightSiblingTreeNode<obj>(objectToBeInserted);
      left_Area.pathToObject = pathToObject;
      return left_Area;
    } else {
      LeftRightSiblingTreeNode<obj> tempNode = left_Area;
      while (tempNode.right_Area != null) {
        tempNode = tempNode.right_Area;
      }
      tempNode.right_Area = new LeftRightSiblingTreeNode<obj>(objectToBeInserted);
      tempNode.right_Area.pathToObject = pathToObject;
      return tempNode.right_Area;
    }
  }

  public void walkAndSearchThrough(String file) {

    if (((String) objectToBeInserted).contains(file)) {
      System.out.println("File: " + file + " found in the following directory: " + this.pathToObject);
    }

    if (left_Area != null) {
      left_Area.walkAndSearchThrough(file);
    }

    if (right_Area != null) {
      right_Area.walkAndSearchThrough(file);
    }

  }

}
