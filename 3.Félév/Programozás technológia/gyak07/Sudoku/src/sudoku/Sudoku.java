package sudoku;

import sudoku.persistence.SudokuIOException;
import sudoku.view.MainWindow;

public class Sudoku {

    public static void main(String[] args) throws SudokuIOException {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }

}
