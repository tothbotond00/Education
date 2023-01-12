package oracleTower.player;

import oracleTower.modell.Position;

public class NormalTower extends TowerInterface{
    public NormalTower(Position position,Player ownerPlayer){
        super(800,position,ownerPlayer,50,500,1,2);
    }
}
