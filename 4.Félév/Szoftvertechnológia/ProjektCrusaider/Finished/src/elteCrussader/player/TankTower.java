package elteCrussader.player;

import elteCrussader.modell.Position;

public class TankTower extends TowerInterface {
    public TankTower(Position position,Player ownerPlayer){
        super(1000,position,ownerPlayer,40,800,1, 1);
    }
}
