package com.a4.GraphADT.Dijikstra;

import java.util.ArrayList;
import java.util.List;

public class EWDiGraph {
  private ArrayList<List<DiEdge>> listOfListOfEdges;
  private int v;

  public EWDiGraph(int v) {
    this.v = v;
    this.listOfListOfEdges = new ArrayList<>();
    for (int i = 0; i < v; i++) {
      listOfListOfEdges.add(new ArrayList<DiEdge>());
    }
  }

  public int getV() {
    return this.v;
  }

  public int getE() {
    int total = 0;
    for (List<DiEdge> i : listOfListOfEdges) {
      total += i.size();
    }
    return total;
  }

  public void add_edge(DiEdge edge) {
    listOfListOfEdges.get(edge.src()).add(edge);
  }

  public List<DiEdge> adj(int v) {
    return listOfListOfEdges.get(v);
  }

  public List<DiEdge> edges() {
    List<DiEdge> edges = new ArrayList<>();
    for (List<DiEdge> l : listOfListOfEdges) {
      for (DiEdge v : l) {
        edges.add(v);
      }
    }
    return edges;
  }

}
