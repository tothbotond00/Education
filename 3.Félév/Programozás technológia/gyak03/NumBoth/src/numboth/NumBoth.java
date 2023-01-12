/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numboth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author bli
 */
public class NumBoth {

    public static <T> int numBoth(Collection<T> c1, Collection<T> c2) {
        HashSet<T> s1 = new HashSet<>(c1);
        HashSet<T> s2 = new HashSet<>(c2);
        s1.retainAll(s2);
        return s1.size();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4));
        ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(2, 3, 7, 8));
        System.out.println("The collections " + c1 + " and " + c2 + " have " 
                + numBoth(c1, c2) + " elements in common not counting duplicates.");
        
    }
    
}
