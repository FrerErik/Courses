package com.a4.GraphADT;

import java.util.ArrayList;
import java.util.List;

public class GeneralBFS {
  GeneralGraphFunctions g;
  int s;
  boolean[] marked;
  public int[] edge_to;

  public GeneralBFS(GeneralGraphFunctions g, int s) {
    this.g = g;
    this.s = s;
    this.marked = new boolean[g.getV()];
    this.edge_to = new int[g.getV()];
    bfs(s);
  }

  public void bfs(int v) {
    List<Integer> q = new ArrayList<Integer>();
    q.add(0, v);
    this.marked[v] = true;
    while (!q.isEmpty()) {
      int vv = q.get(q.size() - 1);
      q.remove(q.size() - 1);
      for (Integer[] w : g.adj(vv)) {
        if (!marked[w[0]]) {
          q.add(0, w[0]);
          this.marked[w[0]] = true;
          this.edge_to[w[0]] = vv;
        }
      }
    }
  }

  public boolean has_path_to(int v) {
    return this.marked[v];
  }

  public List<Integer> path_to(int v) {
    if (!has_path_to(v)) {
      return null;
    }
    int x = v;
    List<Integer> path = new ArrayList<Integer>();
    while (x != this.s) {
      path.add(0, x);
      x = this.edge_to[x];
    }
    path.add(0, this.s);
    return path;

  }

}
