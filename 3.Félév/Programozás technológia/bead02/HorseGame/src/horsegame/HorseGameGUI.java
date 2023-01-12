package horsegame;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class HorseGameGUI {

    private final JFrame frame;
    private final JPanel panel;
    private final JPanel panel2;
    private final JRadioButton jRadioButton4;
    private final JRadioButton jRadioButton6;
    private final JRadioButton jRadioButton8;

    public HorseGameGUI() {
        frame = new JFrame("Horse Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel2 = new JPanel();

        URL url = HorseGameGUI.class.getResource("icon.png");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        jRadioButton4 = new JRadioButton("4x4", true);
        jRadioButton6 = new JRadioButton("6x6");
        jRadioButton8 = new JRadioButton("8x8");
        JButton newgame = new JButton();

        ButtonGroup btg = new ButtonGroup();
        btg.add(jRadioButton4);
        btg.add(jRadioButton6);
        btg.add(jRadioButton8);

        newgame.setText("New Game");
        newgame.addActionListener(new Start());

        panel.add(jRadioButton4);
        panel.add(jRadioButton6);
        panel.add(jRadioButton8);
        panel2.add(newgame);

        frame.getContentPane().add(panel, BorderLayout.NORTH);

        frame.getContentPane().add(panel2, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        menu.add(exitMenuItem);
        exitMenuItem.addActionListener((ActionEvent ae) -> {
            HorseGameGUI gui = new HorseGameGUI();
        });

        frame.pack();
        frame.setSize(250, 135);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class Start implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jRadioButton4.isSelected()) {
                StageGUI stage = new StageGUI(4);
            } else if (jRadioButton6.isSelected()) {
                StageGUI stage = new StageGUI(6);
            } else {
                StageGUI stage = new StageGUI(8);
            }
        }
    }
}
