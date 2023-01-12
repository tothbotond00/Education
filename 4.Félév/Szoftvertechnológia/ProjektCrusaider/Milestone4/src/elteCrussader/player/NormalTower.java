package elteCrussader.player;

import elteCrussader.modell.Position;

public class NormalTower extends TowerInterface{
    public NormalTower(Position position,Player ownerPlayer){
        super(800,position,ownerPlayer,20,400,1, 2);
    }
}
