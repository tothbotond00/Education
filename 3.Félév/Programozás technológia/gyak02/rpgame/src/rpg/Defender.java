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
public class Defender extends Orc {
    public Defender(String name, int HP, int attack) {
        super(name, HP, attack);
    }
    
    @Override
    protected void applyDamageFrom(Character ch){
        this.HP -= (int)ch.ATK/2 ;
    }
}
