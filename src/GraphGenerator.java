/*
 * File: GraphGenerator.java
 * Date: Spring 2022
 * Auth: S. Bowers
 * Desc: Basic helper functions for generating random graphs. 
 */

import java.util.Random;


public class GraphGenerator {

  // singleton class
  private GraphGenerator() {
  }

  // Add edges (at random) to the given graph g with given random
  // number seed.
  // pre: g is a graph with n nodes and no edges
  // post: g has O(n) labeled edges (g is sparse)
  public static void loadSparse(Graph<Integer> g, long seed) {
    Random random = new Random(seed); 
    int n = g.nodeCount();         
    // number of final edges to try
    int e = n + random.nextInt(n);
    // construct e random edges
    for (int i = 0; i < e; ++i) {
      int v1 = random.nextInt(n);
      int v2 = random.nextInt(n);
      g.add(v1, i, v2);
    }
  }

  // Add edges (at random) to the given graph g with given random
  // number seed. If graph is undirected, add edges such that the
  // resulting undirected graph is bipartite.
  // pre: g is a graph with n nodes and no edges
  // post: g has O(n) labeled edges (g is sparse)
  public static void loadSparseAcyclic(Graph<Integer> g, long seed) {
    Random random = new Random(seed); 
    int n = g.nodeCount();         
    if (g.directed()) {
      // number of final edges to try (n <= e <= 2n)
      int e = n + random.nextInt(n);
      for (int i = 0; i < e; ++i) {
        int v1 = random.nextInt(n);
        int v2 = random.nextInt(n);
        // only "forward" edges
        if (v1 < v2) 
          g.add(v1, i, v2);
        else
          g.add(v2, i, v1);
      }
    }
    else {
      // create a bipartite graph
      int j = 0;
      // guess 2n edges
      for (int i = 0; i < 2*n; ++i) {
        int u = random.nextInt(n/2);
        int v = n/2 + random.nextInt(n - (n/2));
        g.add(u, j++, v);
      }
    }
  }


  // ... FOR THESE: USE ERDOS-REMYI ALGORITHM


  // Add edges (at random) to the given graph g with given random
  // number seed. Uses the Erdos-Renyi approach of a (random) edge
  // probability. 
  // pre: g is a directed graph with n nodes and no edges
  // post: g has O(n^2) labeled edges (dense)
  public static void loadDense(Graph<Integer> g, long seed) {
    Random random = new Random(seed); 
    int n = g.nodeCount();
    // probability edge selected: .8 <= p <= 1.0
    double p = 1 - (random.nextDouble() / 5);
    // construct random edges
    int k = 0; 
    for (int i = 0; i < n; ++i) 
      for (int j = 0; j < n; ++j) 
        if (random.nextDouble() <= p)
          g.add(i, k++, j);
  }

  // Add (random) edges to the given graph, where the seed allows for
  // regeneration of the graph.
  // pre: g is a directed graph with n nodes and no edges
  // post: g has O(n^2) directed, labeled edges with no cycles
  public static void loadDenseAcyclic(Graph<Integer> g, long seed) {
    Random random = new Random(seed); 
    int n = g.nodeCount();
    // probability edge selected: .8 <= p <= 1.0
    double p = 1 - (random.nextDouble() / 5);
    // construct  random edges
    int k = 0; 
    if (g.directed()) {
      for (int i = 0; i < n; ++i) 
        for (int j = i + 1; j < n; ++j) 
          if (random.nextDouble() <= p)
            g.add(i, k++, j);
    }
    else {
      int m = n/2;
      for (int i = 0; i < m; ++i)
        for (int j = m+1; j < n; ++j)
          if (random.nextDouble() <= p)
            g.add(i, k++, j);
    }
  }

}