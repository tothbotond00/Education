package model;

import java.util.ArrayList;

public class GameLevel {
    public final GameID        gameID;
    public final int           rows, cols;
    public final LevelItem[][] level;
    public Position            player = new Position(0, 0);
    private int                numBoxes, numBoxesInPlace, numSteps;
    
    public GameLevel(ArrayList<String> gameLevelRows, GameID gameID){
        this.gameID = gameID;
        int c = 0;
        for (String s : gameLevelRows) if (s.length() > c) c = s.length();
        rows = gameLevelRows.size();
        cols = c;
        level = new LevelItem[rows][cols];
        numBoxes = 0;
        numBoxesInPlace = 0;
        numSteps = 0;
        
        for (int i = 0; i < rows; i++){
            String s = gameLevelRows.get(i);
            for (int j = 0; j < s.length(); j++){
                switch (s.charAt(j)){
                    case '@': player = new Position(j, i);
                              level[i][j] = LevelItem.EMPTY; break;
                    case '#': level[i][j] = LevelItem.WALL; break;
                    case '.': level[i][j] = LevelItem.DESTINATION; break;
                    case '$': level[i][j] = LevelItem.BOX; 
                              numBoxes++;
                              break;
                    case '*': level[i][j] = LevelItem.BOX_IN_PLACE; 
                              numBoxes++;
                              numBoxesInPlace++;
                              break;
                    default:  level[i][j] = LevelItem.EMPTY; break;
                }
            }
            for (int j = s.length(); j < cols; j++){
                level[i][j] = LevelItem.EMPTY;
            }
        }
    }

    public GameLevel(GameLevel gl) {
        gameID = gl.gameID;
        rows = gl.rows;
        cols = gl.cols;
        numBoxes = gl.numBoxes;
        numBoxesInPlace = gl.numBoxesInPlace;
        numSteps = gl.numSteps;
        level = new LevelItem[rows][cols];
        player = new Position(gl.player.x, gl.player.y);
        for (int i = 0; i < rows; i++){
            System.arraycopy(gl.level[i], 0, level[i], 0, cols);
        }
    }

    public boolean isGameEnded(){
        return numBoxes <= numBoxesInPlace;
    }
    
    public boolean isValidPosition(Position p){
        return (p.x >= 0 && p.y >= 0 && p.x < cols && p.y < rows);
    }
    
    public boolean isFree(Position p){
        if (!isValidPosition(p)) return false;
        LevelItem li = level[p.y][p.x];
        return (li == LevelItem.EMPTY || li == LevelItem.DESTINATION);
    }
    
    boolean isBox(Position p) {
        if (!isValidPosition(p)) return false;
        LevelItem li = level[p.y][p.x];
        return (li == LevelItem.BOX || li == LevelItem.BOX_IN_PLACE);
    }
    
    public boolean movePlayer(Direction d){
        if (isGameEnded()) return false;
        Position curr = player;
        Position next = curr.translate(d);
        if (isFree(next)) {
            player = next;
            numSteps++;
            return true;
        } 
        return false;
    }
    
    public boolean moveBox(Direction d){
        if (isGameEnded()) return false;
        Position curr = player;
        Position next = curr.translate(d);
        Position next2 = next.translate(d);
        if (isBox(next) && isFree(next2)) {
            player = next;
            if (level[next.y][next.x] == LevelItem.BOX_IN_PLACE){
                level[next.y][next.x] = LevelItem.DESTINATION;
                numBoxesInPlace--;
            } else {
                level[next.y][next.x] = LevelItem.EMPTY;
            }
            if (level[next2.y][next2.x] == LevelItem.DESTINATION){
                level[next2.y][next2.x] = LevelItem.BOX_IN_PLACE;
                numBoxesInPlace++;
            } else {
                level[next2.y][next2.x] = LevelItem.BOX;
            }
            numSteps++;
            return true;
        } 
        return false;
    }
    
    public void printLevel(){
        int x = player.x, y = player.y;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (i == y && j == x)
                    System.out.print('@');
                else 
                    System.out.print(level[i][j].representation);
            }
            System.out.println("");
        }
    }

    public int getNumBoxes() {
        return numBoxes;
    }

    public int getNumBoxesInPlace() {
        return numBoxesInPlace;
    }

    public int getNumSteps() {
        return numSteps;
    }

    
}
