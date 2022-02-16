/*
 * File: AdjacencyMatrixTest.java
 * Date: Spring 2022
 * Auth: 
 * Desc: 
 */

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


public class AdjacencyMatrixTest {

  //--------------------------------------------------------------------
  // Directed Graph Tests
  //--------------------------------------------------------------------

  @Test
  public void checkEmptyDirectedGraph() throws Exception {
    Graph<Integer> g = new AdjacencyMatrix<>(0, true);
    assertEquals(0, g.nodeCount());
    assertEquals(0, g.edgeCount());
    assertEquals(true, g.directed());
  }

  @Test
  public void checkBasicTwoNodeOneEdgeDirectedGraph() throws Exception {
    Graph<String> g = new AdjacencyMatrix<>(2, true);
    g.add(0, "a", 1);
    // check size
    assertEquals(2, g.nodeCount());
    assertEquals(1, g.edgeCount());
    // check edges
    assertTrue(g.hasEdge(0, 1));
    assertEquals("a", g.label(0, 1));
    assertFalse(g.hasEdge(1, 0));
    // check adjacent
    List<Integer> adj = g.adjacent(0);
    assertEquals(1, adj.size());
    assertTrue(adj.contains(1));
    // check inNodes
    adj = g.inNodes(0);
    assertEquals(0, adj.size());
    // check outNodes
    adj = g.outNodes(0);
    assertEquals(1, adj.size());    
    assertTrue(adj.contains(1));
  }

  @Test
  public void checkAddEdgesToDirectedGraph() throws Exception {
    int n = 5;
    Graph<Integer> g = new AdjacencyMatrix<>(n, true);
    assertEquals(n, g.nodeCount());
    assertEquals(0, g.edgeCount());
    int l = 1; // label
    for (int u = 0; u < n; ++u)
      for (int v = u + 1; v < n; ++v)
        g.add(u, l++, v);
    g.add(n-1, l, 0);
    // check edge count
    assertEquals(1+(n*(n-1))/2, g.edgeCount());
    // check edge labels
    l = 1;
    for (int u = 0; u < n; ++u)
      for (int v = u + 1; v < n; ++v)
        assertEquals(l++, g.label(u, v).intValue());
  }

  @Test
  public void checkAdjacentNodesDirectedGraph() throws Exception {
    // create basic 8-node dag (except node 7, which has self loop)
    Graph<Integer> g = new AdjacencyMatrix<>(8, true);
    g.add(0, -1, 2); 
    g.add(1, -1, 2);
    g.add(2, -1, 3);
    g.add(2, -1, 4);
    g.add(2, -1, 5);
    g.add(3, -1, 6);
    g.add(4, -1, 6);
    g.add(5, -1, 6);
    g.add(6, -1, 7);
    // self loop 
    g.add(7, -1, 7); 
    // check node 2
    List<Integer> adj = g.adjacent(2);
    assertEquals(5, adj.size());
    assertTrue(adj.contains(0) && adj.contains(1));
    assertTrue(adj.contains(3) && adj.contains(4) && adj.contains(5));
    // check node 6
    adj = g.adjacent(6);
    assertEquals(4, adj.size());
    assertTrue(adj.contains(3) && adj.contains(4) && adj.contains(5));
    assertTrue(adj.contains(7));
    // check node 7
    adj = g.adjacent(7);
    assertEquals(2, adj.size());
    assertTrue(adj.contains(6) && adj.contains(7));
  }


  /* Add the Following Additional Tests: 
       (1). for each of the three tests below, create a different
            graph with multiple nodes and edges (so three different
            graphs)
       (2). first test should ensure the remove function works correctly
       (3). second test should check that the outNodes function works correctly
       (4). third test should check that the inNodes function works correctly
       (5). be sure to comment your tests, including describing the
            graph you are testing on (again, should be a different
            sized / structured graph for each)
       (6). repeat your three tests for undirected graphs (see below)
       (7). when done, add your name and a description to the file
            header above
       (8). you can add additional tests as desired beyond those asked
            for
  */

  @Test
  public void checkRemoveEdgesToDirectedGraph() throws Exception {
    // make it fail intentionally ... remove to create the actual test
    assertTrue(false);
  }

  @Test
  public void checkOutNodesDirectedGraph() throws Exception {
    // make it fail intentionally ... remove to create the actual test
    assertTrue(false);
  }

  @Test
  public void checkInNodesDirectedGraph() throws Exception {
    // make it fail intentionally ... remove to create the actual test
    assertTrue(false);
  }


  //--------------------------------------------------------------------
  // Undirected Graph Tests
  //--------------------------------------------------------------------

  @Test
  public void checkEmptyUndirectedGraph() throws Exception {
    Graph g = new AdjacencyMatrix(0, false);
    assertEquals(0, g.nodeCount());
    assertEquals(0, g.edgeCount());
    assertEquals(false, g.directed());
  }

  @Test
  public void checkBasicTwoNodeOneEdgeUndirectedGraph() throws Exception {
    Graph<String> g = new AdjacencyMatrix<>(2, false);
    g.add(0, "a", 1);
    // check size
    assertEquals(2, g.nodeCount());
    assertEquals(1, g.edgeCount());
    // check edges
    assertTrue(g.hasEdge(0, 1));
    assertEquals("a", g.label(0, 1));
    assertTrue(g.hasEdge(1, 0));
    assertEquals("a", g.label(1, 0));
    // check adjacent
    List<Integer> adj = g.adjacent(0);
    assertEquals(1, adj.size());
    assertTrue(adj.contains(1));
    adj = g.adjacent(1);
    assertEquals(1, adj.size());
    assertTrue(adj.contains(0));
    // check inNodes
    adj = g.inNodes(0);
    assertEquals(1, adj.size());
    assertTrue(adj.contains(1));
    adj = g.inNodes(1);
    assertEquals(1, adj.size());
    assertTrue(adj.contains(0));
    // check outNodes
    adj = g.outNodes(0);
    assertEquals(1, adj.size());    
    assertTrue(adj.contains(1));
    adj = g.outNodes(1);
    assertEquals(1, adj.size());    
    assertTrue(adj.contains(0));
  }

