/*
 * File: HW1.java
 * Date: Spring 2022
 * Auth: S. Bowers
 * Desc: Basic performance test runner for HW1. Note there are some
 *       additional helper functions for printing graphs (in graphviz
 *       format) and doing a basic stress test.
 */

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class HW1 {
  public static int SEED = 2;
  public static int RUNS = 7;


  // singleton
  private HW1() {
  }


  // total load time (as a double value)
  public static double timeLoadSparse(Graph<Integer> g) {
    Instant start = Instant.now(); 
    GraphGenerator.loadSparse(g, SEED); 
    Instant end = Instant.now(); 
    return ChronoUnit.MICROS.between(start, end);
  }


  // average time to compute in nodes (per node)
  public static double timeInNodes(Graph<Integer> g) {
    Instant start = Instant.now(); 
    for (int i = 0; i < g.nodeCount(); ++i)
      g.inNodes(i);
    Instant end = Instant.now(); 
    return ChronoUnit.MICROS.between(start, end)/((double)g.nodeCount());
  }


  // average time to compute out nodes (per node)
  public static double timeOutNodes(Graph<Integer> g) {
    Instant start = Instant.now(); 
    for (int i = 0; i < g.nodeCount(); ++i)
      g.outNodes(i);
    Instant end = Instant.now(); 
    return ChronoUnit.MICROS.between(start, end)/((double)g.nodeCount());
  }


  // how large until stack space exception
  public static void stressTests() {
    int step = 10000;
    int n = 10000;
    System.out.println("Adjacency Matrix:");
    try {
      while (true) {
        System.out.println("  Trying n = " + n);
        Graph<Integer> g = new AdjacencyMatrix(n, true);
        n += step;
      }
    } catch(OutOfMemoryError ex) {}
    System.out.println("  Failed to create graph with n = " + n);
    step = 10000000;
    n = 10000000;
    System.out.println("\nAdjacency List:");
    try {
      while (true) {
        System.out.println("  Trying n = " + n);
        Graph<Integer> g = new AdjacencyList(n, true);
        // add O(n) edges
        for (int u = 0; u < (n - 1); ++u)
          g.add(u, 0, u+1);
        n += step;
      }
    } catch(OutOfMemoryError ex) {
    }
    System.out.println("  Failed to create graph with n = " + n);
  }


  // basic perf tests
  public static void performanceTests() {
    int start = 5; // must be > 0
    int step = 2;  
    int end = 20480;

    // print header
    System.out.println("# All times in microseconds");
    System.out.println("# Column 1: input (node) size n");
    System.out.println("# Column 2: adj matrix total load time");
    System.out.println("# Column 3: adj matrix avg out nodes");
    System.out.println("# Column 4: adj matrix avg in nodes");
    System.out.println("# Column 5: adj list total load time");
    System.out.println("# Column 6: adj list avg out nodes");
    System.out.println("# Column 7: adj list avg in nodes");

    for (int n = start; n <= end; n *= step) {
      // directed adjacency matrix
      Graph<Integer> g = new AdjacencyMatrix(n, true);
      double c2 = timeLoadSparse(g);
      double c3 = timeOutNodes(g);
      double c4 = timeInNodes(g);
      // directed adjacency list
      g = new AdjacencyList(n, true);
      double c5 = timeLoadSparse(g);
      double c6 = timeOutNodes(g);
      double c7 = timeInNodes(g);

      // skip start value (caching, etc)
      if (n == start)
        continue;

      // print results
      System.out.printf("%d %.2f %.2f %.2f %.2f %.2f %.2f\n",
                        n, c2, c3, c4, c5, c6, c7);
    }
  }


  // helper to save graph to a dotfile
  // to generate graph: dot -Tpdf filename.dot > filename.pdf
  public static void save(Graph<Integer> g, String filename) throws Exception {
    FileWriter f = new FileWriter(filename);
    PrintWriter out = new PrintWriter(new BufferedWriter(f));
    int n = g.nodeCount();
    if (g.directed()) {
      out.println("digraph {");
      out.println(" rankdir=LR;");
      for (int i = 0; i < n; ++i) 
        for (int j = 0; j < n; ++j) 
          if (g.hasEdge(i, j))
            out.println(" " + i + " -> " + j + " [label=" + g.label(i, j) + "];");
      out.println("}");
    }
    else {
      out.println("graph {");
      out.println(" rankdir=LR;");
      for (int i = 0; i < n; ++i) 
        for (int j = i + 1; j < n; ++j) 
          if (g.hasEdge(i, j))
            out.println(" " + i + " -- " + j + " [label=" + g.label(i, j) + "];");
      out.println("}");
    }
    out.close();
  }

  // test driver  
  public static void main(String[] args) throws Exception {
    performanceTests();
  }


}