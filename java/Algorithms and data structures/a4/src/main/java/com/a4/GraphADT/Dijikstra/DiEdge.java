package com.a4.GraphADT.Dijikstra;

import com.a4.GraphADT.Edge;

public class DiEdge extends Edge {

  public DiEdge(int v, int w, float weight) {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  /**
   * @return int
   */
  public int src() {
    return this.v;
  }

  /**
   * @return int
   */
  public int dst() {
    return this.w;
  }

  /**
   * @return float
   */
  public float weight() {
    return this.weight;
  }

}
