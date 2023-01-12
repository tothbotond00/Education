package elteCrussader.player;

public class NormalSoldier extends SoldierInterface{
    public NormalSoldier(Player owner,BuildingInterface target, BuildingInterface ownerbase){
        super(350,owner,200,20,5,target,ownerbase.getPosition());
    }
}
