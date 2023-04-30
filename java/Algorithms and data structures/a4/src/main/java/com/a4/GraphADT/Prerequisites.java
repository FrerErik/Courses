package com.a4.GraphADT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Prerequisites {
  /*
   * In order to find the path so all prerequisites are filled we require
   * to utilize topological sorting
   * since we know that the graph is directed acyclic
   */

  private List<String[]> edges;
  private List<String> vertices;
  private List<Integer> substituteVertices;
  private List<Integer[]> substituteEdges;

  public List<Integer[]> getSubstituteEdges() {
    return this.substituteEdges;
  }

  public List<Integer> getSubstituteVertices() {
    return this.substituteVertices;
  }

  public List<String[]> getEdges() {
    return this.edges;
  }

  public List<String> getVertices() {
    return this.vertices;
  }

  public Prerequisites(String path) {
    File f = new File(path);
    edges = new ArrayList<String[]>();
    vertices = new ArrayList<String>();
    substituteVertices = new ArrayList<Integer>();
    substituteEdges = new ArrayList<Integer[]>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(f));
      String l;
      while ((l = br.readLine()) != null) {
        edges.add(l.split(";"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (String[] temp : edges) {
      for (int i = 0; i < temp.length; i++) {
        if (!vertices.contains(temp[i])) {
          vertices.add(temp[i]);
        }
      }
    }
    for (int i = 0; i < vertices.size(); i++) {
      substituteVertices.add(i);
    }

    for (String[] temp : edges) {
      Integer[] tempI = new Integer[2];
      for (int i = 0; i < temp.length; i++) {
        tempI[i] = vertices.indexOf(temp[i]);
      }
      substituteEdges.add(tempI);
    }
  }

}
