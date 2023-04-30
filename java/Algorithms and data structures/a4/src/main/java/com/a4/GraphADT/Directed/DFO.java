package com.a4.GraphADT.Directed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFO {
  boolean[] marked;
  List<Integer> post;

  public DFO(DirectedGraph graph) {
    marked = new boolean[graph.getV()];
    for (int i = 0; i < marked.length; i++) {
      marked[i] = false;
    }
    post = new ArrayList<Integer>();

    for (int i = 0; i < graph.getV(); i++) {
      if (!marked[i]) {
        dfs(graph, i);
      }
    }
  }

  /**
   * @param graph
   * @param v
   */
  public void dfs(DirectedGraph graph, int v) {
    marked[v] = true;
    for (Integer[] i : graph.adj(v)) {
      if (!marked[i[0]]) {
        dfs(graph, i[0]);
      }
    }
    post.add(v);
  }

  /**
   * @return List<Integer>
   */
  public List<Integer> reverse_post() {
    Collections.reverse(post);
    return post;
  }
}
