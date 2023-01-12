package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import model.Direction;
import model.Game;
import model.GameID;

public class MainWindow extends JFrame{
    private final Game game;
    private Board board;
    private final JLabel gameStatLabel;    
    
    public MainWindow() throws IOException{
        game = new Game();
        
        setTitle("Sokoban");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        URL url = MainWindow.class.getClassLoader().getResource("res/box.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Játék");
        JMenu menuGameLevel = new JMenu("Pálya");
        JMenu menuGameScale = new JMenu("Nagyítás");
        createGameLevelMenuItems(menuGameLevel);
        createScaleMenuItems(menuGameScale, 1.0, 2.0, 0.5);

        JMenuItem menuHighScores = new JMenuItem(new AbstractAction("Dicsőség tábla") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighScoreWindow(game.getHighScores(), MainWindow.this);
            }
        });
        
        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Kilépés") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuGame.add(menuGameLevel);
        menuGame.add(menuGameScale);
        menuGame.add(menuHighScores);
        menuGame.addSeparator();
        menuGame.add(menuGameExit);
        menuBar.add(menuGame);
        setJMenuBar(menuBar);
        
        setLayout(new BorderLayout(0, 10));
        gameStatLabel = new JLabel("label");

        add(gameStatLabel, BorderLayout.NORTH);
        try { add(board = new Board(game), BorderLayout.CENTER); } catch (IOException ex) {}
        
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke); 
                if (!game.isLevelLoaded()) return;
                int kk = ke.getKeyCode();
                Direction d = null;
                switch (kk){
                    case KeyEvent.VK_LEFT:  d = Direction.LEFT; break;
                    case KeyEvent.VK_RIGHT: d = Direction.RIGHT; break;
                    case KeyEvent.VK_UP:    d = Direction.UP; break;
                    case KeyEvent.VK_DOWN:  d = Direction.DOWN; break;
                    case KeyEvent.VK_ESCAPE: game.loadGame(game.getGameID());
                }
                refreshGameStatLabel();
                board.repaint();
                if (d != null && game.step(d)){
                    if (game.isGameEnded()){
                        String msg = "Gratulálok!";
                        if (game.isBetterHighScore()){
                            msg += " Megjavítottad az eddigi teljesítményedet!";
                        }
                        JOptionPane.showMessageDialog(MainWindow.this, msg, "Játék vége", JOptionPane.INFORMATION_MESSAGE);
                    }
                } 
            }
        });

        setResizable(false);
        setLocationRelativeTo(null);
        game.loadGame(new GameID("EASY", 1));
        board.setScale(2.0); // board.refresh();
        pack();
        refreshGameStatLabel();
        setVisible(true);
    }
    
    private void refreshGameStatLabel(){
        String s = "Lépések száma: " + game.getNumSteps();
        s += ", dobozok a helyükön: " + game.getLevelNumBoxesInPlace() + "/" + game.getLevelNumBoxes();
        gameStatLabel.setText(s);
    }
    
    private void createGameLevelMenuItems(JMenu menu){
        for (String s : game.getDifficulties()){
            JMenu difficultyMenu = new JMenu(s);
            menu.add(difficultyMenu);
            for (Integer i : game.getLevelsOfDifficulty(s)){
                JMenuItem item = new JMenuItem(new AbstractAction("Level-" + i) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.loadGame(new GameID(s, i));
                        board.refresh();
                        pack();
                    }
                });
                difficultyMenu.add(item);
            }
        }
    }
    
    private void createScaleMenuItems(JMenu menu, double from, double to, double by){
        while (from <= to){
            final double scale = from;
            JMenuItem item = new JMenuItem(new AbstractAction(from + "x") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (board.setScale(scale)) pack();
                }
            });
            menu.add(item);
            
            if (from == to) break;
            from += by;
            if (from > to) from = to;
        }
    }
    
    public static void main(String[] args) {
        try {
            new MainWindow();
        } catch (IOException ex) {}
    }    
}
