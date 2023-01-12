package sudoku.model;

public class Model {

    public static final int BOX_SIZE = 3;
    public static final int TABLE_SIZE = 9;

    private final Difficulty difficulty;
    private final int gameID;
    private final int[][] table;
    private final boolean[][] fixed;
    private final int[][] solution;
    private int time;

    public Model(Difficulty difficulty, int gameID, int[][] table, boolean[][] fixed,
            int[][] solution, int time) {
        this.difficulty = difficulty;
        this.gameID = gameID;
        this.table = table;
        this.fixed = fixed;
        this.solution = solution;
        this.time = time;
    }

    public void nextNumber(int row, int column) {
        if (fixed[row][column]) {
            return;
        }
        int currentValue = solution[row][column];
        solution[row][column] = 0;
        
        int counter = Model.TABLE_SIZE;
        int newValue = currentValue;
        boolean isUnique;
        
        do {
            --counter;
            isUnique = true;
            newValue = inc(newValue);
            
            for (int i = 0; i < TABLE_SIZE && isUnique; ++i) {
                if (table[i][column] == newValue) {
                    isUnique = false;
                }
            }
            for (int i = 0; i < TABLE_SIZE && isUnique; ++i) {
                if (table[row][i] == newValue) {
                    isUnique = false;
                }
            }
            for (int i = (row / BOX_SIZE) * BOX_SIZE;
                    i < (row / BOX_SIZE) * BOX_SIZE + BOX_SIZE && isUnique; ++i) {
                
                for (int j = (column / BOX_SIZE) * BOX_SIZE;
                        j < (column / BOX_SIZE) * BOX_SIZE + BOX_SIZE && isUnique; ++j) {
                    
                    if (table[i][j] == newValue) {
                        isUnique = false;
                    }
                }
            }

        } while (!isUnique && counter > 0);
        
        if (isUnique) {
            table[row][column] = newValue;
        } else {
            table[row][column] = 0;
        }
    }

    public void clear(int row, int column) {
        if (fixed[row][column]) {
            return;
        }
        table[row][column] = 0;
    }

    public int errorCount() {
        int count = 0;
        for (int i = 0; i < Model.TABLE_SIZE; ++i) {
            for (int j = 0; j < Model.TABLE_SIZE; ++j) {
                if (table[i][j] != solution[i][j]) {
                    ++count;
                }
            }
        }
        return count;
    }

    public void advanceTime() {
        ++time;
    }

    private int inc(int i) {
        if (i < Model.TABLE_SIZE) {
            return i + 1;
        }
        return 1;
    }

    // getters

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getGameID() {
        return gameID;
    }

    public int getNumber(int row, int column) {
        return table[row][column];
    }

    public boolean isFixed(int row, int column) {
        return fixed[row][column];
    }

    public int getTime() {
        return time;
    }

}
