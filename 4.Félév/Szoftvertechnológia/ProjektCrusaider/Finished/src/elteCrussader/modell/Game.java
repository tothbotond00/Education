package elteCrussader.modell;

import elteCrussader.player.*;
import java.util.ArrayList;

/**
 *
 * @author Vajda Marián JG3IH8
 * This class sets up the game, simulates the map, simulates the game and contains
 * all the functions/getters for the game to function as expected.
 * CLEAN CODE ✓
 */

public class Game {
    private GameMap gameMap = null;
    private Player player1, player2;
    private int curPlayer;
    private String selected;
    
    
    /**
    * Constructor of a game
    */ 
    public Game(String pOne, String pTwo) {
        loadGame();
        curPlayer = 1;
        selected = "none";
        player1 = new Player(5000, pOne);
        player2 = new Player(5000, pTwo);
        player1.setBase(new Base(new Position(3,2), player1));
        player2.setBase(new Base(new Position(12,46), player2));
        gameMap.getTile(player1.getBase().getPosition()).putBuilding(player1.getBase());
        gameMap.getTile(player2.getBase().getPosition()).putBuilding(player2.getBase());
    }
    
    /**
    * loads a gamemap 
    */ 
    public void loadGame(){
        gameMap = new GameMap();
    }

    public void newGame(){
        gameMap = new GameMap();
        player1.resetPlayer(5000);
        player2.resetPlayer(5000);
        gameMap.getTile(player1.getBase().getPosition()).putBuilding(player1.getBase());
        gameMap.getTile(player2.getBase().getPosition()).putBuilding(player2.getBase());
    }

    /**
    * Prints the current state of the map with representations
    */ 
    public void printGameMap(){ gameMap.printMap(); }
    
    public String getSelected(){ return selected; }
    public void setSelected( String target ) { selected = target; } 
    public Player getPlayer(){
        if (curPlayer == 1) return player1;
        else return player2;
    }
    public Player getEnemyPlayer(){
        if (curPlayer == 1) return player2;
        else return player1;
    }
    public void changeCurPlayer(){ 
        if (curPlayer == 1) curPlayer = 2;
        else curPlayer = 1;
    }

