package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import model.GameID;

public class Database {
    private final String tableName = "highscore";
    private final Connection conn;
    private final HashMap<GameID, Integer> highScores;
    
    public Database(){
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/sokoban?"
                    + "serverTimezone=UTC&user=student&password=asd123");
        } catch (Exception ex) {
            System.out.println("No connection");
        }
        this.conn = c;
        highScores = new HashMap<>();
        loadHighScores();
    }
    
    public boolean storeHighScore(GameID id, int newScore){
        return mergeHighScores(id, newScore, newScore > 0);
    }
    
    public ArrayList<HighScore> getHighScores(){
        ArrayList<HighScore> scores = new ArrayList<>();
        for (GameID id : highScores.keySet()){
            HighScore h = new HighScore(id, highScores.get(id));
            scores.add(h);
            System.out.println(h);
        }
        return scores;
    }
    
    private void loadHighScores(){
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()){
                String diff = rs.getString("Difficulty");
                int level = rs.getInt("GameLevel");
                int steps = rs.getInt("Steps");
                GameID id = new GameID(diff, level);
                mergeHighScores(id, steps, false);
            }
        } catch (Exception e){ System.out.println("loadHighScores error: " + e.getMessage());}
    }
    
    private boolean mergeHighScores(GameID id, int score, boolean store){
        System.out.println("Merge: " + id.difficulty + "-" + id.level + ":" + score + "(" + store + ")");
        boolean doUpdate = true;
        if (highScores.containsKey(id)){
            int oldScore = highScores.get(id);
            doUpdate = ((score < oldScore && score != 0) || oldScore == 0);
        }
        if (doUpdate){
            highScores.remove(id);
            highScores.put(id, score);
            if (store) return storeToDatabase(id, score) > 0;
            return true;
        }
        return false;
    }
    
    private int storeToDatabase(GameID id, int score){
        try (Statement stmt = conn.createStatement()){
            String s = "INSERT INTO " + tableName + 
                    " (Difficulty, GameLevel, Steps) " + 
                    "VALUES('" + id.difficulty + "'," + id.level + 
                    "," + score + 
                    ") ON DUPLICATE KEY UPDATE Steps=" + score;
            return stmt.executeUpdate(s);
        } catch (Exception e){
            System.out.println("storeToDatabase error");
        }
        return 0;
    }
    
}
