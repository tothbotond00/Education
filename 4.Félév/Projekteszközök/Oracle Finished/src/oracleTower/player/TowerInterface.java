package oracleTower.player;

import oracleTower.modell.Position;

public abstract class TowerInterface extends BuildingInterface{
    private int price; //Building's price
    private int damage; // Bulding's damage
    private int level;  // Building's level
    private int radius;

    public TowerInterface(int price, Position position,Player ownerPlayer,int damage, int hp, int level,int radius){
        super(hp,position,ownerPlayer);
        this.price=price;
        this.damage=damage;
        this.level=level;
        this.radius = radius;
    }

    public int getPrice(){
        return price;
    }

    
    public int getDamage(){
        return damage;
    }

    
    public int getLevel(){
        return level;
    }


    public void setPrice(int price){
        this.price=price;
    }

    
    public void setDamage(int damage){
        this.damage=damage;
    }

    
    public void setLevel(int level){
        this.level=level;
    }

    public int getRadius(){
        return radius;
    }


}
