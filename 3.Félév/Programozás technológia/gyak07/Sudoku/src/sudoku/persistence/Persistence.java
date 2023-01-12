package sudoku.persistence;

import java.io.File;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sudoku.model.Difficulty;
import sudoku.model.Model;
import sudoku.resources.Resources;

public class Persistence {

    public Model loadNewGame(Difficulty difficulty, int currentIndex)
            throws SudokuIOException {
        String[] availableGames = null;

        switch (difficulty) {
            case EASY:
                availableGames = Resources.EASY_GAMES;
                break;
            case MEDIUM:
                availableGames = Resources.MEDIUM_GAMES;
                break;
            case HARD:
                availableGames = Resources.HARD_GAMES;
                break;
        }

        int index;
        Random random = new Random();
        if (currentIndex >= 0) { // Ha egy pályát ki akarunk hagyni.
            index = random.nextInt(availableGames.length - 1);
            if (index >= currentIndex) {
                ++index;
            }
        } else { // Ha bármelyik pálya választható.
            index = random.nextInt(availableGames.length);
        }

        String fileName = availableGames[index];

        try {
            return load(Resources.getResource(fileName));
        } catch (URISyntaxException ex) {
            throw new SudokuIOException();
        }
    }

    public Model loadNewGame(Difficulty difficulty)
            throws SudokuIOException {
        return loadNewGame(difficulty, -1);
    }

    public Model load(File file) throws SudokuIOException {
        try (Scanner sc = new Scanner(file)) {
            Difficulty difficulty = Difficulty.valueOf(sc.next().trim());

            int gameID = sc.nextInt();

            if (gameID < 0) {
                throw new SudokuIOException();
            }

            int[][] table = new int[Model.TABLE_SIZE][Model.TABLE_SIZE];
            boolean[][] fixed = new boolean[Model.TABLE_SIZE][Model.TABLE_SIZE];

            for (int i = 0; i < Model.TABLE_SIZE; ++i) {
                for (int j = 0; j < Model.TABLE_SIZE; ++j) {
                    table[i][j] = sc.nextInt();
                    if (table[i][j] < -Model.TABLE_SIZE || table[i][j] > Model.TABLE_SIZE) {
                        throw new SudokuIOException();
                    }
                    if (table[i][j] <= 0) {
                        fixed[i][j] = false;
                        table[i][j] = -table[i][j];
                    } else {
                        fixed[i][j] = true;
                    }
                }
            }

            int[][] solution = loadSolutionFor(difficulty, gameID);

            int time = 0;
            if (sc.hasNextInt()) {
                time = sc.nextInt();
            }

            return new Model(difficulty, gameID, table, fixed, solution, time);
        } catch (Exception e) {
            throw new SudokuIOException();
        }
    }

    private int[][] loadSolutionFor(Difficulty difficulty, int gameID)
            throws SudokuIOException {
        String fileName = "";
        switch (difficulty) {
            case EASY:
                fileName = Resources.EASY_SOLUTIONS[gameID];
                break;
            case MEDIUM:
                fileName = Resources.MEDIUM_SOLUTIONS[gameID];
                break;
            case HARD:
                fileName = Resources.HARD_SOLUTIONS[gameID];
                break;
        }

        File file;
        try {
            file = Resources.getResource(fileName);
        } catch (URISyntaxException ex) {
            throw new SudokuIOException();
        }

        try (Scanner sc = new Scanner(file)) {
            int[][] solution = new int[Model.TABLE_SIZE][Model.TABLE_SIZE];

            for (int i = 0; i < Model.TABLE_SIZE; ++i) {
                for (int j = 0; j < Model.TABLE_SIZE; ++j) {
                    solution[i][j] = sc.nextInt();
                    if (solution[i][j] < 0 || solution[i][j] > Model.TABLE_SIZE) {
                        throw new SudokuIOException();
                    }
                }
            }

            return solution;
        } catch (Exception e) {
            throw new SudokuIOException();
        }
    }

    public void save(File file, Model model) throws SudokuIOException {
        if (file == null || model == null) {
            throw new IllegalArgumentException();
        }
        try (PrintWriter pw = new PrintWriter(file)) {

            pw.println(model.getDifficulty() + " " + model.getGameID());
            for (int i = 0; i < Model.TABLE_SIZE; ++i) {
                for (int j = 0; j < Model.TABLE_SIZE; ++j) {
                    int num = model.getNumber(i, j);
                    if (!model.isFixed(i, j)) {
                        num = -num;
                    }
                    pw.print(num + " ");
                }
                pw.println();
            }

            pw.println(model.getTime());
        } catch (Exception e) {
            throw new SudokuIOException();
        }
    }

}
