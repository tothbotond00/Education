package elteCrussader.tile;
import java.util.ArrayList;
import elteCrussader.modell.Position;
import elteCrussader.player.BuildingInterface;
import elteCrussader.player.SoldierInterface;

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
        type = switch (c) {
            case 'M' -> new Mountain();
            case 'S' -> new Swamp();
            default -> new Normal();
        };
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

    public boolean isSoldier() {
        return isSoldier;
    }
    
    public boolean putBuilding(BuildingInterface building){
        if (!isSoldier && !isBuilding && type.isNormal()){
            isBuilding = true;
            this.building = building;
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
        }else return false;
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
