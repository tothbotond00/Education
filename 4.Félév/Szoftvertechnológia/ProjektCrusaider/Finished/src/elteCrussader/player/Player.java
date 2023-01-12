package elteCrussader.player;

import elteCrussader.modell.Game;
import java.util.ArrayList;

public class Player {
    private int money; // player's money
    private String name;
    private Base base;
    private int towerCount; // player's number of towers
    private int soldiersCount; // player's number of soldiers
    private int mineCount; // player's number of mines
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


    public ArrayList<SoldierInterface> getSoldiers(){
        return soldiers;
    }
    
    public ArrayList<TowerInterface> getTowers(){
        return towers;
    }
    
    public ArrayList<Mine> getMines(){
        return mines;
    }


    public void setName(String name){
        this.name=name;
    }

    public void setBase(Base base){
        this.base= base;
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

    public void resetPlayer(int money){
        this.money = money;
        this.towerCount=0;
        this.soldiersCount=0;
        this.mineCount=0;
        this.base.setHp(5000);
        towers.clear();
        soldiers.clear();
        mines.clear();
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
