/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class Cow extends Animal{

    public Cow( String name, int weight, int feedNum) {
        super("C", name, weight, feedNum);
    }
    
    @Override
    public boolean isSkinny(){
        return weight < 100; 
    }
}
