package elteCrussader.tile;
/**
 *
 * @author MQH41V
 */
public class Mountain extends Field {
    
    public Mountain(){
        super(2);
    }

    @Override
    public String toString() {
        return "M";
    }
    
    @Override
    public boolean isMountain(){
        return true;
    }
    
}
