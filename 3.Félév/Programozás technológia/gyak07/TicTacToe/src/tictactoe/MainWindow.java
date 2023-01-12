package tictactoe;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class MainWindow extends BaseWindow {
    
    private List<Window> gameWindows = new ArrayList<>();
    
    public MainWindow() {
        
        JButton small = new JButton();
        small.setText("3 x 3");
        
        small.addActionListener(getActionListener(3));
        
        JButton medium = new JButton();
        medium.setText("4 x 4");

        medium.addActionListener(getActionListener(4));

        JButton large = new JButton();
        large.setText("5 x 5");
        
        large.addActionListener(getActionListener(5));
        
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(small);
        getContentPane().add(medium);
        getContentPane().add(large);
    }
    
    private ActionListener getActionListener(final int size) {
        return new ActionListener() { 

            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window(size, MainWindow.this);
                window.setVisible(true);
                gameWindows.add(window);
            }
            
        };
    }
    
    public List<Window> getWindowList() {
        return gameWindows;
    }
    
    @Override
    protected void doUponExit() {
        System.exit(0);
    }

}
