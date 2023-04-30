package com.a4.GraphADT.Undirected;

import java.util.ArrayList;
import java.util.List;

import com.a4.GraphADT.GeneralGraphFunctions;

public class UndirectedGraph extends GeneralGraphFunctions {

  /**
   * gets total edges
   * 
   * @return int edges
   */
  public int getE() {
    int total = 0;
    for (List<Integer[]> i : listOfListOfEdges) {
      total += i.size();
    }
    return total / 2;
  }

  public UndirectedGraph(int v) {
    listOfListOfEdges = new ArrayList<>();
    for (int i = 0; i < v; i++) {
      listOfListOfEdges.add(new ArrayList<Integer[]>());
    }
  }

  /**
   * adds vertex 1 to vertex 2
   * 
   * @param v vertex 1
   * @param w vertex 2
   */
  public void add_edge(int v, int w) {
    add_edge(v, w, 1);
  }

  /**
   * @param v
   * @param w
   * @param weight
   */
  public void add_edge(int v, int w, int weight) {
    if (v < getV() && w < getV()) {
      listOfListOfEdges.get(v).add(new Integer[] { w, weight });
      listOfListOfEdges.get(w).add(new Integer[] { v, weight });
    }
  }

  /**
   * Gets the mean degree
   * 
   * @return int
   */
  public double meanDegree() {
    return (double) 2 * this.getE() / this.getV();
  }

  public void removeEdge(int v, int w) {
    if (hasEdge(v, w)) {
      List<Integer[]> edgeTo = listOfListOfEdges.get(v);
      List<Integer[]> edgeFrom = listOfListOfEdges.get(w);
      for (int i = 0; i < edgeFrom.size(); i++) {
        if (edgeFrom.get(i)[0] == v) {
          listOfListOfEdges.get(w).remove(i);
        }
      }

      for (int i = 0; i < edgeTo.size(); i++) {
        if (edgeTo.get(i)[0] == w) {
          listOfListOfEdges.get(v).remove(i);
        }
      }

    } else {
      System.out.println("edge not Found");
    }
  }

}
