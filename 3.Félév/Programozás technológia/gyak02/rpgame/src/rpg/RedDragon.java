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
public class RedDragon extends Dragon {
    public RedDragon(String name, int HP, int attack) {
        super(name, HP, attack);
        ATTACK_THRESHOLD = 20;
    }
}
