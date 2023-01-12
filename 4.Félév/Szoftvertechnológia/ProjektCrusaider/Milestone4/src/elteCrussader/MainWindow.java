package elteCrussader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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

import elteCrussader.modell.*;
import elteCrussader.player.*;
import elteCrussader.view.*;

/**
 *
 * @author Vajda Marián JG3IH8
 * This class sets up the jframe where the board gets printed and runs the game.
 * Also contains all the menu options needed for the game.
 */
public class MainWindow extends JFrame{
    private Game game;
    private Map map;
    private String name1;
    private String name2;
    private Timer timer;
    private int roundCounter = 1;

    
    
    public MainWindow() throws IOException{
        
        /**
        * We set up the basic information of the frame here
        */
        setTitle("ELTE Crussader");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        URL url = MainWindow.class.getClassLoader().getResource("elteCrussader/res/logo.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
              
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Menü");
        
        JMenuItem menuNewGame;
        menuNewGame = new JMenuItem(new AbstractAction("Új játék"){
            @Override
            public void actionPerformed(ActionEvent e) {
               roundCounter = 1;
               // game = new Game();
               game.newGame();
               //try { add(map = new Map(game), BorderLayout.NORTH);} catch (IOException ex) {}
               map.refresh();
               game.resetCurrentPlayer();
               // resetMainWindow(currentPlayer,currentRound ,currentMoney);
            }
        });
        
        
        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Kilépés") {
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
        * We set up the key events here
        */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke); 
                if (!game.isMapLoaded()) return;
                int kk = ke.getKeyCode();
                switch (kk){
                     
                }
            }
        });
        setResizable(false);
        askName1("Első játékos neve:");
        askName2("Második játékos neve:");
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
        initilaizeJButton(box, "Normal unit", "elteCrussader/res/normal3.png", "normal soldier",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Support unit", "elteCrussader/res/normal3.png","support soldier",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Destoryer unit", "elteCrussader/res/normal3.png", "destroyer soldier",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Fighter unit", "elteCrussader/res/normal3.png", "fighter soldier",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Speedy boi", "elteCrussader/res/normal3.png", "speedy boi",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Normal tower", "elteCrussader/res/normalt3.png", "normal tower",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Tank tower", "elteCrussader/res/normalt3.png", "tank tower",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Damage tower", "elteCrussader/res/normalt3.png", "damage tower",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Mine", "elteCrussader/res/mine3.png", "mine",currentPlayer,currentRound,currentMoney);
        initilaizeJButton(box, "Kör befejezése", "elteCrussader/res/stop.png", "stop",currentPlayer,currentRound,currentMoney);
        add(box,BorderLayout.SOUTH);
        pack();
        setVisible(true);
        
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                writePlayer(currentPlayer,currentRound,currentMoney);
            }
        });
        timer.start();
        
    }


    /**
    * Asks for names and checks them, makes sure the names are not empty and
    * different kell?
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
     * Makes a Jbutton with the following paramteres.
     * @param box The box where the button will be put.
     * @param text The inside text of the button.
     * @param icon The icon which represents the button.
     * @param value The value for the actionlistener.
     * @param currentPlayer the JLabel which must be updated after a button press.
     */
    private void initilaizeJButton(Box box, String text, String icon, String value,JLabel currentPlayer, JLabel currentRound, JLabel currentMoney){
        
        Icon ico = new ImageIcon(MainWindow.class.getClassLoader().getResource(icon));
        JButton button = new JButton(text,ico);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
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
                       if (game.getPlayer1().getBase().isDestroyed()){
                           JOptionPane.showMessageDialog(MainWindow.this,game.getPlayer2().getName() + " wins!");
                       }else if (game.getPlayer2().getBase().isDestroyed()){
                           JOptionPane.showMessageDialog(MainWindow.this,game.getPlayer1().getName() + " wins!");
                       }
                   }
               }else{
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
                            case "speedy boi":
                                
                                game.getPlayer().buySoldiers
                                (new SpeedyBoi(game.getPlayer(), game.getEnemyPlayer().getBase(),game.getPlayer().getBase()));
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
        //box.add(Box.createRigidArea(new Dimension(30,20)));
        
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
        }else {
            game.changeCurPlayer();
            writePlayer(currentPlayer,currentRound,currentMoney);
            game.setSelected("none");
            roundCounter++;
        }
    }


        /*private void resetMainWindow(JLabel currentPlayer, JLabel currentRound, JLabel currentMoney){
            if(game.getCurPlayer() == 2)
            changePlayer(currentPlayer,currentRound,currentMoney);
        };*/
    
    /**
     * Rewrites the JLabel with the data from the modell
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
    * Main, we start the main window here.
    */
    public static void main(String[] args) {
        try {
            new MainWindow();
        } catch (IOException ex) {}
    }
}