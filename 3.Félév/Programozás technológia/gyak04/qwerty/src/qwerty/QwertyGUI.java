package qwerty;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class QwertyGUI {
    private final JFrame frame;
    private final JTextField output;
    private final JPanel buttons;
    private final JPanel result;

    
    public QwertyGUI(){
        frame = new JFrame("QWERTY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        result = new JPanel();
        result.setLayout(new BoxLayout(result,BoxLayout.X_AXIS));
        output = new JTextField(50);
        output.setEditable(false);
        result.add(output);
        
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(2, 4));
        JButton qButton = new JButton("Q");
        qButton.addActionListener(new QwertyActionListener("Q"));
        buttons.add(qButton);
        JButton wButton = new JButton("W");
        wButton.addActionListener(new QwertyActionListener("W"));
        buttons.add(wButton);
        JButton eButton = new JButton("E");
        eButton.addActionListener(new QwertyActionListener("E"));
        buttons.add(eButton);
        JButton rButton = new JButton("R");
        rButton.addActionListener(new QwertyActionListener("R"));
        buttons.add(rButton);
        JButton tButton = new JButton("T");
        tButton.addActionListener(new QwertyActionListener("T"));
        buttons.add(tButton);
        JButton yButton = new JButton("Y");
        yButton.addActionListener(new QwertyActionListener("Y"));
        buttons.add(yButton);
        JButton backButton = new JButton("<=");
        backButton.addActionListener(new QwertyActionListener("BACK"));
        buttons.add(backButton);
        JButton clearButton = new JButton("CLEAR");
        clearButton.addActionListener(new QwertyActionListener("CLEAR"));
        buttons.add(clearButton);
        
        frame.getContentPane().add(BorderLayout.SOUTH, buttons);
        frame.getContentPane().add(BorderLayout.NORTH, result);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu calcMenu = new JMenu("Menu");
        menuBar.add(calcMenu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        calcMenu.add(exitMenuItem);
        exitMenuItem.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        
        frame.pack();
        frame.setVisible(true);
    }
    
    class QwertyActionListener implements ActionListener{
        private final String type;
        
        public QwertyActionListener(String type) {
            this.type = type;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            String res ="";
                switch (type) {
                    case "Q" -> res = "Q";
                    case "W" -> res = "W";
                    case "E" -> res = "E";
                    case "R" -> res = "R";
                    case "T" -> res = "T";
                    case "Y" -> res = "Y";
                    case "CLEAR" -> output.setText("");
                    case "BACK" -> {
                    if (output.getText().equals("")) {
                    } else {
                        String val = output.getText();
                        output.setText(val.substring(0, val.length()-1));
                    }
                }
                }
            output.setText(output.getText() + res);
        }
    }
}
