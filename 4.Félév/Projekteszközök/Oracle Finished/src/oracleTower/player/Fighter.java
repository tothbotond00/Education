package oracleTower.player;

public class Fighter extends SoldierInterface{
    public Fighter(Player owner,BuildingInterface target, BuildingInterface ownerbase){
        super(500,owner,600,20,5,target,ownerbase.getPosition()); 
    }
}
