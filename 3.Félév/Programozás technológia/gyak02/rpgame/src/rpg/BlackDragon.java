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
public class BlackDragon extends Dragon {
    public BlackDragon(String name, int HP, int attack) {
        super(name, HP, attack);
        ATTACK_THRESHOLD = 60;
    }
}
