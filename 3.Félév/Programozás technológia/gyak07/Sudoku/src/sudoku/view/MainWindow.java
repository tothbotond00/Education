package sudoku.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import sudoku.model.Difficulty;
import sudoku.model.Model;
import sudoku.persistence.Persistence;
import sudoku.persistence.SudokuIOException;

public class MainWindow extends JFrame {

    private Model model;
    private final Persistence persistence
            = new Persistence();

    private MenuBar menuBar;

    private final JButton[][] grid = new JButton[Model.TABLE_SIZE][Model.TABLE_SIZE];

    private boolean changed = false;

    private Timer moveChecker;
    private Timer clock;

    private JLabel timeLabel;

    public MainWindow() {
        setTitle("Sudoku");
        setSize(400, 450);

        URL url = MainWindow.class.getResource("icon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (changed) {
                    showExitConfirmation();
                } else {
                    System.exit(0);
                }
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(Model.TABLE_SIZE, Model.TABLE_SIZE));

        for (int i = 0; i < Model.TABLE_SIZE; ++i) {
            for (int j = 0; j < Model.TABLE_SIZE; ++j) {
                addButton(mainPanel, i, j);
            }
        }

        timeLabel = new JLabel();
        updateTimeLabel();

        setLayout(new BorderLayout());
        add(timeLabel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        menuBar = new MenuBar(saveAction, loadAction, startNewGameAction, checkAction);

        setJMenuBar(menuBar);
        saveAction.setEnabled(false);
        checkAction.setEnabled(false);
    }

    private void showExitConfirmation() {
        stopTimers();

        int n = JOptionPane.showConfirmDialog(this,
                "Valóban ki akar lépni mentés nélkül?",
                "Megerősítés", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

        startTimers();
    }

    private final Action saveAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            stopTimers();

            FileChooser chooser = new FileChooser("Mentés");
            File chosenFile = chooser.open();
            if (chosenFile != null) {
                try {
                    persistence.save(chosenFile, model);
                    changed = false;
                } catch (SudokuIOException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this,
                            "Hiba történt a fájl mentésekor.");
                }
            }

            startTimers();
        }
    };

    private final Action loadAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            stopTimers();

            FileChooser chooser = new FileChooser("Betöltés");
            File chosenFile = chooser.open();
            if (chosenFile != null) {
                try {
                    model = persistence.load(chosenFile);
                    saveAction.setEnabled(true);
                    checkAction.setEnabled(true);
                    resetGridAndTimers();
                } catch (SudokuIOException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this,
                            "Hiba történt a fájl betöltésekor.");
                }
            }

            startTimers();
        }
    };

    private final Action startNewGameAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            stopTimers();

            Difficulty difficulty = menuBar.getDifficulty();
            try {
                model = persistence.loadNewGame(difficulty);
                changed = true;
                saveAction.setEnabled(true);
                checkAction.setEnabled(true);
                resetGridAndTimers();
            } catch (SudokuIOException ex) {
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Hiba történt új játék betöltésekor.");
            }

            startTimers();
        }
    };

    private void addButton(JPanel mainPanel, int i, int j) {
        grid[i][j] = new JButton();
        mainPanel.add(grid[i][j]);

        grid[i][j].addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                if (moveChecker != null) {
                    moveChecker.restart();
                }
            }

        });

        grid[i][j].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (model == null) {
                    return;
                }

                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        model.nextNumber(i, j);
                        updateTextOfButton(i, j);
                        break;
                    case MouseEvent.BUTTON3:
                        model.clear(i, j);
                        updateTextOfButton(i, j);
                        break;
                }

                changed = true;
            }

        });

    }

    private final Action checkAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            stopTimers();

            int errors = model.errorCount();

            if (errors == 0) {
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Gratulálunk! Ön nyert!");
                try {
                    model = persistence.loadNewGame(model.getDifficulty(), model.getGameID());
                    resetGridAndTimers();
                } catch (SudokuIOException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this,
                            "Hiba történt új játék betöltése közben.");
                }
            } else {
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Sajnos a feladvány még nincs kész. Hibás/hátralévő mezők: " + errors);
            }

            startTimers();
        }

    };

    private void resetGridAndTimers() {
        for (int i = 0; i < Model.TABLE_SIZE; ++i) {
            for (int j = 0; j < Model.TABLE_SIZE; ++j) {
                updateTextOfButton(i, j);
                if (model != null) {
                    if (model.isFixed(i, j)) {
                        grid[i][j].setEnabled(false);
                    } else {
                        grid[i][j].setEnabled(true);
                    }
                }
            }
        }

        stopTimers();

        moveChecker = new Timer(5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimers();
                JOptionPane.showMessageDialog(MainWindow.this, "5 másodperce nem aktív!");
                startTimers();
            }

        });
        moveChecker.setRepeats(false);
        moveChecker.start();

        clock = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.advanceTime();
                updateTimeLabel();
            }

        });
        clock.start();
        
        updateTimeLabel();
    }

    private void updateTextOfButton(int i, int j) {
        int num = model.getNumber(i, j);
        if (num == 0) {
            grid[i][j].setText("");
        } else {
            grid[i][j].setText(String.valueOf(num));
        }
    }

    private void stopTimers() {
        if (moveChecker != null) {
            moveChecker.stop();
        }
        if (clock != null) {
            clock.stop();
        }
    }

    private void startTimers() {
        if (moveChecker != null) {
            moveChecker.restart();
        }
        if (clock != null) {
            clock.restart();
        }
    }

    private void updateTimeLabel() {
        if (model == null) {
            timeLabel.setText("Nincs aktív játék.");
        } else {
            timeLabel.setText("Eltelt idő: " + model.getTime() + " mp");
        }
    }

}
