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

    /**
     * Purpose: Generic getter for whether the graph is directed or
     *          undirected.
     * @return True if directed, false if undirected.
     */
    public boolean directed() {
        return directed;
    }


    /**
     * Purpose: Adds a new edge value to the matrix if one is not already
     *          in the provided matrix location.
     * @param node1 Integer of matrix coordinate.
     * @param label T type that adds a label to the given edge.
     * @param node2 Integer of matrix coordinate.
     */
    public void add(int node1, T label, int node2) {
        boolean isNewNode = true;
        // if the edge is a new and valid edge.
        if (matrix[node1][node2] == null) {
            if (directed) {
                matrix[node1][node2] = label;
                if (DEBUG) {
                    System.out.println("Adding Directed Edge ["
                            + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
                }
            } else { // Undirected
                matrix[node1][node2] = label;
                matrix[node2][node1] = label;
                if (DEBUG) {
                    System.out.println("Adding Undirected Edge ["
                            + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
                }
            }
            ++edgeCount;
        } else {
            System.out.println("Failed Add... Node already exists.");
            exit(0);
        }

        // check if node1 is a new Vertex to count
        for(int index = 0; index < matrix.length; ++index) {
            if (matrix[node1][index] != null) {
                isNewNode = false;
                break;
            }
        }
        if (isNewNode) {
            ++nodeCount;
        }

        // check if node2 is a new Vertex to count
        isNewNode = true;
        for(int index = 0; index < matrix.length; ++index) {
            if (matrix[index][node2] != null) {
                isNewNode = false;
                break;
            }
        }
        if (isNewNode) { // node2 is a new node
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
        boolean isNode = true;
        // if the edge is a valid edge.
        if (matrix[node1][node2] != null) {
            if (directed) {
                if (DEBUG) {
                    System.out.println("\nRemoving Directed Edge ["
                            + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
                }
                matrix[node1][node2] = null;
            } else {
                if (DEBUG) {
                    System.out.println("\nRemoving  Undirected Edge ["
                            + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
                }
                matrix[node1][node2] = null;
                matrix[node2][node1] = null;
            }
            --edgeCount;
        } else {
            System.out.println("Failed Remove... Node already null.");
            exit(0);
        }

        // check if node1 is an empty Vertex to decrement count
        for(int index = 0; index < matrix.length; ++index) {
            if (matrix[node1][index] != null) {
                if (DEBUG) {
                    System.out.println("non-Decrementing nodeCount: ["
                            + node1 + "][" + index +"]");
                }
                isNode = false;
                break;
            }
        }
        if (isNode) {
            if (DEBUG) {
                System.out.println("Decrementing nodeCount because node1");
            }
            --nodeCount;
            return; // no need to check node2 and double count on corner nodes
        }
        isNode = true;

        // check if node2 is an empty Vertex to decrement count
        for(int index = 0; index < matrix.length; ++index) {
            if (matrix[index][node2] != null) {
                if (DEBUG) {
                    System.out.println("non-Decrementing nodeCount: ["
                            + index + "][" + node2 + "]");
                }
                isNode = false;
                break;
            }
        }
        if (isNode) { // node2 is a new node
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
            if (directed) {
                matrix[node1][node2] = label;
                if (DEBUG) {
                    System.out.println("\nSetting label on Directed Edge: ["
                            + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
                }
            } else {
                matrix[node1][node2] = label;
                matrix[node2][node1] = label;
                if (DEBUG) {
                    System.out.println("\nSetting label on Undirected Edge: ["
                            + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
                }
            }
        } else {
            if (DEBUG) {
                System.out.println("Edge does not exists: ["
                        + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
            }
            exit(0);
        }
    }


    /**
     * Purpose: Fetches the value of the edge label given two coordinate
     *          nodes.
     * @param node1 Integer of matrix coordinate.
     * @param node2 Integer of matrix coordinate.
     * @return T type label to the given edge.
     */
    public T label(int node1, int node2) {
        // if the edge is a valid edge.
        if (matrix[node1][node2] != null) {
            if (DEBUG) {
                System.out.println("\nFetching edge label: ["
                        + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
            }
            return matrix[node1][node2];
        } else {
            if (DEBUG) {
                System.out.println("Failed to Fetch label: ["
                        + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
            }
            return null;
        }
    }


    /**
     * Purpose: Determines if an edge exists at the provided coordinates.
     * @param node1 Integer of matrix coordinate.
     * @param node2 Integer of matrix coordinate.
     * @return True if the graph contains an edge from node1 to node2 and
     *         false otherwise.
     *    NOTE: If the graph is undirected, no directionality is implied by
     *          the edge.
     */
    public boolean hasEdge(int node1, int node2) {
        if (matrix[node1][node2] != null) {
            if (DEBUG) {
                System.out.println("\nEdge found between nodes: ["
                        + node1 + "][" + node2 + "]: " + matrix[node1][node2]);
            }
            return true;
        }
        return false;
    }


    /**
     * Purpose: Finds all out and in nodes for a given node. The
     *          matrix is checked using a for loop that checks
     *          the possible node matches via the matrix column
     *          and row index. If a value is stored in that matrix
     *          address, the node index is added to a list. After
     *          all possible adjacent nodes are found, the list is
     *          looped through to delete any duplicate values. The
     *          cleaned list is then returned.
     * @param node Integer index of the node to be checked for
     *             adjacent nodes.
     * @return List of adjacent nodes.
     */
    public List<Integer> adjacent(int node) {
        List<Integer> adjacentNodes = new ArrayList<>();
        for (int index = 0; index < matrix.length; ++index) {
            if (directed) {
                if (hasEdge(index, node)) {
                    adjacentNodes.add(index);
                    if (DEBUG) {
                        System.out.println("\nInNode found: "
                                + node);
                    }
                }
                if (hasEdge(node, index)) {
                    adjacentNodes.add(index);
                    if (DEBUG) {
                        System.out.println("\nOutNode found: "
                                + node);
                    }
                }
            } else { // Undirected
                if (hasEdge(node, index)) {
                    adjacentNodes.add(index);
                    adjacentNodes.add(node);
                    if (DEBUG) {
                        System.out.println("\nNode found: "
                                + index + ", " + node);
                    }
                }
            }
        }
        // loop through the adjacentNodes and remove any duplicate values.
        for (int ptr = 0; ptr < adjacentNodes.size(); ++ptr) {
            for (int index = ptr + 1; index < adjacentNodes.size(); ++index) {
                if (adjacentNodes.get(ptr).equals(adjacentNodes.get(index))) {
                    if (DEBUG) {
                        System.out.println("\nList Duplicate found: "
                                + adjacentNodes.get(index));
                    }
                    //noinspection SuspiciousListRemoveInLoop
                    adjacentNodes.remove(index);
                }
            }
        }
        return adjacentNodes;
    }


    /**
     * Purpose: Loop through and check each possible node that could be
     *          traversed to from the provided node. If it has an edge
     *          it is added to the list to be returned.
     * @param node Integer value of the node to be checked.
     * @return List of integers representing the nodes that this provided
     *         node can traverse to.
     */
    public List<Integer> outNodes(int node) {
        // TODO: fix this method and test.
        List<Integer> list = new ArrayList<>();
        for (int index = 0; index < matrix.length; ++index) {
            if (directed) {
                if (hasEdge(node, index)) {
                    list.add(index);
                    if (DEBUG) {
                        System.out.println("\nInNode found: "
                                + node);
                    }
                }
            } else { // Undirected
                if (hasEdge(node, index)) {
                    list.add(index);
                    if (DEBUG) {
                        System.out.println("\nInNode found: "
                                + index + ", " + node);
                    }
                }
            }
        }
        return list;
    }

    // Returns all nodes on an incoming edge (in-edge) from the given
    // node. For an undirected graph, this method returns the same nodes
    // as adjacent.
    public List<Integer> inNodes(int node) {
        // TODO: fix the test and the undirected functionality.
        List<Integer> list = new ArrayList<>();
        for (int index = 0; index < matrix.length; ++index) {
            if (directed) {
                if (hasEdge(index, node)) {
                    list.add(index);
                    if (DEBUG) {
                        System.out.println("\nInNode found: " + index);
                    }
                }
            } else { // Undirected
                if (hasEdge(index, node)) {
                    list.add(index);
                    if (DEBUG) {
                        System.out.println("\nInNode found: " + index);
                    }
                }
            }
        }
        return list;
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