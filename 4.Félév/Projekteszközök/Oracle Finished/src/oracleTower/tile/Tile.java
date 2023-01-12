/**
 * This package conatins the tile for the modell and the gamemap. 
 */
package oracleTower.tile;

import java.util.ArrayList;
import oracleTower.modell.Position;
import oracleTower.player.BuildingInterface;
import oracleTower.player.SoldierInterface;

/**
 *
 * @author MQH41V
 */
public class Tile {
    private final Field type;
    private boolean isBuilding;
    private boolean isSoldier;
    private BuildingInterface building;
    private ArrayList<SoldierInterface> soldiers;
    private Position pos;

    public Tile(char c,Position pos) {
        switch (c) {
            case 'M' : type = new Mountain(); break;
            case 'S' : type = new Swamp(); break;
            default  : type = new Normal();
        }
        isBuilding = false;
        isSoldier = false;
        building = null;
        soldiers = new ArrayList<>();
        this.pos = new Position(pos.getX(),pos.getY()); 
    }

    public Field getType() {
        return type;
    }

    public boolean isBuilding() {
        return isBuilding;
    }

    public BuildingInterface getBuilding() {
        return building;
    }

    public boolean isSoldier() {
        return isSoldier;
    }
    
    public boolean putBuilding(BuildingInterface target){
        if (!isSoldier && !isBuilding && type.isNormal()){
            isBuilding = true;
            this.building = target;
            return true;
        } else return false;
    }
    
    public boolean putSoldier(SoldierInterface soldier){
        if (!isBuilding ){
            isSoldier = true;
            soldiers.add(soldier);
            return true;
        }else return false;
    }
    
   public boolean removeBuilding(){
        if(isBuilding){
            isBuilding=false;
             building = null;
             return true;
        }else{
            return false;
        }
    }
    
    public boolean removeSoldier(SoldierInterface soldier){
        if (soldiers.contains(soldier)){
            soldiers.remove(soldier);
            isSoldier = !soldiers.isEmpty();
            return true;
        }else return false;
    }

    public ArrayList<SoldierInterface> getSoldiers() {
        return soldiers;
    }


    public Position getPos() {
        return pos;
    }
    
    public int getX(){
        return pos.getX();
    }
    
    public int getY(){
        return pos.getY();
    }
}
