package oracleTower.player;

public class Mage extends SoldierInterface{
    public Mage(Player owner,BuildingInterface target, BuildingInterface ownerbase){
        super(800,owner,200,160,5,target,ownerbase.getPosition());
    }
}
