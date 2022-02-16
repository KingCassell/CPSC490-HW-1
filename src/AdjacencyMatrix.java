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

import java.util.List;

import static java.lang.System.exit;


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

    // for debug print statements
    private final boolean DEBUG = true;


    //--------------------------------------------------------------------
    // constructor
    //--------------------------------------------------------------------

    /**
     * Purpose: Primary Constructor for the AdjacencyMatrix class.
     * @param nodeCount The number of nodes to be added to the graph.
     * @param directed Provided Directionality of the graph.
     */
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
        // TODO: finish this method
        return true;
    }


    /**
     * Purpose: Adds a new edge value to the matrix if one is not already
     *          in the provided matrix location.
     * @param node1 Integer of matrix coordinate.
     * @param label T type that adds a label to the given edge.
     * @param node2 Integer of matrix coordinate.
     */
    public void add(int node1, T label, int node2) {
        boolean isVertex = true;
        // if the edge is a new and valid edge.
        if (matrix[node1][node2] == null) {
            matrix[node1][node2] = label;
            ++edgeCount;
            if (DEBUG) {
                System.out.println("Adding Node [" + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
            }
        } else {
            System.out.println("Node already exists.");
            exit(0);
        }

        // check if node1 is a new Vertex to count
        for(int index = 0; index < matrix.length; ++index) {
            if (matrix[node1][index] != null) {
                isVertex = false;
                break;
            }
        }
        if (isVertex) {
            ++nodeCount;
        }
        isVertex = true;

        // check if node2 is a new Vertex to count
        for(int index = 0; index < matrix.length; ++index) {
            if (matrix[index][node2] != null) {
                isVertex = false;
                break;
            }
        }
        if (isVertex) { // node2 is a new node
            ++nodeCount;
        }
    }


    /**
     * Purpose: remove the edges stored in the matrix if the edge exists
     *          and updates the edgeCount and nodeCount variables.
     * @param node1 Integer of matrix coordinate.
     * @param node2 Integer of matrix coordinate.
     */
    public void remove(int node1, int node2) {
        boolean isVertex = true;
        // if the edge is a valid edge.
        if (matrix[node1][node2] != null) {
            if (DEBUG) {
                System.out.println("\nRemoving Node [" + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
            }
            matrix[node1][node2] = null;
            --edgeCount;
        } else {
            System.out.println("Node already exists.");
            exit(0);
        }

        // check if node1 is an empty Vertex to decrement count
        for(int index = 0; index < matrix.length; ++index) {
            if (matrix[node1][index] != null) {
                if (DEBUG) {
                    System.out.println("non-Decrementing nodeCount: [" + node1 + "][" + index +"]");
                }
                isVertex = false;
                break;
            }
        }
        if (isVertex) {
            if (DEBUG) {
                System.out.println("Decrementing nodeCount because node1");
            }
            --nodeCount;
            return; // no need to check again and double count on corner nodes
        }
        isVertex = true;

        // check if node2 is an empty Vertex to decrement count
        for(int index = 0; index < matrix.length; ++index) {
            if (matrix[index][node2] != null) {
                if (DEBUG) {
                    System.out.println("non-Decrementing nodeCount: [" + index + "][" + node2 + "]");
                }
                isVertex = false;
                break;
            }
        }
        if (isVertex) { // node2 is a new node
            if (DEBUG) {
                System.out.println("Decrementing nodeCount because node2");
            }
            --nodeCount;
        }
    }


    /**
     * Purpose: Set a provided node to a new label value only if the node
     *          already exists.
     * @param node1 Integer of matrix coordinate.
     * @param label T type that adds a label to the given edge.
     * @param node2 Integer of matrix coordinate.
     */
    public void set(int node1, T label, int node2) {
        // if the edge is a valid edge.
        if (matrix[node1][node2] != null) {
            matrix[node1][node2] = label;
            if (DEBUG) {
                System.out.println("\nSetting Node [" + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
            }
        } else {
            System.out.println("Node does not yet exist");
            exit(0);
        }
    }

    // Returns the label on the give edge.
    // Pre: the edge exists
    public T label(int node1, int node2) {
        // TODO: finish this method
        // if the edge is a valid edge.
        if (matrix[node1][node2] != null) {
            if (DEBUG) {
                System.out.println("\nFetching Node [" + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
            }
            return matrix[node1][node2];
        } else {
            System.out.println("Node does not yet exist");
            return null;
        }
    }

    // Returns true if the graph contains an edge from node1 to node2
    // and false otherwise. Note that if the graph is undirected, no
    // directionality is implied by the edge.
    public boolean hasEdge(int node1, int node2) {
        // TODO: finish this method
        return false;
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

    // Returns the number of nodes in the graph
    public int nodeCount() {
        // TODO: finish this method
        return nodeCount;
    }

    // Returns the number of edges in the graph. In a directed graph, it
    // returns the total number of directed edges. In an undirected
    // graph, it returns the number of undirected edges.
    public int edgeCount() {
        // TODO: finish this method
        return edgeCount;
    }

}