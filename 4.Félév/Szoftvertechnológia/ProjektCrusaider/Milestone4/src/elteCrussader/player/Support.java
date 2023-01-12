package elteCrussader.player;

public class Support extends SoldierInterface {
    public Support(Player owner,BuildingInterface target, BuildingInterface ownerbase){
        super(800,owner,150,15,5,target,ownerbase.getPosition()); 
    }
}
