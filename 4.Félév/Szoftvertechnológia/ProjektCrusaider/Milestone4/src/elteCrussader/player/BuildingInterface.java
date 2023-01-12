package elteCrussader.player;

import elteCrussader.modell.Position; 

public abstract class BuildingInterface {
    private int hp;  //Building's hp
    private Position position;  //Building's position
    private Player ownerPlayer; //Player who possess the building

    public BuildingInterface(int hp, Position position,Player ownerPlayer ){
        this.hp=hp;
        this.position=position; 
        this.ownerPlayer=ownerPlayer; 
    }
    
    public void damageHp(int dmg){
        hp = hp - dmg;
    }


    public int getHp(){
        return hp;
    }

    public Position getPosition(){
        return position;
    }

    public Player getOwnerPlayer(){
        return ownerPlayer;
    }


    public void setHp(int hp){
        this.hp=hp;
    }

    public void setPosition(Position position){
        this.position=position;
    }

    public void setOwnerPlayer(Player ownerPlayer){
        this.ownerPlayer=ownerPlayer;
    }
    
    public boolean isDestroyed(){
        return hp<=0;
    }

}
