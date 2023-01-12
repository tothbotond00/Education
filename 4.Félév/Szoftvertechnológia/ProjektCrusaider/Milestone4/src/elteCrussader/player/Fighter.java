package elteCrussader.player;

public class Fighter extends SoldierInterface{
    public Fighter(Player owner,BuildingInterface target, BuildingInterface ownerbase){
        super(500,owner,200,20,5,target,ownerbase.getPosition()); 
    }
}
