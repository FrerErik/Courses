package com.a4.GraphADT.Dijikstra;

import com.a4.ReusedClasses.MinHeap;

public class Dijkstra {
  DiEdge[] edge_to;
  public Float[] dist_to;
  MinHeap pq;

  public Dijkstra(EWDiGraph g, int s) {
    this.edge_to = new DiEdge[g.getV()];
    this.dist_to = new Float[g.getV()];
    for (int i = 0; i < this.dist_to.length; i++) {
      this.dist_to[i] = Float.POSITIVE_INFINITY;
    }
    this.dist_to[s] = (float) s;
    pq = new MinHeap(1);

    pq.add(new DiEdge(s, Integer.MAX_VALUE, 0.0f));
    while (pq.getHeapSize() > 0) {
      DiEdge e = (DiEdge) pq.pop();
      int v = e.v;
      for (DiEdge w : g.adj(v)) {
        relax(w);
      }
    }
  }

  /**
   * use relax on edge
   * 
   * @param e
   */
  public void relax(DiEdge e) {
    if (dist_to[e.dst()] > (dist_to[e.src()] + e.weight)) {
      dist_to[e.dst()] = dist_to[e.src()] + e.weight;
      edge_to[e.dst()] = e;
      for (int i = 0; i < pq.heap.length; i++) {
        if (pq.heap[i].v == e.dst()) {
          pq.heap[i] = new DiEdge(e.dst(), Integer.MAX_VALUE, dist_to[e.dst()]);
          pq.pD();
          break;
        } else {
          pq.add(new DiEdge(e.dst(), Integer.MAX_VALUE, dist_to[e.dst()]));
        }
      }
    }
  }
}
