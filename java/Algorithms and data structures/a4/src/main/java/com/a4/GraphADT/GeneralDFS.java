package com.a4.GraphADT;

import java.util.ArrayList;
import java.util.List;

public class GeneralDFS {
  public GeneralGraphFunctions g;
  public int s;
  boolean[] marked;
  int[] edge_to;

  public GeneralDFS(GeneralGraphFunctions g, int s) {
    this.g = g;
    this.s = s;
    this.marked = new boolean[g.getV()];
    this.edge_to = new int[g.getV()];
    dfs(s);
  }

  public void dfs(int v) {
    this.marked[v] = true;
    for (Integer[] i : this.g.adj(v)) {
      if (!marked[i[0]]) {
        dfs(i[0]);
        this.edge_to[i[0]] = v;
      }
    }
  }

  public boolean has_path_to(int v) {
    return this.marked[v];
  }

  public List<Integer> path_to(int v) {
    if (!this.has_path_to(v)) {
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
