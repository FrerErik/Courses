package com.a4.GraphADT.Kruskal;

import java.util.ArrayList;
import java.util.List;

public class EWGraph {
  private ArrayList<List<EdgeMST>> listOfListOfEdges;
  private int v;

  public EWGraph(int v) {
    this.v = v;
    listOfListOfEdges = new ArrayList<>();
    for (int i = 0; i < v; i++) {
      listOfListOfEdges.add(new ArrayList<EdgeMST>());
    }
  }

  public int getV() {
    return this.v;
  }

  public int getE() {
    int total = 0;
    for (List<EdgeMST> i : listOfListOfEdges) {
      total += i.size();
    }
    return total / 2;
  }

  public void add_edge(EdgeMST edge) {
    int v = edge.either();
    int w = edge.other(v);
    listOfListOfEdges.get(v).add(edge);
    listOfListOfEdges.get(w).add(edge);
  }

  public List<EdgeMST> adj(int v) {
    return listOfListOfEdges.get(v);
  }

  public List<EdgeMST> edges() {
    List<EdgeMST> edges = new ArrayList<>();
    for (List<EdgeMST> l : listOfListOfEdges) {
      for (EdgeMST v : l) {
        edges.add(v);
      }
    }
    return edges;
  }

}
