/* 
 * File: AdjacencyMatrix.java
 * Date: Spring 2022
 * Auth: Dustin Cassell
 * Desc: 
 */


/* TODO: 
 *   (1) Implement the functions below. Note some are "stubbed" out so
 *       that the code will compile. You will need to implement all of
 *       the functions (even those stubbed out).
 *   (2) Make sure your code is commented and well formatted
 *   (3) Fill out the file header above
 */ 

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AdjacencyMatrix<T> implements Graph<T> {

  // the total number of nodes in the graph (numbered 0 to nodes-1)
  private int nodeCount;

  // the total number of edges in the graph 
  private int edgeCount;

  // true if the graph is directed, false if undirected
  private boolean directed;

  // the adjacency matrix, where an edge from i to j exists if
  // matrix[i][j] is not null
  private T[][] matrix;


  //--------------------------------------------------------------------
  // constructor
  //--------------------------------------------------------------------

  // Initializes the graph with the given number of nodes and the
  // given directionality.
  public AdjacencyMatrix(int nodeCount, boolean directed) {
    this.nodeCount = nodeCount;
    this.directed = directed;
    matrix = (T[][])(new Object[nodeCount][nodeCount]);
  }


  //--------------------------------------------------------------------
  // graph methods
  //--------------------------------------------------------------------

  // Returns true if the graph is directed and false if undirected.
  public boolean directed() {
    return false;
  }

  // Adds a labeled edge to the graph from node1 to node2. Only adds
  // the edge if valid nodes are given and the edge doesn't already
  // exist.
  public void add(int node1, T label, int node2) {
  }

  // Removes an edge from the graph if it exists
  public void remove(int node1, int node2) {
  }

  // Updates the label for a given edge. If the edge does not exist,
  // the edge is not added.
  public void set(int node1, T label, int node2) {
  }

  // Returns the label on the give edge.
  // Pre: the edge exists
  public T label(int node1, int node2) {
    return null;
  }

  // Returns true if the graph contains an edge from node1 to node2
  // and false otherwise. Note that if the graph is undirected, no
  // directionality is implied by the edge.
  public boolean hasEdge(int node1, int node2) {
    return false;
  }

  // Returns all nodes adjacent to the given node. In a directed
  // graph, this includes the set of nodes on outgoing and incoming
  // edges, i.e., that lie on out-edges and in-edges. 
  public List<Integer> adjacent(int node) {
    return null;
  }

  // Returns all nodes on an outgoing edge (out-edge) from the given
  // node. For an undirected graph, this method returns the same nodes
  // as adjacent.
  public List<Integer> outNodes(int node) {
    return null;
  }

  // Returns all nodes on an incoming edge (in-edge) from the given
  // node. For an undirected graph, this method returns the same nodes
  // as adjacent. 
  public List<Integer> inNodes(int node) {
    return null;
  }

  // Returns the number of nodes in the graph
  public int nodeCount() {
    return 0;
  }

  // Returns the number of edges in the graph. In a directed graph, it
  // returns the total number of directed edges. In an undirected
  // graph, it returns the number of undirected edges.
  public int edgeCount() {
    return 0;
  }

}