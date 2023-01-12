package elteCrussader.player;

import elteCrussader.modell.Position;

public class DamageTower extends TowerInterface{
    public DamageTower(Position position,Player ownerPlayer){
        super(1500,position,ownerPlayer,100,500,1, 3);
    } 
}
