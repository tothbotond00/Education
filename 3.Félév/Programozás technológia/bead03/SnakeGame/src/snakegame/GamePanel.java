package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    
    //panel 
    SnakeModell modell;
    static final int S_WIDTH = 600;
    static final int S_HEIGHT = 600;
    static final int UNIT = 25;
    static final int DELAY = 100;
    boolean running = false;
    Timer timer;
    
    ///images
    BufferedImage snakeHeadR;
    BufferedImage snakeHeadL;
    BufferedImage snakeHeadU;
    BufferedImage snakeHeadD;
    BufferedImage snakeBody;
    BufferedImage apple;
    BufferedImage rock;
    BufferedImage tail;
    
    /**
     * Makes a modell with the width height and unit adds aa keyadapter and starts
     * the drawing cicyle.
     */
    public GamePanel(){
        
        //importing the images
        try{
            //Loading the images in!
            snakeHeadR = ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\Tantárgyak\\Programozás technológia"
                    + "\\bead03\\SnakeGame\\src\\snakegame\\res\\snakefacer.png"));
            snakeHeadL = ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\Tantárgyak\\Programozás technológia"
                    + "\\bead03\\SnakeGame\\src\\snakegame\\res\\snakefacel.png"));
            snakeHeadU = ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\Tantárgyak\\Programozás technológia"
                    + "\\bead03\\SnakeGame\\src\\snakegame\\res\\snakefaceu.png"));
            snakeHeadD = ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\Tantárgyak\\Programozás technológia"
                    + "\\bead03\\SnakeGame\\src\\snakegame\\res\\snakefaced.png"));
            snakeBody = ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\Tantárgyak\\Programozás technológia"
                    + "\\bead03\\SnakeGame\\src\\snakegame\\res\\snakebody.png"));
            apple = ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\Tantárgyak\\Programozás technológia"
                    + "\\bead03\\SnakeGame\\src\\snakegame\\res\\apple.png"));
            rock = ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\Tantárgyak\\Programozás technológia"
                    + "\\bead03\\SnakeGame\\src\\snakegame\\res\\wall.png"));
            tail = ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\Tantárgyak\\Programozás technológia"
                    + "\\bead03\\SnakeGame\\src\\snakegame\\res\\tail.png"));
        }catch(Exception e){
            System.err.print("UNKNOWNERROR");
        }finally{
            modell = new SnakeModell(S_WIDTH,S_HEIGHT,UNIT);
            this.setPreferredSize(new Dimension(S_WIDTH,S_HEIGHT));
            this.setBackground(Color.black);
            this.setFocusable(true);
            this.addKeyListener(new CustomKeyAdapter());
            start();
        }
    }
    
    /**
     * starts the Timer and the drawing cicle.
     */
    public void start(){
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics grap){
        super.paintComponent(grap);
        draw(grap);
    }
    
    /**
     * Draws the apple, the rock, the snake and the score in the top left corner.
     * @param grap the Graphics element 
     */
    public void draw(Graphics grap){
            
        
        if(running){
            grap.drawImage(apple,modell.getAppleX(),modell.getAppleY(), UNIT, UNIT,null);
            
            for (int i = 0; i < modell.getRockNum(); i++) {
                grap.drawImage(rock,modell.getRockX(i), modell.getRockY(i), UNIT, UNIT,null);
            }
        
            for (int i = 0; i < modell.getParts(); i++) {
                if( i == 0){
                    switch (modell.getDirection()) {
                        case 'r' -> grap.drawImage(snakeHeadR,modell.getX(i), modell.getY(i),UNIT, UNIT,null);
                        case 'l' -> grap.drawImage(snakeHeadL,modell.getX(i), modell.getY(i),UNIT,UNIT,null);
                        case 'u' -> grap.drawImage(snakeHeadU,modell.getX(i), modell.getY(i),UNIT,UNIT,null);
                        default -> grap.drawImage(snakeHeadD,modell.getX(i), modell.getY(i),UNIT,UNIT,null);
                    }
                }else if (i == modell.getParts()-1 ){
                    grap.drawImage(tail,modell.getX(i), modell.getY(i),UNIT,UNIT,null);
                }else {
                    grap.drawImage(snakeBody,modell.getX(i), modell.getY(i),UNIT,UNIT,null);
                }
            }
        }
        try{
            String fName = "Minecraft.ttf";
            InputStream is = getClass().getResourceAsStream(fName);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
        }
        
        grap.setColor(Color.red);
        grap.setFont(new Font("Minecraft",Font.PLAIN ,30) );
        FontMetrics met = getFontMetrics(grap.getFont());
        grap.drawString("Score : "+ modell.getScore(),
                (S_WIDTH -met.stringWidth("Score : "+ modell.getScore()))/30,
                grap.getFont().getSize());
        if (!running) {
            gameOver(grap);
        }
    }
    
    /**
     * Draws out the Game Over Sceern!
     * @param grap the Graphics element 
     */
    public void gameOver(Graphics grap){
        try{
            String fName = "Minecraft.ttf";
            InputStream is = getClass().getResourceAsStream(fName);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
        }
        grap.setColor(Color.red);
        grap.setFont( new Font("Minecraft", Font.BOLD, 75));
        FontMetrics met = getFontMetrics(grap.getFont());
        grap.drawString("Game Over",(S_WIDTH -met.stringWidth("Game Over"))/2,S_HEIGHT/2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            modell.move();
            modell.checkA();
            running = !modell.checkC();
            if(!running){
                PopUpGUI pop = new PopUpGUI(modell.getScore());
            }
        }
        repaint();
    }
    
    /**
     * A custom class extended from KeyAdapter
     * Decides if the pressed key is an arrow key and if it is,
     * changes the direction to the pressed key's direction.
     * NOTE: you can't tilt 180 degrees 
     * eg. : you can't go left if you are going right!!!
    */
    public class CustomKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT -> {
                    if(modell.getDirection() != 'r'){
                        modell.setDirection('l');
                    }
                }
                case KeyEvent.VK_RIGHT -> {
                    if(modell.getDirection() != 'l'){
                        modell.setDirection('r');
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if(modell.getDirection() != 'u'){
                        modell.setDirection('d');
                    }
                }
                case KeyEvent.VK_UP -> {
                    if(modell.getDirection() != 'd'){
                        modell.setDirection('u');
                    }
                }
            }
        }
    }
    
}
