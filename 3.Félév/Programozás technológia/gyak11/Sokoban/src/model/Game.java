package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import persistence.Database;
import persistence.HighScore;
import res.ResourceLoader;

public class Game {
    private final HashMap<String, HashMap<Integer, GameLevel>> gameLevels;
    private GameLevel gameLevel = null;
    private final Database database;
    private boolean isBetterHighScore = false;

    public Game() {
        gameLevels = new HashMap<>();
        database = new Database();
        readLevels();
    }

    // ------------------------------------------------------------------------
    // The 'interesting' part :)
    // ------------------------------------------------------------------------

    public void loadGame(GameID gameID){
        gameLevel = new GameLevel(gameLevels.get(gameID.difficulty).get(gameID.level));
        isBetterHighScore = false;
    }
    
    public void printGameLevel(){ gameLevel.printLevel(); }
    
    public boolean step(Direction d){
        boolean stepped = (gameLevel.movePlayer(d) || gameLevel.moveBox(d));
        if (stepped && isGameEnded()){
            GameID id = gameLevel.gameID;
            int steps = gameLevel.getNumSteps();
            isBetterHighScore = database.storeHighScore(id, steps);
        }
        return stepped;
    }
    
    // ------------------------------------------------------------------------
    // Getter methods
    // ------------------------------------------------------------------------
    
    public Collection<String> getDifficulties(){ return gameLevels.keySet(); }
    
    public Collection<Integer> getLevelsOfDifficulty(String difficulty){
        if (!gameLevels.containsKey(difficulty)) return null;
        return gameLevels.get(difficulty).keySet();
    }
    
    public boolean isLevelLoaded(){ return gameLevel != null; }
    public int getLevelRows(){ return gameLevel.rows; }
    public int getLevelCols(){ return gameLevel.cols; }
    public LevelItem getItem(int row, int col){ return gameLevel.level[row][col]; }
    public GameID getGameID(){ return (gameLevel != null) ? gameLevel.gameID : null; }
    public int getLevelNumBoxes(){ return (gameLevel != null) ? gameLevel.getNumBoxes() : 0; }
    public int getLevelNumBoxesInPlace(){ return (gameLevel != null) ? gameLevel.getNumBoxesInPlace(): 0; }
    public int getNumSteps(){ return (gameLevel != null) ? gameLevel.getNumSteps(): 0; }
    public boolean isGameEnded(){ return (gameLevel != null && gameLevel.isGameEnded()); }
    public boolean isBetterHighScore(){ return isBetterHighScore; }

    public Position getPlayerPos(){ // MAKE IT ~IMMUTABLE
        return new Position(gameLevel.player.x, gameLevel.player.y); 
    }
    
    public ArrayList<HighScore> getHighScores() {
        return database.getHighScores();
    }
    
    // ------------------------------------------------------------------------
    // Utility methods to load game levels from res/levels.txt resource file.
    // ------------------------------------------------------------------------

    private void readLevels(){
        InputStream is;
        is = ResourceLoader.loadResource("res/levels.txt");
        
        try (Scanner sc = new Scanner(is)){
            String line = readNextLine(sc);
            ArrayList<String> gameLevelRows = new ArrayList<>();
            
            while (!line.isEmpty()){
                GameID id = readGameID(line);
                if (id == null) return;

                gameLevelRows.clear();
                line = readNextLine(sc);
                while (!line.isEmpty() && line.trim().charAt(0) != ';'){
                    gameLevelRows.add(line);                    
                    line = readNextLine(sc);
                }
                addNewGameLevel(new GameLevel(gameLevelRows, id));
            }
        } catch (Exception e){
            System.out.println("Ajaj");
        }
    }
    
    private void addNewGameLevel(GameLevel gameLevel){
        HashMap<Integer, GameLevel> levelsOfDifficulty;
        if (gameLevels.containsKey(gameLevel.gameID.difficulty)){
            levelsOfDifficulty = gameLevels.get(gameLevel.gameID.difficulty);
            levelsOfDifficulty.put(gameLevel.gameID.level, gameLevel);
        } else {
            levelsOfDifficulty = new HashMap<>();
            levelsOfDifficulty.put(gameLevel.gameID.level, gameLevel);
            gameLevels.put(gameLevel.gameID.difficulty, levelsOfDifficulty);
        }
        database.storeHighScore(gameLevel.gameID, 0);
    }
  
    private String readNextLine(Scanner sc){
        String line = "";
        while (sc.hasNextLine() && line.trim().isEmpty()){
            line = sc.nextLine();
        }
        return line;
    }
    
    private GameID readGameID(String line){
        line = line.trim();
        if (line.isEmpty() || line.charAt(0) != ';') return null;
        Scanner s = new Scanner(line);
        s.next(); // skip ';'
        if (!s.hasNext()) return null;
        String difficulty = s.next().toUpperCase();
        if (!s.hasNextInt()) return null;
        int id = s.nextInt();
        return new GameID(difficulty, id);
    }    
}
