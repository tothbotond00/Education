package elteCrussader.tile;
/**
 *
 * @author MQH41V
 * This is abstract mainclass for every tiletype
 */
public abstract class Field {
    private final int value;

    public Field(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public boolean isNormal(){
        return false;
    }
    
    public boolean isSwamp(){
        return false;
    }
    
    public boolean isMountain(){
        return false;
    }
    
}
