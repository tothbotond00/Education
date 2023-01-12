/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

/**
 *
 * @author LENOVO
 */
public abstract class Dragon extends Character{
    protected int ATTACK_THRESHOLD;
    
    public Dragon(String name, int HP, int attack) {
        super(name, HP, attack);
    }
    
    @Override
    protected void applyDamageFrom(Character ch){
        if (ch.ATK <= this.ATTACK_THRESHOLD){
            
        }
        else{
            this.HP -= ch.ATK;
        }
    }
}
