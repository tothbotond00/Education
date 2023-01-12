/**
 * This package contains the player, the soldier and the building classes
 */
package oracleTower.player;

import oracleTower.spell.Spell;
import oracleTower.tile.Tile;



import java.util.ArrayList;

public class Player {
    private Base base;
    private int money; // player's money
    private int towerCount; // player's number of towers
    private int soldiersCount; // player's number of soldiers 
    private int mineCount; // player's number of mines
    private String name; 
    private ArrayList<TowerInterface> towers; // player's towers
    private ArrayList<SoldierInterface> soldiers; //player's soldiers 
    private ArrayList<Mine> mines; // player's mines


    public Player(int money,String name){
        this.name = name;
        this.money=money;
        this.towerCount=0;
        this.soldiersCount=0;
        this.mineCount=0;
        towers = new ArrayList<TowerInterface>();
        soldiers = new ArrayList<SoldierInterface>();
        mines = new ArrayList<Mine>();
    }

    public String getName(){
        return name;
    }

    public Base getBase(){
        return base;
    }

    public int getMoney(){
        return money;
    }

    public int getTowerCount(){
        return towerCount;
    }

    public int getSoldiersCount(){
        return soldiersCount;
    }

    public int getMineCount(){
        return mineCount;
    }  

    public void setBase(Base base){
        this.base = base;
    } 

    public ArrayList<SoldierInterface> getSoldiers(){
        return soldiers;
    }
    
    public ArrayList<TowerInterface> getTowers(){
        return towers;
    }

     public ArrayList<Mine> getMines(){
        return mines;
    }

    public void setMoney(int money){
        this.money=money;
    }

    public void setTowerCount(int towerCount){
        this.towerCount=towerCount;
    }

    public void setSoldiersCount(int soldiersCount){
        this.soldiersCount=soldiersCount;
    }

    public void setMineCount(int mineCount){
        this.mineCount = mineCount;
    }

    public boolean buyTower(TowerInterface tower){
        if(tower.getPrice() <= money){
            money-=tower.getPrice();
            ++towerCount;
            towers.add(tower);
            return true;
        }
        return false;
    }

    public boolean buySoldiers(SoldierInterface soldier){
        if(soldier.getPrice()<=money){
            money-=soldier.getPrice();
            ++soldiersCount;
            soldiers.add(soldier);
            return true;
        }
        return false;
    }

    public boolean buyMine(Mine mine){
        if(mine.getPrice()<=money){
            money-=mine.getPrice();
            ++mineCount;
            mines.add(mine);
            return true;
        }
        return false;
    }


    public void earnMoney(){
        money+=soldiersCount*40;
        money+=towerCount*100;

        for(Mine mine : mines){
            money+=mine.getProduce();
        }

    }

    /**
     * Checks if the tile the spell was placed on is valid, 
     * and if the player has enough money to buy it.
     * If so, the specified spell is used
     * @param spell type of spell used
     * @param tile tile on which the spell is used
     * @param playerToApplyOn the player to apply the spell on (same in heal, enemy
     *                        in others)
     * @return true if the spell was used
     */
    public boolean buySpell(Spell spell, Tile tile, Player spellToUseOn){
        if(spell.canBeUsed(tile, this, spellToUseOn)){
            if(spell.getPrice()<=money){
                money-=spell.getPrice();
                spell.use(tile, this, spellToUseOn);
                return true;
            }
        }
        return false;
    }

    public void resetPlayer(int initMoney){
        this.money = initMoney;
        this.towerCount=0;
        this.soldiersCount=0;
        this.mineCount=0;
        towers.clear();
        soldiers.clear();
        mines.clear();
        base.setHp(5000);
    }

    public BuildingInterface getRandomBuilding(){
        if (towerCount == 0 && mineCount == 0) return base;
        else if ((int)Math.floor(Math.random()*(2-1+1)+1) == 1 && mineCount != 0){
            int index = (int)(Math.random() * mineCount);
            return mines.get(index);
        } else if (towerCount != 0) {
            int index = (int)(Math.random() * towerCount);
            return towers.get(index);
        } else {
            int index = (int)(Math.random() * mineCount);
            return mines.get(index);
        }
    } 



    @Override
    public String toString() {

        return "Player's money:" + getMoney() + ",Soldiers:" + soldiers + "and Towers:" +  towers;
    }


}
