/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

/**
 *
 * @author bli
 */
public abstract class Animal {
    
    public void move() {
        System.out.println("Animal moves");
    }
    
    public abstract void makeSound();
    
}
