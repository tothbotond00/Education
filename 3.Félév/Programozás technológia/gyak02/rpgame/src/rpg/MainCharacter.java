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
public class MainCharacter extends Character {
    
    protected double defense;
    
    
    public MainCharacter(String name, int HP, int attack, double defense) {
        super(name, HP, attack);
        this.defense = defense;
    }
    
    @Override
    protected void applyDamageFrom(Character ch){
        HP -= (int)ch.ATK / this.defense;
    }
}
