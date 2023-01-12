package tictactoe;

public class Model {

    private int size;

    private Player actualPlayer;

    private Player[][] table;

    public Model(int size) {
        this.size = size;
        actualPlayer = Player.X;

        table = new Player[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                table[i][j] = Player.NOBODY;
            }
        }
    }

    public Player step(int row, int column) {
        if (table[row][column] != Player.NOBODY) {
            return table[row][column];
        }

        table[row][column] = actualPlayer;

        if (actualPlayer == Player.X) {
            actualPlayer = Player.O;
        } else {
            actualPlayer = Player.X;
        }

        return table[row][column];
    }

    public Player findWinner() {
        for (int i = 0; i < size; ++i) {
            if (table[0][i] != Player.NOBODY) {
                boolean ok = true;
                for (int j = 1; ok && j < size; ++j) {
                    ok = ok && table[j][i] == table[0][i];
                }
                if (ok) {
                    return table[0][i];
                }
            }
        }
        for (int i = 0; i < size; ++i) {
            if (table[i][0] != Player.NOBODY) {
                boolean ok = true;
                for (int j = 1; ok && j < size; ++j) {
                    ok = ok && table[i][j] == table[i][0];
                }
                if (ok) {
                    return table[i][0];
                }
            }
        }
        if (table[0][0] != Player.NOBODY) {
            boolean ok = true;
            for (int j = 1; ok && j < size; ++j) {
                ok = ok && table[j][j] == table[0][0];
            }
            if (ok) {
                return table[0][0];
            }
        }
        if (table[0][size - 1] != Player.NOBODY) {
            boolean ok = true;
            for (int j = 1; ok && j < size; ++j) {
                ok = ok && table[j][size - 1 - j] == table[0][size - 1];
            }
            if (ok) {
                return table[0][size - 1];
            }
        }

        return Player.NOBODY;
    }

    public Player getActualPlayer() {
        return actualPlayer;
    }

}
