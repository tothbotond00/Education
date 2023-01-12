package snakegame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PopUpGUI {
    private final JFrame frame;
    private final JTextField input;
    private final int score;
    
    /**
     * Shows a pop-up screen for your name and score to save.
     * Note: if you exit the GUI your score won't be saved!
     * @param score 
     */
    public PopUpGUI(int score){
        this.score = score;
        frame = new JFrame("INSERT NAME");
        frame.setTitle("INSERT NAME");
        frame.setResizable(false);
        
        URL url = PopUpGUI.class.getResource("res\\snakefaceu.png");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result,BoxLayout.X_AXIS));
        input = new JTextField(20);
        result.add(input);
        JButton qButton = new JButton("SUBMIT");
        qButton.addActionListener(new SubmitActionListener());
        result.add(qButton);
        
        frame.add(result);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);        
    }
    
    class SubmitActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(input.getText().length() != 0){
                try{
                    HighScores highScores = new HighScores(10);
                    highScores.putHighScore(input.getText(), score);
                }catch(SQLException ex){
                    Logger.getLogger(PopUpGUI.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    frame.dispose();
                }
            }
        }
    }

}
