package snakegame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class StartGUI {
    private final JFrame frame;
    
    /**
     * Shows the rules and makes it possible to navigate to the HighScoresGUI
     * and to the GameGUI.
     */
    public StartGUI(){
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        URL url = StartGUI.class.getResource("res\\snakefaceu.png");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        
        JPanel top = new JPanel();
        JLabel label1 = new JLabel();
        label1.setText("""
                      Rules:
                      """);
        JLabel label2 = new JLabel();
        label2.setText("""
                      The point of the game is to collect the most apples possible.
                      Your snake gets bigget everytime you eat an apple.
                      """);
        JLabel label3 = new JLabel();
        label3.setText("""
                      You can controll the snake with the arrow keys.
                      Watch out for rocks, because they kill you instantly if you hit them.
                      """);
        JLabel label4 = new JLabel();
        label4.setText("""
                      Also try to avoid the end of the frame because it kills you as well.
                      Don't touch yourself, because it ends the game as well.
                      """);
        JLabel label5 = new JLabel();
        label5.setText("""
                      If you have finished the game you can save you score and name into the Highscore list.
                      """);
        top.add(label1);
        top.add(label2);
        top.add(label3);
        top.add(label4);
        
        frame.getContentPane().add(top);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenuItem newGame = new JMenuItem("New Game");
        menu.add(newGame);
        newGame.addActionListener((ActionEvent ae) -> {
            GameGUI gui = new GameGUI();
        });
        JMenuItem highScore = new JMenuItem("HighScroes");
        menu.add(highScore);
        highScore.addActionListener((ActionEvent ae) -> {
            HighScoreGUI gui = new HighScoreGUI();
        });
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        menu.add(exitMenuItem);
        exitMenuItem.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        
        frame.pack();
        frame.setSize(810, 130);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
