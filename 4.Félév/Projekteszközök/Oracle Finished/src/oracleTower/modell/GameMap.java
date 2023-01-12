package oracleTower.modell;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import oracleTower.tile.Tile;

import oracleTower.player.SoldierInterface;


/**
 *
 * @author MQH41V
 * A representation for the GameMap on a modell level.
 */
public class GameMap {
    private final int ROWS;
    private final int COLS;
    private final Tile [] [] tileMatrix;
    private Graph dijsktra;
    
    /**
     * Creates the GameMap from a file and initialises the Tiles according to that.
     */
    public GameMap(){
        ArrayList<String> gameMapRows = readMaps();
        int c = 0;
        for (String s : gameMapRows) if (s.length() > c) c = s.length();
        ROWS = gameMapRows.size();
        COLS = c;
        tileMatrix = new Tile[ROWS][COLS];
        for (int i = 0; i < ROWS; i++){
            String s = gameMapRows.get(i);
            for (int j = 0; j < COLS; j++){
                switch (s.charAt(j)){
                    case 'M' : this.tileMatrix[i][j] = new Tile('M',new Position(i,j)); break;
                    case 'S' : this.tileMatrix[i][j] = new Tile('S',new Position(i,j)); break;
                    default  : this.tileMatrix[i][j] = new Tile('N',new Position(i,j)); 
                }
            }
        }
        dijsktra = new Graph();
    }
    
    /**
     * Makes the Tile reacheable from outisde of the GameMap.
     * @param p the position which the tile is on
     * @return the Tile object on the current position
     */
    public Tile getTile(Position p){
        return tileMatrix[p.getX()][p.getY()];
    }
    
