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
public abstract class Character {
    protected String name;
    protected int HP;
    protected int ATK;

    public Character(String name, int HP, int ATK) {
        this.name = name;
        this.HP = HP;
        this.ATK = ATK;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    @Override
    public String toString() {
        return  name + " has " + HP + " HP, and " + ATK + " ATK";
    }
    
    public void attack(Character ch){
        ch.attacked(this);
    }
    
    public void attacked(Character ch){
        applyDamageFrom(ch);
    }
    
    public boolean isAlive() {
        return HP > 0;
    }
    
    protected void applyDamageFrom(Character ch){
        this.HP -= ch.ATK;
    }
    
}
