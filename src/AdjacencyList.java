/*
 * File: AdjacencyList.java
 * Date: Spring 2022
 * Auth: Dustin Cassell
 * Desc: Class for a list implementation of a graph.
 */


/* TODO:
 *   (1) Implement the functions below. Note some are "stubbed" out so
 *       that the code will compile. You will need to implement all of
 *       the functions (even those stubbed out).
 *   (2) Make sure your code is commented and well formatted
 *   (3) Fill out the file header above
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AdjacencyList<T> implements Graph<T> {

    // the total number of nodes in the graph (numbered 0 to nodes-1)
    private int nodeCount;

    // the total number of edges in the graph
    private int edgeCount;

    // true if the graph is directed, false if undirected
    private boolean directed;

    // the adjacency list as an array of node-to-label mappings
    private Map<Integer,T>[] adjList;


    //--------------------------------------------------------------------
    // constructor
    //--------------------------------------------------------------------

    // Initializes the graph with the given number of nodes and the
    // given directionality.
    public AdjacencyList(int nodeCount, boolean directed) {
        this.nodeCount = nodeCount;
        this.directed = directed;
        adjList = new HashMap[nodeCount];
        // initialize each array element to an empty hashmap
        for (int i = 0; i < nodeCount; ++i)
            adjList[i] = new HashMap<>();
    }


    //--------------------------------------------------------------------
    // graph methods
    //--------------------------------------------------------------------

    // Returns true if the graph is directed and false if undirected.

    /**
     * Purpose: Generic getter for the field boolean directed.
     * @return true if the graph is directed, otherwise false.
     */
    public boolean directed() {
        return directed;
    }

    // Adds a labeled edge to the graph from node1 to node2. Only adds
    // the edge if valid nodes are given and the edge doesn't already
    // exist.
    public void add(int node1, T label, int node2) {
        // TODO: finish this method
        if (directed) {
            // check if edge already exists assuming the start is node1 for directed
            if (adjList[node1].isEmpty()) {
                // if the nodes are valid nodes
                adjList[node1].put(node2, label);
            }
        } else {
            // check if edge already exists
            if (adjList[node1].isEmpty() && adjList[node2].isEmpty()) {
                adjList[node1].put(node2, label);
                adjList[node2].put(node1, label);
            }
        }
    }

    // Removes an edge from the graph if it exists
    public void remove(int node1, int node2) {
        // TODO: finish this method
    }

    // Updates the label for a given edge. If the edge does not exist,
    // the edge is not added.
    public void set(int node1, T label, int node2) {
        // TODO: finish this method
        if (directed) {
            if (adjList[node1].get(node2) != null) {
                // if node 1 and 2 exists, the label will be changed
                adjList[node1].put(node2, label);
                adjList[node2].put(node1, label);
            }
        } else { // Undirected graph
            if (adjList[node1].get(node2) != null && adjList[node2].get(node1) != null) {
                // if node 1 and 2 exists, the label will be changed
                adjList[node1].put(node2, label);
                adjList[node2].put(node1, label);
            }
        }
    }


    /**
     * Purpose: Generic Getter for the label attribute between Node 1
     *          and Node 2. Directionality is intended to be from Node 1
     *          to Node 2. For Undirected Graphs, the order of Nodes does
     *          not matter.
     * @param node1 Starting Node to be checked if directional. If undirected,
     *              order doesn't matter.
     * @param node2 Ending Node to be checked if directional. If undirected,
     *              order doesn't matter.
     * @return Type T label object stored between Node 1 and Node 2.
     */
    public T label(int node1, int node2) {
        return adjList[node1].get(node2);
    }


    /**
     * Purpose: if the provided nodes have an edge from node 1 to node 2
     *          this method will return true. This works for both directed
     *          and undirected because if it is directed, the only direction
     *          to be checked is from node 1 to node 2. If it is an
     *          undirected graph, the order of nodes will not matter.
     * @param node1 Starting Node to be checked if directional. If undirected,
     *              order doesn't matter.
     * @param node2 Ending Node to be checked if directional. If undirected,
     *              order doesn't matter.
     * @return True if there is a non-null object stored in the label between
     *         Node 1 and Node 2.
     */
    public boolean hasEdge(int node1, int node2) {
        return adjList[node1].get(node2) != null;
    }

    // Returns all nodes adjacent to the given node. In a directed
    // graph, this includes the set of nodes on outgoing and incoming
    // edges, i.e., that lie on out-edges and in-edges.
    public List<Integer> adjacent(int node) {
        // TODO: finish this method
        return null;
    }

    // Returns all nodes on an outgoing edge (out-edge) from the given
    // node. For an undirected graph, this method returns the same nodes
    // as adjacent.
    public List<Integer> outNodes(int node) {
        // TODO: finish this method
        return null;
    }

    // Returns all nodes on an incoming edge (in-edge) from the given
    // node. For an undirected graph, this method returns the same nodes
    // as adjacent.
    public List<Integer> inNodes(int node) {
        // TODO: finish this method
        return null;
    }

    /**
     * Purpose: Generic getter for the number of nodes currently in the graph.
     * @return Integer count of nodes in Graph.
     */
    public int nodeCount() {
        return nodeCount;
    }

    /**
     * Purpose: Generic getter for the number of edges currently in the graph.
     * @return Integer count of edges in Graph.
     *    NOTE: In a directed graph, it returns the total number of directed
     *          edges. In an undirected graph, it returns the number of
     *          undirected edges.
     */
    public int edgeCount() {
        return edgeCount;
    }

}