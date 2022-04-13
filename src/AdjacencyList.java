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


    /**
     * Purpose: Adds a labeled edge to the graph from node1 to node2 if a directed graph.
     *          This method only adds the edges if there is no data already inserted into
     *          the list.
     * @param node1 The starting node if it's a directed graph.
     * @param label The data of type T to be stored as the label for the edge.
     * @param node2 The ending node if it's a directed graph.
     */
    public void add(int node1, T label, int node2) {
        if (directed) {
            if (adjList[node1].get(node2) == null) {
                // if graph is directed and node1 is empty,
                // add node2 as connected to node1.
                adjList[node1].put(node2, label);
                ++edgeCount;
            } else {
                System.out.println("Node1 is already empty");
            }
        } else {
            if (adjList[node1].get(node2) == null) {
                // if graph is undirected and has any node info stored inside,
                // add both node1 and node2 data.
                adjList[node1].put(node2, label);
                adjList[node2].put(node1, label);
                ++edgeCount;
            } else {
                System.out.println("Node1 is already empty");
            }
        }
    }

    // Removes an edge from the graph if it exists
    public void remove(int node1, int node2) {
        // TODO: finish this method
        if (directed) {
            if (adjList[node1].get(node2) != null) {
                // if graph is directed and has a node associated with node 1
                adjList[node1].remove(node2);
                --edgeCount;
            } else {
                System.out.println("Node1 is already empty");
            }
        } else {
            if (adjList[node1].get(node2) != null) {
                // if graph is undirected and has any node info stored inside,
                // remove both node1 and node2 data.
                adjList[node1].remove(node2);
                adjList[node2].remove(node1);
                --edgeCount;
            } else {
                System.out.println("Node1 is already empty");
            }
        }
    }

    // Updates the label for a given edge. If the edge does not exist,
    // the edge is not added.
    public void set(int node1, T label, int node2) {
        // TODO: finish this method
        if (directed) {
            // Directionality assumed from node1 to node2 from calling function.
            adjList[node1].put(node2, label);
        } else {
            // if undirected add label to both edges.
            adjList[node1].put(node2, label);
            adjList[node2].put(node1, label);
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