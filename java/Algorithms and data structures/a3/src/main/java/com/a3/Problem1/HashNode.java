package com.a3.Problem1;

public class HashNode<obj> {
  public obj val;
  public boolean isInside;


  public HashNode(obj val) {
    this(val, true);
  }
  
  public HashNode(obj val, boolean isInside) {
    this.val = val;
    this.isInside = isInside;
  }
}
