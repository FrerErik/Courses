package com.a4.GraphADT.Directed;

import java.util.ArrayList;
import java.util.List;

import com.a4.GraphADT.GeneralGraphFunctions;

public class DirectedGraph extends GeneralGraphFunctions {

  public DirectedGraph(int v) {
    listOfListOfEdges = new ArrayList<>();
    for (int i = 0; i < v; i++) {
      listOfListOfEdges.add(new ArrayList<Integer[]>());
    }
  }

  public int getE() {
    int total = 0;
    for (List<Integer[]> i : listOfListOfEdges) {
      total += i.size();
    }
    return total;
  }

  public void add_edge(int from, int to, int weight) {
    if (from < getV() && to < getV()) {
      listOfListOfEdges.get(from).add(new Integer[] { to, weight });
    }
  }

  public void add_edge(int from, int to) {
    add_edge(from, to, 1);
  }

  public void removeEdge(int from, int to) {
    if (hasEdge(from, to)) {
      List<Integer[]> edgeFrom = listOfListOfEdges.get(from);
      for (int i = 0; i < edgeFrom.size(); i++) {
        if (edgeFrom.get(i)[0] == to) {
          listOfListOfEdges.get(from).remove(i);
        }
      }
    }
  }

  public double meanDegree() {
    return (double) getE() / getV();
  }

}
