package com.a4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.a4.GraphADT.Undirected.UndirectedGraph;
import com.a4.GraphADT.GeneralBFS;
import com.a4.GraphADT.GeneralDFS;
import com.a4.GraphADT.Prerequisites;
import com.a4.GraphADT.Dijikstra.Edge;
import com.a4.GraphADT.Dijikstra.Dijkstra;
import com.a4.GraphADT.Dijikstra.EWDiGraph;
import com.a4.GraphADT.Directed.DFO;
import com.a4.GraphADT.Directed.DirectedGraph;
import com.a4.GraphADT.Kruskal.EWGraph;
import com.a4.GraphADT.Kruskal.EdgeMST;
import com.a4.GraphADT.Kruskal.Kruskal;

public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    undirectedGraph(); // P1 & P2
    directedGraph(); // P1 & P2
    // KruskalTests(); // P3
    // DijkstraTests(); // P4
    // rpoTest(); // P5
    // P5("data.txt"); // P5
  }

  private static void P5(String path) {
    Prerequisites pr = new Prerequisites(path);
    System.out.println("vertices key");
    for (int i = 0; i < pr.getSubstituteVertices().size(); i++) {
      System.out.println(pr.getVertices().get(i) + "|" +
          pr.getSubstituteVertices().get(i));
    }

    for (int i = 0; i < pr.getEdges().size(); i++) {
      System.out.println(Arrays.toString(pr.getEdges().get(i)) + "|" +
          Arrays.toString(pr.getSubstituteEdges().get(i)));
    }

    DirectedGraph dg = new DirectedGraph(pr.getVertices().size());

    for (Integer[] temp : pr.getSubstituteEdges()) {
      dg.add_edge(temp[0], temp[1]);
    }

    DFO dfo = new DFO(dg);
    List<String> reversePostTranslated = new ArrayList<String>();
    for (int i : dfo.reverse_post()) {
      reversePostTranslated.add(pr.getVertices().get(i));
    }
    System.out.println("reverse post: ");
    System.out.println(reversePostTranslated);

    System.out.println("order to take courses while fullfilling prerequisites");
  }

  private static void rpoTest() {
    DirectedGraph dg = new DirectedGraph(7);
    dg.add_edge(0, 1);
    dg.add_edge(0, 2);
    dg.add_edge(0, 5);
    dg.add_edge(1, 4);
    dg.add_edge(3, 2);
    dg.add_edge(3, 4);
    dg.add_edge(3, 5);
    dg.add_edge(3, 6);
    dg.add_edge(5, 2);
    dg.add_edge(6, 0);
    dg.add_edge(6, 4);

    DFO dfo = new DFO(dg);
    System.out.println(dfo.reverse_post());
  }

  private static void DijkstraTests() {
    EWDiGraph dg = new EWDiGraph(8);
    Edge[] edges = new Edge[] { new Edge(0, 1, 5), new Edge(0, 3, 8), new Edge(0, 6, 9), new Edge(1, 2, 15),
        new Edge(1, 3, 4), new Edge(1, 4, 12), new Edge(2, 7, 9), new Edge(3, 4, 7), new Edge(3, 5, 6),
        new Edge(4, 2, 3), new Edge(4, 7, 11), new Edge(5, 4, 1), new Edge(5, 7, 13), new Edge(6, 3, 5),
        new Edge(6, 5, 4), new Edge(6, 7, 20) };
    for (Edge edge : edges) {
      dg.add_edge(edge);
    }
    Dijkstra spd = new Dijkstra(dg, 0);
    System.out.println(Arrays.toString(spd.dist_to));
  }

  private static void KruskalTests() {
    EWGraph g = new EWGraph(8);
    EdgeMST[] edges = new EdgeMST[] { new EdgeMST(0, 1, 7), new EdgeMST(0, 3, 6), new EdgeMST(0, 6, 9),
        new EdgeMST(1, 2, 5),
        new EdgeMST(1, 3, 2), new EdgeMST(1, 4, 10), new EdgeMST(2, 4, 4), new EdgeMST(2, 7, 13), new EdgeMST(3, 5, 1),
        new EdgeMST(3, 6, 12), new EdgeMST(4, 5, 3), new EdgeMST(4, 7, 8), new EdgeMST(5, 6, 14), new EdgeMST(5, 7, 16),
        new EdgeMST(6, 7, 20) };
    for (EdgeMST edge : edges) {
      g.add_edge(edge);
    }
    Kruskal mst = new Kruskal(g);
    for (EdgeMST e : mst.edges()) {
      System.out.println(e.print());
    }
    System.out.println(mst.weight());
  }

  private static void undirectedGraph() {
    UndirectedGraph gU = new UndirectedGraph(15);
    gU.add_edge(0, 2);
    gU.add_edge(2, 3);
    gU.add_edge(1, 3);
    gU.add_edge(3, 4);
    gU.add_edge(4, 5);
    gU.add_edge(4, 6);
    gU.add_edge(5, 6);
    gU.add_edge(6, 7);
    gU.add_edge(7, 8);
    gU.add_edge(8, 9);
    gU.add_edge(9, 10);
    gU.add_edge(10, 6);
    gU.add_edge(11, 12);
    gU.add_edge(12, 13);
    gU.add_edge(12, 14);
    gU.add_edge(13, 14);
    Iterator<List<Integer[]>> vertexIterator = gU.vertexIterator();
    while (vertexIterator.hasNext()) {
      System.out.println(vertexIterator.next());
    }

    Iterator<Integer[]> edgeIterator = gU.edgeIterator();
    while (edgeIterator.hasNext()) {
      System.out.println(Arrays.toString(edgeIterator.next()));
    }

    System.out.println("Edges found in vertex 4");

    Iterator<Integer[]> vertex4Edges = gU.adjacencyIterator(4);

    while (vertex4Edges.hasNext()) {
      System.out.println(Arrays.toString(vertex4Edges.next()));
    }

    System.out.println("removing [5, 1] in 4");

    gU.removeEdge(4, 5);

    vertex4Edges = gU.adjacencyIterator(4);

    while (vertex4Edges.hasNext()) {
      System.out.println(Arrays.toString(vertex4Edges.next()));
    }
    System.out.println("degree: " + gU.degree(4));

    System.out.println("adding it back");
    gU.add_edge(4, 5);
    vertex4Edges = gU.adjacencyIterator(4);

    while (vertex4Edges.hasNext()) {
      System.out.println(Arrays.toString(vertex4Edges.next()));
    }

    System.out.println("degree: " + gU.degree(4));

    System.out.println("dfs----------------------------------------------------------------");
    GeneralDFS dfs = new GeneralDFS(gU, 3);
    System.out.println(dfs.has_path_to(0));
    System.out.println(dfs.has_path_to(1));
    System.out.println(dfs.has_path_to(2));

    System.out.println("bfs----------------------------------------------------------------");

    GeneralBFS bfs = new GeneralBFS(gU, 3); //
    System.out.println(bfs.has_path_to(5));
    System.out.println(bfs.has_path_to(11));
    System.out.println(bfs.path_to(8));
    System.out.println(Arrays.toString(bfs.edge_to));
  }

  private static void directedGraph() {
    DirectedGraph gU = new DirectedGraph(15);
    gU.add_edge(0, 2);
    gU.add_edge(2, 3);
    gU.add_edge(1, 3);
    gU.add_edge(3, 4);
    gU.add_edge(4, 5);
    gU.add_edge(4, 6);
    gU.add_edge(5, 6);
    gU.add_edge(6, 7);
    gU.add_edge(7, 8);
    gU.add_edge(8, 9);
    gU.add_edge(9, 10);
    gU.add_edge(10, 6);
    gU.add_edge(11, 12);
    gU.add_edge(12, 13);
    gU.add_edge(12, 14);
    gU.add_edge(13, 14);

    Iterator<List<Integer[]>> vertexIterator = gU.vertexIterator(); // makes sense either 1 or 2 edges can be seen in
                                                                    // the picture and 14 is the only one that does not
                                                                    // point to anything
    while (vertexIterator.hasNext()) {
      System.out.println(vertexIterator.next());
    }

    Iterator<Integer[]> edgeIterator = gU.edgeIterator();
    while (edgeIterator.hasNext()) {
      System.out.println(Arrays.toString(edgeIterator.next()));
    }

    System.out.println("Edges found in vertex 4");

    Iterator<Integer[]> vertex4Edges = gU.adjacencyIterator(4);

    while (vertex4Edges.hasNext()) {
      System.out.println(Arrays.toString(vertex4Edges.next()));
    }

    System.out.println("removing [5, 1] in 4");

    gU.removeEdge(4, 5);

    vertex4Edges = gU.adjacencyIterator(4);

    while (vertex4Edges.hasNext()) {
      System.out.println(Arrays.toString(vertex4Edges.next()));
    }
    System.out.println("degree: " + gU.degree(4));

    System.out.println("adding it back");

    gU.add_edge(4, 5);
    vertex4Edges = gU.adjacencyIterator(4);

    while (vertex4Edges.hasNext()) {
      System.out.println(Arrays.toString(vertex4Edges.next()));
    }

    System.out.println("degree: " + gU.degree(4));

    System.out.println("dfs----------------------------------------------------------------");

    GeneralDFS dfs = new GeneralDFS(gU, 3);
    System.out.println(dfs.has_path_to(0));
    System.out.println(dfs.has_path_to(1));
    System.out.println(dfs.has_path_to(2));
    System.out.println(dfs.path_to(2));

    System.out.println("bfs ----------------------------------------------------------------");

    GeneralBFS bfs = new GeneralBFS(gU, 3); // TODO: fix
    System.out.println(bfs.has_path_to(5));
    System.out.println(bfs.has_path_to(11));
    System.out.println(bfs.path_to(8));
    System.out.println(Arrays.toString(bfs.edge_to));
  }
}
