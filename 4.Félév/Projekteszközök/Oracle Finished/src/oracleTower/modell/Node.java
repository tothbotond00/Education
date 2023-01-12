package oracleTower.modell;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author BAELDUNG
 */
public class Node {
    private Position position;
    
    private LinkedList<Node> shortestPath = new LinkedList<>();
    
    private Integer distance = Integer.MAX_VALUE;
    
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance2) {
        adjacentNodes.put(destination, distance2);
    }
 
    public Node(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    
    public void setDistance(Integer distance) {
        this.distance = distance;
    }
    
    public Integer getDistance() {
        return distance;
    }

    public LinkedList<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.shortestPath, other.shortestPath)) {
            return false;
        }
        if (!Objects.equals(this.distance, other.distance)) {
            return false;
        }
        if (!Objects.equals(this.adjacentNodes, other.adjacentNodes)) {
            return false;
        }
        return true;
        }


    @Override
    public String toString() {
        return "Node{" + "position=" + position + ", distance=" + distance ;
    }
    
    
}
