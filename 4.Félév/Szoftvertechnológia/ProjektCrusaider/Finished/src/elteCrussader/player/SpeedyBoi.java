package elteCrussader.player;

public class SpeedyBoi extends SoldierInterface{
    public SpeedyBoi(Player owner,BuildingInterface target, BuildingInterface ownerbase){
        super(800,owner,350,160,10,target,ownerbase.getPosition()); 
    }
}
