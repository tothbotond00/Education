package snakegame;

public class HighScore {
    
    private final String name;
    private final int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "HighScore{" + "name=" + name + ", score=" + score + '}';
    }
    

}
