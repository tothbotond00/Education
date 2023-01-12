package oracleTower.tile;
/**
 *
 * @author MQH41V
 */
public class Swamp extends Field {
    
    public Swamp(){
        super(3);
    }

    @Override
    public String toString() {
        return "S";
    }
    
    @Override
    public boolean isSwamp(){
        return true;
    }
    
}
