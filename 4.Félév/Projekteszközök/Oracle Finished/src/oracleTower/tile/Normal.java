package oracleTower.tile;
/**
 *
 * @author MQH41V
 */
public class Normal extends Field {
    
    public Normal(){
        super(1);
    }
    
    @Override
    public String toString() {
        return "N";
    }
    
    @Override
    public boolean isNormal(){
        return true;
    }
    
}
