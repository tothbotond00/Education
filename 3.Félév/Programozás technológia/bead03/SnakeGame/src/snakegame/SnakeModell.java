package snakegame;

import java.util.Random;

public class SnakeModell {
    private final int width;
    private final int height;
    private final int unit;
    private int parts;
    private final int snakeX[];
    private final int snakeY[];
    private final int rockNum;
    private final int rockX[];
    private final int rockY[];
    private int score ;
    private int appleX;
    private int appleY;
    private char direction;
    private final Random random;
    
    /**
     * Constructor for the modell behind the game.
     * @param width the with of the jPanel (basically the stage)
     * @param height the height of the jPanel (basically the stage)
     * @param unit the size of the cells in the matrix we draw during animation
     */
    public SnakeModell(int width, int height, int unit){
        this.width = width;
        this.height = height;
        this.unit = unit;
        parts = 2;
        snakeX = new int [unit*unit];
        snakeY = new int [unit*unit];
        snakeX[0] = width/2;
        snakeY[0] = height/2;
        snakeX[1] = width/2 - unit;
        snakeY[1] = height/2;
        rockNum = 15;
        rockX = new int [rockNum];
        rockY = new int [rockNum];
        score = 0;
        random = new Random();
        newApple();
        genRocks();
        direction = 'r';
    }
    
    /**
     * Safely generates a new apple.
     * If the generated place has a snake part or a rock on it generates a new one.
     */
    private void newApple(){
        int rX = 0;
        int rY = 0;
        boolean good = true;
        while(good){
            rX = random.nextInt((int)(width/unit))*unit;
            rY = random.nextInt((int)(width/unit))*unit;
            boolean check = false;
            for (int i = parts; i > 0; i--) {
                if(snakeX[i] == rX && snakeY[i] == rY){
                    check = true;
                }
            }
            for (int i = 0; i < rockNum; i++) {
                if(rockX[i] == rX && rockY[i] == rY){
                    check = true;
                }
            }
            good = check;
        }
        appleX = rX;
        appleY = rY;
    }
    
    /**
     * Safely generates the rocks for the game.
     * If the generated place has a snake part it generates a new one.
     */
    private void genRocks() {
        int rX = 0;
        int rY = 0;
        for (int i = 0; i < rockNum; i++){
            boolean good = true;
            while(good){
                rX = random.nextInt((int)(width/unit))*unit;
                rY = random.nextInt((int)(width/unit))*unit;
                boolean check = false;
                for (int j = parts; j > 0; j--) {
                    if(snakeX[j] == rX && snakeY[j] == rY){
                        check = true;
                    }
                }
            good = check;
            }
            rockX[i] = rX;
            rockY[i] = rY;
        }
    }
    
    /**
     * Moves the snake into a direction starting from the tail.
     * The head goes to the set direction
     */
    public void move(){
        for (int i = parts; i > 0; i--) {
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        
        switch(direction){
            case 'u' :
                snakeY[0] = snakeY[0] - unit;
                break;
            case 'd' :
                snakeY[0] = snakeY[0] + unit;
                break;
            case 'l' :
                snakeX[0] = snakeX[0] - unit;
                break;
            default :
                snakeX[0] = snakeX[0] + unit;
        }
    }
    
    /**
     * Check collisions with everything except apples.
     * @return returns true if the snake collided with a border/rock/himself
     */
    public boolean checkC(){
        boolean run = false;
        //head collides with body
        for(int i = parts-1; i > 0; i-- ){
            if((snakeX[0] == snakeX[i] ) && (snakeY[0] == snakeY[i])){
                run = true;
            }
        }
        //head collides with border
        if(snakeX[0] < 0){
            run = true;
        }
        else if(snakeX[0] > width-unit){
            run = true;
        }
        else if(snakeY[0] < 0){
            run = true;
        }
        else if(snakeY[0] > height-unit){
            run = true;
        }
        //head collides with rock
        for(int i = 0; i < rockNum; i++){
            if((snakeX[0] == rockX[i] ) && (snakeY[0] == rockY[i])){
                run = true;
            }
        }
        return run;
    }
    
    /**
     * Check collision between the head and the apple.
     * @return returns true if the snake ate the apple
     */
    public boolean checkA(){
        if((appleX == snakeX[0]) && (appleY == snakeY[0])){
            parts++;
            score++;
            newApple();
            return true;
        }
        return false;
    }
    
    ///Standard getter and setter methods
    
    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
    
    public int getScore() {
        return score;
    }
    
    /**
     * Adds +1 to the score.
     */
    public void addScore() {
        this.score++;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public int getParts() {
        return parts;
    }
    
    /**
     * gives the "i"th element of the x axis.
     * @param i the element's number
     * @return x[i]
     */
    public int getX (int i){
        return snakeX[i];
    }
    
    /**
     * gives the "i"th element of the y axis.
     * @param i the element's number
     * @return y[i]
     */
    public int getY (int i){
        return snakeY[i];
    }

    public int getRockNum() {
        return rockNum;
    }
    
    /**
     * gives the "i"th element of the x axis of the rock array.
     * @param i the rock element's number
     * @return RockX[i]
     */
    public int getRockX (int i){
        return rockX[i];
    }
    
    /**
     * gives the "i"th element of the y axis of the rock array.
     * @param i the rock element's number
     * @return RockY[i]
     */
    public int getRockY (int i){
        return rockY[i];
    }
    
}
