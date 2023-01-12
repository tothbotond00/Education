/**
 * This package contains the GUI
 */
package oracleTower;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import oracleTower.modell.Game;
import oracleTower.player.Destroyer;
import oracleTower.player.Fighter;
import oracleTower.player.Mage;
import oracleTower.player.NormalSoldier;
import oracleTower.view.Map;
import oracleTower.player.Support;

/**
 *
 * @author Vajda Marián JG3IH8
 * This class sets up the JFrame where the map gets printed and runs the game.
 * Also contains all the menu options and dialogs needed for the game.
 */
public class MainWindow extends JFrame{
    private Game game;
    private Map map;
    private String name1;
    private String name2;
    private Timer timer;
    private int roundCounter = 1;
    Icon dialogIcon = new ImageIcon("oracleTower/res/logo.png");
    
    
    public MainWindow() throws IOException{

        /**
        * We set up the basic information of the Frame here.
        */
        setTitle("Oracle Tower");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        URL url = MainWindow.class.getClassLoader().getResource("oracleTower/res/logo.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
              
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Menu");
        
        JMenuItem menuNewGame;
        menuNewGame = new JMenuItem(new AbstractAction("New Game"){
            @Override
            public void actionPerformed(ActionEvent e) {
               newGame();
            }
        });
        
        
        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        menuGame.add(menuNewGame);
        menuGame.addSeparator();
        menuGame.add(menuGameExit);
        menuBar.add(menuGame);
        setJMenuBar(menuBar);
        
        setLayout(new BorderLayout(0, 10));
 
        /**
        * Here we ask for the information of the players, set up the board and
        * start the game itself and the timer needed for it
        */
        
        
        setResizable(false);
        askName1("Name of the first player:");
        askName2("Name of the second player:");
        game = new Game(name1,name2);
        try { add(map = new Map(game), BorderLayout.NORTH);} catch (IOException ex) {}
        map.refresh();
        
       ///THIS PART CREATES THE INTERACTIVE UI FOR THE WINDOW
        Box gameInfo = Box.createHorizontalBox();
        JLabel currentRound = new JLabel ("Round: " + roundCounter);
        currentRound.setBorder(new EmptyBorder(0, 25, 0, 80));
        currentRound.setFont(new Font("Calibri", Font.BOLD, 50));
        JLabel currentPlayer = new JLabel ( name1 +"'s turn!" );
        currentPlayer.setBorder(new EmptyBorder(0, 0, 0, 80));
        currentPlayer.setFont(new Font("Calibri", Font.BOLD, 50));
        currentPlayer.setForeground(Color.RED);
        JLabel currentMoney = new JLabel(game.getPlayer1().getMoney()+" $ ");
        currentMoney.setBorder(new EmptyBorder(0, 0, 0, 80));
        currentMoney.setFont(new Font("Calibri", Font.BOLD, 50));
        currentMoney.setForeground(Color.GREEN);
        
        gameInfo.add(currentRound);
        gameInfo.add(currentPlayer);
        gameInfo.add(currentMoney);
        
    
        add(gameInfo,BorderLayout.CENTER);
        
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(0, 0, 15, 0));
        box.add(Box.createRigidArea(new Dimension(25,0)));
        initilaizeJButton(box, "Normal", "oracleTower/res/normal3.png", "normal soldier",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Support", "oracleTower/res/support3.png","support soldier",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Destoryer", "oracleTower/res/destroyer3.png", "destroyer soldier",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Fighter", "oracleTower/res/fighter3.png", "fighter soldier",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Mage", "oracleTower/res/mage3.png", "mage soldier",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Arrows", "oracleTower/res/arrows.png", "arrows spell",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Cannon", "oracleTower/res/cannon.png", "cannon spell",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Heal", "oracleTower/res/heal.png", "heal spell",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Normal", "oracleTower/res/normalt3.png", "normal tower",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Tank", "oracleTower/res/tankt3.png", "tank tower",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Damage", "oracleTower/res/damaget3.png", "damage tower",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Mine", "oracleTower/res/mine3.png", "mine",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "End turn", "oracleTower/res/stop.png", "stop",currentPlayer,currentRound,currentMoney);
        add(box,BorderLayout.SOUTH);
        pack();
        setVisible(true);
        
        //Timer needed for refreshing the map
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                writePlayer(currentPlayer,currentRound,currentMoney);
            }
        });
        timer.start();
        
    }

    /**
    * Asks for names and checks them, makes sure the names are not empty and do not match
    */
    private void askName1(String s){
        name1 = JOptionPane.showInputDialog(MainWindow.this, s);
        if(name1.length() == 0){
            this.askName1("Első játékos neve újra (nem lehet üres):");
        }
    }
    
    private void askName2(String s){
        name2 = JOptionPane.showInputDialog(MainWindow.this, s);
        if(name2.length() == 0){
            this.askName2("Második játékos neve újra (nem lehet üres):");
        }
        if(name2.compareTo(name1) == 0){
            this.askName2("Második játékos neve újra (nem lehet az első játékos neve):");
        }
    }
    
    /**
     * Makes a JButton with the following parameters
     * @param box The box where the button will be put.
     * @param text The inside text of the button.
     * @param icon The icon which represents the button.
     * @param value The value for the ActionListener.
     * @param currentPlayer a JLabel which must be updated after a button press.
     * @param currentRound a JLabel which must be updated after a button press.
     * @param currentMoney a JLabel which must be updated after a button press.
     */
    private void initilaizeJButton(Box box, String text, String icon, String value,JLabel currentPlayer, JLabel currentRound, JLabel currentMoney){
        
        Icon ico = new ImageIcon(MainWindow.class.getClassLoader().getResource(icon));
        JButton button = new JButton(text,ico);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setPreferredSize(new Dimension(105, 120));
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) { 
                if (value == "stop"){
                    changePlayer(currentPlayer,currentRound,currentMoney);
                    if (game.getCurPlayer() == 1) {
                        game.stepAll();
                        map.refresh();
                        game.shootAll();
                        map.refresh();

                        //Placeholder win message dialog
                        int chosenOption=2;
                        Object[] options = {"Nem Game",
                         "Quit!"};
                        if (game.getPlayer1().getBase().isDestroyed()){
                            int n = JOptionPane.showOptionDialog(MainWindow.this,"Congratulations! " +game.getPlayer2().getName() + " won!","Game over",
                                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,dialogIcon,options,options[0]);
                             if (n == 0){
                                newGame();
                            }
                            if (n == 1){
                                System.exit(0);
                            }
                        }
                        else if (game.getPlayer2().getBase().isDestroyed()){
                            int n = JOptionPane.showOptionDialog(MainWindow.this,"Congratulations! " +game.getPlayer1().getName() + " won!","Game over",
                                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,dialogIcon,options,options[0]);
                            if (n == 0){
                                newGame();
                            }
                            if (n == 1){
                                System.exit(0);
                            }
                        }

                        switch(chosenOption){
                            case 0: 
                                newGame(); 
                                chosenOption = 2; 
                                break;
                            case 1: 
                                System.exit(0);
                                break;
                            default: 
                                break;
                        }
                    }
                }
                else{
                    switch (value) {
                            case "normal soldier":

                                 game.getPlayer().buySoldiers
                                 (new NormalSoldier(game.getPlayer(), game.getEnemyPlayer().getBase(),game.getPlayer().getBase()));
                                 break;

                           case "support soldier":
                               
                               game.getPlayer().buySoldiers
                               (new Support(game.getPlayer(), game.getEnemyPlayer().getBase(),game.getPlayer().getBase()));
                                break;
                            case "destroyer soldier":
                                
                                game.getPlayer().buySoldiers
                                (new Destroyer(game.getPlayer(), game.getEnemyPlayer().getRandomBuilding(),game.getPlayer().getBase()));
                                break;
                            case "fighter soldier":
                                
                                game.getPlayer().buySoldiers
                                (new Fighter(game.getPlayer(), game.getEnemyPlayer().getBase(),game.getPlayer().getBase()));
                                break;
                            case "mage soldier":
                                game.getPlayer().buySoldiers
                                (new Mage(game.getPlayer(), game.getEnemyPlayer().getBase(),game.getPlayer().getBase()));
                                break;
                            case "normal tower":
                                game.setSelected(value);
                                break;
                            case "tank tower":
                                game.setSelected(value);
                                break;
                            case "damage tower":
                                game.setSelected(value);
                                break;
                            case "mine":
                                game.setSelected(value);
                                break;
                            case "arrows spell":
                                game.setSelected(value);
                                break;
                            case "cannon spell":
                                game.setSelected(value);
                                break;
                            case "freeze spell":
                                game.setSelected(value);
                                break;
                            case "heal spell":
                                game.setSelected(value);
                                break;
                           default:
                               break;
                   }
                   writePlayer(currentPlayer,currentRound,currentMoney);
                   map.refresh();
               }
           }  
        });
        
        box.add(button);
        box.add(Box.createHorizontalGlue());
        
    }
    
    /**
     * Changes the current player after a stop button press.
     * @param currentPlayer the JLanel which gets updated after the button press
     */
    private void changePlayer(JLabel currentPlayer, JLabel currentRound, JLabel currentMoney){
        game.getPlayer().earnMoney();
        if (game.getCurPlayer() == 1){
            game.changeCurPlayer();
            writePlayer(currentPlayer,currentRound,currentMoney);
            game.setSelected("none");
        }
        else{
            game.changeCurPlayer();
            writePlayer(currentPlayer,currentRound,currentMoney);
            game.setSelected("none");
            roundCounter++;
        }
    }
    
    /**
     * Rewrites the JLabel with the data from the model
     * @param currentPlayer the JLabel which will be updated.
     */
    private void writePlayer(JLabel currentPlayer, JLabel currentRound, JLabel currentMoney) {
        if(game.getCurPlayer() == 1){ 
            currentRound.setText("Round: " + roundCounter);
            currentPlayer.setText( name1 +"'s turn!" );
            currentMoney.setText( game.getPlayer1().getMoney()+" $" );
            currentPlayer.setForeground(Color.RED);
        }
        else{
            currentRound.setText("Round: " + roundCounter);
            currentPlayer.setText( name2 +"'s turn!" );
            currentMoney.setText( game.getPlayer2().getMoney()+" $" );
            currentPlayer.setForeground(Color.BLUE);
        }
    }
    
    /**
     * Restarts the game.
     */
    private void newGame(){
        roundCounter = 1;
        game.newGame();
        map.refresh();
        game.resetCurrentPlayer();
    }
    
    /**
    * Main, we start the main window here.
    */
    public static void main(String[] args) {
        try {
            new MainWindow();
        } catch (IOException ex) {}
    }
}
