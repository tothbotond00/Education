package horsegame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class StageGUI {

    private final HorseGameModell modell;
    private JButton selected;

    private final int type;
    private final JFrame frame;
    private final JLabel label;

    URL url1 = HorseGameGUI.class.getResource("icon.png");
    URL url2 = HorseGameGUI.class.getResource("white.png");
    URL url3 = HorseGameGUI.class.getResource("black.png");

    public StageGUI(int type) {

        this.type = type;
        frame = new JFrame(this.type + "x" + this.type + " Horse Game");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modell = new HorseGameModell(this.type);

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(url1));

        JPanel top = new JPanel();
        label = new JLabel();
        updateLabelText();
        top.add(label);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(type, type));

        for (int i = 0; i < type; ++i) {
            for (int j = 0; j < type; ++j) {
                addButton(mainPanel, i, j);
            }
        }

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(top, BorderLayout.NORTH);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

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
            StageGUI stage = new StageGUI(type);
            frame.dispose();
        });

        frame.pack();
        frame.setSize(600, 630);
        if (type == 8) {
            frame.setSize(800, 800);
        }
        frame.setVisible(true);
    }

    private void addButton(JPanel panel, int i, int j) {
        final JButton button = new JButton();
        button.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        button.setBorder(border);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///if the button has a horse in it
                if (modell.getCurrentPlayer() && modell.getWHorse1().getCor().getX() == i
                        && modell.getWHorse1().getCor().getY() == j) {
                    modell.select(modell.getWHorse1());
                    selected = button;
                } else if (modell.getCurrentPlayer() && modell.getWHorse2().getCor().getX() == i
                        && modell.getWHorse2().getCor().getY() == j) {
                    modell.select(modell.getWHorse2());
                    selected = button;
                } else if (!modell.getCurrentPlayer() && modell.getBHorse2().getCor().getX() == i
                        && modell.getBHorse2().getCor().getY() == j) {
                    modell.select(modell.getBHorse2());
                    selected = button;
                } else if (!modell.getCurrentPlayer() && modell.getBHorse1().getCor().getX() == i
                        && modell.getBHorse1().getCor().getY() == j) {
                    modell.select(modell.getBHorse1());
                    selected = button;
                } //if it is targeted by a horse BUT doesn't have a horse in it
                else if (!modell.horseList().contains(new Coordinate(i, j))) {
                    if (modell.getSelectedHorse() == null) ; else if (modell.getSelectedHorse().getColor() == Paint.WHITE) {
                        if (modell.getSelectedHorse().moves().contains(new Coordinate(i, j))) {
                            button.setIcon(new ImageIcon(url2));
                            button.setBackground(Color.WHITE);
                            selected.setIcon(null);
                            if (modell.getWHorse1().equals(modell.getSelectedHorse())) {
                                modell.Wmove1(i, j);
                            } else {
                                modell.Wmove2(i, j);
                            }
                            modell.deselect();
                            updateLabelText();
                            if (modell.getEnd()) {
                                showGameOverMessage();
                            }
                        }
                    } else if (modell.getSelectedHorse().getColor() == Paint.BLACK) {
                        if (modell.getSelectedHorse().moves().contains(new Coordinate(i, j))) {
                            button.setIcon(new ImageIcon(url3));
                            button.setBackground(Color.BLACK);
                            selected.setIcon(null);
                            if (modell.getBHorse1().equals(modell.getSelectedHorse())) {
                                modell.Bmove1(i, j);
                            } else {
                                modell.Bmove2(i, j);
                            }
                            modell.deselect();
                            updateLabelText();
                            if (modell.getEnd()) {
                                showGameOverMessage();
                            }
                        }
                    }
                }
            }
        });

        if (i == 0 && j == 0) {
            button.setIcon(new ImageIcon(url2));
            button.setBackground(Color.WHITE);
        }
        if (i == type - 1 && j == type - 1) {
            button.setIcon(new ImageIcon(url2));
            button.setBackground(Color.WHITE);
        }
        if (i == type - 1 && j == 0) {
            button.setIcon(new ImageIcon(url3));
            button.setBackground(Color.BLACK);
        }
        if (i == 0 && j == type - 1) {
            button.setIcon(new ImageIcon(url3));
            button.setBackground(Color.BLACK);
        }
        panel.add(button);
    }

    private void updateLabelText() {
        if (modell.getCurrentPlayer()) {
            label.setText("WHITE's turn!");
        } else {
            label.setText("BLACK's turn!");
        }
    }

    private void showGameOverMessage() {
        if (modell.getCurrentPlayer()) {
            JOptionPane.showMessageDialog(frame,
                    "GAME OVER! BLACK WINS!");
            StageGUI stage = new StageGUI(type);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame,
                    "GAME OVER! WHITE WINS!");
            StageGUI stage = new StageGUI(type);
            frame.dispose();
        }
    }

}