  @Test
  public void checkAddEdgesToUndirectedGraph() throws Exception {
    int n = 5;
    Graph<Integer> g = new AdjacencyMatrix<>(n, false);
    assertEquals(n, g.nodeCount());
    assertEquals(0, g.edgeCount());
    int l = 1; // label
    for (int u = 0; u < n; ++u)
      for (int v = u + 1; v < n; ++v)
        g.add(u, l++, v);
    // check edge count
    assertEquals((n*(n-1))/2, g.edgeCount());
    // check edge labels
    l = 1;
    for (int u = 0; u < n; ++u) {
      for (int v = u + 1; v < n; ++v) {
        assertEquals(l, g.label(u, v).intValue());
        assertEquals(l++, g.label(v, u).intValue());
      }
    }
  }


  @Test
  public void checkAdjacentNodesUndirectedGraph() throws Exception {
    // create basic 8-node dag (except node 7, which has self loop)
    Graph<Integer> g = new AdjacencyMatrix<>(8, true);
    g.add(0, -1, 2); 
    g.add(1, -1, 2);
    g.add(2, -1, 3);
    g.add(2, -1, 4);
    g.add(2, -1, 5);
    g.add(3, -1, 6);
    g.add(4, -1, 6);
    g.add(5, -1, 6);
    g.add(6, -1, 7);
    // self loop 
    g.add(7, -1, 7); 
    // check node 2
    List<Integer> adj = g.adjacent(2);
    assertEquals(5, adj.size());
    assertTrue(adj.contains(0) && adj.contains(1));
    assertTrue(adj.contains(3) && adj.contains(4) && adj.contains(5));
    // check node 6
    adj = g.adjacent(6);
    assertEquals(4, adj.size());
    assertTrue(adj.contains(3) && adj.contains(4) && adj.contains(5));
    assertTrue(adj.contains(7));
    // check node 7
    adj = g.adjacent(7);
    assertEquals(2, adj.size());
    assertTrue(adj.contains(6) && adj.contains(7));
  }

  @Test
  public void checkRemoveEdgesToUndirectedGraph() {
    // fill the graph with the same add method test code.
    int n = 5;
    int l = 1; // label
    int count;
    Graph<Integer> g = new AdjacencyMatrix<>(n, true);

    // Fill the matrix and check filling correctly.
    for (int u = 0; u < n; ++u) {
      for (int v = u + 1; v < n; ++v) {
        g.add(u, l, v);
        assertEquals(l, g.label(u, v).intValue());
        ++l;
      }
    }
    g.add(n-1, l, 0);
    assertEquals(l, g.label(n-1, 0).intValue());
    // check edge count
    assertEquals(1+(n*(n-1))/2, g.edgeCount());
    count = g.edgeCount();

    // remove edges and check removing correctly.
    for (int u = 0; u < n; ++u) {
      for (int v = u + 1; v < n; ++v) {
        // remove each Vertex
        g.remove(u, v);
        assertEquals(--count, g.edgeCount());
        assertNull(g.label(u, v));
      }
    }
    g.remove(n-1, 0);
    assertEquals(0,g.edgeCount());
  }

  @Test
  public void checkOutNodesUndirectedGraph() throws Exception {
    // make it fail intentionally ... remove to create the actual test
    assertTrue(false);
  }

  @Test
  public void checkInNodesUndirectedGraph() throws Exception {
    // make it fail intentionally ... remove to create the actual test
    assertTrue(false);
  }

  @Test
  public void checkSetLabelUndirectedGraph() {
    // fill the graph with the same add method test code.
    int n = 5;
    int l = 1; // label
    Graph<Integer> g = new AdjacencyMatrix<>(n, true);

    // Fill the matrix
    for (int u = 0; u < n; ++u) {
      for (int v = u + 1; v < n; ++v) {
        g.add(u, l, v);
        assertEquals(l, g.label(u, v).intValue());
        ++l;
      }
    }
    g.add(n-1, l, 0);
    assertEquals(l, g.label(n-1, 0).intValue());
    // check edge count
    assertEquals(1+(n*(n-1))/2, g.edgeCount());

    // verify set method works to overwrite existing nodes.
    l = 42;
    for (int u = 0; u < n; ++u) {
      for (int v = u + 1; v < n; ++v) {
        // remove each Vertex
        g.set(u, l, v);
        assertEquals(l, g.label(u, v).intValue());
      }
    }
  }

  @Test
  public void checkLabelEdgeUndirectedGraph() {
    int n = 5;
    int l = 42;
    Graph<Integer> g = new AdjacencyMatrix<>(n, true);

    // fill the graph with some edges and check.
    g.add(0,l,0);
    assertEquals(l, g.label(0, 0).intValue());
  }

  @Test
  public void checkHasEdgeUndirectedGraph() {
    int n = 5;
    int l = 42;
    Graph<Integer> g = new AdjacencyMatrix<>(n, true);

    // fill the graph with some edges and check.
    g.add(0,l,0);
    assertTrue(g.hasEdge(0, 0));
    assertFalse(g.hasEdge(1, 1));
  }

}