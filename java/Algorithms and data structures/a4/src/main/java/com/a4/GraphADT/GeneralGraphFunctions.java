package com.a4.GraphADT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeneralGraphFunctions {
  public ArrayList<List<Integer[]>> listOfListOfEdges;

  /**
   * get number of vertex
   * 
   * @return int
   */
  public int getV() {
    return this.listOfListOfEdges.size();
  }

  /**
   * Checks adjacent vertices to vertex
   * 
   * @param v vertex
   * @return List<Integer>
   */
  public List<Integer[]> adj(int v) {
    if (v < getV()) {
      return this.listOfListOfEdges.get(v);
    }
    return null;
  }

  /**
   * 
   * Checks if vertex v has an edge to vertex w does not look both ways
   * however it should work if both adts are kept consistent
   * 
   * @param v vertex 1
   * @param w vertex 2
   * @return boolean
   */
  public boolean hasEdge(int v, int w) {
    List<Integer[]> edgeListV = listOfListOfEdges.get(v);
    for (int i = 0; i < edgeListV.size(); i++) {
      Integer[] edge = edgeListV.get(i);
      if (edge[0] == w) {
        return true;
      }
    }
    return false;
  }

  /**
   * Provides max degree value in the graph
   * 
   * @return int
   */
  public int maxDegree() {
    int max = 0;
    for (int v = 0; v < getV(); v++) {
      int tmpDegree = degree(v);
      if (tmpDegree > max) {
        max = tmpDegree;
      }
    }
    return max;
  }

  /**
   * Gets degree of input vertex
   * 
   * @param v vertex
   * @return degree of vertex
   */
  public int degree(int v) {
    int d = 0;
    List<Integer[]> list = listOfListOfEdges.get(v);
    for (int i = 0; i < list.size(); i++) {
      d += 1;
    }
    return d;
  }

  /**
   * @return Iterator<obj> TODO
   */
  public Iterator<Integer[]> edgeIterator() {
    return new EdgeIterator();
  }

  /**
   * @return Iterator<obj> TODO
   */
  public Iterator<List<Integer[]>> vertexIterator() {
    return new VertexIterator();
  }

  /**
   * @return Iterator<obj> TODO
   */
  public Iterator<Integer[]> adjacencyIterator(int v) {
    return new adjacencyIterator(v);
  }

  private class EdgeIterator implements Iterator<Integer[]> { // do later
    List<Integer[]> list;
    int currEdge = 0;

    public EdgeIterator() {
      list = new ArrayList<Integer[]>();
      Iterator<List<Integer[]>> vertexIterator = vertexIterator();
      while (vertexIterator.hasNext()) {
        for (Integer[] v : vertexIterator.next()) {
          list.add(v);
        }
      }
    }

    @Override
    public boolean hasNext() {
      return currEdge < list.size();
    }

    @Override
    public Integer[] next() {

      return list.get(currEdge++);
    }

  }

  private class VertexIterator implements Iterator<List<Integer[]>> {

    private int currVertex = 0;

    @Override
    public boolean hasNext() {
      return listOfListOfEdges.size() > currVertex;
    }

    @Override
    public List<Integer[]> next() {
      return listOfListOfEdges.get(currVertex++);
    }

  }

  private class adjacencyIterator implements Iterator<Integer[]> {

    List<Integer[]> list;
    int currEdge = 0;

    public adjacencyIterator(int v) {
      list = listOfListOfEdges.get(v);

    }

    @Override
    public boolean hasNext() {
      return currEdge < list.size();
    }

    @Override
    public Integer[] next() {
      return list.get(currEdge++);
    }

  }

}
