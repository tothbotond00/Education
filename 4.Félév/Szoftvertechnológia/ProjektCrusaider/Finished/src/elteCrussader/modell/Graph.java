package elteCrussader.modell;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author BAELDUNG
 * CLEAN CODE ✓
 */
public class Graph {

    private final Set<Node> nodes = new HashSet<>();
    
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }
    

    public Node getNode(Position position){
        for(Node node: nodes){
            if(node.getPosition() == position) return node;
        }
        return null;
    }


    /**
     * After calculation, the shortestPath and distance attributes are set for 
     * each node in the graph, we can iterate through them to 
     * verify that the results match exactly what was found in the previous section.
     * @param graph The Graph with the connections for the matrix.
     * @param source The staring node for the Djsktra algorithm.
     * @return A Graph with the fixed distances from teh source. and their trace
     */
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry < Node, Integer> adjacencyPair: 
              currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
            }
        return graph;
    }
    
    /**
     * The getLowestDistanceNode() method, returns the node
     * with the lowest distance from the unsettled nodes set.
     * @param unsettledNodes A Set which conatins the Nodes.
     * @return The Node with the lowest distance attribute.
     */
    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    
    /**
     * The calculateMinimumDistance() method compares the actual distance with the newly 
     * calculated one while following the newly explored path.
     * @param evaluationNode the Node we will evaluate in the method.
     * @param edgeWeigh The dsitance between the two nodes.
     * @param sourceNode the souce node for the Dijsktra.
     */
    private static void calculateMinimumDistance(Node evaluationNode,
    Integer edgeWeigh, Node sourceNode) {
      Integer sourceDistance = sourceNode.getDistance();
      if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
          evaluationNode.setDistance(sourceDistance + edgeWeigh);
          LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
          shortestPath.add(sourceNode);
          evaluationNode.setShortestPath(shortestPath);
      }
    }

  

}


