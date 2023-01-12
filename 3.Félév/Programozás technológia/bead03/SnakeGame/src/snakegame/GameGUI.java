package snakegame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameGUI {
    private final JFrame frame;
    
    /**
     * Shows the GamePanel.
     */
    public GameGUI(){
        frame = new JFrame();
        frame.add(new GamePanel());
        frame.setTitle("SnakeGame");
        frame.setResizable(false);
        
        URL url = GameGUI.class.getResource("res\\snakefaceu.png");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenuItem newMenuItem = new JMenuItem("New Game");
        menu.add(newMenuItem);
        JMenuItem closeMenuItem = new JMenuItem("Close Application");
        menu.add(closeMenuItem);
        JMenuItem exitMenuItem = new JMenuItem("Exit from Application");
        menu.add(exitMenuItem);
        closeMenuItem.addActionListener((ActionEvent ae) -> {
            frame.dispose();
        });
        exitMenuItem.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        newMenuItem.addActionListener((ActionEvent ae) -> {
            GameGUI stage = new GameGUI();
            frame.dispose();
        });
        
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
