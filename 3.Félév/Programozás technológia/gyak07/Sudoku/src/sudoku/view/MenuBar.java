package sudoku.view;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import sudoku.model.Difficulty;

public class MenuBar extends JMenuBar {
    
    private Difficulty difficulty = Difficulty.EASY;
    
    public MenuBar(Action saveAction, Action loadAction,
            Action startNewGameAction, Action checkAction) {
        
        JMenu fileMenu = new JMenu("Fájl");
        fileMenu.setMnemonic('F');

        JMenuItem save = new JMenuItem(saveAction);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        save.setText("Mentés");
        save.setMnemonic('M');
        fileMenu.add(save);

        JMenuItem load = new JMenuItem(loadAction);
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));
        load.setText("Betöltés");
        load.setMnemonic('B');
        fileMenu.add(load);

        JMenu newGameMenu = new JMenu("Új játék");
        
        JMenuItem startNewGame = new JMenuItem(startNewGameAction);
        startNewGame.setText("Indítás");
        
        newGameMenu.add(startNewGame);
        
        newGameMenu.addSeparator();
        
        ButtonGroup group = new ButtonGroup();
        
        JRadioButtonMenuItem easy = new JRadioButtonMenuItem();
        easy.setText("Könnyű");
        easy.setSelected(true);
        easy.addActionListener(actionListener);
        easy.setActionCommand(Difficulty.EASY.name());
        group.add(easy);
        
        JRadioButtonMenuItem medium = new JRadioButtonMenuItem();
        medium.setText("Közepes");
        medium.addActionListener(actionListener);
        medium.setActionCommand(Difficulty.MEDIUM.name());
        group.add(medium);
        
        JRadioButtonMenuItem hard = new JRadioButtonMenuItem();
        hard.setText("Nehéz");
        hard.addActionListener(actionListener);
        hard.setActionCommand(Difficulty.HARD.name());
        group.add(hard);
        
        newGameMenu.add(easy);
        newGameMenu.add(medium);
        newGameMenu.add(hard);
        
        JMenuItem checkMenuItem = new JMenuItem(checkAction);
        checkMenuItem.setText("Ellenőrzés");
        
        add(fileMenu);
        add(newGameMenu);
        add(checkMenuItem);
    }

    private ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            difficulty = Difficulty.valueOf(actionCommand);
        }
        
    };

    public Difficulty getDifficulty() {
        return difficulty;
    }    
}
