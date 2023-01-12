package elteCrussader.player;

public class Destroyer extends SoldierInterface{
    public Destroyer(Player owner,BuildingInterface target, BuildingInterface ownerbase){
        super(500,owner,600,50,5,target,ownerbase.getPosition()); 
    }
}
