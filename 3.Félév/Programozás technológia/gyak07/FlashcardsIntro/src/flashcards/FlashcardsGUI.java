package flashcards;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FlashcardsGUI {
    private final FlashcardsModel model;

    private final JFrame frame;
    private final JPanel northPanel;
    private final JPanel southPanel;
    private final JTextArea display;
    private final JLabel scoreLabel;

    public FlashcardsGUI() {
        model = new FlashcardsModel();
        frame = new JFrame("Flashcards");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        northPanel = new JPanel();
        display = new JTextArea("Please open a cards file.", 10, 40);
        display.setEditable(false);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        northPanel.add(display);
        frame.getContentPane().add(northPanel, BorderLayout.NORTH);

        southPanel = new JPanel();
        scoreLabel = new JLabel("0/0");
        southPanel.add(scoreLabel);
        ArrayList<String> buttonLabels = new ArrayList<>(
                Arrays.asList("Reset", "Toggle Q/A", "Wrong answer", "Good answer"));
        ArrayList<ActionListener> listeners = new ArrayList<>(
                Arrays.asList(new ResetButtonActionListener(),
                        new ToggleButtonActionListener(),
                        new AnswerButtonActionListener(false),
                        new AnswerButtonActionListener(true)));
        for (int i = 0; i < buttonLabels.size(); ++i) {
            JButton button = new JButton(buttonLabels.get(i));
            southPanel.add(button);
            button.addActionListener(listeners.get(i));
        }
        frame.getContentPane().add(southPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openMenuItem = new JMenuItem("Open...");
        openMenuItem.addActionListener(new OpenActionListener());
        fileMenu.add(openMenuItem);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });

        frame.pack();
        frame.setVisible(true);
    }

    public void reset() {
        model.reset();
        updateScore();
        updateDisplay();
    }

    private void updateScore() {
        scoreLabel.setText(model.getScore() + "/" + model.getCardNumber());
    }

    private void updateDisplay() {
        display.setText(model.currentCardText());
    }

    class OpenActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("C:\\Users\\LENOVO\\Desktop\\Tantárgyak\\Programozás technológia (JAVA)\\gyak07\\FlashcardsIntro"));
            int returnVal = fc.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    model.read(file);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, "File not found!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "IO error!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                reset();
            }
        }
    }

    class ToggleButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.invertQuestion();
            updateDisplay();
        }

    }

    class ResetButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.reset();
            updateScore();
            updateDisplay();
        }

    }

    class AnswerButtonActionListener implements ActionListener {

        private final boolean incScore;

        public AnswerButtonActionListener(boolean incScore) {
            this.incScore = incScore;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            model.nextCard(incScore);
            updateScore();
            updateDisplay();
        }

    }

}