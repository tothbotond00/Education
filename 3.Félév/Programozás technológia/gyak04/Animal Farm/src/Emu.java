/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class Emu extends Animal {
    public Emu( String name, int weight, int feedNum) {
        super("E", name, weight, feedNum);
    }
    
    @Override
    public boolean isSkinny(){
        return weight < 20; 
    }
}
