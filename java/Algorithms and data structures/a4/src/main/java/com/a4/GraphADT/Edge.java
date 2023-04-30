package com.a4.GraphADT;

public class Edge implements Comparable<Edge> {
  public int v;
  public int w;
  public float weight;

  public boolean it(Edge other) {
    return this.weight < other.weight;
  }

  public String print() {
    return "Edge(" + this.v + ", " + this.w + ", " + this.weight + ")";
  }

  @Override
  public int compareTo(Edge o) {
    return (int) (this.weight - o.weight);
  }

}
