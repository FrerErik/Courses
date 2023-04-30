package com.a4.GraphADT.Kruskal;

import java.util.ArrayList;
import java.util.List;

import com.a4.ReusedClasses.MinHeap;
import com.a4.ReusedClasses.QuickFind;

public class Kruskal {
  List<EdgeMST> mst;
  MinHeap pq;
  QuickFind quf;

  public Kruskal(EWGraph g) {
    this.mst = new ArrayList<>();
    this.pq = new MinHeap(1);
    this.quf = new QuickFind(g.getV());
    for (EdgeMST e : g.edges()) {
      pq.add(e);
    }

    while ((pq.getHeapSize() > 0) && (mst.size() < g.getV() - 1)) {
      EdgeMST e = (EdgeMST) pq.pop();
      int v = e.either();
      int w = e.other(v);
      if (!quf.isConnected(v, w)) {
        quf.union(v, w);
        mst.add(e);
      }
    }

  }

  public List<EdgeMST> edges() {
    return mst;
  }

  public float weight() {
    float weight = 0.0f;

    for (EdgeMST e : mst) {
      weight += e.weight;
    }
    return weight;
  }

}
