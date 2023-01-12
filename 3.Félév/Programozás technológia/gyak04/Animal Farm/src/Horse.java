/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class Horse extends Animal{
    
    public Horse( String name, int weight, int feedNum) {
        super("H", name, weight, feedNum);
    }
    
    @Override
    public boolean isSkinny(){
        return weight < 60; 
    }
}
