package horsegame;

import java.util.ArrayList;

public class HorseGameModell {

    private final int size;
    private final Tile[][] table;
    private final Horse WHorse1;
    private final Horse WHorse2;
    private final Horse BHorse1;
    private final Horse BHorse2;
    private boolean end;
    //currentPlayer is true when it is White's turn otherwise it is Black's turn
    private boolean currentPlayer;
    private final Horse selectedHorse;
    private boolean isSelect;

    /**
     * Initilasizes the table. Sets all tile colors to gray and puts down the 4
     * horses.
     *
     * @param size the size of the table (can be 4x4, 6x6 and 8x8)
     */
    public HorseGameModell(int size) {
        this.size = size;
        table = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = new Tile();
            }
        }
        WHorse1 = new Horse(0, 0, Paint.WHITE, size);
        WHorse2 = new Horse(size - 1, size - 1, Paint.WHITE, size);
        BHorse1 = new Horse(size - 1, 0, Paint.BLACK, size);
        BHorse2 = new Horse(0, size - 1, Paint.BLACK, size);

        table[0][0].putHorse();
        table[size - 1][size - 1].putHorse();
        table[size - 1][0].putHorse();
        table[0][size - 1].putHorse();

        table[0][0].setColor(Paint.WHITE);
        table[size - 1][size - 1].setColor(Paint.WHITE);
        table[size - 1][0].setColor(Paint.BLACK);
        table[0][size - 1].setColor(Paint.BLACK);
        currentPlayer = true;
        selectedHorse = new Horse(WHorse1);
        isSelect = false;
        end = false;
    }

    /**
     * Selects a horse which will move in the next action.
     *
     * @param h the selected horse
     */
    public void select(Horse h) {
        selectedHorse.change(h);
        isSelect = true;
    }

    /**
     * Deselects the horse which was selected.
     */
    public void deselect() {
        isSelect = false;
    }

    public Horse getSelectedHorse() {
        if (!isSelect) {
            return null;
        }
        Horse h = new Horse(selectedHorse);
        return h;
    }

    public boolean getEnd() {
        return end;
    }

    /**
     * Moves the White Horse1 to the coordinate
     *
     * @param x the x coordinate of the target
     * @param y the y coordinate of the target
     */
    public void Wmove1(int x, int y) {
        table[WHorse1.getCor().getX()][WHorse1.getCor().getY()].removeHorse();
        WHorse1.go(x, y);
        table[x][y].putHorse();
        table[x][y].setColor(Paint.WHITE);
        end = isWin(Paint.WHITE);
        currentPlayer = !currentPlayer;
    }

    /**
     * Moves the White Horse2 to the coordinate
     *
     * @param x the x coordinate of the target
     * @param y the y coordinate of the target
     */
    public void Wmove2(int x, int y) {
        table[WHorse2.getCor().getX()][WHorse2.getCor().getY()].removeHorse();
        WHorse2.go(x, y);
        table[x][y].putHorse();
        table[x][y].setColor(Paint.WHITE);
        end = isWin(Paint.WHITE);
        currentPlayer = !currentPlayer;
    }

    /**
     * Moves the Black Horse1 to the coordinate
     *
     * @param x the x coordinate of the target
     * @param y the y coordinate of the target
     */
    public void Bmove1(int x, int y) {
        table[BHorse1.getCor().getX()][BHorse1.getCor().getY()].removeHorse();
        BHorse1.go(x, y);
        table[x][y].putHorse();
        table[x][y].setColor(Paint.BLACK);
        end = isWin(Paint.BLACK);
        currentPlayer = !currentPlayer;
    }

    /**
     * Moves the Black Horse2 to the coordinate
     *
     * @param x the x coordinate of the target
     * @param y the y coordinate of the target
     */
    public void Bmove2(int x, int y) {
        table[BHorse2.getCor().getX()][BHorse2.getCor().getY()].removeHorse();
        BHorse2.go(x, y);
        table[x][y].putHorse();
        table[x][y].setColor(Paint.BLACK);
        end = isWin(Paint.BLACK);
        currentPlayer = !currentPlayer;
    }

    public Horse getWHorse1() {
        return new Horse(WHorse1);
    }

    public Horse getWHorse2() {
        return new Horse(WHorse2);
    }

    public Horse getBHorse1() {
        return new Horse(BHorse1);
    }

    public Horse getBHorse2() {
        return new Horse(BHorse2);
    }

    public Tile[][] getTiles() {
        Tile[][] table2 = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table2[i][j] = new Tile(table[i][j]);
            }
        }
        return table2;
    }

    public boolean getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * returns if the game is finished for the specific color type
     *
     * @param color the color we search through
     * @return if the game has ended
     */
    public boolean isWin(Paint color) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (table[i][j].getColor() == color) {
                    int counter = 1;
                    j++;
                    while (counter != 4 && j < size && table[i][j].getColor() == color) {
                        counter++;
                        j++;
                    }
                    if (counter == 4) {
                        return true;
                    }
                }
            }
        }
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (table[i][j].getColor() == color) {
                    int counter = 1;
                    i++;
                    while (counter != 4 && i < size && table[i][j].getColor() == color) {
                        counter++;
                        i++;
                    }
                    if (counter == 4) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (table[i][i].getColor() == color) {
                int counter = 1;
                i++;
                while (counter != 4 && i < size && table[i][i].getColor() == color) {
                    counter++;
                    i++;
                }
                if (counter == 4) {
                    return true;
                }
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            if (table[i][size - i - 1].getColor() == color) {
                int counter = 1;
                i--;
                while (counter != 4 && i >= 0 && table[i][size - i - 1].getColor() == color) {
                    counter++;
                    i--;
                }
                if (counter == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Coordinate> horseList() {
        ArrayList<Coordinate> ar = new ArrayList<>();
        ar.add(new Coordinate(WHorse1.getCor()));
        ar.add(new Coordinate(WHorse2.getCor()));
        ar.add(new Coordinate(BHorse1.getCor()));
        ar.add(new Coordinate(BHorse2.getCor()));
        return ar;
    }

}