    public void resetCurrentPlayer( ){curPlayer = 1;}
    public int getCurPlayer(){ return curPlayer;}
    public GameMap getGameMap(){return gameMap;}
    public boolean isMapLoaded(){ return gameMap != null; }
    public int getRows() { return gameMap.getROWS();}
    public int getCols() { return gameMap.getCOLS();}
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }

    public int getBase1Health(){ // MAKE IT ~IMMUTABLE
        return player1.getBase().getHp();
    }
    
    public int getBase2Health(){ // MAKE IT ~IMMUTABLE
        return player2.getBase().getHp();
    }
    
    /**
     * Steps with every soldier for each player using the Dijsktra algorithm
     * @return True if the step methods were successfull
     */
    public boolean stepAll(){
        ArrayList <SoldierInterface> finished = new ArrayList<>();
        stepSoldiers(player1, finished, player2);stepSoldiers(player2, finished, player1);
        attackAllFinished(finished);
        killAll(player1);killAll(player2);
        destroyAll(player1);destroyAll(player2);
        return true;
    }
    
    /**
     * Shhots with every tower for each player
     * @return Tre if the shoot methods were succesfull
     */
    public boolean shootAll() {
        shootTowers(player1);
        shootTowers(player2);
        killAll(player1);killAll(player2);
        return true;
    }
    
    public boolean isGameEnded(){ return (getBase1Health() <= 0 || getBase2Health() <= 0); }
    
    /**
     * Checks if the source is in the radius of the target
     * @param source the source's position
     * @param target the target's position
     * @return true if the source is in the raidus of the target
     */
    public static boolean radius(Position source, Position target){
        int sourceX = source.getX();
        int sourceY = source.getY();
        int targetX = target.getX();
        int targetY = target.getY();


        for (int i = -1 ; i <= 1 ; i++){
            for (int j = -1; j <= 1; j ++){
                if (sourceX + i == targetX && sourceY + j == targetY){
                    return true;
                }    
            }
        } return false;
    } 
    
    /**
     * Kills all the soldiers of the Player that reached the enemy base. 
     * @param player the owner of the Soldiers.
     * @return true if there were soldiers to be removed
     */
    private boolean killAll(Player player){
        ArrayList<SoldierInterface> toRemove = new ArrayList<SoldierInterface>();
        for (SoldierInterface soldier : player.getSoldiers()){
            if (soldier.isDead()){
                player.setSoldiersCount(player.getSoldiersCount()-1);
                gameMap.getTile(soldier.getPosition()).removeSoldier(soldier);
                toRemove.add(soldier);
            }
        } return player.getSoldiers().removeAll(toRemove);
    }
    
    /**
     * Kills all the soldiers of the Player that reached the enemy base. 
     * @param player the owner of the Soldiers.
     * @return true if there were soldiers to be removed
     */
    private boolean destroyAll(Player player){
        ArrayList<BuildingInterface> toRemove = new ArrayList<BuildingInterface>();
        for (BuildingInterface tower : player.getTowers()){
            //////DESTORY TOWERSBOTI
            if (tower.isDestroyed()){
                player.setTowerCount(player.getTowerCount()-1);
                gameMap.getTile(tower.getPosition()).removeBuilding();
                toRemove.add(tower);
            }
        }
        for (BuildingInterface mine : player.getMines()){
            //////DESTORY TOWERSBOTI
            if (mine.isDestroyed()){
                player.setMineCount(player.getMineCount()-1);
                gameMap.getTile(mine.getPosition()).removeBuilding();
                toRemove.add(mine);
            }
        } 
        player.getTowers().removeAll(toRemove);
        player.getMines().removeAll(toRemove);
        return true;
    }
    
    /**
     * Steps for every Soldier of the player
     * @param player the owner player
     * @param finished an array that will store the finished soldiers
     * @return true if nothing went wrong
     */
    private boolean stepSoldiers(Player player, ArrayList<SoldierInterface> finished, Player enemy){
        for (SoldierInterface soldier : player.getSoldiers()){
            Node node;
            if (soldier.getTarget().isDestroyed()){
                soldier.setTarget(enemy.getRandomBuilding());
            }
            if (soldier instanceof SpeedyBoi){
                node = gameMap.getShortestDistance(soldier.getPosition(), soldier.getTarget().getPosition(), true);
            } else node = gameMap.getShortestDistance(soldier.getPosition(), soldier.getTarget().getPosition(),false);
            Position source = soldier.getPosition();
            gameMap.getTile(source).removeSoldier(soldier);
            for (Node n : node.getShortestPath() ){
                if(n.getDistance() < soldier.getMovementSpeed()){
                    source = n.getPosition();
                    soldier.move(source);
                } else break;
            }
            if (soldier instanceof Support){
                healAll(soldier.getPosition().getX(), soldier.getPosition().getY(), soldier);
            }
            else if (soldier instanceof Fighter){
                attackAll(soldier.getPosition().getX(), soldier.getPosition().getY(), soldier);
            }
            gameMap.getTile(source).putSoldier(soldier);
            if (radius(soldier.getPosition(),soldier.getTarget().getPosition())){
                finished.add(soldier);
            }
        } return true;
    }
    
    /**
     * Shoots for every tower in the players possesion
     * @param player the owner player of the towers
     * @return true if the shots were succesfull
     */
    private boolean shootTowers(Player player){
        for (TowerInterface tower : player.getTowers()){
            ArrayList<SoldierInterface> soldiers = 
                    gameMap.getAdjecentSoldiers(tower.getPosition().getX(), tower.getPosition().getY(), tower.getRadius());
            for (SoldierInterface soldier :soldiers){
                if (soldier.getOwner().getName() != tower.getOwnerPlayer().getName()){
                    soldier.damageHp(tower.getDamage());
                }
            }
        } return true;
    }
    
    /**
     * Attacks the enemy base for every finished unit
     * @param finished an array of finished soldiers
     * @return true if the attacks were succesfull
     */
    private boolean attackAllFinished (ArrayList<SoldierInterface> finished){
        for (SoldierInterface soldier : finished){
            int dmg = soldier.getDmg();
            soldier.getTarget().damageHp(dmg);
        } return true;
    }
    
    /**
     * Heals every unit in the supports raidus
     * @param x the x coordiante of the support
     * @param y the y coordiante of the support
     * @param support the support itself
     * @return true if the healings were succefull
     */
    private boolean healAll(int x ,int y, SoldierInterface support){
        ArrayList<SoldierInterface> forHeal = gameMap.getAdjecentSoldiers(x,y, 1);
        for (SoldierInterface soldier : forHeal){
            if (soldier.getOwner().getName() == support.getOwner().getName()){
                soldier.damageHp(-20);
            }
        } return true;
    }
    
    /**
     * 
     * Attacks every unit in the fighter raidus
     * @param x the x coordiante of the fighter
     * @param y the y coordiante of the fighter
     * @param fighter the fighter itself
     * @return true if the attacks were succefull
     */
    private boolean attackAll(int x ,int y, SoldierInterface fighter){
        ArrayList<SoldierInterface> forDamage = gameMap.getAdjecentSoldiers(x,y, 2);
        for (SoldierInterface soldier : forDamage){
            if (soldier.getOwner().getName() != fighter.getOwner().getName()){
                soldier.damageHp(fighter.getDmg());
            }
        } return true;
    }
    
    /**
     * Checks if you can build a building on a tile
     * @param selectedPos the position fo the tile
     * @return true if you can build on it
     */
    public boolean validPos(Position selectedPos) {
        return gameMap.getTile(selectedPos).getType().toString() == "N" 
            && !(gameMap.getTile(selectedPos).isBuilding())
            && !(gameMap.getTile(selectedPos).isSoldier())
            && selectedPos.getX() != player1.getBase().getPosition().getX() + 1
            && selectedPos.getY() != player1.getBase().getPosition().getY() + 1
            && selectedPos.getX() != player2.getBase().getPosition().getX() + 1
            && selectedPos.getY() != player2.getBase().getPosition().getY() + 1
            && gameMap.runDijsktraBaseCheck(player1.getBase().getPosition(),
                                            player2.getBase().getPosition(), selectedPos);
    }
 
}