    /**
     * Makes the GameMap printable (only for testing).
     */
    public void printMap(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(tileMatrix[i][j].getType());
            }
        }
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLS() {
        return COLS;
    }

     /**
     * Gets the neighbouring soldiers from a Position
     * @param x the x coordiante of the position
     * @param y the y coordinate of the position
     * @param radius the radius of the search
     * @return the soldiers in the radius of the tile on the given position
     */
    public ArrayList<SoldierInterface> getAdjecentSoldiers(int x, int y, int radius){
        ArrayList<SoldierInterface> soldiers = new ArrayList<SoldierInterface>();
        for (int i = -radius ; i <= radius ; i++){
            for (int j = -radius; j <= radius; j ++){
                try{
                if (tileMatrix[x + i][y + j].isSoldier()) 
                    soldiers.addAll(tileMatrix[x + i][y + j].getSoldiers());
                }catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
            }
        } return soldiers;
    }

    
    /**
     * MADE BY: GERGO MUIK
     * A reading method which reads the map from an imput file.
     * @return A String with the field attributes for the constructor.
     */
    public ArrayList<String> readMaps(){
        InputStream is;
        is = getClass().getResourceAsStream("/oracleTower/res/Maps.txt");
        
        try (Scanner sc = new Scanner(is)){
            String line = " ";
            ArrayList<String> gameMapRows = new ArrayList<>();
            
            while (!line.isEmpty()){
                line = readNextLine(sc);
                while (!line.isEmpty() && line.trim().charAt(0) != ';'){
                    gameMapRows.add(line);                    
                    line = readNextLine(sc);
                }
            }
            return gameMapRows;
        } catch (IllegalArgumentException  e){
        }
        return null;
    }
    
    /**
     * MADE BY: GERGO MUIK
     * Only a helper function for the readMaps().
     * @param sc a Scanner.
     * @return  Returns the next line in a file
     */
    private String readNextLine(Scanner sc){
        String line = "";
        while (sc.hasNextLine() && line.trim().isEmpty()){
            line = sc.nextLine();
        }
        return line;
    }
    
   /**
     * Calculates the shortes distance from a position.
     * @param pos The starting position on the map
     * @param target The target part of the map
     * @param speedy true if the unit can go trough mountains
     * @return Returns a Node with a distance and the Nodes which lead to the target. 
     */
    public Node getShortestDistance(Position pos, Position target, boolean speedy){
        dijsktra = new Graph();
        Node nodeMatrix [][] = new Node [ROWS][COLS];
        initDijsktra(nodeMatrix);
        if (speedy) connectNodes(nodeMatrix, null, false , 0);
        else connectNodes(nodeMatrix, null, false , 2);
        
        Graph graph2 = new Graph();
        graph2 = Graph.calculateShortestPathFromSource(dijsktra, nodeMatrix[pos.getX()] [pos.getY()]);
        for (Node node : graph2.getNodes()){
            if (checkSurroundings(node,target) && node.getPosition() != target){
                return node;
            }
        } return null;
    }
    
    /**
     * Runs the Dijstra algorithm for the position then checks if the target can be reached.
     * @param pos the Position on the GameMap
     * @param target the Postion of the target on the GameMap
     * @param leave the position that will be left out so we can see if Dijsktra works without it
     * @return true if the target is reachable from the position
     */
    public boolean runDijsktraBaseCheck(Position pos, Position target, Position leave){
        dijsktra = new Graph();
        Node nodeMatrix [][] = new Node [ROWS][COLS];
        initDijsktra(nodeMatrix);
        connectNodes(nodeMatrix, leave, true, 2);
        
        Graph graph2 = new Graph();
        graph2 = Graph.calculateShortestPathFromSource(dijsktra, nodeMatrix[pos.getX()+1] [pos.getY()+1]);
        for (Node node : graph2.getNodes()){
            if(checkSurroundings(node, target) && node.getDistance() != Integer.MAX_VALUE){
                return true;
            }
        } return false;
    }
    
    /**
     * Initialises the dijstra for a search
     * @param nodeMatrix a matrix for creating the dependecies
     * @return true if the init finished
     */
    private boolean initDijsktra(Node nodeMatrix [][]){
        dijsktra = new Graph();
        
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                nodeMatrix[i][j] = (new Node(new Position(i,j)));
            }
        }
        return true;
    }
    
    /**
     * Connects nodes with a specific rule set
     * @param nodeMatrix the nodeMatrix itself
     * @param leave the tile we have to leave out if we have to skip
     * @param skip if we have to skip a tile
     * @param speedy if we are initialising for a speedyboy
     * @return true if the initialisation was succefull
     */
    private boolean connectNodes(Node nodeMatrix [] [], Position leave, boolean skip, int speedy ){
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                if ( ( skip && leave.getX() == i && leave.getY() == j) ) continue;
                if (i - 1 > 0 && !tileMatrix[i-1][j].isBuilding() && tileMatrix[i-1][j].getType().getValue() != speedy ) 
                    nodeMatrix[i][j].addDestination(nodeMatrix[i-1][j], tileMatrix[i-1][j].getType().getValue());
                if (i + 1 < ROWS && !tileMatrix[i+1][j].isBuilding() && tileMatrix[i+1][j].getType().getValue() != speedy ) 
                    nodeMatrix[i][j].addDestination(nodeMatrix[i+1][j], tileMatrix[i+1][j].getType().getValue());
                if (j - 1 > 0 && !tileMatrix[i][j-1].isBuilding() && tileMatrix[i][j-1].getType().getValue() != speedy ) 
                    nodeMatrix[i][j].addDestination(nodeMatrix[i][j-1], tileMatrix[i][j-1].getType().getValue());
                if (j + 1 < COLS && !tileMatrix[i][j+1].isBuilding() && tileMatrix[i][j+1].getType().getValue() != speedy )
                    nodeMatrix[i][j].addDestination(nodeMatrix[i][j+1], tileMatrix[i][j+1].getType().getValue());
            }
        }
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                dijsktra.addNode(nodeMatrix[i][j]);
            }
        }
        return true;
    }
    
    /**
     * Checks the surroundings of a node in dijstra
     * @param node the node itself
     * @param target the target that has to be checked
     * @return true if the target is in the radius of the node
     */
    private boolean checkSurroundings(Node node, Position target){
        if (node.getPosition().getX() - 1 == target.getX() && node.getPosition().getY() == target.getY())
            return true;
        else if (node.getPosition().getX() + 1 == target.getX() && node.getPosition().getY() == target.getY())
            return true;
        else if(node.getPosition().getX() == target.getX() && node.getPosition().getY() + 1 == target.getY())
            return true;
        else if(node.getPosition().getX() == target.getX() && node.getPosition().getY() - 1 == target.getY())
            return true;
        else return false;
    }
}
